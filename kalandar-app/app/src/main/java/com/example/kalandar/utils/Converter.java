package com.example.kalandar.utils;

import android.util.Log;

import com.example.kalandar.model.Event;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Converter {
    public Event strToEvent(String s) throws JSONException, ParseException {
        JSONObject json = new JSONObject(s);

        Date date_start_str = new Date((Long) json.get("start"));
        Date date_end_str = new Date((Long) json.get("end"));

        LocalDate date = date_start_str.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalTime time = date_start_str.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();

        Event evn = new Event((String) json.get("id"), (String) json.get("desc"),date,time,date_start_str,date_end_str);

        // Log.e("Response", "Oui : " + evn.toString());
        return evn;
    }

    public ArrayList<Event> arrToEvent(ArrayList<String> toConvert) throws JSONException, ParseException {
        ArrayList<Event> list = new ArrayList<>();
        for (int i = 0; i < toConvert.size(); i++) {
            String s = toConvert.get(i);
            list.add(this.strToEvent(s));
        }

        return list;
    }
}
