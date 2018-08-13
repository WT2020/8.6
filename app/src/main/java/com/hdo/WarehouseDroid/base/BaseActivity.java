package com.hdo.WarehouseDroid.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * description 基类Activity
 * author 张建银
 * version 1.0
 * created 2017/9/6
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setLayout();
        initView();
        initData();
        setView();
    }

    //设置布局
    public abstract void setLayout();

    //初始化数据
    public abstract void initData();

    //初始化视图
    public abstract void initView();

    //设置视图
    public abstract void setView();

}
