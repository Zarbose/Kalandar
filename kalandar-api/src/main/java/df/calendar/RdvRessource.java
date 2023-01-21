package df.calendar;

import df.calendar.model.Rdv;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.Objects;

public class RdvRessource {
    UriInfo uriInfo;
    Request request;
    String id;

    public RdvRessource(UriInfo uriInfo, Request request, String id) {
        super();
        this.uriInfo = uriInfo;
        this.request = request;
        this.id = id;
    }

    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Rdv getRdv() {
        Rdv rdv = DaoRdv.instance.getRdvs().get(id);
        return rdv;
    }

    @DELETE
    public Response deleteRdv() {
        Rdv rdv = DaoRdv.instance.getRdvs().remove(id);
        if (rdv != null)
            return Response.status(Response.Status.OK).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response putRdvJson(@PathParam("id") String id, final Rdv rdv) {

        if (!Objects.equals(id, rdv.getId()))
            return Response.status(Response.Status.NOT_FOUND).build();
        if(DaoRdv.instance.getRdvs().containsKey(rdv.getId())) {
            DaoRdv.instance.getRdvs().put(id, rdv);
            return Response.status(Response.Status.OK).build();
        } else
            return Response.status(Response.Status.NOT_FOUND).build();
    }
}
