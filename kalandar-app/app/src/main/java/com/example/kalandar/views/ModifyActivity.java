package com.example.kalandar.views;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.example.kalandar.R;
import com.example.kalandar.model.Event;
import com.example.kalandar.model.EventTransiant;
import com.example.kalandar.utils.CalendarUtils;
import com.example.kalandar.utils.Converter;

import org.json.JSONException;

import java.text.ParseException;
import java.time.LocalTime;
import java.util.Date;

public class ModifyActivity extends AppCompatActivity {
    private EditText eventNameET;
    private TextView eventDateTV, eventTimeTV;
    private TimePicker eventStart, eventEnd;
    private Event evn;

    private LocalTime time;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_modify);
        initWidgets();

        Intent intention = getIntent();
        String eventString = (String) intention.getExtras().get("event");
        // Log.e("Response", "eventEnd : " + eventString);
        try {
            this.evn=Converter.strToEvent(eventString);
        } catch (JSONException | ParseException e) {
            e.printStackTrace();
        }
        // savedInstanceState.ge
        this.eventNameET.setText(this.evn.getDesc());

        this.eventStart.setHour(Converter.getHour(this.evn.getStart()));
        this.eventStart.setMinute(Converter.getMinutes(this.evn.getStart()));

        this.eventEnd.setHour(Converter.getHour(this.evn.getEnd()));
        this.eventEnd.setMinute(Converter.getMinutes(this.evn.getEnd()));

        this.time = this.evn.getTime();
        eventDateTV.setText("Date: " + CalendarUtils.formattedDate(CalendarUtils.selectedDate));
    }

    private void initWidgets()
    {
        this.eventNameET = findViewById(R.id.eventNameET);
        this.eventDateTV = findViewById(R.id.eventDateTV);
        this.eventStart = findViewById(R.id.timePickerStart);
        this.eventEnd = findViewById(R.id.timePickerEnd);

        this.eventStart.setIs24HourView(true);
        this.eventEnd.setIs24HourView(true);
    }

    public void saveEventAction(View view) throws ParseException {
        String eventName = eventNameET.getText().toString();
        String eventStart = this.eventStart.getHour()+":"+this.eventStart.getMinute();
        String eventEnd = this.eventEnd.getHour()+":"+this.eventEnd.getMinute();

        Date start = Converter.stringToDate(eventStart,CalendarUtils.selectedDate);
        Date end = Converter.stringToDate(eventEnd,CalendarUtils.selectedDate);

        Event newEvent = new Event(this.evn.getId(),eventName, CalendarUtils.selectedDate,this.time,start,end);
        // Log.e("Response", "EVENT " + newEvent.toString());

        for (int i = 0; i < Event.eventsList.size(); i++) {
            Event tmp = Event.eventsList.get(i);
            if (tmp.getId().equals(newEvent.getId())){
                Event.eventsList.set(i,newEvent);
                Log.e("Response", "EVENT " + Event.eventsList.get(i).toString());
                break;
            }
        }

        // PUT
        EventTransiant evn = new EventTransiant(newEvent.getId(),newEvent.getDesc(),newEvent.getStart(),newEvent.getEnd());
        evn.sendPUT();

        finish();
    }

}
