package com.example.lfs.cuhksz_controler.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lfs.cuhksz_controler.R;
import com.example.lfs.cuhksz_controler.entity.Temperature;

import java.util.List;

/**
 * Created by lfs on 2018/6/10.
 */

public class TemperatureAdapter extends ArrayAdapter<Temperature> {
    private Context context;
    private int resourceId;
    private ListView listView;


    public TemperatureAdapter(Context context, int textViewResourceId, List<Temperature> objects, ListView listView){
        super(context,textViewResourceId,objects);
        this.context=context;
        resourceId=textViewResourceId;
        this.listView=listView;
    }


    @Override
    public View getView(int position, View viewTemperature, ViewGroup parent) {
        View view;
        view= LayoutInflater.from(getContext()).inflate(resourceId,null);
        //获得对象
        Temperature temperature=getItem(position);
        //获得自定义布局的控件对象
        TextView textId=view.findViewById(R.id.text_number);
        TextView textTemperature=view.findViewById(R.id.text_temperature);
        TextView textTime=view.findViewById(R.id.text_time);
        //将数据加入
        textId.setText(temperature.getId());
        textTemperature.setText(temperature.getTemperature());
        textTime.setText(temperature.getTime());
        return view;
    }

}
