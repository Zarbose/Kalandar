package df.calendar.model;

import com.owlike.genson.annotation.JsonProperty;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Event {
    @JsonProperty("id")
    private String id;

    @JsonProperty("desc")
    private String desc;

    @JsonProperty("date")
    private Date date;

    @JsonProperty("end")
    private Date end;


    public Event() {
        super();
        this.id=null;
        this.desc=null;
        this.date=null;
        this.end=null;
    }
    public Event(String id, String desc, Date date, Date end) {
        this.id=id;
        this.desc=desc;
        this.date=date;
        this.end=end;
    }

    public String getId() {
        return id;
    }
    public String getDesc() {
        return desc;
    }
    public Date getDate() {
        return date;
    }

    public Date getEnd(){
        return end;
    }

    public String toString(){
        String patternDate = "dd/MM/YYYY";
        String patternTime = "H:m:s";
        SimpleDateFormat formatDate = new SimpleDateFormat(patternDate);
        String str_date = formatDate.format(this.date);

        SimpleDateFormat formatTime = new SimpleDateFormat(patternTime);
        String str_time = formatTime.format(this.date);

        return "id = " + this.id + " desc = " + this.desc + " date = "+str_date+" time = "+str_time;
    }
}
