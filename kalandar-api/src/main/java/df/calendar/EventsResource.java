package df.calendar;


import com.owlike.genson.Genson;
import df.calendar.model.Event;
import df.calendar.utils.RequestHttp;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.ArrayList;
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
    @Produces(MediaType.APPLICATION_JSON)
    public String getRdvJson() {
        String json=new String("");
        try{
            Genson genson = new Genson();

            List<Event> allEvent = new ArrayList<Event>();
            allEvent.addAll(DaoRdv.instance.getRdvs().values());

            json = genson.serialize(allEvent);
        }
        catch (Exception e){ }

        try{
            System.out.println("Test 1");
            RequestHttp request = new RequestHttp();
            System.out.println("Test 2");
            Event evn = DaoRdv.instance.getRdvs().get("1");
            Genson genson = new Genson();
            String tmp_json = genson.serialize(evn);
            request.postRequest(tmp_json);

            System.out.println("Test Fin");
        }
        catch (IOException e){
            System.out.println(e);
        }

        return json;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response newRdv2(String data){
        Genson genson = new Genson();

        try{
            Event event = genson.deserialize(data, Event.class);

            if(DaoRdv.instance.getRdvs().containsKey(event.getId())) {
                return Response.status(Response.Status.NOT_FOUND).build();
            } else {
                DaoRdv.instance.getRdvs().put(event.getId(), event);
                return Response.status(Response.Status.OK).build();
            }
        }
        catch (Exception e) { }
        return Response.status(Response.Status.NOT_FOUND).build();
    }


    @Path("{id}")
    public EventRessource getRdv(@PathParam("id") String id) {
        return new EventRessource(uriInfo, request, id);
    }

}
