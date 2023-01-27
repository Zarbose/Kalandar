package com.example.kalandar.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.time.LocalDate;
import java.util.ArrayList;

import static com.example.kalandar.utils.CalendarUtils.daysInMonthArray;
import static com.example.kalandar.utils.CalendarUtils.monthYearFromDate;

import com.example.kalandar.model.Event;
import com.example.kalandar.utils.CalendarAdapter;
import com.example.kalandar.utils.CalendarUtils;
import com.example.kalandar.R;
import com.example.kalandar.utils.RequestsHttp;
import com.example.kalandar.utils.RequestsListener;
import com.example.kalandar.utils.ThreadManager;

public class MainActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener, RequestsListener
{
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;

    private ArrayList<Event> eventsList = new ArrayList<>();
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
        CalendarUtils.selectedDate = LocalDate.now();
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
        startActivity(new Intent(this, WeekViewActivity.class));
    }

    @Override
    public void updateAllEvents() {
        String data = this.th.getResultData();
        Log.e("Response", "" + data);
    }
}