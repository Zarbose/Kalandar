package df.calendar.model;

import com.owlike.genson.annotation.JsonProperty;

import java.util.Date;

public class Rdv {
    @JsonProperty("id")
    private String id;
    @JsonProperty("desc")
    private String desc;
    @JsonProperty("startDate")
    private Date startDate;
    @JsonProperty("endDate")
    private Date endDate;

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
