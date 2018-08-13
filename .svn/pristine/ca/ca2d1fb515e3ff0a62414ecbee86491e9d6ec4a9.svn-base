package com.hdo.WarehouseDroid.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hdo.WarehouseDroid.R;
import com.hdo.WarehouseDroid.adapter.AddOutThingAdapter;
import com.hdo.WarehouseDroid.adapter.AddThingAdapter;
import com.hdo.WarehouseDroid.base.BaseActivity;
import com.hdo.WarehouseDroid.bean.InThingResp;
import com.hdo.WarehouseDroid.bean.OutThing;
import com.hdo.WarehouseDroid.bean.OutThingResp;
import com.hdo.WarehouseDroid.bean.Thing;
import com.hdo.WarehouseDroid.utils.NetworkUtils;
import com.hdo.WarehouseDroid.utils.SpUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

 /**
  * description 入/出库物品可添加列表Activity
  * author 吴小雪
  * version 1.0
  * created 2017/11/25
  * modified by 张建银 添加搜索和分页加载实现
  * modified by 张建银 on 2018/2/2 出库物品上传customerId
  * modified by 张建银 on 2018/2/5 添加无物品提示
  */

public class ChooseThingActivity extends BaseActivity {

    //toolbar组件
    Toolbar toolbar;
    TextView toolbar_title;
    TextView toolbar_btn;

    ListView list;
    AddThingAdapter thingAdapter;
    AddOutThingAdapter outThingAdapter;
    Context context;

    private Boolean isIn;
    //入库
    private List<Thing> things = new ArrayList<>();
    //出库
    private List<OutThing> outThings = new ArrayList<>();

    //是否滑动到底部
    private boolean isBottom;
    //是否加载更多
    private boolean isLoadingMore;
    //页数
    private int page = 0;

    //搜索相关
    private SearchView searchView;
    private String searchString;

    private String customerId;

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_choose_thing);
    }

    @Override
    public void initData() {
        context = ChooseThingActivity.this;
        Intent intent = getIntent();
        searchString = "";
        isIn = intent.getBooleanExtra("flag",true);
        customerId = intent.getStringExtra("customerId");
        things = new ArrayList<>();
        outThings = new ArrayList<>();
        getDataFromServer();
    }

    @Override
    public void initView() {
      toolbar = (Toolbar)findViewById(R.id.toolbar);
      toolbar_btn = (TextView) findViewById(R.id.toolbar_button);
      toolbar_title = (TextView)findViewById(R.id.toolbar_title);
      list = (ListView)findViewById(R.id.add_thing_list);
      searchView = findViewById(R.id.search_view);
    }

    @Override
    public void setView() {
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if(supportActionBar!=null){
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setDisplayShowTitleEnabled(false);
        }
        toolbar_btn.setVisibility(View.GONE);
        toolbar_title.setText("选择添加物品列表");
        if(isIn) {
            thingAdapter = new AddThingAdapter(context,things,isIn);
            list.setAdapter(thingAdapter);
        }else {
            outThingAdapter = new AddOutThingAdapter(context,outThings,isIn);
            list.setAdapter(outThingAdapter);
        }
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(isIn){
                    Thing thing = new Thing();
                    thing = things.get(i);
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("thing", thing);
                    bundle.putBoolean("flag",isIn);
                    intent.putExtras(bundle);
                    setResult(RESULT_OK,intent);
                    finish();
                }else {
                    OutThing thing = new OutThing();
                    thing = outThings.get(i);
                    Intent intent = new Intent();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("thing", thing);
                    bundle.putBoolean("flag",isIn);
                    intent.putExtras(bundle);
                    setResult(RESULT_OK,intent);
                    finish();
                }


            }
        });
        //上拉加载监听
        list.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {
                if (i == SCROLL_STATE_IDLE&&isBottom&&!isLoadingMore){
                    isLoadingMore = true;
                    getDataFromServer();
                }
            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                if (i+i1 == i2){
                    isBottom = true;
                } else {
                    isBottom = false;
                }
            }
        });
        //搜索监听
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                search(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (newText.equals("")){
                    search(newText);
                }
                return false;
            }
        });
    }

    //获取入库物品信息 获取到的数据存入list中
    public void getInGoods(){
        context = ChooseThingActivity.this;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Call call = NetworkUtils.getInstance().inGoodsList(SpUtils.getUser(context).getToken(),++page,searchString);
                    try {
                        Response response = call.execute();
                        String result = response.body().string();
                        Log.e("redultgoods",result);
                        InThingResp inThingResp = new Gson().fromJson(result,InThingResp.class);
                        if(inThingResp.getCode().equals("200")){
                            things.addAll(inThingResp.getData().getRows());
                            handleInOrOut();
                        }
                    } catch (IOException e) {
//                        e.printStackTrace();
                        Log.e("",e.getMessage()+e.getCause());
                    }

                }
            }).start();

    }
    //获取出库物品信息 获取到的数据存入list中
    public void getOutGoods(){

            context = ChooseThingActivity.this;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Log.e("token", SpUtils.getUser(context).getToken());
                    Call call = NetworkUtils.getInstance().outGoodsList(SpUtils.getUser(context).getToken(),++page,searchString,customerId);
                    try {
                        Response response = call.execute();
                        String result = response.body().string();
                        OutThingResp outThingResp = new Gson().fromJson(result,OutThingResp.class);
                        if(outThingResp.getCode().equals("200")){
                            outThings.addAll(outThingResp.getData().getRows());
                            handleInOrOut();
                        }
                    } catch (IOException e) {
//                        e.printStackTrace();
                        Log.e("",e.getMessage()+e.getCause());
                    }
                    handleInOrOut();
                }
            }).start();
        }

    private void handleInOrOut(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (isIn){
                    thingAdapter.setObjects(things);
                }else {
                    if(outThings.size()==0){
                        Toast.makeText(context,"该客户无物品可出库",Toast.LENGTH_LONG).show();
                    }
                    outThingAdapter.setObjects(outThings);
                }
            }
        });
    }

    private void getDataFromServer(){
        if(isIn){
            getInGoods();
        }else {
            getOutGoods();
        }
    }

    //执行搜索
    private void search(String search){
        things.clear();
        outThings.clear();
        page = 0;
        searchString = search;
        getDataFromServer();
    }

    //设置返回按钮
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

}
