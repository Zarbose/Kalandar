package com.example.kalandar.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.kalandar.R;
import com.example.kalandar.model.Event;
import com.example.kalandar.utils.CalendarUtils;
import com.example.kalandar.utils.HttpRequest;
import com.example.kalandar.utils.RequestHttp;

import org.chromium.net.CronetEngine;
import org.chromium.net.UrlRequest;

import java.time.LocalTime;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class EventEditActivity extends AppCompatActivity /*implements UrlListener*/
{
    private EditText eventNameET;
    private TextView eventDateTV, eventTimeTV;

    private LocalTime time;

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
        Event newEvent = new Event(eventName, CalendarUtils.selectedDate, time);
        Event.eventsList.add(newEvent);
        // POST
    }
}
