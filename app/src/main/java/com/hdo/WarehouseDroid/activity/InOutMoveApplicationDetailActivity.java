package com.hdo.WarehouseDroid.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.hdo.WarehouseDroid.R;
import com.hdo.WarehouseDroid.base.BaseActivity;
import com.hdo.WarehouseDroid.bean.InOutAppThingResp;
import com.hdo.WarehouseDroid.bean.InOutList;
import com.hdo.WarehouseDroid.bean.WarehouseThing;
import com.hdo.WarehouseDroid.utils.NetworkUtils;
import com.hdo.WarehouseDroid.utils.SpUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * description 入/移/出库审核Activity
 * author 张建银
 * version 1.0
 * created 2017/11/23
 * modified on 2017/12/22 实现申请详细数据的获取与申请审批
 */

public class InOutMoveApplicationDetailActivity extends BaseActivity {

    //toolbar组件
    Toolbar toolbar;
    TextView toolbar_title;
    TextView toolbar_btn;

    LinearLayout icThings;
    TextView tvThings;

    Context context;

    InOutList list;
    boolean type;

    EditText edId;
    EditText edHouse;
    EditText edTime;
    EditText edPeople;
    EditText edRemark;
    EditText edState;

    List<WarehouseThing> things;

    //取消按钮
    Button cancel;
    //提交按钮
    Button submit;

    String token;

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_in_out_move_application_detail);
    }

    @Override
    public void initData() {
        context = InOutMoveApplicationDetailActivity.this;
        token = SpUtils.getUser(context).getToken();
        Bundle bundle = getIntent().getExtras();
        if (bundle!=null){
            list = (InOutList)bundle.getSerializable("list");
            type = bundle.getBoolean("type");
        }
        getDataFromServer();
    }

    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            switch (message.what){
                case 200:
                    tvThings.setText("共有"+things.size()+"批次物品，点击查看");
                    break;
                case 101:
                    Toast.makeText(context,"登录失效",Toast.LENGTH_LONG).show();
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
                    Call call;
                    if(type){
                        call = NetworkUtils.getInstance().inAppListDetail(context,token,list.getId());
                    }else{
                        call = NetworkUtils.getInstance().outAppListDetail(context,token,list.getId());
                    }
                    Response response = call.execute();
                    ResponseBody body = response.body();
                    if (body == null){
                        handler.sendEmptyMessage(-1);
                    } else {
                        String result = body.string();
                        InOutAppThingResp resp = new Gson().fromJson(result,InOutAppThingResp.class);
                        if(resp.getCode().equals("200")){
                            things = resp.getData().getRows();
                            handler.sendEmptyMessage(200);
                        } else if (resp.getCode().equals("101")){
                            handler.sendEmptyMessage(101);
                        } else {
                            handler.sendEmptyMessage(500);
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
        icThings = findViewById(R.id.ic_things);
        edId = findViewById(R.id.tv_application_detail_id);
        edHouse = findViewById(R.id.tv_application_detail_house);
        edTime = findViewById(R.id.tv_application_detail_time);
        edPeople = findViewById(R.id.tv_application_detail_people);
        edRemark = findViewById(R.id.tv_come_go);
        edState = findViewById(R.id.tv_application_detail_state);
        tvThings = findViewById(R.id.tv_things);
        cancel = findViewById(R.id.btn_cancel);
        submit = findViewById(R.id.btn_commit);
    }

    @Override
    public void setView() {
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if(supportActionBar!=null){
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setDisplayShowTitleEnabled(false);
        }
        toolbar_title.setText("审核");
        toolbar_btn.setVisibility(View.GONE);
        icThings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, InOutThingsListActivity.class);
                intent.putExtra("canAdd",false);
                intent.putExtra("flag",type);
                Bundle bundle = new Bundle();
                bundle.putSerializable("thingList",(ArrayList<WarehouseThing>)things);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        edId.setText(list.getNumber());
        edHouse.setText(list.getWarehouse());
        edRemark.setText(list.getRemark());
        if (type){
            edPeople.setText(list.getInUser());
            edTime.setText(list.getInOpDate());
            edState.setText(context.getResources().getStringArray(R.array.in_thing_apply_state)[list.getState()-1]);
        }else{
            edPeople.setText(list.getOutUser());
            edTime.setText(list.getOutOpDate());
            edState.setText(context.getResources().getStringArray(R.array.out_thing_apply_state)[list.getState()-1]);
        }
        //取消点击
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //提交点击
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Call call;
                        if (type){
                            call = NetworkUtils.getInstance().inAppCheck(token,list.getId());
                        }else{
                            call = NetworkUtils.getInstance().outAppCheck(token,list.getId());
                        }
                        try {
                            Response response = call.execute();
                            ResponseBody body = response.body();
                            if (body == null) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(context, "网络请求失败", Toast.LENGTH_LONG).show();
                                    }
                                });
                            } else {
                                String result = body.string();
                                JSONObject object = new JSONObject(result);
                                if (object.getString("code").equals("200")) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(context, "审核成功", Toast.LENGTH_LONG).show();
                                            finish();
                                        }
                                    });
                                } else if (object.getString("code").equals("101")) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            ReLoging.JumpToLoginPager(context);//登录失效后跳转至登录页面
                                        }
                                    });
                                } else {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(context, "未知错误，请重试", Toast.LENGTH_LONG).show();
                                        }
                                    });
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
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
