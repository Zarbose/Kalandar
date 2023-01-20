package df.calendar.model;

import java.util.Date;

public class Rdv {
    private String id;
    private String desc;
    private Date startDate, endDate;

    public Rdv() {
        super();
        this.id=null;
        this.desc=null;
        this.startDate=null;
        this.endDate=null;
    }
    public Rdv(String id, String desc, Date startDate, Date endDate) {
        this.id=id;
        this.desc=desc;
        this.startDate=startDate;
        this.endDate=endDate;
    }

    public String getId() {
        return id;
    }
    public String getDesc() {
        return desc;
    }
    public Date getStartDate() {
        return startDate;
    }
    public Date getEndDate() {
        return endDate;
    }

    public void printRdv(){
        System.out.println("id = "+this.id+" desc = "+this.desc);
    }
}
