package com.hdo.WarehouseDroid.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.hdo.WarehouseDroid.R;
import com.hdo.WarehouseDroid.adapter.HouseStateDetailListAdapter;
import com.hdo.WarehouseDroid.base.BaseActivity;
import com.hdo.WarehouseDroid.bean.StateDetailThing;

import java.util.ArrayList;

/**
 * description 库存批次物品列表界面
 * author 张建银
 * version 1.0
 * created 2017/12/22.
 */

public class StateDetailListActivity extends BaseActivity {

    //toolbar组件
    Toolbar toolbar;
    TextView toolbar_title;
    TextView toolbar_btn;

    ListView thingList;
    ArrayList<StateDetailThing> things;
    HouseStateDetailListAdapter adapter;

    Context context;

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_state);
    }

    @Override
    public void initData() {
        context = StateDetailListActivity.this;
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            things = (ArrayList<StateDetailThing>) bundle.getSerializable("things");
        }else {
            things = new ArrayList<>();
        }
    }

    @Override
    public void initView() {
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar_title = (TextView)findViewById(R.id.toolbar_title);
        toolbar_btn = (TextView)findViewById(R.id.toolbar_button);
        thingList = findViewById(R.id.lv_state);
    }

    @Override
    public void setView() {
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if(supportActionBar!=null){
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setDisplayShowTitleEnabled(false);
        }
        toolbar_title.setText("详细物品");
        toolbar_btn.setVisibility(View.GONE);
        adapter = new HouseStateDetailListAdapter(context, things);
        thingList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
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
