package ch.heigvd.nrj.rest;

import ch.heigvd.nrj.exceptions.EntityNotFoundException;
import ch.heigvd.nrj.model.ApartmentConsumptionFact;
import ch.heigvd.nrj.services.crud.ApartmentConsumptionsFactsManagerLocal;
import ch.heigvd.nrj.services.to.ApartmentConsumptionsFactsTOServiceLocal;
import ch.heigvd.nrj.to.PublicApartmentConsumptionFactsTO;
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
 * This is the REST API endpoint for the Rooms resource. When REST clients
 * send HTTP requests, they will be routed to this class (because of the
 *
 * @Path annotation). They will then be routed to the appropriate methods
 * (because of the
 * @GET,
 * @POST and other annotations).
 *
 * This class is a stateless session bean, which allows us to inject other
 * stateless session beans with annotations. That is practical, because when we
 * receive requests from REST clients, we can delegate most of the work to DAOs
 * and Transfer Object services.
 *
 * @author Olivier Liechti ======= import ch.heigvd.nrj.model.Room; import
 * javax.ejb.Stateless; import javax.ws.rs.Path;
 *
 * /**
 *
 * @author nicolas
 */
@Stateless
@Path("apartmentConsumptionsFacts")
public class ApartmentConsumptionsFactsResource {

    @Context
    private UriInfo context;
    @EJB
    ApartmentConsumptionsFactsManagerLocal apartmentConsumptionFactsManager;
    @EJB
    ApartmentConsumptionsFactsTOServiceLocal apartmentConsumptionFactsTOService;

    /**
     * Creates a new instance of ApartmentConsumptionFactsResource
     */
    public ApartmentConsumptionsFactsResource() {
    }

    /**
     * Creates a new Room resource from the provided representation
     *
     * @return an instance of PublicRoomTO
     */
    @POST
    @Consumes({"application/json"})
    public Response createResource(PublicApartmentConsumptionFactsTO newApartmentConsumptionFactsTO) {
        ApartmentConsumptionFact newConsumption = new ApartmentConsumptionFact();
        apartmentConsumptionFactsTOService.updateApartmentConsumptionFactEntity(newConsumption, newApartmentConsumptionFactsTO);
        long newConsumptionId = apartmentConsumptionFactsManager.create(newConsumption);
        URI createdURI = context.getAbsolutePathBuilder().path(Long.toString(newConsumptionId)).build();
        return Response.created(createdURI).build();
    }

    /**
     * Retrieves a representation of a list of Room resources
     *
     * @return an instance of PublicRoomTO
     */
    @GET
    @Produces({"application/json"})
    public List<PublicApartmentConsumptionFactsTO> getResourceList() {
        List<ApartmentConsumptionFact> consumptions = apartmentConsumptionFactsManager.findAll();
        List<PublicApartmentConsumptionFactsTO> result = new LinkedList<>();
        for (ApartmentConsumptionFact consumption : consumptions) {
            result.add(apartmentConsumptionFactsTOService.buildPublicApartmentConsumptionFactTO(consumption));
        }
        return result;
    }

    /**
     * Retrieves representation of an Room resource
     *
     * @param id this id of the room
     * @return an instance of PublicRoomTO
     * @throws ch.heigvd.skeleton.exceptions.EntityNotFoundException
     */
    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public PublicApartmentConsumptionFactsTO getResource(@PathParam("id") long id) throws EntityNotFoundException {
        ApartmentConsumptionFact consumption = apartmentConsumptionFactsManager.findById(id);
        PublicApartmentConsumptionFactsTO consumptionTO = apartmentConsumptionFactsTOService.buildPublicApartmentConsumptionFactTO(consumption);
        return consumptionTO;
    }

    /**
     * Updates an Room resource
     *
     * @param id this id of the Room
     * @param updatedRoomTO a TO containing the Room data
     * @return an instance of PublicRoomTO
     * @throws ch.heigvd.skeleton.exceptions.EntityNotFoundException
     */
    @PUT
    @Path("{id}")
    @Consumes({"application/json"})
    public Response Resource(PublicApartmentConsumptionFactsTO updatedRoomTO, @PathParam("id") long id) throws EntityNotFoundException {
        ApartmentConsumptionFact consumptionToUpdate = apartmentConsumptionFactsManager.findById(id);
        apartmentConsumptionFactsTOService.updateApartmentConsumptionFactEntity(consumptionToUpdate, updatedRoomTO);
        apartmentConsumptionFactsManager.update(consumptionToUpdate);
        return Response.ok().build();
    }

    /**
     * Deletes an Room resource
     *
     * @param id this id of the room
     * @return an instance of PublicRoomTO
     * @throws ch.heigvd.skeleton.exceptions.EntityNotFoundException
     */
    @DELETE
    @Path("{id}")
    public Response deleteResource(@PathParam("id") long id) throws EntityNotFoundException {
        apartmentConsumptionFactsManager.delete(id);
        return Response.ok().build();
    }
}
