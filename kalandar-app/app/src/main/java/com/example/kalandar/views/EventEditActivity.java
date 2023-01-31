package com.example.kalandar.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.kalandar.R;
import com.example.kalandar.model.Event;
import com.example.kalandar.model.EventTransiant;
import com.example.kalandar.utils.CalendarUtils;
import com.example.kalandar.utils.Converter;
import com.example.kalandar.requests.ThreadManager;

import java.text.ParseException;
import java.time.LocalTime;
import java.util.Date;

public class EventEditActivity extends AppCompatActivity
{
    private EditText eventNameET;
    private TextView eventDateTV, eventTimeTV;
    private TimePicker eventStart, eventEnd;

    private LocalTime time;
    private ThreadManager th;

    // private WeekViewActivity view;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_event_edit);
        initWidgets();
        this.time = LocalTime.now();
        eventDateTV.setText("Date: " + CalendarUtils.formattedDate(CalendarUtils.selectedDate));
        // this.eventTimeTV.setText("Time: " + CalendarUtils.formattedTime(time));
    }

    private void initWidgets()
    {
        this.eventNameET = findViewById(R.id.eventNameET);
        this.eventDateTV = findViewById(R.id.eventDateTV);
        // this.eventTimeTV = findViewById(R.id.eventTimeTV);
        this.eventStart = findViewById(R.id.timePickerStart);
        this.eventEnd = findViewById(R.id.timePickerEnd);

        this.eventStart.setIs24HourView(true);
        this.eventEnd.setIs24HourView(true);
    }

    public void saveEventAction(View view) throws ParseException {
        String eventName = eventNameET.getText().toString();
        String eventStart = this.eventStart.getHour()+":"+this.eventStart.getMinute();
        String eventEnd = this.eventEnd.getHour()+":"+this.eventEnd.getMinute();

        Converter conv = new Converter();
        Date start = conv.stringToDate(eventStart,CalendarUtils.selectedDate);
        Date end = conv.stringToDate(eventEnd,CalendarUtils.selectedDate);
        // Log.e("Response", "eventStart : " + start);
        // Log.e("Response", "eventEnd : " + end);

        Event newEvent = new Event(eventName, CalendarUtils.selectedDate, time,start,end);
        Event.eventsList.add(newEvent);

        // Post
        EventTransiant evn = new EventTransiant(newEvent.getId(),newEvent.getDesc(),newEvent.getStart(),newEvent.getEnd());
        evn.sendPOST();

        finish();
    }
}