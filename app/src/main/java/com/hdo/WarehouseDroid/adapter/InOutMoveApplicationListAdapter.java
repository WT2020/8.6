package com.hdo.WarehouseDroid.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hdo.WarehouseDroid.R;
import com.hdo.WarehouseDroid.activity.InOutMoveApplicationDetailActivity;
import com.hdo.WarehouseDroid.bean.InOutList;

import java.util.ArrayList;
import java.util.List;

/**
 * description 入/出库申请列表适配器类
 * author 张建银
 * version 1.0
 * Created on 2017/11/24.
 * modified on 2017/12/15 根据数据改变修改适配器填充
 * modified on 2018/2/5 未审核变红
 */

public class InOutMoveApplicationListAdapter extends BaseAdapter {

    private Context context;
    private List<InOutList> objects;
    private Boolean isInOut;
    private Boolean isIn;

    public void setObjects(ArrayList<InOutList> objects){
        this.objects = objects;
        this.notifyDataSetChanged();
    }

    public InOutMoveApplicationListAdapter(Context context, List<InOutList> objects, Boolean isInOut, Boolean isIn){
        this.context = context;
        this.objects = objects;
        this.isInOut = isInOut;
        this.isIn = isIn;
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
        InOutList list = (InOutList) getItem(position);
        if (convertView == null) {
            viewHolder = new ViewHolder();
            if (isInOut){
                view = LayoutInflater.from(context).inflate(R.layout.item_in_out_application, parent, false);
                viewHolder.house = view.findViewById(R.id.tv_item_application_house);
            } else {
                view = LayoutInflater.from(context).inflate(R.layout.item_move_application, parent, false);
            }
            viewHolder.time = view.findViewById(R.id.tv_item_application_time);
            viewHolder.people = view.findViewById(R.id.tv_item_application_people);
            viewHolder.state = view.findViewById(R.id.tv_item_application_state);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        if (list!=null){
            if (isInOut){
                viewHolder.house.setText(list.getWarehouse());
                if (isIn){
                    viewHolder.time.setText(list.getInOpDate());
                    viewHolder.people.setText(list.getInUser());
                    viewHolder.state.setText(context.getResources().getStringArray(R.array.in_thing_apply_state)[list.getState()-1]);
                }else{
                    viewHolder.time.setText(list.getOutOpDate());
                    viewHolder.people.setText(list.getOutUser());
                    viewHolder.state.setText(context.getResources().getStringArray(R.array.out_thing_apply_state)[list.getState()-1]);
                }
                if(list.getState()==1){
                    viewHolder.state.setTextColor(android.graphics.Color.RED);
                }
            }else{

            }
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, InOutMoveApplicationDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("list",objects.get(position));
                bundle.putBoolean("type",isIn);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        return view;
    }

    private class ViewHolder{
        private TextView house;
        private TextView time;
        private TextView people;
        private TextView state;
    }

}
