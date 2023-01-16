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
import java.util.Date;
import java.util.List;

@Path("/bonjour")
public class RdvResource {
    @Context
    UriInfo uriInfo;

    @Context
    Request request;

    @Path("/rdv/get")
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Rdv> getRdv() {
        List<Rdv> allRdv = new ArrayList<Rdv>();
        allRdv.add(new Rdv(1,"title","desc",new Date(),new Date()));
        return allRdv;
    }


}
