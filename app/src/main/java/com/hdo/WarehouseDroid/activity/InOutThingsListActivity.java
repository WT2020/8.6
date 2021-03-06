package com.hdo.WarehouseDroid.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.hdo.WarehouseDroid.R;
import com.hdo.WarehouseDroid.adapter.HouseThingListAdapter;
import com.hdo.WarehouseDroid.base.BaseActivity;
import com.hdo.WarehouseDroid.bean.WarehouseInfo;
import com.hdo.WarehouseDroid.bean.WarehouseThing;

import java.util.ArrayList;

/**
 * description 入/出库物品列表Activity1
 * author 张建银
 * version 1.0
 * created 2017/11/23
 * modified on 2017/12/15 修改本地模拟数据
 *  modified on 2017/12/18  获取物品列表数据
 *  modified on 2017/12/21 完成出入库物品列表的实现
 *  modified on 2017/12/26 完成出入库物品修改和删除的实现
 *  modified by 张建银 on 2018/2/2 中转数据
 */

public class InOutThingsListActivity extends BaseActivity {
    //toolbar组件
    Toolbar toolbar;
    TextView toolbar_title;
    TextView toolbar_btn;

    //入库列表信息
    ListView thingList;
    ArrayList<WarehouseThing> things;
    HouseThingListAdapter adapter;
    WarehouseInfo.WarehouseZone zone;

    Context context;

    //是否能添加物品
    Boolean canAdd;

    Boolean isIn;

    String customerId;

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_in_out_move_things);
    }

    @Override
    public void initData() {
        context = InOutThingsListActivity.this;
        if(things==null){
            things = new ArrayList<>();
        }
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        //看是从入库申请进来的还是出库申请进来的
        isIn = intent.getBooleanExtra("flag",true);
        customerId = intent.getStringExtra("customerId");
        if (bundle!=null){
            things = (ArrayList<WarehouseThing>) bundle.getSerializable("thingList");
            zone = (WarehouseInfo.WarehouseZone) bundle.getSerializable("zone");
        }
        canAdd = intent.getBooleanExtra("canAdd",true);
        adapter = new HouseThingListAdapter(context,things,isIn,canAdd,zone);
    }

    @Override
    public void initView() {
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar_title = (TextView)findViewById(R.id.toolbar_title);
        toolbar_btn = (TextView)findViewById(R.id.toolbar_button);
        thingList = findViewById(R.id.lv_things);
    }

    @Override
    public void setView() {
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if(supportActionBar!=null){
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setDisplayShowTitleEnabled(false);
        }
        toolbar_title.setText("物品列表");
        if (canAdd){
            toolbar_btn.setText("添加物品");
            //添加物品
            toolbar_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (isIn){
                        Intent intent = new Intent(context, InThingAddLookUpActivity.class);
                        intent.putExtra("flag",isIn);
                        intent.putExtra("customerId",customerId);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("zone",zone);
                        intent.putExtras(bundle);
                        startActivityForResult(intent,1);
                    }else {
                        Intent intent = new Intent(context, OutThingAddLookUpActivity.class);
                        intent.putExtra("flag",isIn);
                        intent.putExtra("customerId",customerId);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("zone",zone);
                        intent.putExtras(bundle);
                        startActivityForResult(intent,1);
                    }

                }
            });
        } else {
            toolbar_btn.setVisibility(View.GONE);
        }
        thingList.setAdapter(adapter);
    }

    //返回数据
    private void returnThings(){
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putSerializable("thing",things);
        intent.putExtras(bundle);
        setResult(RESULT_OK,intent);
        finish();
    }

    //设置返回按钮
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            returnThings();
        }
        return super.onOptionsItemSelected(item);
    }

    //返回键
    @Override
    public void onBackPressed() {
        returnThings();
        super.onBackPressed();
    }

    //获取返回的数据（包括添加的和修改的）
    private void getReturnedData(Intent data,int reqCode){
        if (reqCode==1||reqCode==2){
            Bundle bundle = data.getExtras();
            if (bundle!=null){
                WarehouseThing returnData = (WarehouseThing) bundle.getSerializable("warehouseThing");
                int pos = bundle.getInt("pos");
                if (reqCode!=1&&pos!=-1){
                    //修改
                    things.set(pos,returnData);
                }else{
                    //添加
                    things.add(returnData);
                }
                adapter.notifyDataSetChanged();
            }
        }
    }

    //重写onActivityResult方法，获取数据
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == RESULT_OK){
            getReturnedData(data,requestCode);
        }
    }
}
