package df.calendar.model;

import java.util.Date;

public class Rdv {
    private String id;
    private String title, desc;
    private Date startDate, endDate;

    public Rdv() {
        super();
        this.id=null;
        this.title=null;
        this.desc=null;
        this.startDate=null;
        this.endDate=null;
    }
    public Rdv(String id, String title, String desc, Date startDate, Date endDate) {
        this.id=id;
        this.title=title;
        this.desc=desc;
        this.startDate=startDate;
        this.endDate=endDate;
    }

    public String getId() {
        return id;
    }
    public String getTitle() {
        return title;
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
}
