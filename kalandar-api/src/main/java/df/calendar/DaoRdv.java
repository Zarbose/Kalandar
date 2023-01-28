package df.calendar;

import df.calendar.model.Event;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public enum DaoRdv {
    instance;
    private Map<String, Event> contentProvider = new HashMap<String, Event>();

    private DaoRdv() {

        try{
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy-H-m-s", Locale.FRANCE);
            String dateInString = "28-01-2023-18-30-00";
            Date start = formatter.parse(dateInString);
            dateInString = "28-01-2023-19-30-00";
            Date end = formatter.parse(dateInString);
            Event event = new Event("1","desc1",start, end);
            this.contentProvider.put(event.getId(), event);

            dateInString = "29-01-2023-18-30-00";
            start = formatter.parse(dateInString);
            dateInString = "29-01-2023-19-30-00";
            event = new Event("2","desc2",start,end);
            this.contentProvider.put(event.getId(), event);


            dateInString = "01-01-2023-18-30-00";
            start = formatter.parse(dateInString);
            dateInString = "01-01-2023-19-30-00";
            event = new Event("3","desc3", start,end);
            this.contentProvider.put(event.getId(), event);
        }catch (ParseException e){}

    }
    public Map<String, Event> getRdvs(){
        return contentProvider;
    }

}
