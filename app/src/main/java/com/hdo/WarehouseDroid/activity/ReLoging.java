package com.hdo.WarehouseDroid.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import com.hdo.WarehouseDroid.utils.SpUtils;

/**
 * @author Admin
 * @version $Rev$
 * @des ${TODO}  登录失效后直接跳转至登录页面
 * @updateAuthor $Author$
 * @updateDes ${TODO}
 */
public class ReLoging {
    public static void JumpToLoginPager(final Context context){
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setTitle("提示")
                .setMessage("登录失效，即将重新登录")
                .setCancelable(false)
                .show();
        SpUtils.putBoolean(context,"fromChangePsw",true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //跳转登录界面
                context.startActivity(new Intent(context, LoginActivity.class));
                SpUtils.putBoolean(context,"fromChangePsw",true);
                Activity activity = (Activity) context;
                activity.finish();
            }
        },2000);
    }
}
