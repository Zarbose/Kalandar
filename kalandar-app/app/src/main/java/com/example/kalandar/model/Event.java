package com.example.kalandar.model;


import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

/** Represents an event used inside the app
 * @author Simon and Tanguy
 */
public class Event
{
    public static ArrayList<Event> eventsList = new ArrayList<>();

    private String id;
    private String desc;
    private LocalDate date;
    private LocalTime time;
    private Date start;
    private Date end;

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

    @NonNull
    public String toString(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id", this.id);
            jsonObject.put("desc", this.desc);
            jsonObject.put("date", this.date);
            jsonObject.put("time", this.time);
            jsonObject.put("start", this.start.getTime());
            jsonObject.put("end", this.end.getTime());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }

    public EventTransient toEventTransient(){
        return new EventTransient(this.id,this.desc,this.start,this.end);
    }

    public String getId() {
        return id;
    }

    public Date getStart() {
        return start;
    }

    public Date getEnd() {
        return end;
    }

    public String getDesc()
    {
        return desc;
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