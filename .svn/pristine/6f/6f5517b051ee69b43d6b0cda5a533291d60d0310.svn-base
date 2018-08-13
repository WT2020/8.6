package com.hdo.WarehouseDroid.activity;


import android.app.ProgressDialog;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hdo.WarehouseDroid.R;
import com.hdo.WarehouseDroid.base.BaseActivity;
import com.hdo.WarehouseDroid.utils.NetworkUtils;
import com.hdo.WarehouseDroid.utils.UpdateManager;

import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * description 关于我们页
 * author 吴小雪
 * version 1.0
 * created 2017/9/18
 * modified by 张建银 on 2017/10/25 修改图标大小,添加版本号显示与检查更新
 */
public class AboutActivity extends BaseActivity {

    //toolbar组件
    Toolbar toolbar;
    TextView toolbar_title;
    TextView toolbar_btn;
    //版本信息
    TextView versionName;
    Button llVersionName;

    //更新管理器
    UpdateManager updateManager;
    ProgressDialog dialog;

    //当前版本名
    String version;
    //最新版本信息
    int latestVersionCode;
    String updateMsg;
    String updateUrl;
    //Log标签
    String TAG = this.getClass().getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    }

    @Override
    public void setLayout()
    {
        setContentView(R.layout.activity_about);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar_title = (TextView)findViewById(R.id.toolbar_title);
        toolbar_btn = (TextView)findViewById(R.id.toolbar_button);
        versionName = (TextView)findViewById(R.id.versionName);
        llVersionName = (Button) findViewById(R.id.btnUpdates);
    }

    @Override
    public void setView() {
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if(supportActionBar!=null){
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setDisplayShowTitleEnabled(false);
        }
        toolbar_title.setText("关于我们");
        toolbar_btn.setVisibility(View.GONE);
        versionName.setText(getAppVersionName());
        llVersionName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLatestVersionCode();
            }
        });
    }

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            dialog.dismiss();
            switch (message.what) {
                case -1:
                    Toast.makeText(AboutActivity.this, "网络请求失败，请检查网络！", Toast.LENGTH_SHORT).show();
                    break;
                case 0:
                    //检查权限
                    updateManager.verifyStoragePermissions(AboutActivity.this);
                    //检查并可以下载更新
                    updateManager.checkUpdate(updateUrl,latestVersionCode,updateMsg,false);
                    break;
                case 1:
                    Toast.makeText(AboutActivity.this, "已经是最新版啦！", Toast.LENGTH_SHORT).show();
                    break;
            }
            return false;
        }
    });

    private void getLatestVersionCode(){
        dialog = ProgressDialog.show(this, "连接中", "查找新版本中，请等待...", false, true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Call call = NetworkUtils.getInstance().getLatestVersionCode();
                    Response response = call.execute();
                    ResponseBody body = response.body();
                    if(body==null){
                        //网络请求失败
                        handler.sendEmptyMessage(-1);
                    }else{
                        String result = body.string();
                        Log.e(TAG,result);
                        JSONObject object = new JSONObject(result);
                        latestVersionCode = Integer.parseInt(object.getString("versionCode"));
                        int appCode = getAppVersionCode();
                        if(latestVersionCode>appCode){
                            updateMsg = object.getString("description");
                            updateUrl = object.getString("url");
                            //可以更新新版
                            handler.sendEmptyMessage(0);
                        }else{
                            //已经最新了
                            handler.sendEmptyMessage(1);
                        }
                    }
                }catch (Exception e){
                    handler.sendEmptyMessage(-1);
                }
            }
        }).start();
    }

    //设置返回按钮
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    //获取版本名，如1.2.1
    private String getAppVersionName(){
        try {
            PackageManager manager = this.getPackageManager();
            PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
            return info.versionName;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    //获取版本号,如15
    private int getAppVersionCode(){
        try {
            PackageManager manager = this.getPackageManager();
            PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
            return info.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}
