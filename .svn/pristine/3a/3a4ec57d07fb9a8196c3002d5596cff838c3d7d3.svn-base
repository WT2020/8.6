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
import com.hdo.WarehouseDroid.bean.Settlement;

/**
 * description 账单详细信息界面
 * author 张建银
 * version 1.0
 * created 2018/1/23.
 */

public class SettlementDetailActivity extends BaseActivity {

    //toolbar组件
    Toolbar toolbar;
    TextView toolbar_title;
    TextView toolbar_btn;

    Context context;

    Settlement settlement;

    EditText etYearMonth;
    EditText etState;
    EditText etAllMoney;
    EditText etPaidMoney;
    EditText etPeople;
    EditText etDays;

    @Override
    public void setLayout() {
        setContentView(R.layout.activity_settlement_detail);
    }

    @Override
    public void initData() {
        context = SettlementDetailActivity.this;
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            settlement = (Settlement) bundle.getSerializable("settlement");
        }
    }

    @Override
    public void initView() {
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar_title = (TextView)findViewById(R.id.toolbar_title);
        toolbar_btn = (TextView)findViewById(R.id.toolbar_button);
        etYearMonth = findViewById(R.id.tv_settlement_detail_month_year);
        etState = findViewById(R.id.tv_settlement_detail_state);
        etAllMoney = findViewById(R.id.tv_settlement_detail_all_money);
        etPaidMoney = findViewById(R.id.tv_settlement_detail_paid_money);
        etPeople = findViewById(R.id.tv_settlement_detail_people);
        etDays = findViewById(R.id.tv_settlement_detail_days);
    }

    @Override
    public void setView() {
        setSupportActionBar(toolbar);
        ActionBar supportActionBar = getSupportActionBar();
        if(supportActionBar!=null){
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setDisplayShowTitleEnabled(false);
        }
        toolbar_title.setText("账单详情");
        toolbar_btn.setVisibility(View.GONE);
        if(settlement!=null){
            etYearMonth.setText(settlement.getYearMonth());
            etState.setText(context.getResources().getStringArray(R.array.settlement_state)[Integer.parseInt(settlement.getStatus())]);
            etAllMoney.setText(String.valueOf(settlement.getApMoney()));
            etPaidMoney.setText(String.valueOf(settlement.getAlreadyApMoney()));
            etPeople.setText(settlement.getCustomer().getName());
            etDays.setText(String.valueOf(settlement.getDays()));
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
