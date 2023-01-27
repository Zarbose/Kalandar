package df.calendar;

import df.calendar.model.Event;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public enum DaoRdv {
    instance;
    private Map<String, Event> contentProvider = new HashMap<String, Event>();

    private DaoRdv() {
        Event event = new Event("1","desc1",new Date(), new Date());
        this.contentProvider.put(event.getId(), event);
        event = new Event("2","desc2",new Date(),new Date());
        this.contentProvider.put(event.getId(), event);
        event = new Event("3","desc3", new Date(),new Date());
        this.contentProvider.put(event.getId(), event);
    }
    public Map<String, Event> getRdvs(){
        return contentProvider;
    }

}
