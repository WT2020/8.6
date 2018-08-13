package com.hdo.WarehouseDroid.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hdo.WarehouseDroid.R;
import com.hdo.WarehouseDroid.bean.OutThing;
import com.hdo.WarehouseDroid.bean.Thing;

import java.util.List;

/**
 * 项目名称：WarehouseDroid
 * 创建人:吴小雪
 * 创建时间:2017/12/15  14:50
 */

public class GoodsNameAdapter extends ArrayAdapter <Thing>{
    private int resourceId;

    public GoodsNameAdapter(Context context, int textViewResourceId, List<Thing>list) {
        super(context, textViewResourceId,list);
        resourceId = textViewResourceId;
    }


    @Nullable
    @Override
    public Thing getItem(int position) {
        return super.getItem(position);
    }

    @NonNull
    @Override
    public View getView(int position,  View convertView, ViewGroup parent) {
        Thing thing = getItem(position);
        View view;
        ViewHolder viewHolder;
        if(convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.ThingName = (TextView)view.findViewById(R.id.goods_name);
            view.setTag(viewHolder);
        }else {
            view = convertView;
            viewHolder = (ViewHolder)view.getTag();
        }
        viewHolder.ThingName.setText(thing.getGoods().getName());
        return view;
    }

    @Override
    public View getDropDownView(int position,  View convertView,  ViewGroup parent) {
        Thing thing = getItem(position);
        if(convertView==null){
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);

        }
        TextView textView = (TextView)convertView.findViewById(android.R.id.text1);
        textView.setText(thing.getGoods().getName());
        textView.setTextSize(14);
        return convertView;
    }

    class ViewHolder{
        TextView ThingName;
    }
}
