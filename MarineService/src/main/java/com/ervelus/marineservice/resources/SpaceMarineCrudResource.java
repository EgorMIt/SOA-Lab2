package com.ervelus.marineservice.resources;

import com.ervelus.marineservice.service.SpaceMarineCrudService;
import ru.egormit.library.PageDto;
import ru.egormit.library.SpaceMarineCreateRequest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/spacemarines")
public class SpaceMarineCrudResource {
    @Inject
    private SpaceMarineCrudService crudService;

    @GET
    @Consumes("text/json")
    @Produces("text/json")
    public Response getAllSpaceMarines(PageDto pageDto) {
        return Response.ok().entity(crudService.getAllSpaceMarines(pageDto)).build();
    }

    @GET
    @Path("/{id}")
    @Produces("text/json")
    public Response getSpaceMarine(@PathParam("id") Long id) {
        return Response.ok().entity(crudService.getSpaceMarineById(id)).build();
    }

    @POST
    @Consumes("text/json")
    @Produces("text/json")
    public Response createSpaceMarine(SpaceMarineCreateRequest request) {
        crudService.createSpaceMarine(request);
        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    @Produces("text/json")
    public Response deleteSpaceMarine(@PathParam("id") Long id) {
        crudService.deleteSpaceMarine(id);
        return Response.ok().build();
    }

    @PUT
    @Path("/{id}")
    @Produces("text/json")
    public Response updateSpaceMarine(@PathParam("id") Long id, SpaceMarineCreateRequest request) {
        crudService.updateSpaceMarine(id, request);
        return Response.ok().build();
    }
}