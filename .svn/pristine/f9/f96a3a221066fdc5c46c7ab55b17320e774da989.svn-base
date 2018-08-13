package com.hdo.WarehouseDroid.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.hdo.WarehouseDroid.R;
import com.hdo.WarehouseDroid.activity.HouseStateActivity;
import com.hdo.WarehouseDroid.activity.InOutHouseActivity;
import com.hdo.WarehouseDroid.activity.InOutMoveApplicationActivity;
import com.hdo.WarehouseDroid.activity.SettlementActivity;
import com.hdo.WarehouseDroid.base.BaseFragment;
import com.hdo.WarehouseDroid.utils.SpUtils;

/**
 * description HomeFragment
 * author 张建银
 * version 1.0
 * created 2017/11/22
 */

public class HomeFragment extends BaseFragment {
    //入库申请
    LinearLayout llHomeIn;
    //移库申请
    LinearLayout llHomeMove;
    //出库申请
    LinearLayout llHomeOut;
    //申请管理
    LinearLayout llHomeAllCheck;
    //库存查询
    LinearLayout llHomeState;
    //账单记录
    LinearLayout llSettlement;
    //Log标签
    String TAG = this.getClass().getName();
    //用户类型
    String userType;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view = setLayout(container);
        initView(view);
        return view;
    }

    @Override
    public View setLayout(ViewGroup container){
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_home,container,false);
        return view;
    }

    @Override
    public void initView(View view){
        llHomeIn = view.findViewById(R.id.ll_home_in);
        llHomeMove = view.findViewById(R.id.ll_home_move);
        llHomeOut  = view.findViewById(R.id.ll_home_out);
        llHomeAllCheck = view.findViewById(R.id.ll_home_all_check);
        llHomeState = view.findViewById(R.id.ll_home_state);
        llSettlement = view.findViewById(R.id.ll_home_settlement);
    }

    @Override
    public void initData(){
        userType = SpUtils.getString(getActivity(),"userType","customer");
    }

    @Override
    public void setView(){
        //进入入库申请界面
        llHomeIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userType.equals("admin")){
                    Intent intent = new Intent(getActivity(), InOutHouseActivity.class);
                    intent.putExtra("flag",true);
                    startActivity(intent);
                }else{
                    Toast.makeText(getActivity(),"无权限进入",Toast.LENGTH_LONG).show();
                }
            }
        });
        //进入移库申请界面
        llHomeMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"无权限进入",Toast.LENGTH_LONG).show();
            }
        });
        //进入出库申请界面
        llHomeOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (userType.equals("admin")){
                    Intent intent = new Intent(getActivity(), InOutHouseActivity.class);
                    intent.putExtra("flag",false);
                    startActivity(intent);
                }else{
                    Toast.makeText(getActivity(),"无权限进入",Toast.LENGTH_LONG).show();
                }
            }
        });
        //进入申请管理界面
        llHomeAllCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), InOutMoveApplicationActivity.class);
                startActivity(intent);
            }
        });
        //进入库存查询界面
        llHomeState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), HouseStateActivity.class);
                startActivity(intent);
            }
        });
        //进入账单记录界面
        llSettlement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SettlementActivity.class);
                startActivity(intent);
            }
        });
    }
}
