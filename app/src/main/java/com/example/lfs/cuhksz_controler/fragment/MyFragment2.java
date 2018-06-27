package com.example.lfs.cuhksz_controler.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lfs.cuhksz_controler.R;
import com.example.lfs.cuhksz_controler.adapter.TemperatureAdapter;
import com.example.lfs.cuhksz_controler.entity.Temperature;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Jay on 2015/8/28 0028.
 */
public class MyFragment2 extends Fragment implements View.OnClickListener, SeekBar.OnSeekBarChangeListener {

    private Context context;
    private Activity activity;
    private Button add,delete;
    private ListView listView;
    private Button electricButton,magneticButton;
    private SeekBar electricSeekBar,magneticSeekBar;
    private TextView electricText,magneticText;
    private TemperatureAdapter adapter;
    private int electricState,magneticState;

    //定义数据
    private List<Temperature> mData;
    public MyFragment2() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2,container,false);
        context=getActivity();
        activity=getActivity();
        init(view);
        return view;
    }
//    绑定对应的控件
    private void init(View view) {
        electricState=0;
        magneticState=0;
        add=view.findViewById(R.id.button_add);
        add.setOnClickListener(this);
        delete=view.findViewById(R.id.button_delete);
        delete.setOnClickListener(this);
        listView=view.findViewById(R.id.listview);
        initList();
//       电场磁场
        electricButton=view.findViewById(R.id.electric_button);
        electricSeekBar=view.findViewById(R.id.electric_bar);
        electricText=view.findViewById(R.id.electric_number);
        //监听器
        electricSeekBar.setOnSeekBarChangeListener(this);
        electricButton.setOnClickListener(this);
//        磁场
        magneticButton=view.findViewById(R.id.magnetic_button);
        magneticSeekBar=view.findViewById(R.id.magnetic_bar);
        magneticText=view.findViewById(R.id.magnetic_number);
        //监听器
        magneticSeekBar.setOnSeekBarChangeListener(this);
        magneticButton.setOnClickListener(this);
    }

    private void initList() {
        //初始化数据
        initData();
        //创建自定义Adapter的对象
        adapter = new TemperatureAdapter(activity,R.layout.temperature_item,mData,listView);
        //将布局添加到ListView中
        listView.setAdapter(adapter);
    }

    private void initData() {
        mData = new ArrayList<Temperature>();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_add:
                showSettleDialog();
                break;
            case R.id.button_delete:
                mData.remove(mData.size()-1);
                adapter.notifyDataSetInvalidated();
                break;
            case R.id.electric_button:
                electricState=(electricState+1)%2;
                if(electricState==1){
                    electricButton.setText("down");

                }else if(electricState==2) {
                    electricButton.setText("up");
                }else if(electricState==0) {
                    electricButton.setText("off");
                }
                break;
            case R.id.magnetic_button:
                magneticState=(magneticState+1)%3;
                if(magneticState==1){
                    magneticButton.setText("down");
                }else if(magneticState==2) {
                    magneticButton.setText("up");
                }else if(magneticState==0) {
                    magneticButton.setText("off");
                }
                break;
        }
    }
    private void showSettleDialog() {
        //弹出框
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        //    通过LayoutInflater来加载一个xml的布局文件作为一个View对象
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_setting_temperature, null);
        //    设置我们自己定义的布局文件作为弹出框的Content
        builder.setView(view);
        final EditText editId,editTemperature,editTime;
        editId= view.findViewById(R.id.edit_id);
        editTemperature= view.findViewById(R.id.edit_temperature);
        editTime=  view.findViewById(R.id.edit_time);
        editId.setText((mData.size()+1)+"");

        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(!editId.getText().toString().isEmpty()&&!editTemperature.getText().toString().isEmpty()&&
                        !editTime.getText().toString().isEmpty()){
                    mData.add(new Temperature(editId.getText().toString(),editTemperature.getText().toString()+" ℃",
                            editTime.getText().toString()+" s"));
                    adapter.notifyDataSetInvalidated();

                }else {
                    Toast.makeText(context,"please fill all blanks",Toast.LENGTH_SHORT).show();
                }

            }
        });
        builder.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.show();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if(seekBar.getTag().toString().equals("1")){
            electricText.setText(progress/10+"");
        }else if(seekBar.getTag().toString().equals("2")){
            magneticText.setText(progress/10+"");
        }

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
