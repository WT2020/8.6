package com.hdo.WarehouseDroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hdo.WarehouseDroid.R;
import com.hdo.WarehouseDroid.activity.StateDetailActivity;
import com.hdo.WarehouseDroid.activity.StateListDetailActivity;
import com.hdo.WarehouseDroid.bean.StateDetailThing;

import java.util.ArrayList;

/**
 * description 库存详细批次列表适配器类
 * author 张建银
 * version 1.0
 * Created on 2017/12/22
 */

public class HouseStateDetailListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<StateDetailThing> objects;

    public HouseStateDetailListAdapter(Context context, ArrayList<StateDetailThing> objects){
        this.context = context;
        this.objects = objects;
    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public Object getItem(int pos){
        return objects.get(pos);
    }

    @Override
    public long getItemId(int pos){
        return pos;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        StateDetailThing thing = objects.get(position);
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_state_detail, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.name = view.findViewById(R.id.tv_state_detail_thing_name);
            viewHolder.batchNum = view.findViewById(R.id.tv_state_detail_thing_num);
            viewHolder.warehouse = view.findViewById(R.id.tv_state_detail_thing_warehouse);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        if(thing!=null){
            viewHolder.name.setText(thing.getGoods().getName());
            viewHolder.batchNum.setText(String.valueOf(thing.getBatchNum()));
            viewHolder.warehouse.setText(thing.getWarehouse().getName()+"-"+thing.getWarehouse().getShelves()+"-"+thing.getWarehouse().getZone());
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, StateListDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("thing",objects.get(position));
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        return view;
    }

    private class ViewHolder{
        private TextView name;
        private TextView batchNum;
        private TextView warehouse;
    }

}
