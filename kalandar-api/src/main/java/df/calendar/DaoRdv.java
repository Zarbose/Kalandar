package df.calendar;

import df.calendar.model.Rdv;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public enum DaoRdv {
    instance;
    // private ArrayList<Rdv> contentProvider = new ArrayList<>();
    private Map<String, Rdv> contentProvider = new HashMap<String, Rdv>();

    private DaoRdv() {
        Rdv rdv = new Rdv("1","desc1",new Date(),new Date());
        contentProvider.put(rdv.getId(), rdv);
        rdv = new Rdv("2","desc2",new Date(),new Date());
        contentProvider.put(rdv.getId(), rdv);
        rdv = new Rdv("3","desc3",new Date(),new Date());
        contentProvider.put(rdv.getId(), rdv);
    }
    public Map<String, Rdv> getRdvs(){
        return contentProvider;
    }

}
