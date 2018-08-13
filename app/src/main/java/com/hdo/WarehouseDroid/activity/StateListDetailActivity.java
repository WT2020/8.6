package com.hdo.WarehouseDroid.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.hdo.WarehouseDroid.R;
import com.hdo.WarehouseDroid.base.BaseActivity;
import com.hdo.WarehouseDroid.bean.StateDetailThing;

/**
 * description 库存批次物品详细信息界面
 * author 张建银
 * version 1.0
 * created 2018/1/24.
 */

public class StateListDetailActivity extends BaseActivity {

    //toolbar组件
    Toolbar toolbar;
    TextView toolbar_title;
    TextView toolbar_btn;

    Context context;

    StateDetailThing thing;

    EditText etName;
    EditText etBatch;
    EditText etPosition;
    EditText etNum;
    EditText etIsPallet;
    EditText etState;

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_state_list_detail);
    }

    @Override
    public void initData() {
        context = StateListDetailActivity.this;
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            thing = (StateDetailThing) bundle.getSerializable("thing");
        }
    }

    @Override
    public void initView() {
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar_title = (TextView)findViewById(R.id.toolbar_title);
        toolbar_btn = (TextView)findViewById(R.id.toolbar_button);
        etName = findViewById(R.id.tv_state_list_detail_thing_name);
        etBatch = findViewById(R.id.tv_state_list_detail_thing_batch);
        etPosition = findViewById(R.id.tv_state_list_detail_thing_warehouse);
        etNum = findViewById(R.id.tv_state_list_detail_thing_num);
        etIsPallet = findViewById(R.id.tv_state_list_detail_thing_is_pallet);
        etState = findViewById(R.id.tv_state_list_detail_thing_state);
    }

    @Override
    public void setView() {
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if(supportActionBar!=null){
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setDisplayShowTitleEnabled(false);
        }
        toolbar_title.setText("批次物品详情");
        toolbar_btn.setVisibility(View.GONE);
        if(thing!=null){
            etName.setText(thing.getGoods().getName());
            etBatch.setText(thing.getBatch());
            etPosition.setText(thing.getWarehouse().getName()+"-"+thing.getWarehouse().getShelves()+"-"+thing.getWarehouse().getZone());
            etNum.setText(String.valueOf(thing.getBatchNum()));
            etIsPallet.setText(context.getResources().getStringArray(R.array.is_pallet)[Integer.parseInt(thing.getIsPallet())]);
            etState.setText(context.getResources().getStringArray(R.array.thing_detail_state)[Integer.parseInt(thing.getState())-1]);
        }
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
