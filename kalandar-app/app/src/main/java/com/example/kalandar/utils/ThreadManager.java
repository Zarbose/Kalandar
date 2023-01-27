package com.example.kalandar.utils;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

public class ThreadManager implements Handler.Callback, Runnable
{

    private RequestsHttp r;
    private String resultData;
    private RequestsListener listener=null;

    private Handler monHandler;
    private Thread monThread;

    public ThreadManager(RequestsHttp r){
        this.r=r;
        this.monHandler=new Handler(this);
        this.monThread=new Thread(this);
        this.monThread.start();
    }

    public ThreadManager(RequestsHttp r,RequestsListener listener){
        this.r=r;
        this.listener=listener;
        this.monHandler=new Handler(this);
        this.monThread=new Thread(this);
        this.monThread.start();
    }

    public String getResultData() {
        return resultData;
    }

    @Override
    public boolean handleMessage(@NonNull Message message) { // UI thread
        String data = message.getData().getString("event");
        // Log.e("Response", "" + data);
        this.resultData=data;
        
        if (this.listener != null) {
            listener.updateAllEvents();
        }

        return true;
    }

    @Override
    public void run() { // Thread
        Message msg=null;
        String server_response;
        Bundle bundle=new Bundle();

        msg=this.monHandler.obtainMessage();

        if (r.getType().equals("GET_ALL")){
            this.r.requestGetAll();
            server_response=this.r.getServer_response();
            bundle.putString("event",server_response);
            msg.setData(bundle);
        }

        this.monHandler.sendMessage(msg);
    }
}
