package com.example.kalandar.model;


import androidx.annotation.NonNull;

import com.example.kalandar.requests.RequestsHttp;
import com.example.kalandar.requests.RequestsListener;
import com.example.kalandar.requests.ThreadManager;
import com.owlike.genson.Genson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/** Represents an event used inside the api
 * @author Simon and Tanguy
 */
public class EventTransient
{
    private String id;
    private String desc;
    private Date start;
    private Date end;

    public EventTransient() {
        super();
        this.id=null;
        this.desc=null;
        this.start =null;
        this.end=null;
    }

    public EventTransient(String id, String desc, Date start, Date end) {
        this.id=id;
        this.desc=desc;
        this.start = start;
        this.end=end;
    }

    public String getId() {
        return id;
    }

    @NonNull
    public String toString(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", this.id);
            jsonObject.put("desc", this.desc);
            jsonObject.put("start", this.start.getTime());
            jsonObject.put("end", this.end.getTime());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    public void sendPOST(){
        Genson genson = new Genson();
        String json = genson.serialize(this);
        RequestsHttp request = new RequestsHttp("POST",json);
        ThreadManager th = new ThreadManager(request);
    }

    public void sendDELETE(RequestsListener listener){
        RequestsHttp request = new RequestsHttp("DELETE",this.id);
        ThreadManager th = new ThreadManager(request,listener);
    }

    public void sendPUT(){
        Genson genson = new Genson();
        String json = genson.serialize(this);
        RequestsHttp request = new RequestsHttp("PUT",this.id,json);
        ThreadManager th = new ThreadManager(request);
    }
}
