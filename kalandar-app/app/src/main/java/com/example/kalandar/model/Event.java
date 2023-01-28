package com.example.kalandar.model;

import com.example.kalandar.utils.RequestsHttp;
import com.example.kalandar.utils.ThreadManager;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

public class Event
{
    public static ArrayList<Event> eventsList = new ArrayList<>();

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

    private String id;
    private String desc;
    private LocalDate date;
    private LocalTime time;
    private Date start;
    private Date end;

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
}