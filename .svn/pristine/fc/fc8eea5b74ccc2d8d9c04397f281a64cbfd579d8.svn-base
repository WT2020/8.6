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
import com.hdo.WarehouseDroid.bean.BaseThing;
import com.hdo.WarehouseDroid.bean.Thing;

import java.util.ArrayList;
import java.util.List;

/**
 * description 库存查询列表适配器类
 * author 张建银
 * version 1.0
 * Created on 2017/11/23.
 * modified on 2017/12/15 根据数据改变修改适配器填充
 */

public class HouseStateListAdapter extends BaseAdapter {

    private Context context;
    private List<Thing> objects;

    public void setObjects(ArrayList<Thing> objects){
        this.objects = objects;
    }

    public HouseStateListAdapter(Context context, List<Thing> objects){
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
        Thing thing = (Thing) getItem(position);
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_state, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.name = view.findViewById(R.id.tv_state_thing_name);
            viewHolder.num = view.findViewById(R.id.tv_state_thing_num);
            viewHolder.state = view.findViewById(R.id.tv_state);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        if (thing !=null){
            viewHolder.name.setText(thing.getGoods().getName());
            viewHolder.num.setText(String.valueOf(thing.getNum()));
            viewHolder.state.setText(context.getResources().getStringArray(R.array.thing_state)[Integer.parseInt(thing.getGoods().getState())]);
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, StateDetailActivity.class);
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
        private TextView num;
        private TextView state;
    }

}
