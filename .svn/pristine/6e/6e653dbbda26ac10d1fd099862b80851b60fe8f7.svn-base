package com.hdo.WarehouseDroid.utils;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.NotificationCompat;
import android.text.TextUtils;
import android.widget.Toast;

import com.hdo.WarehouseDroid.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by 张建银 on 2017/7/20.
 * @modified on 2017/7/25 修改和整合更新方法，完成取消下载功能和自动下载更新功能实现
 *           on 2017/7/26 增加本地apk文件版本校验，修复无限提示更新的Bug
 */

public class UpdateManager {
    //apk文件存储文件夹
    private String downLoadPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Download/";
    //更新方式
    // 0:先提示更新，同意后再下载更新，之后安装更新。为默认选项
    // 1:下载更新并提示，同意后安装更新。当有网络连接为WIFI时自动下载更新
    private int type = 0;
    //apk下载URL
    private String url = "";
    //最新apk版本
    private int versionCode = 0;
    //更新内容
    private String updateMessage = "";
    //文件名
    private String fileName = null;
    //通知管理器
    private NotificationManager mNotifyManager;
    //通知构造器
    private NotificationCompat.Builder mBuilder;
    //基础对话框
    private AlertDialog dialog;
    //下载进度对话框
    private ProgressDialog progressDialog;
    //是否取消下载标志
    private boolean IsCancel;
    //apk文件是否完整
    private boolean isTrueApkFile = false;
    //进度值
    private int mProgress = 0;
    //文件大小
    private long fileSize = 0;
    //apk文件
    private File apkFile;
    //应用上下文
    private Context context;
    //Android 6.0以上读取内存卡动态权限变量声明 Greated by 梁明 2017.07.12
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    DialogInterface.OnClickListener onClickListener;

    public static UpdateManager getInstance(Context context) {
        return new UpdateManager(context);
    }

    private UpdateManager(Context context) {
        setContext(context);
    }

    //检查更新
    public void checkUpdate(String url, int versionCode, String updateMessage, boolean isForced) {
        setUrl(url);
        setVersionCode(versionCode);
        setUpdateMessage(updateMessage);
        //是WIFI连接并且isFored标志为真时，更新类型为1（自动下载更新）
        if (isForced&&isWifi()) {
            setType(1);
        }
        File dir = new File(downLoadPath);
        if (!dir.exists()) {
            dir.mkdir();
        }
        String fileName = url.substring(url.lastIndexOf("/") + 1, url.length());
        setFileName(fileName);
        apkFile = new File(downLoadPath + fileName);
        //获取从应用本地保存的Apk文件大小，以校验apk文件是否完整
        fileSize = SpUtils.getLong(context,"apkFileSize",0);
        //当文件存在并且文件大小等于之前应用本地保存的大小时，apk完整
        if(apkFile.exists()&&apkFile.length()==fileSize){
            isTrueApkFile = true;
        }
        if(type == 1){//如果可以自动下载更新
            if(apkFile.exists()){//如果文件存在
                if(!isTrueApkFile){//如果apk文件不完整
                    deleteFile(apkFile);//删除apk
                    createNotification();//创建通知
                    downloadFile();//重新下载
                }else {//若apk完整
                    if(isApkFileLatest(versionCode)){//若Apk最新
                        showDialog();//直接弹出对话框
                    }else {//否则
                        deleteFile(apkFile);//删除apk
                        createNotification();//创建通知
                        downloadFile();//重新下载
                    }
                }
            }else{//若文件不存在，创建通知并下载
                createNotification();
                downloadFile();
            }
        }else{//若不可以自动下载，则弹出对话框让用户选择是否下载
            showDialog();
        }
    }

    /**
     * 弹出版本更新提示框
     */
    private void showDialog() {
        String title = "";
        String left = "";
        if (isTrueApkFile&&isApkFileLatest(versionCode)) {
            title = "安装新版本";
            left = "立即安装";
        } else {
            title = "发现新版本";
            left = "立即更新";
        }
        onClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i){
                    case Dialog.BUTTON_POSITIVE:
                        dialog.dismiss();
                        if (isTrueApkFile&&isApkFileLatest(versionCode)) {//若apk完整，则直接弹出安装进行提示
                            installApk(apkFile);
                        } else {//若apk不完整，则创建进度条并下载
                            if (url != null && !TextUtils.isEmpty(url)) {
                                deleteFile(apkFile);
                                createProgress();
                                downloadFile();
                            } else {
                                Toast.makeText(context, "下载地址错误", Toast.LENGTH_SHORT).show();
                            }
                        }
                        break;
                    case Dialog.BUTTON_NEGATIVE:
                        dialog.dismiss();
                        break;
                    default:
                        break;
                }
            }
        };
        dialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(updateMessage)
                .setCancelable(true)
                .setPositiveButton(left,onClickListener)
                .setNegativeButton("下次再说",onClickListener)
                .create();
        dialog.show();
    }

    private Handler UpdateProgressHandler = new Handler(new Handler.Callback(){
        @Override
        public boolean handleMessage(Message msg) {
            if(type==1){//type为1（可以自动下载）
                switch (msg.what){
                    case 1://下载成功
                        isTrueApkFile = true;
                        //更新通知栏
                        finishNotification();
                        //弹出安装提示
                        showDialog();
                        break;
                    case 0://下载中
                        //更新通知栏的下载进度
                        updateNotification();
                        break;
                    case -1://下载中断
                        mNotifyManager.cancel(10086);
                        break;
                    default:
                        break;
                }
            }else{
                switch (msg.what){
                    case 1://下载成功
                        isTrueApkFile = true;
                        //取消进度条显示并安装
                        progressDialog.dismiss();
                        installApk(apkFile);
                        break;
                    case 0://下载中
                        //更新进度对话框进度条
                        progressDialog.setProgress(mProgress);
                        break;
                    case -1://下载取消
                        //取消进度条显示并删除为下载完的损坏的apk文件
                        progressDialog.dismiss();
                        deleteFile(apkFile);
                        break;
                    default:
                        break;
                }
            }
            return true;
        }
    });

    /**
     * 下载apk
     */
    private void downloadFile() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
                    conn.connect();
                    InputStream is = conn.getInputStream();
                    int length = conn.getContentLength();
                    fileSize = length;
                    //保存apk文件大小到本地，以便于下次校验使用
                    SpUtils.putLong(context,"apkFileSize",fileSize);
                    apkFile = new File(downLoadPath,fileName);
                    FileOutputStream fos = new FileOutputStream(apkFile);
                    int count = 0;
                    byte[] buffer = new byte[1024];
                    while (!IsCancel){//不取消，就一直下载知道下载完成
                        int numRead = is.read(buffer);
                        count += numRead;
                        // 计算进度条的当前位置
                        mProgress = (int)(((double) count)/length * 100);
                        // 更新进度条
                        UpdateProgressHandler.sendEmptyMessage(0);
                        // 下载完成
                        if (numRead < 0){
                            UpdateProgressHandler.sendEmptyMessage(1);
                            break;
                        }
                        fos.write(buffer, 0, numRead);
                    }
                    if(IsCancel){//取消下载
                        UpdateProgressHandler.sendEmptyMessage(-1);
                    }
                    fos.close();
                    is.close();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //取消下载
    public void cancelDownload(){
        IsCancel = true;
        isTrueApkFile = false;
    }

    //删除文件
    private boolean deleteFile(File file){
        if(file.isFile()&&file.exists()){
            return file.delete();
        }
        return false;
    }

    /**
     * 强制更新时显示在屏幕的进度条
     */
    private void createProgress() {
        progressDialog = new ProgressDialog(context);
        progressDialog.setMax(100);
        progressDialog.setCancelable(true);
        progressDialog.setMessage("正在下载...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setButton(DialogInterface.BUTTON_POSITIVE, "取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //调用取消下载
                cancelDownload();
                progressDialog.dismiss();
            }
        });
        progressDialog.show();
    }

    /**
     * 创建通知栏进度条
     */
    private void createNotification() {
        mNotifyManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        mBuilder = new NotificationCompat.Builder(context);
        mBuilder.setSmallIcon(R.mipmap.ic_launcher);
        mBuilder.setContentTitle("版本更新");
        mBuilder.setContentText("正在下载...");
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.N){
            mBuilder.setProgress(100, 0, false);
        }
        Intent intent = new Intent();
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pendingIntent);
        Notification notification = mBuilder.build();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        mNotifyManager.notify(10086, notification);
    }

    /**
     * 更新通知栏进度条
     */
    private void updateNotification() {
        //修复API23(Android 6.0)及以下更新进度系统卡死的问题
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.N){
            mBuilder.setProgress(100, mProgress, false);
            Notification notification = mBuilder.build();
            notification.flags = Notification.FLAG_AUTO_CANCEL;
            mNotifyManager.notify(10086, notification);
        }
    }

    /**
     * 完成通知栏进度条
     */
    private void finishNotification() {
        mBuilder.setContentTitle("下载完成");
        mBuilder.setContentText("新版本更新下载完成");
        Notification notification = mBuilder.build();
        notification.flags = Notification.FLAG_AUTO_CANCEL;
        mNotifyManager.notify(10086, notification);
    }


    /**
     * 安装apk
     *
     * @param file    APK文件
     */
    private void installApk(File file) {
        if(isTrueApkFile){//完整apk才能安装
            Intent intent =new Intent(Intent.ACTION_VIEW);
            Uri contentUri;
            if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.N){
                //API 24以上需要校验apk包
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                String authority = context.getPackageName()+".provider";
                contentUri= FileProvider.getUriForFile(context,authority,file);
            }else {
                contentUri = Uri.fromFile(file);
            }
            intent.setDataAndType(contentUri, "application/vnd.android.package-archive");
            context.startActivity(intent);
        }
    }

    /**
     * @return 当前本地apk是否最新
     */
    public boolean isApkFileLatest(int latestCode){
        boolean isLatest = false;
        PackageManager pm = context.getPackageManager();
        PackageInfo packInfo = pm.getPackageArchiveInfo(apkFile.getAbsolutePath(), PackageManager.GET_ACTIVITIES);
        if(latestCode==packInfo.versionCode){
            isLatest = true;
        }
        return isLatest;
    }

    /**
     * @return 当前应用的版本号
     */
    public int getVersionCode() {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }


    /**
     * 读取内存卡动态权限
     */
    public void verifyStoragePermissions(Activity activity) {
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE);
        }
    }

    /**
     * 判断当前网络是否wifi
     */
    private boolean isWifi() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
        return (activeNetInfo != null && activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setVersionCode(int versionCode) {
        this.versionCode = versionCode;
    }

    public void setType(int type) {
        this.type = type;
    }

    private void setUpdateMessage(String updateMessage) {
        this.updateMessage = updateMessage;
    }

    private void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setContext(Context context) {
        this.context = context;
    }
}
