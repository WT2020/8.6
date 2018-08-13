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
import com.hdo.WarehouseDroid.activity.InOutThingsListActivity;
import com.hdo.WarehouseDroid.activity.InThingAddLookUpActivity;
import com.hdo.WarehouseDroid.activity.OutThingAddLookUpActivity;
import com.hdo.WarehouseDroid.bean.WarehouseInfo;
import com.hdo.WarehouseDroid.bean.WarehouseThing;

import java.util.List;

/**
 * description 入/出库物品列表适配器类
 * author 张建银
 * version 1.0
 * Created on 2017/11/23.
 * modified on 2017/12/15 根据数据改变修改适配器填充
 * modified on 2017/12/26 添加长按删除和修改判断的实现
 * modified by 张建银 on 2018/2/2 中转数据
 */

public class HouseThingListAdapter extends BaseAdapter {

    private Context context;
    private List<WarehouseThing> objects;
    private Boolean isIn;
    private boolean canAdd;
    private WarehouseInfo.WarehouseZone zone;

    public HouseThingListAdapter(Context context, List<WarehouseThing> objects, Boolean isIn, boolean canAdd, WarehouseInfo.WarehouseZone zone){
        this.context = context;
        this.objects = objects;
        this.isIn = isIn;
        this.canAdd = canAdd;
        this.zone = zone;
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
        WarehouseThing thing = (WarehouseThing) getItem(position);
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_in_out_move_thing, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.id = view.findViewById(R.id.tv_thing_id);
            viewHolder.name = view.findViewById(R.id.tv_thing_name);
            viewHolder.num = view.findViewById(R.id.tv_thing_change_num);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        if (thing!=null){
            viewHolder.id.setText(thing.getGoods().getRfidCode());
            viewHolder.name.setText(thing.getGoods().getName());
            if (isIn){
                viewHolder.num.setText(String.valueOf(thing.getAddAmount()));
            }else{
                viewHolder.num.setText(String.valueOf(thing.getRemoveAmount()));
            }
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent;
                Bundle bundle;
                if (isIn){
                    intent = new Intent(context, InThingAddLookUpActivity.class);
                    bundle = new Bundle();

                }else{
                    intent = new Intent(context, OutThingAddLookUpActivity.class);
                    bundle = new Bundle();
                }
                bundle.putSerializable("thing",objects.get(position));
                bundle.putInt("type",1);
                bundle.putBoolean("flag",isIn);
                intent.putExtras(bundle);
//                Intent intent = new Intent(context, InThingAddLookUpActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putSerializable("thing",objects.get(position));
//                bundle.putInt("type",1);
//                bundle.putBoolean("flag",isIn);
//                intent.putExtras(bundle);
                //type:2-查看（可修改）/1-查看（不可修改）/0-添加
                if (canAdd){
                    //查看可修改
                    bundle.putInt("type",2);
                    //传入pos
                    bundle.putInt("pos",position);
                    bundle.putSerializable("zone",zone);
                    intent.putExtras(bundle);
                    ((InOutThingsListActivity)context).startActivityForResult(intent,2);
                }else{
                    //仅查看，不可修改
                    bundle.putInt("type",1);
                    bundle.putSerializable("zone",zone);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                }
            }
        });
        if (canAdd){
            //长按删除
            view.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    objects.remove(position);
                    HouseThingListAdapter.this.notifyDataSetChanged();
                    return false;
                }
            });
        }
        return view;
    }

    private class ViewHolder{
        private TextView id;
        private TextView name;
        private TextView num;
    }
}
