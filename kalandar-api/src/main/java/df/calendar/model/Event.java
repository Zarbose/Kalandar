package df.calendar.model;

import com.owlike.genson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Event {
    @JsonProperty("id")
    private String id;

    @JsonProperty("desc")
    private String desc;

    @JsonProperty("date")
    private Date date;

    @JsonProperty("time")
    private Date time;

    public Event() {
        super();
        this.id=null;
        this.desc=null;
        this.date=null;
        this.time=null;
    }
    public Event(String id, String desc, Date date, Date time) {
        this.id=id;
        this.desc=desc;
        this.date=date;
        this.time=time;
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
    public Date getTime() {
        return time;
    }

    public String toString(){
        String patternDate = "dd/MM/YYYY";
        String patternTime = "H:m:s";
        SimpleDateFormat formatDate = new SimpleDateFormat(patternDate);
        String str_date = formatDate.format(this.date);

        SimpleDateFormat formatTime = new SimpleDateFormat(patternTime);
        String str_time = formatTime.format(this.time);

        return "id = " + this.id + " desc = " + this.desc + " date = "+str_date+" time = "+str_time;
    }
}
