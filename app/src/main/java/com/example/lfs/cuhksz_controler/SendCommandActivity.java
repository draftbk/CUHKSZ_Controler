package com.example.lfs.cuhksz_controler;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
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
    /**
     * 接收服务器消息 变量
     */
    // 输入流对象
    InputStream is;
    // 输入流读取器对象
    InputStreamReader isr ;
    BufferedReader br ;
    // 接收服务器发送过来的消息
    String response;
    /**
     * 发送消息到服务器 变量
     */
    // 输出流对象
    OutputStream outputStream;
    // 线程池
    private ExecutorService mThreadPool;
    // Socket变量
    private Socket socket;
    // 接收的text
    private String receText="";
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
                mySocketBinder.startSocket();
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
        // 初始化线程池
        mThreadPool = Executors.newCachedThreadPool();
        // 初始化handler
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                receText=receText+msg.obj.toString();
                testReveiver.setText(receText);
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
                    // 利用线程池直接开启一个线程 & 执行该线程
//                    mThreadPool.execute(new Runnable() {
//                        @Override
//                        public void run() {
//                            try {
//                                // 创建Socket对象 & 指定服务端的IP 及 端口号
//                                socket = new Socket(editIp.getText().toString(), 2000);
//                                // 判断客户端和服务器是否连接成功
//                                System.out.println(socket.isConnected());
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                            try {
//                                //吧out也在这里初始化
//                                outputStream = socket.getOutputStream();
//                                // 步骤1：创建输入流对象InputStream
//                                is = socket.getInputStream();
//                                // 步骤2：创建输入流读取器对象 并传入输入流对象
//                                // 该对象作用：获取服务器返回的数据
//                                isr = new InputStreamReader(is);
//                                br = new BufferedReader(isr);
//                                while(true){
//                                    System.out.println("这里");
//                                    // 步骤3：通过输入流读取器对象 接收服务器发送过来的数据
//                                    response = br.readLine();
//                                    // 步骤4:通知主线程,将接收的消息显示到界面
//                                    System.out.println("这里2");
//                                    Message msg = new Message();
//                                    msg.obj=response;
//                                    handler.sendMessage(msg);
//                                }
//                            } catch (IOException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    });
                }else {
                    showToast("关闭连接");
                    Intent stopService=new Intent(this,MyService.class);
                    stopService(stopService);
                    //解绑service
                    unbindService(connection);
//                    try {
//                        // 断开 客户端发送到服务器 的连接，即关闭输出流对象OutputStream
//                        outputStream.close();
//                        // 断开 服务器发送到客户端 的连接，即关闭输入流读取器对象BufferedReader
//                        br.close();
//                        // 最终关闭整个Socket连接
//                        socket.close();
//                        // 判断客户端和服务器是否已经断开连接
//                        System.out.println(socket.isConnected());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
                }
                break;
            case R.id.button_send:
                // 利用线程池直接开启一个线程 & 执行该线程
                mThreadPool.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            // 步骤1：从Socket 获得输出流对象OutputStream
                            // 该对象作用：发送数据
                            String message=editContent.getText().toString();
                            outputStream.write((message+"\n").getBytes("utf-8"));
                            // 特别注意：数据的结尾加上换行符才可让服务器端的readline()停止阻塞
                            // 步骤3：发送数据到服务端
                            outputStream.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                });
                break;
            case R.id.button_cancel:
                finish();
                break;
        }
    }


    private void showToast(String s) {
        Toast.makeText(SendCommandActivity.this,s,Toast.LENGTH_SHORT).show();
    }

    /**
     * 从参数的Socket里获取最新的消息
     */
    private void startReader(final Socket socket) {

        mThreadPool.execute(new Runnable() {
            @Override
            public void run() {
               // DataInputStream reader;
                try {
                    // 获取读取流
                 //   reader = new DataInputStream( socket.getInputStream());
                    while (true) {
                        System.out.println("*等待客户端输入*");
                        // 读取数据
                        BufferedReader br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        String line=br.readLine();
                        String msg = line;
                        System.out.println("获取到客户端的信息：" + line);
                        Message message=new Message();
                        message.obj=msg+"\n";
                        handler.sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     *创建菜单
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.send_command_activity_menu,menu); //通过getMenuInflater()方法得到MenuInflater对象，再调用它的inflate()方法就可以给当前活动创建菜单了，第一个参数：用于指定我们通过哪一个资源文件来创建菜单；第二个参数：用于指定我们的菜单项将添加到哪一个Menu对象当中。
        return true; // true：允许创建的菜单显示出来，false：创建的菜单将无法显示。
    }

    /**
     *菜单的点击事件
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_connect:
                Toast.makeText(this, "你点击了 添加！", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            // 断开 客户端发送到服务器 的连接，即关闭输出流对象OutputStream
            outputStream.close();
            // 断开 服务器发送到客户端 的连接，即关闭输入流读取器对象BufferedReader
            br.close();
            // 最终关闭整个Socket连接
            socket.close();
            // 判断客户端和服务器是否已经断开连接
            System.out.println(socket.isConnected());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
