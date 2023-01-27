package com.example.kalandar.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kalandar.R;
import com.example.kalandar.model.Event;
import com.example.kalandar.utils.CalendarUtils;

import java.time.LocalTime;
import java.util.Date;

public class EventEditActivity extends AppCompatActivity
{
    private EditText eventNameET;
    private TextView eventDateTV, eventTimeTV;

    private LocalTime time;
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
        // Post

        newEvent.getAll();
        finish();
    }
}