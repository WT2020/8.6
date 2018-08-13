package com.hdo.WarehouseDroid.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * description 基类Fragment
 * author 张建银
 * version 1.0
 * created 2017/9/6
 */

public abstract class BaseFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //先设置布局
        View view = setLayout(container);
        //再初始视图组件
        initView(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initData();
        setView();
    }
    /**
     * 设置视图布局
     * @param container
     * @return 布局View
     */
    public abstract View setLayout(ViewGroup container);
    /**
     * 初始化控件
     * @param view
     */
    public abstract void initView(View view);

    /**
     * 初始化数据
     */
    public abstract void initData();
    /**
     * 设置控件
     */
    public abstract void setView();
}
