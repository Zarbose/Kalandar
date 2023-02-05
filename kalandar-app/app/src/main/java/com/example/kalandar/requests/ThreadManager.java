package com.example.kalandar.requests;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

/** Represents the manager for the thread
 * @author Simon and Tanguy
 */
public class ThreadManager implements Handler.Callback, Runnable
{
    private final RequestsHttp r;
    private String resultData;
    private RequestsListener listener=null;

    private final Handler monHandler;
    private final Thread monThread;

    public ThreadManager(RequestsHttp r){
        this.r=r;
        this.listener=null;
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
        this.resultData=data;

        if (this.listener != null && this.r.getType().equals("GET_ALL")) {
            listener.updateMain();
        }
        else if (this.listener != null && this.r.getType().equals("DELETE")){
            listener.updateWeek();
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
        else if (r.getType().equals("POST")){
            this.r.requestPost();
            server_response=this.r.getServer_response();
            bundle.putString("event",server_response);
            msg.setData(bundle);
        }
        else if (r.getType().equals("DELETE")){
            this.r.requestDelete();
            server_response=this.r.getServer_response();
            bundle.putString("event",server_response);
            msg.setData(bundle);
        }
        else if(r.getType().equals("PUT")){
            this.r.requestPut();
            server_response=this.r.getServer_response();
            bundle.putString("event",server_response);
            msg.setData(bundle);
        }
        else{
            Log.e("Response", "Unknow type of request" + r.getType());
        }

        this.monHandler.sendMessage(msg);
    }
}
