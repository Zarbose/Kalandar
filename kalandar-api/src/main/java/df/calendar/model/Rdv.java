package df.calendar.model;

import java.util.Date;

public class Rdv {
    private Integer id;
    private String title, desc;
    private Date startDate, endDate;

    public Rdv(Integer id, String title, String desc, Date startDate, Date endDate) {
        this.id=id;
        this.title=title;
        this.desc=desc;
        this.startDate=startDate;
        this.endDate=endDate;
    }

    public Integer getId() {
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
