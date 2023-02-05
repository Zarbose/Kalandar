package com.example.kalandar.utils;


import com.example.kalandar.model.Event;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Converter {
    public static Event strToEvent(String s) throws JSONException, ParseException {
        JSONObject json = new JSONObject(s);

        Date date_start_str = new Date((Long) json.get("start"));
        Date date_end_str = new Date((Long) json.get("end"));

        LocalDate date = date_start_str.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalTime time = date_start_str.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();

        Event evn = new Event((String) json.get("id"), (String) json.get("desc"),date,time,date_start_str,date_end_str);

        return evn;
    }

    public static ArrayList<Event> arrToEvent(ArrayList<String> toConvert) throws JSONException, ParseException {
        ArrayList<Event> list = new ArrayList<>();
        for (int i = 0; i < toConvert.size(); i++) {
            String s = toConvert.get(i);
            list.add(Converter.strToEvent(s));
        }

        return list;
    }

    public static LocalTime dateToLocalTime(Date d){
        LocalTime time = d.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
        return time;
    }

    /**
     *
     * @param s an hour
     * @param d a date
     * @return a Date object
     */
    public static Date stringToDate(String s, LocalDate d) throws ParseException {
        String str_date = CalendarUtils.formattedDate(d); // dd MMMM yyyy   s = H:m
        String fullDate = str_date+" "+s;
        fullDate=fullDate.replace(":"," ");

        SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy H m", Locale.ENGLISH);

        String dateInString = fullDate;
        return formatter.parse(dateInString);
    }

    public static int getHour(Date d){
        return d.getHours();
    }

    public static int getMinutes(Date d){
        return d.getMinutes();
    }
}
