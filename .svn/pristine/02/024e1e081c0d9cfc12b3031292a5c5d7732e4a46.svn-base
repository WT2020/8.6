package com.hdo.WarehouseDroid.activity;


import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.hdo.WarehouseDroid.R;
import com.hdo.WarehouseDroid.base.BaseActivity;
import com.hdo.WarehouseDroid.bean.User;
import com.hdo.WarehouseDroid.utils.SpUtils;

/**
 * author 吴小雪
 * version 1.0
 * description 我的资料页面
 * created 2017-10-24
 * modified on 2017/12/29 实现个人资料的显示
 */

public class PersonalInfoActivity extends BaseActivity {
    Toolbar toolbar;
    TextView title;
    TextView toolbarButton;

    TextView userName;
    TextView userNumber;
    TextView userDepartment;
//    TextView userAddress;
    TextView userPhone;

//    Button cancel;
//    Button verify;

    User user;

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_personal_info);
    }

    @Override
    public void initData() {
        user = SpUtils.getUser(PersonalInfoActivity.this);
    }

    @Override
    public void initView() {
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        title = (TextView)findViewById(R.id.toolbar_title);
        toolbarButton = (TextView)findViewById(R.id.toolbar_button);

        userName = (TextView)findViewById(R.id.tv_username);
        userName.setEnabled(false);
        userNumber = (TextView)findViewById(R.id.tv_user_number);
        userNumber.setEnabled(false);
        userDepartment = (TextView)findViewById(R.id.tv_my_department);
        userDepartment.setEnabled(false);
//        userAddress = (TextView)findViewById(R.id.tv_user_address);
//        userAddress.setEnabled(false);
        userPhone = (TextView)findViewById(R.id.tv_user_phone);
        userPhone.setEnabled(false);

//        cancel = (Button)findViewById(R.id.btn_cancel);
//        verify = (Button)findViewById(R.id.verify);
    }

    @Override
    public void setView() {
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if(supportActionBar!=null){
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setDisplayShowTitleEnabled(false);
        }
        title.setText("个人资料");
        toolbarButton.setVisibility(View.GONE);
        userName.setText(user.getName());
        userNumber.setText(user.getNumber());
        userDepartment.setText(user.getIdentity());
        userPhone.setText(user.getPhone());
    }
    //设置返回按钮
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
