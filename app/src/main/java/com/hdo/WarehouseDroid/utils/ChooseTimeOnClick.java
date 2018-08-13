package com.hdo.WarehouseDroid.utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.hdo.WarehouseDroid.R;

/**
 * author 梁明
 * Created by Admin on 2017/9/13.
 */

public class ChooseTimeOnClick implements View.OnClickListener{
    private Context mContext;
    private TextView time;
    AlertDialog.Builder builder;
    private DatePicker datePicker;
    private TimePicker timePicker;
    private View v;
    public ChooseTimeOnClick(Context context, TextView time) {
        this.mContext = context;
        this.time = time;

    }
    @Override
    public void onClick(View view) {
        chooseTime();
    }

    private void chooseTime() {
        builder = new AlertDialog.Builder(mContext);
        v = (ScrollView) LayoutInflater.from(mContext).inflate(R.layout.date_time_dialog,null);
        datePicker = (DatePicker) v.findViewById(R.id.date_picker);
        timePicker = (TimePicker) v.findViewById(R.id.time_picker);
        builder.setView(v);
        builder.setTitle("选择起始时间：");
        builder.setCancelable(false);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                StringBuffer sb = new StringBuffer();
                sb.append(String.format("%d-%02d-%02d",
                        datePicker.getYear(),
                        datePicker.getMonth() + 1,
                        datePicker.getDayOfMonth()));
                sb.append(" ");
                sb.append(timePicker.getCurrentHour())
                        .append(":").append(timePicker.getCurrentMinute());

                time.setText(sb+":00");
                dialogInterface.cancel();
            }
        });

        builder.setNegativeButton("取 消", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        Dialog startDialog = builder.create();
        startDialog.show();
    }
}
