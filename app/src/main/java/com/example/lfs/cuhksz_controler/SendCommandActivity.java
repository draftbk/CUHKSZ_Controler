package com.example.lfs.cuhksz_controler;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lfs.cuhksz_controler.application.MyApplication;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SendCommandActivity extends AppCompatActivity implements View.OnClickListener {
    private Button sendButton,cancelButton;
    private EditText  editContent,editIp;
    private TextView testReveiver;
    private Switch connectSwitch;
    private Handler handler;
    private MyService.MySocketBinder mySocketBinder;
    private ServiceConnection connection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_command);
        init();
        initService();
    }
    private void initService() {
        connection=new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                mySocketBinder= (MyService.MySocketBinder) service;
                mySocketBinder.startSocket(editIp.getText().toString(), 2000,handler);
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        };
    }

    private void init() {
        connectSwitch=findViewById(R.id.switch_connect);
        testReveiver=findViewById(R.id.text_receiver);
        editContent=findViewById(R.id.edit_content);
        editIp=findViewById(R.id.edit_ip);
        sendButton=findViewById(R.id.button_send);
        cancelButton=findViewById(R.id.button_cancel);
        sendButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
        // 初始化handler
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                testReveiver.setText(msg.obj.toString());
            }
        };
        connectSwitch.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.switch_connect:
                if (connectSwitch.isChecked()){
                    showToast("打开连接");
                    Intent startService=new Intent(this,MyService.class);
                    startService(startService);
                    Intent bindIntent=new Intent(this,MyService.class);
                    //绑定服务
                    bindService(bindIntent,connection,BIND_AUTO_CREATE);
                }else {
                    showToast("关闭连接");
                    Intent stopService=new Intent(this,MyService.class);
                    stopService(stopService);
                    //解绑service
                    unbindService(connection);
                }
                break;
            case R.id.button_send:
                showToast(mySocketBinder.sendMessage(editContent.getText().toString()));
                break;
            case R.id.button_cancel:
                finish();
                break;
        }
    }


    private void showToast(String s) {
        Toast.makeText(SendCommandActivity.this,s,Toast.LENGTH_SHORT).show();
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
