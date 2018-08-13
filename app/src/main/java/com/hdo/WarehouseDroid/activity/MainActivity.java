package com.hdo.WarehouseDroid.activity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.activity.CaptureActivity;
import com.hdo.WarehouseDroid.R;
import com.hdo.WarehouseDroid.base.BaseActivity;
import com.hdo.WarehouseDroid.fragment.HomeFragment;
import com.hdo.WarehouseDroid.fragment.MineFragment;


/**
 * description 主界面Activity
 * author 张建银
 * version 1.0
 * created 2017/9/6
 *  modified by 吴小雪 on 2017/9/11 添加扫码
 */

public class MainActivity extends BaseActivity {

    //底部Tab导航栏
    BottomNavigationView navigation;

    //toolbar组件
    Toolbar toolbar;
    TextView toolBarTitle;
    ImageButton toolBarButton;

    //片段布局变换器
    FragmentTransaction ft;

    //权限动态获取
    private int ACCESS_NETWORK_STATE_REQUEST_CODE = 1;
    private int ACCESS_CAMERA_REQUEST_CODE = 2;

    @Override
    public void setLayout(){
        setContentView(R.layout.activity_main);
    }

    @Override
    public void initData(){

    }

    @Override
    public void initView(){
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolBarTitle = (TextView) findViewById(R.id.toolbar_title);
        toolBarButton = (ImageButton) findViewById(R.id.toolbar_button);
        toolBarButton.setVisibility(View.VISIBLE);
    }

    @Override
    public void setView(){
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if(supportActionBar!=null){
            supportActionBar.setDisplayShowTitleEnabled(false);
        }
        //设置默认界面
        setDefault();
        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                switch (item.getItemId()) {
                    case R.id.navigation_home:
                        //主页视图
                        ft.replace(R.id.content,new HomeFragment());

                        toolBarButton.setVisibility(View.VISIBLE);
                        toolBarTitle.setText(R.string.title_home);
                        break;
                    case R.id.navigation_mine:
                        //我的视图
                        ft.replace(R.id.content,new MineFragment());
                        toolBarButton.setVisibility(View.GONE);
                        toolBarTitle.setText(R.string.title_mine);
                        break;
                }
                ft.commit();
                return true;
            }
        });
        if(Build.VERSION.SDK_INT>=23){
            checkPermission();
        }
        toolBarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
                startActivityForResult(intent, 0xA2);
            }
        });
    }

    //API23及以上动态权限获取
    private void checkPermission(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            //申请ACCESS_COARSE_LOCATION权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_NETWORK_STATE},
                    ACCESS_NETWORK_STATE_REQUEST_CODE);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            //申请照相机权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                    ACCESS_CAMERA_REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode,Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        //扫描结果回调
        if (requestCode == 65698 && resultCode == 161) {
            Bundle bundle = data.getExtras();
            if (bundle!=null){
                Toast.makeText(MainActivity.this,bundle.getString("qr_scan_result"),Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(MainActivity.this,"扫描失败请重试",Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onResume(){
        super.onResume();
        //由于onActivityResult()方法先于onResume()执行，因此得等到onResume()执行后再执行片段布局视图的更新
        if(ft!=null){
            ft.commit();
        }
        ft=null;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //可在此继续其他操作
    }

    //从图片选择/拍照中的回调结果中获取图片Uri
    public static Uri getUri(Context context , Intent intent) {
        Uri uri = intent.getData();
        String type = intent.getType();
        if (uri.getScheme().equals("file") && (type.contains("image/"))) {
            String path = uri.getEncodedPath();
            if (path != null) {
                path = Uri.decode(path);
                ContentResolver cr = context.getContentResolver();
                StringBuffer buff = new StringBuffer();
                buff.append("(").append(MediaStore.Images.ImageColumns.DATA).append("=")
                        .append("'" + path + "'").append(")");
                Cursor cur = cr.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        new String[] { MediaStore.Images.ImageColumns._ID },
                        buff.toString(), null, null);
                int index = 0;
                for (cur.moveToFirst(); !cur.isAfterLast(); cur.moveToNext()) {
                    index = cur.getColumnIndex(MediaStore.Images.ImageColumns._ID);
                    index = cur.getInt(index);
                }
                if (index != 0) {
                    Uri uri_temp = Uri.parse("content://media/external/images/media/" + index);
                    if (uri_temp != null) {
                        uri = uri_temp;
                    }
                }
            }
        }
        return uri;
    }

    /**
     * 设置默认选中fragment
     */
    private void setDefault() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content,new HomeFragment());
        ft.commit();
    }

}
