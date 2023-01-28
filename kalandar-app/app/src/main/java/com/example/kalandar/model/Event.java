package com.example.kalandar.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.example.kalandar.utils.RequestsHttp;
import com.example.kalandar.utils.ThreadManager;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class Event implements Parcelable
{
    public static ArrayList<Event> eventsList = new ArrayList<>();

    private String id;
    private String desc;
    private LocalDate date;
    private LocalTime time;
    private Date start;
    private Date end;

    protected Event(Parcel in) {
        id = in.readString();
        desc = in.readString();
        date = LocalDate.now();
        time = LocalTime.now();
        start = new Date();
        end = new Date();
    }

    public static final Creator<Event> CREATOR = new Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel in) {
            return new Event(in);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };

    public static ArrayList<Event> eventsForDate(LocalDate date)
    {
        ArrayList<Event> events = new ArrayList<>();

        for(Event event : eventsList)
        {
            if(event.getDate().equals(date))
                events.add(event);
        }

        return events;
    }

    public Event(String desc, LocalDate date, LocalTime time, Date start, Date end) {
        String pattern = "ddMMYYYYHmsS";
        SimpleDateFormat formatDate = new SimpleDateFormat(pattern);
        String id = formatDate.format(new Date());

        this.id=id;
        this.desc = desc;
        this.date = date;
        this.time = time;

        this.start=start;
        this.end=end;
    }

    public Event(String id, String desc, LocalDate date, LocalTime time, Date start, Date end) {
        this.id=id;
        this.desc = desc;
        this.date = date;
        this.time = time;

        this.start=start;
        this.end=end;
    }

    public void sendPOST(){
        // TODO
    }

    public void getById(){
        // TODO
    }

    public void getAll(){
        RequestsHttp request = new RequestsHttp("GET_ALL");
        ThreadManager th = new ThreadManager(request);
    }

    @NonNull
    public String toString(){
        return "[id="+this.id+", desc="+this.desc+", date="+this.date+", time="+this.time+", start="+this.start+", end="+this.end;
    }

    public String getDesc()
    {
        return desc;
    }

    public void setDesc(String desc)
    {
        this.desc = desc;
    }

    public LocalDate getDate()
    {
        return date;
    }

    public void setDate(LocalDate date)
    {
        this.date = date;
    }

    public LocalTime getTime()
    {
        return time;
    }

    public void setTime(LocalTime time)
    {
        this.time = time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(desc);
        parcel.writeString(date.toString());
        parcel.writeString(time.toString());
        parcel.writeString(start.toString());
        parcel.writeString(end.toString());
    }
}