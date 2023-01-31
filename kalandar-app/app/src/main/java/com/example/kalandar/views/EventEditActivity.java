package com.example.kalandar.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kalandar.R;
import com.example.kalandar.model.Event;
import com.example.kalandar.model.EventTransiant;
import com.example.kalandar.utils.CalendarUtils;
import com.example.kalandar.utils.RequestsHttp;
import com.example.kalandar.utils.ThreadManager;
import com.owlike.genson.Genson;

import java.time.LocalTime;
import java.util.Date;

public class EventEditActivity extends AppCompatActivity
{
    private EditText eventNameET;
    private TextView eventDateTV, eventTimeTV;

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
        this.eventTimeTV.setText("Time: " + CalendarUtils.formattedTime(time));
    }

    private void initWidgets()
    {
        this.eventNameET = findViewById(R.id.eventNameET);
        this.eventDateTV = findViewById(R.id.eventDateTV);
        this.eventTimeTV = findViewById(R.id.eventTimeTV);
    }

    public void saveEventAction(View view) {
        String eventName = eventNameET.getText().toString();
        Event newEvent = new Event(eventName, CalendarUtils.selectedDate, time,new Date(),new Date());
        Event.eventsList.add(newEvent);

        EventTransiant evn = new EventTransiant(newEvent.getId(),newEvent.getDesc(),newEvent.getStart(),newEvent.getEnd());

        // Post
        Genson genson = new Genson();
        String json = genson.serialize(evn);
        RequestsHttp request = new RequestsHttp("POST",json);
        this.th = new ThreadManager(request);

        finish();
    }
}