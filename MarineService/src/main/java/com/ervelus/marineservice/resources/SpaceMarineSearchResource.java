package com.ervelus.marineservice.resources;

import com.ervelus.marineservice.service.SpaceMarineSearchService;
import ru.egormit.library.SpaceMarineFilterRequest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/spacemarines/search")
public class SpaceMarineSearchResource {
    @Inject
    private SpaceMarineSearchService searchService;

    @POST
    @Consumes("text/json")
    @Produces("text/json")
    public Response filterSpaceMarine(SpaceMarineFilterRequest request) {
        return Response.ok().entity(searchService.findAllSpaceMarineByFilter(request)).build();
    }

    @POST
    @Path("/name")
    @Produces("text/json")
    public Response getSpaceMarinesByName(SpaceMarineFilterRequest request) {
        return Response.ok().entity(searchService.findAllSpaceMarineByName(request)).build();
    }

    @POST
    @Path("/health/greater")
    @Produces("text/json")
    public Response getSpaceMarinesWithHealthGreaterThan(SpaceMarineFilterRequest request) {
        return Response.ok().entity(searchService.findAllSpaceMarineWithHealthGreaterThan(request)).build();
    }
}
