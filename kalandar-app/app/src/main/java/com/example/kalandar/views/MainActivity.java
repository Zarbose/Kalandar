package com.example.kalandar.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.Reader;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.kalandar.utils.CalendarUtils.daysInMonthArray;
import static com.example.kalandar.utils.CalendarUtils.monthYearFromDate;

import com.example.kalandar.model.Event;
import com.example.kalandar.model.EventTransiant;
import com.example.kalandar.utils.CalendarAdapter;
import com.example.kalandar.utils.CalendarUtils;
import com.example.kalandar.R;
import com.example.kalandar.utils.RequestsHttp;
import com.example.kalandar.utils.RequestsListener;
import com.example.kalandar.utils.ThreadManager;
import com.owlike.genson.Genson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener, RequestsListener
{
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;

    private ArrayList<String> eventsList = new ArrayList<String>();
    private ThreadManager th;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // GET ALL
        RequestsHttp request = new RequestsHttp("GET_ALL");
        this.th = new ThreadManager(request,this);

        setContentView(R.layout.activity_main);
        initWidgets();
        CalendarUtils.selectedDate = LocalDate.now(); // ICI
        setMonthView();
    }

    private void initWidgets()
    {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.monthYearTV);
    }

    private void setMonthView()
    {
        monthYearText.setText(monthYearFromDate(CalendarUtils.selectedDate));
        ArrayList<LocalDate> daysInMonth = daysInMonthArray(CalendarUtils.selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(daysInMonth, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
    }

    public void previousMonthAction(View view)
    {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusMonths(1);
        setMonthView();
    }

    public void nextMonthAction(View view)
    {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusMonths(1);
        setMonthView();
    }

    @Override
    public void onItemClick(int position, LocalDate date)
    {
        if(date != null)
        {
            CalendarUtils.selectedDate = date;
            setMonthView();
        }
    }

    public void weeklyAction(View view)
    {
        Intent intent = new Intent(this, WeekViewActivity.class);
        // intent.putParcelableArrayListExtra("events",this.eventsList);
        intent.putStringArrayListExtra("events",this.eventsList);
        startActivity(intent);
    }

    @Override
    public void updateAllEvents() {
        String data = this.th.getResultData();

        Genson genson = new Genson();
        ArrayList<Object> listEvents = genson.deserialize(data, ArrayList.class);

        for (int i = 0; i < listEvents.size(); i++) {
            HashMap<String,Object> elm = (HashMap<String, Object>) listEvents.get(i);

            Date date_start_str = new Date((long) elm.get("start"));
            Date date_end_str = new Date((long) elm.get("end"));

            LocalDate date = date_start_str.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalTime time = date_start_str.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();

            Event evn =  new Event((String) elm.get("id"),(String) elm.get("desc"),date,time,date_start_str,date_end_str);
            // eventsList.add(evn.toString());

            Event.eventsList.add(evn);
        }
        // Log.e("Response", "" + eventsList.get(0).toString());
    }

}