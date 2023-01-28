package com.example.kalandar.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class EventTransiant
{

    private String id;
    private String desc;
    private Date start;
    private Date end;


    public EventTransiant() {
        super();
        this.id=null;
        this.desc=null;
        this.start =null;
        this.end=null;
    }
    public EventTransiant(String id, String desc, Date start, Date end) {
        this.id=id;
        this.desc=desc;
        this.start = start;
        this.end=end;
    }
    public String getId() {
        return id;
    }
    public String getDesc() {
        return desc;
    }
    public Date getStart() {
        return start;
    }
    public Date getEnd(){
        return end;
    }

    public String toString(){
        String patternDate = "dd/MM/YYYY";
        String patternTime = "H:m:s";
        SimpleDateFormat formatDate = new SimpleDateFormat(patternDate);
        String str_date = formatDate.format(this.start);
        SimpleDateFormat formatTime = new SimpleDateFormat(patternTime);
        String str_time = formatTime.format(this.start);

        return "id = " + this.id + " desc = " + this.desc + " date = "+str_date+" time = "+str_time;
    }
}
