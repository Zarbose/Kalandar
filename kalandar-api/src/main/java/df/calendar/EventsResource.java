package df.calendar;


import com.owlike.genson.Genson;
import df.calendar.model.Event;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Path("/rdv")
public class EventsResource {
    @Context
    UriInfo uriInfo;

    @Context
    Request request;


    @GET
    @Produces(MediaType.TEXT_XML)
    public List<Event> getRdvListe() {
        List<Event> tousEvent = new ArrayList<Event>();
        tousEvent.addAll(DaoRdv.instance.getRdvs().values());
        return tousEvent;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getRdvList2() {
        StringBuffer result = new StringBuffer("<html><body><h1>Rdv</h1><ul>");
        String loc = uriInfo.getAbsolutePath().toString();
        for (Event r : DaoRdv.instance.getRdvs().values()) {
            result.append("<li><a href=\""+loc+ "/"+ r.getId()+"\">"
                    +loc+ "/"+ r.getId()+"</a></li>");
        }
        return result.toString()+"</ul></body></html>";
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public String getRdvJson() {

        Genson genson = new Genson();

        List<Event> allEvent = new ArrayList<Event>();
        allEvent.addAll(DaoRdv.instance.getRdvs().values());

        String json = genson.serialize(allEvent);
        return json;
    }

    @POST
    @Produces(MediaType.TEXT_XML)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String newRdv(@FormParam("desc") String desc) throws IOException {
        String pattern = "ddMMYYYYHmsS";
        SimpleDateFormat formatDate = new SimpleDateFormat(pattern);
        String id = formatDate.format(new Date());


        Event event = new Event(id,desc,new Date(), new Date());
        DaoRdv.instance.getRdvs().put(event.getId(), event);
        return "<?xml version=\"1.0\"?>" + "<links> <href>" + uriInfo.getAbsolutePath() + "/"+ id + " </href> </links>";
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newRdv2(String data){
        Genson genson = new Genson();
        Event event = genson.deserialize(data, Event.class);

        if(DaoRdv.instance.getRdvs().containsKey(event.getId())) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            DaoRdv.instance.getRdvs().put(event.getId(), event);
            return Response.status(Response.Status.OK).build();
        }
    }


    @Path("{id}")
    public EventRessource getRdv(@PathParam("id") String id) {
        return new EventRessource(uriInfo, request, id);
    }

}
