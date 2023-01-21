package df.calendar.model;

import com.owlike.genson.annotation.JsonProperty;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;
@XmlRootElement
public class Rdv {
    @XmlElement(name = "id")
    @JsonProperty("id")
    private String id;
    @XmlElement(name = "desc")
    @JsonProperty("desc")
    private String desc;
    @XmlElement(name = "startDate")
    @JsonProperty("startDate")
    private Date startDate;
    @XmlElement(name = "endDate")
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

    public String toString(){
        return "id = " + this.id + " desc = " + this.desc;
    }
}
