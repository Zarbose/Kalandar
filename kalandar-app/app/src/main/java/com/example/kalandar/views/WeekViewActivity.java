package com.example.kalandar.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.time.LocalDate;
import java.util.ArrayList;

import static com.example.kalandar.utils.CalendarUtils.daysInWeekArray;
import static com.example.kalandar.utils.CalendarUtils.monthYearFromDate;

import com.example.kalandar.model.EventTransient;
import com.example.kalandar.requests.RequestsListener;
import com.example.kalandar.utils.CalendarAdapter;
import com.example.kalandar.utils.CalendarUtils;
import com.example.kalandar.model.Event;
import com.example.kalandar.R;
import com.example.kalandar.utils.EventAdapter;

/** Represents the week view
 * @author Simon and Tanguy
 */
public class WeekViewActivity extends AppCompatActivity implements CalendarAdapter.OnItemListener, RequestsListener
{
    private TextView monthYearText;
    private RecyclerView calendarRecyclerView;
    private ListView eventListView;
    private Event toDelete = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_week_view);
        initWidgets();
        setWeekView();
        initListener();
    }

    private void initWidgets()
    {
        calendarRecyclerView = findViewById(R.id.calendarRecyclerView);
        monthYearText = findViewById(R.id.monthYearTV);
        eventListView = findViewById(R.id.eventListView);
    }

    private void initListener(){
        eventListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Event evn = (Event) parent.getAdapter().getItem(position);

                PopupMenu popup = new PopupMenu(WeekViewActivity.this, view);
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        String title = item.getTitle().toString();
                        EventTransient evnT = evn.toEventTransient();
                        if (title.equals("Modify")){
                            Intent intent = new Intent(WeekViewActivity.this, ModifyActivity.class);
                            intent.putExtra("event",evn.toString());
                            startActivity(intent);
                        }
                        else if (title.equals("Delete")){
                            WeekViewActivity.this.toDelete = evn;
                            evnT.sendDELETE(WeekViewActivity.this);
                        }

                        return true;
                    }
                });
                popup.show();
                return true;
            }
        });
    }

    private void setWeekView()
    {
        monthYearText.setText(monthYearFromDate(CalendarUtils.selectedDate));
        ArrayList<LocalDate> days = daysInWeekArray(CalendarUtils.selectedDate);

        CalendarAdapter calendarAdapter = new CalendarAdapter(days, this);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarAdapter);
        setEventAdpater();
    }


    public void previousWeekAction(View view)
    {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusWeeks(1);
        setWeekView();
    }

    public void nextWeekAction(View view)
    {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusWeeks(1);
        setWeekView();
    }

    @Override
    public void onItemClick(int position, LocalDate date)
    {
        CalendarUtils.selectedDate = date;
        setWeekView();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        setEventAdpater();
        setWeekView();
    }

    private void setEventAdpater()
    {
        ArrayList<Event> dailyEvents = Event.eventsForDate(CalendarUtils.selectedDate);
        EventAdapter eventAdapter = new EventAdapter(getApplicationContext(), dailyEvents);
        eventListView.setAdapter(eventAdapter);
    }

    public void newEventAction(View view)
    {
        Intent intent = new Intent(this, EventEditActivity.class);
        startActivity(intent);
    }

    @Override
    public void updateMain() { }

    @Override
    public void updateWeek() {
        Event.eventsList.remove(this.toDelete);
        setWeekView();
    }
}
