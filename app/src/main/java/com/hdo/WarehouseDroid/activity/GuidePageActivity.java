package com.hdo.WarehouseDroid.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.hdo.WarehouseDroid.R;
import com.hdo.WarehouseDroid.bean.User;
import com.hdo.WarehouseDroid.utils.SpUtils;


/**
 * author 梁明
 * version 1.0
 * description 引导页
 * create 2017.09.06
 * modified by 张建银 on 2017/9/11 界面改为全屏
 */
public class GuidePageActivity extends Activity {
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_page);
        boolean autoLogin = SpUtils.getBoolean(this, "autoLogin", true);
        User user = SpUtils.getUser(GuidePageActivity.this);
        final Intent intent;
        if (autoLogin&&user!=null&&!user.getToken().equals("")){
            intent = new Intent(GuidePageActivity.this, MainActivity.class);
        }else{
            intent = new Intent(GuidePageActivity.this, LoginActivity.class);
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
            }
        }, 1000);

    }



    @Override
    protected void onStop(){
        super.onStop();
        finish();
    }

}
