package com.example.lfs.cuhksz_controler;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    public MyService() {
    }
    private MySocketBinder mySocketBinder=new MySocketBinder();
    class MySocketBinder extends Binder{
        public void startSocket(){
            Log.e("MyService","startSocket onCreate");
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mySocketBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.e("MyService","myService onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e("MyService","myService onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("MyService","myService onDestroy");
    }
}
