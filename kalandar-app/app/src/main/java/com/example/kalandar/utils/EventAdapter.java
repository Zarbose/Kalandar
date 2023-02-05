package com.example.kalandar.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.kalandar.R;
import com.example.kalandar.model.Event;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class EventAdapter extends ArrayAdapter<Event>
{
    public EventAdapter(@NonNull Context context, List<Event> events)
    {
        super(context, 0, events);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        Event event = getItem(position);

        if (convertView == null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.event_cell, parent, false);

        TextView eventCellTV = convertView.findViewById(R.id.eventCellTV);

        Date end = event.getEnd();
        Converter conv = new Converter();
        LocalTime str_end = conv.dateToLocalTime(end);

        String eventTitle = event.getDesc() +" "+ CalendarUtils.formattedTime(event.getTime()).toString().replace(":","h")+" - "+CalendarUtils.formattedTime(str_end).toString().replace(":","h");
        eventCellTV.setText(eventTitle);

        return convertView;
    }
}
