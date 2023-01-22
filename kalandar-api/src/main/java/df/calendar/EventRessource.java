package df.calendar;

import df.calendar.model.Event;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.Objects;

public class EventRessource {
    UriInfo uriInfo;
    Request request;
    String id;

    public EventRessource(UriInfo uriInfo, Request request, String id) {
        super();
        this.uriInfo = uriInfo;
        this.request = request;
        this.id = id;
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Event getRdv() {
        Event event = DaoRdv.instance.getRdvs().get(id);
        return event;
    }

    @DELETE
    public Response deleteRdv() {
        Event event = DaoRdv.instance.getRdvs().remove(id);
        if (event != null)
            return Response.status(Response.Status.OK).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putRdvJson(@PathParam("id") String id, final Event event) {

        if (!Objects.equals(id, event.getId()))
            return Response.status(Response.Status.NOT_FOUND).build();
        if(DaoRdv.instance.getRdvs().containsKey(event.getId())) {
            DaoRdv.instance.getRdvs().put(id, event);
            return Response.status(Response.Status.OK).build();
        } else
            return Response.status(Response.Status.NOT_FOUND).build();
    }
}
