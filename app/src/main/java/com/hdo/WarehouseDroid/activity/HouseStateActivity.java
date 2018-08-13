package com.hdo.WarehouseDroid.activity;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hdo.WarehouseDroid.R;
import com.hdo.WarehouseDroid.adapter.HouseStateListAdapter;
import com.hdo.WarehouseDroid.base.BaseActivity;
import com.hdo.WarehouseDroid.bean.InThingResp;
import com.hdo.WarehouseDroid.bean.Thing;
import com.hdo.WarehouseDroid.utils.NetworkUtils;
import com.hdo.WarehouseDroid.utils.SpUtils;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * description 库存查询界面
 * author 张建银
 * version 1.0
 * created 2017/11/23.
 * modified on 2017/12/15 修改本地模拟数据
 * modified on 2017/12/22 实现库存物品数据获取
 * modified on 2017/12/25 实现多页数据上拉加载，实现搜索
 */

public class HouseStateActivity extends BaseActivity {

    //toolbar组件
    Toolbar toolbar;
    TextView toolbar_title;
    TextView toolbar_btn;

    ListView thingList;
    ArrayList<Thing> things;
    HouseStateListAdapter listAdapter;

    Context context;

    //是否滑动到底部
    private boolean isBottom;
    //是否加载更多
    private boolean isLoadingMore;
    //页数
    private int page = 0;

    //搜索相关
    private SearchView searchView;
    private String searchString;

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_state);
    }

    @Override
    public void initData() {
        things = new ArrayList<>();
        context = HouseStateActivity.this;
        searchString = "";
        getDataFromServer();
        listAdapter = new HouseStateListAdapter(context, things);
        thingList.setAdapter(listAdapter);
    }

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what){
                case 200:
                    listAdapter.setObjects(things);
                    listAdapter = new HouseStateListAdapter(context, things);
                    thingList.setAdapter(listAdapter);
                    break;
                case 101:
                    ReLoging.JumpToLoginPager(context);//登录失效后跳转至登录页面
                    break;
                case 500:
                    Toast.makeText(context,"未知错误，请重试",Toast.LENGTH_LONG).show();
                    break;
                default:
                    Toast.makeText(context,"网络请求失败",Toast.LENGTH_LONG).show();
                    break;
            }
            return false;
        }
    });

    private void getDataFromServer(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String token = SpUtils.getUser(context).getToken();
                    Call call = NetworkUtils.getInstance().stateMain(context,token,++page,searchString);
                    if (call!=null){
                        Response response = call.execute();
                        ResponseBody body = response.body();
                        if (body == null){
                            handler.sendEmptyMessage(-1);
                        } else {
                            String result = body.string();
                            InThingResp resp = new Gson().fromJson(result,InThingResp.class);
                            if(resp.getCode().equals("200")){
                                things.addAll(resp.getData().getRows());
                                handler.sendEmptyMessage(200);
                            } else if (resp.getCode().equals("101")){
                                handler.sendEmptyMessage(101);
                            } else {
                                handler.sendEmptyMessage(500);
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void initView() {
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar_title = (TextView)findViewById(R.id.toolbar_title);
        toolbar_btn = (TextView)findViewById(R.id.toolbar_button);
        thingList = findViewById(R.id.lv_state);
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
        searchView.setFocusable(false);
        toolbar_title.setText("库存查询");
        toolbar_btn.setVisibility(View.GONE);
        searchView.setVisibility(View.VISIBLE);
        listAdapter = new HouseStateListAdapter(context, things);
        thingList.setAdapter(listAdapter);
        //上拉加载监听
        thingList.setOnScrollListener(new AbsListView.OnScrollListener() {
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

    //执行搜索
    private void search(String search){
        things.clear();
        page = 0;
        searchString = search;
        getDataFromServer();
    }

    @Override
    public void onResume(){
        super.onResume();
        things.clear();
        page = 0;
        listAdapter = new HouseStateListAdapter(context, things);
        thingList.setAdapter(listAdapter);
        getDataFromServer();
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
