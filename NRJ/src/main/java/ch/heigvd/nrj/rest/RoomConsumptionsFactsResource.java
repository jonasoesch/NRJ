package ch.heigvd.nrj.rest;

import ch.heigvd.nrj.exceptions.EntityNotFoundException;
import ch.heigvd.nrj.model.RoomConsumptionFact;
import ch.heigvd.nrj.services.crud.RoomConsumptionsFactsManagerLocal;
import ch.heigvd.nrj.services.to.RoomConsumptionsFactsTOServiceLocal;
import ch.heigvd.nrj.to.PublicRoomConsumptionFactsTO;
import java.net.URI;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * This is the REST API endpoint for the RoomConsumptionFact resource. When REST
 * clients send HTTP requests, they will be routed to this class (because of the
 *
 * @Path annotation). They will then be routed to the appropriate methods
 * (because of the
 * @GET,
 * @POST and other annotations).
 *
 * @author Olivier Liechti
 */
@Stateless
@Path("roomConsumptionsFacts")
public class RoomConsumptionsFactsResource {

    @Context
    private UriInfo context;
    @EJB
    RoomConsumptionsFactsManagerLocal roomConsumptionsFactsManager;
    @EJB
    RoomConsumptionsFactsTOServiceLocal roomConsumptionsFactsTOService;

    /**
     * Creates a new instance of RoomConsumptionsFactsResource
     */
    public RoomConsumptionsFactsResource() {
    }

    /**
     * Creates a new RoomConsumptionFacts resource from the provided
     * representation
     *
     * @return an instance of PublicRoomConsumptionFactsTO
     */
    @POST
    @Consumes({"application/json"})
    public Response createResource(PublicRoomConsumptionFactsTO newConsumptionTO) { // roomConsFact
        RoomConsumptionFact newConsumption = new RoomConsumptionFact();
        roomConsumptionsFactsTOService.updateRoomConsumptionFactEntity(newConsumption, newConsumptionTO);
        long newConsumptionId = roomConsumptionsFactsManager.create(newConsumption);
        URI createdURI = context.getAbsolutePathBuilder().path(Long.toString(newConsumptionId)).build();
        return Response.created(createdURI).build();
    }

    /**
     * Retrieves a representation of a list of RoomConsumptionFact
     *
     * @return a List of PublicRoomConsumptionFactsTO
     */
    @GET
    @Produces({"application/json"})

    public List<PublicRoomConsumptionFactsTO> getResourceList() {
        List<RoomConsumptionFact> consumptions = roomConsumptionsFactsManager.findAll();
        List<PublicRoomConsumptionFactsTO> result = new LinkedList<>();
        for (RoomConsumptionFact consumption : consumptions) {
            result.add(roomConsumptionsFactsTOService.buildPublicRoomConsumptionFactTO(consumption));
        }
        return result;
    }

    /**
     * Retrieves a representation of a RoomConsumptionFact
     *
     * @param id this id of the RoomConsumptionFacts
     * @return an instance of PublicRoomConsumptionFactsTO
     * @throws ch.heigvd.skeleton.exceptions.EntityNotFoundException
     */
    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public PublicRoomConsumptionFactsTO getResource(@PathParam("id") long id) throws EntityNotFoundException {
        RoomConsumptionFact consumption = roomConsumptionsFactsManager.findById(id);
        PublicRoomConsumptionFactsTO consumptionTO = roomConsumptionsFactsTOService.buildPublicRoomConsumptionFactTO(consumption);
        return consumptionTO;
    }

    /**
     * Updates a RoomConsumptionFact resource
     *
     * @param id this id of the RoomConsumptionFact
     * @param updatedRoomConsumptionFactTO a TO containing the
     * RoomConsumptionFact data
     * @return an instance of PublicRoomConsumptionFactTO
     * @throws ch.heigvd.skeleton.exceptions.EntityNotFoundException
     */
    @PUT
    @Path("{id}")
    @Consumes({"application/json"})
    public Response Resource(PublicRoomConsumptionFactsTO updatedRoomConsumptionFactTO, @PathParam("id") long id) throws EntityNotFoundException {
        RoomConsumptionFact roomConsumptionFactToUpdate = roomConsumptionsFactsManager.findById(id);
        roomConsumptionsFactsTOService.updateRoomConsumptionFactEntity(roomConsumptionFactToUpdate, updatedRoomConsumptionFactTO);
        roomConsumptionsFactsManager.update(roomConsumptionFactToUpdate);
        return Response.ok().build();
    }

    /**
     * Deletes an RoomConsumptionFact resource
     *
     * @param id this id of the roomConsumptionFact
     * @return an instance of PublicRoomConsumptionFactTO
     * @throws ch.heigvd.skeleton.exceptions.EntityNotFoundException
     */
    @DELETE
    @Path("{id}")
    public Response deleteResource(@PathParam("id") long id) throws EntityNotFoundException {
        roomConsumptionsFactsManager.delete(id);
        return Response.ok().build();
    }
}
