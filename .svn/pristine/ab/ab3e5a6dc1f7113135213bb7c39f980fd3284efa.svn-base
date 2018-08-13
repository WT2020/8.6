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
import com.hdo.WarehouseDroid.activity.SettlementDetailActivity;
import com.hdo.WarehouseDroid.bean.Settlement;

import java.util.ArrayList;
import java.util.List;

/**
 * description 账单记录列表适配器类
 * author 张建银
 * version 1.0
 * Created on 2018/1/23.
 */

public class SettlementListAdapter extends BaseAdapter {

    private Context context;
    private List<Settlement> objects;

    public void setObjects(ArrayList<Settlement> objects){
        this.objects = objects;
    }

    public SettlementListAdapter(Context context, List<Settlement> objects){
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
        Settlement settlement = (Settlement) getItem(position);
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_settlement, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.name = view.findViewById(R.id.tv_settlement_customer_name);
            viewHolder.monthYear = view.findViewById(R.id.tv_settlement_month_year);
            viewHolder.state = view.findViewById(R.id.tv_settlement_state);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        if (settlement !=null){
            viewHolder.name.setText(settlement.getCustomer().getName());
            viewHolder.monthYear.setText(settlement.getYearMonth());
            viewHolder.state.setText(context.getResources().getStringArray(R.array.settlement_state)[Integer.parseInt(settlement.getStatus())]);
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SettlementDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("settlement",objects.get(position));
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        return view;
    }

    private class ViewHolder{
        private TextView name;
        private TextView monthYear;
        private TextView state;
    }

}
