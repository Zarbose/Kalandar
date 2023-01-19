package df.calendar;


import df.calendar.model.Rdv;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.List;

@Path("/rdv")
public class RdvsResource {
    @Context
    UriInfo uriInfo;

    @Context
    Request request;


    @GET
    @Produces(MediaType.TEXT_XML)
    public List<Rdv> getRdvListe() {
        List<Rdv> tousRdv = new ArrayList<Rdv>();
        tousRdv.addAll(DaoRdv.instance.getRdv());
        return tousRdv;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getRdvList2() {
        StringBuffer result = new StringBuffer("<html><body><h1>Rdv</h1><ul>");
        String loc = uriInfo.getAbsolutePath().toString();
        for (Rdv r : DaoRdv.instance.getRdv()) {
            result.append("<li><a href=\""+loc+ "/"+ r.getId()+"\">"
                    +loc+ "/"+ r.getId()+"</a></li>");
        }
        return result.toString()+"</ul></body></html>";
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Rdv> getRdv() {
        List<Rdv> allRdv = new ArrayList<Rdv>();
        allRdv.addAll(DaoRdv.instance.getRdv());
        return allRdv;
    }
}
