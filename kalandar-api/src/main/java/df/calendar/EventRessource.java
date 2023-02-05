package df.calendar;

import com.owlike.genson.Genson;
import df.calendar.model.Event;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.Objects;

/** Represents the management of an event
 * @author Simon and Tanguy
 */
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
    @Produces(MediaType.APPLICATION_JSON)
    public String getRdv() {
        Genson genson = new Genson();

        Event event = DaoRdv.instance.getRdvs().get(id);

        String json = genson.serialize(event);
        return json;
    }

    @DELETE
    public Response deleteRdv() {
        System.out.println("DELETE");
        Event event = DaoRdv.instance.getRdvs().remove(id);
        if (event != null)
            return Response.status(Response.Status.OK).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putRdvJson(@PathParam("id") String id, final Event event) {
        System.out.println("PUT");
        if (!Objects.equals(id, event.getId()))
            return Response.status(Response.Status.NOT_FOUND).build();
        if(DaoRdv.instance.getRdvs().containsKey(event.getId())) {
            DaoRdv.instance.getRdvs().put(id, event);
            return Response.status(Response.Status.OK).build();
        } else
            return Response.status(Response.Status.NOT_FOUND).build();
    }
}
