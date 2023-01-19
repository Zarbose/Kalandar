package df.calendar;

import df.calendar.model.Rdv;

import java.util.ArrayList;
import java.util.Date;

public enum DaoRdv {
    instance;
    private ArrayList<Rdv> contentProvider = new ArrayList<>();

    private DaoRdv() {
        Rdv rdv = new Rdv(1,"title1","desc1",new Date(),new Date());
        contentProvider.add(rdv);
        rdv = new Rdv(2,"title2","desc2",new Date(),new Date());
        contentProvider.add(rdv);
        rdv = new Rdv(3,"title3","desc3",new Date(),new Date());
        contentProvider.add(rdv);
    }
    public ArrayList<Rdv> getRdv(){
        return contentProvider;
    }

}
