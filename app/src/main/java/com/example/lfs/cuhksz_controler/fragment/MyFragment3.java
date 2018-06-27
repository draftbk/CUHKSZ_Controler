package com.example.lfs.cuhksz_controler.fragment;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;


import com.example.lfs.cuhksz_controler.R;
import com.example.lfs.cuhksz_controler.entity.Temperature_table;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;




/**
 * Created by Jay on 2015/8/28 0028.
 */
public class MyFragment3 extends Fragment implements View.OnClickListener {

    private Context context;
    private Activity activity;
    private View view;
    private  LineChart mLineChart ;

    public MyFragment3() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment3,container,false);
        context=getActivity();
        activity=getActivity();
        init();

        return view;
    }

    private void init() {
        mLineChart= (LineChart) view.findViewById(R.id.chart);
        Temperature_table[] dataObjects = {
                new Temperature_table(1,2),new Temperature_table(2,3),
                new Temperature_table(3,2),new Temperature_table(4,5),
                new Temperature_table(5,9),new Temperature_table(2,3)} ;
        List<Entry> entries = new ArrayList<Entry>();

        for (Temperature_table data : dataObjects) {

            // turn your data into Entry objects
            entries.add(new Entry(data.getTime(), data.getTemperature()));
        }
        LineDataSet dataSet = new LineDataSet(entries, "Label"); // add entries to dataset
        dataSet.setColor(R.color.colorPrimary);
        dataSet.setValueTextColor(R.color.bg_black); // styling, ...
        LineData lineData = new LineData(dataSet);
        mLineChart.setData(lineData);
        mLineChart.invalidate(); // refresh
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
    }








}
