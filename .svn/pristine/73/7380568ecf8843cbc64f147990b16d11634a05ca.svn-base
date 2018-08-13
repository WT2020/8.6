package com.hdo.WarehouseDroid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.hdo.WarehouseDroid.R;
import com.hdo.WarehouseDroid.bean.WarehouseInfo;

import java.util.ArrayList;

/**
 * description 仓库适配器
 * author 张建银
 * version 1.0
 * created 2018/2/2
 */

public class WareHouseInfoAdapter extends ArrayAdapter<WarehouseInfo> {

    private int viewId;

    public WareHouseInfoAdapter(Context context, int viewId, ArrayList<WarehouseInfo> warehouseInfos){
        super(context,viewId,warehouseInfos);
        this.viewId = viewId;
    }

    @Nullable
    @Override
    public WarehouseInfo getItem(int position) {
        return super.getItem(position);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView,@NonNull ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        WarehouseInfo warehouseInfo = getItem(position);
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(viewId,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.name = view.findViewById(R.id.goods_name);
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        }
        if(warehouseInfo!=null&&warehouseInfo.getWarehouseName()!=null){
            viewHolder.name.setText(warehouseInfo.getWarehouseName());
        }
        return view;
    }

    @Override
    public View getDropDownView(int position,  View convertView,@NonNull  ViewGroup parent) {
        WarehouseInfo warehouseInfo = getItem(position);
        if(convertView==null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        }
        TextView textView = convertView.findViewById(android.R.id.text1);
        if(warehouseInfo!=null&&warehouseInfo.getWarehouseName()!=null){
            textView.setText(warehouseInfo.getWarehouseName());
        }
        textView.setTextSize(14);
        return convertView;
    }

    class ViewHolder{
        TextView name;
    }

}
