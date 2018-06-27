package com.example.lfs.cuhksz_controler.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lfs.cuhksz_controler.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Jay on 2015/8/28 0028.
 */
public class MyFragment1 extends Fragment implements View.OnClickListener {

    private Context context;
    private Activity activity;
    private View view;
    private Spinner powderSpinner,liquidSpinner1,liquidSpinner2,liquidSpinner3,
            orderSpinner1,orderSpinner2,orderSpinner3,orderSpinner4;
    private TextView powderText,liquidText1,liquidText2,liquidText3,
            orderText1,orderText2,orderText3,orderText4;
    private static final String [] powderData ={"powder1","powder2","powder3"};
    private static final String [] liquidData1 ={"liquid1","liquid2","liquid3"};
    private static final String [] liquidData2 ={"liquid1","liquid2","liquid3"};
    private static final String [] liquidData3 ={"liquid1","liquid2","liquid3"};
    private static final String [] orderData ={"powder","liquid1","liquid2","liquid3"};

    public MyFragment1() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment1, container, false);
        context=getActivity();
        activity=getActivity();
        init();
        return view;
    }



    private void init() {
        initSpinner();

    }

//    初始化相关initSpinner
    private void initSpinner() {
//        powder 相关spinner
        powderText=view.findViewById(R.id.text_powder);
        powderText.setText(powderData[0]);
        powderText.setOnClickListener(this);
        powderSpinner=view.findViewById(R.id.spinner_powder);
        ArrayAdapter<String> powderAdapter=new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,powderData);
        powderAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        powderSpinner.setAdapter(powderAdapter);
        powderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                powderText.setText(powderData[arg2]);
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
        //    liquid1 相关spinner
        liquidText1=view.findViewById(R.id.text_liquid_1);
        liquidText1.setText(liquidData1[0]);
        liquidText1.setOnClickListener(this);
        liquidSpinner1=view.findViewById(R.id.spinner_liquid_1);
        ArrayAdapter<String> liquidAdapter1=new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,liquidData1);
        liquidAdapter1.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        liquidSpinner1.setAdapter(liquidAdapter1);
        liquidSpinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                liquidText1.setText(liquidData1[arg2]);
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
        //    liquid2 相关spinner
        liquidText2=view.findViewById(R.id.text_liquid_2);
        liquidText2.setText(liquidData2[0]);
        liquidText2.setOnClickListener(this);
        liquidSpinner2=view.findViewById(R.id.spinner_liquid_2);
        ArrayAdapter<String> liquidAdapter2=new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,liquidData2);
        liquidAdapter2.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        liquidSpinner2.setAdapter(liquidAdapter2);
        liquidSpinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                liquidText2.setText(liquidData2[arg2]);
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
        //    liquid3 相关spinner
        liquidText3=view.findViewById(R.id.text_liquid_3);
        liquidText3.setText(liquidData3[0]);
        liquidText3.setOnClickListener(this);
        liquidSpinner3=view.findViewById(R.id.spinner_liquid_3);
        ArrayAdapter<String> liquidAdapter3=new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,liquidData3);
        liquidAdapter3.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        liquidSpinner3.setAdapter(liquidAdapter3);
        liquidSpinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                liquidText3.setText(liquidData3[arg2]);
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
        //    order1 相关spinner
        orderText1=view.findViewById(R.id.text_order_1);
        orderText1.setOnClickListener(this);
        orderSpinner1=view.findViewById(R.id.spinner_order_1);
        ArrayAdapter<String> orderAdapter1=new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,orderData);
        orderAdapter1.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        orderSpinner1.setAdapter(orderAdapter1);
        orderSpinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                orderText1.setText(orderData[arg2]);
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
        //    order2 相关spinner
        orderText2=view.findViewById(R.id.text_order_2);
        orderText2.setOnClickListener(this);
        orderSpinner2=view.findViewById(R.id.spinner_order_2);
        ArrayAdapter<String> orderAdapter2=new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,orderData);
        orderAdapter2.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        orderSpinner2.setAdapter(orderAdapter2);
        orderSpinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                orderText2.setText(orderData[arg2]);
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
        //    order3 相关spinner
        orderText3=view.findViewById(R.id.text_order_3);
        orderText3.setOnClickListener(this);
        orderSpinner3=view.findViewById(R.id.spinner_order_3);
        ArrayAdapter<String> orderAdapter3=new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,orderData);
        orderAdapter3.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        orderSpinner3.setAdapter(orderAdapter3);
        orderSpinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                orderText3.setText(orderData[arg2]);
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
        //    order4 相关spinner
        orderText4=view.findViewById(R.id.text_order_4);
        orderText4.setOnClickListener(this);
        orderSpinner4=view.findViewById(R.id.spinner_order_4);
        ArrayAdapter<String> orderAdapter4=new ArrayAdapter<String>(context,android.R.layout.simple_spinner_item,orderData);
        orderAdapter4.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        orderSpinner4.setAdapter(orderAdapter4);
        orderSpinner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                // TODO Auto-generated method stub
                orderText4.setText(orderData[arg2]);
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub

            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.text_powder:
                powderSpinner.performClick();
                break;
            case R.id.text_liquid_1:
                liquidSpinner1.performClick();
                break;
            case R.id.text_liquid_2:
                liquidSpinner2.performClick();
                break;
            case R.id.text_liquid_3:
                liquidSpinner3.performClick();
                break;
            case R.id.text_order_1:
                orderSpinner1.performClick();
                break;
            case R.id.text_order_2:
                orderSpinner2.performClick();
                break;
            case R.id.text_order_3:
                orderSpinner3.performClick();
                break;
            case R.id.text_order_4:
                orderSpinner4.performClick();
                break;
        }
    }



}
