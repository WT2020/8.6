package com.hdo.WarehouseDroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hdo.WarehouseDroid.R;
import com.hdo.WarehouseDroid.bean.Thing;

import java.util.List;


/**
 * 项目名称：WarehouseDroid
 * 创建人:吴小雪
 * 创建时间:2017/12/25  11:48
 */

public class AddThingAdapter extends BaseAdapter {
    private Context context;
    List<Thing> list;
    private Boolean isIn;

    public void setObjects(List<Thing> objects){
        this.list = objects;
        this.notifyDataSetChanged();
    }

    public AddThingAdapter(Context context,List<Thing>list,Boolean isIn) {
        this.context = context;
        this.list  = list;
        this.isIn = isIn;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(final int position, View convertView,  ViewGroup parent) {
        View view;
        ViewHolder viewHolder;
        Thing thing = list.get(position);
        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.addthingitem, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.ThingName = view.findViewById(R.id.thing_name);
            viewHolder.ThingNumber = view.findViewById(R.id.thing_number);
            //viewHolder.Numder = view.findViewById(R.id.number);
            view.setTag(viewHolder);
        } else {
            view = convertView;
            viewHolder = (ViewHolder) view.getTag();
        }
        if(thing!=null){
            viewHolder.ThingName.setText(thing.getGoods().getName());
            viewHolder.ThingNumber.setText(thing.getGoods().getRfidCode());
            //viewHolder.Numder.setText(String.valueOf(thing.getNum()));
        }
       /* view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("thing",list.get(position));
                bundle.putBoolean("flag",isIn);
                intent.putExtras(bundle);
                setResult(RESULT_OK,intent);
                finish();
            }
        });*/
        return view;
    }
    class ViewHolder{
        TextView ThingNumber;
        TextView ThingName;
        //TextView Numder;
    }
}
