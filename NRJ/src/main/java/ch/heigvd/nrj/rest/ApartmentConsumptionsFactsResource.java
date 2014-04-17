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
 * This is the REST API endpoint for the Apartment Consumption Fact resource.
 * When REST clients send HTTP requests, they will be routed to this class
 * (because of the
 *
 * @Path annotation). They will then be routed to the appropriate methods
 * (because of the
 * @GET,
 * @POST and other annotations).
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
     * Creates a new Apartment Consumption Fact from the provided representation
     *
     * @return an instance of PublicApartmentConsumptionFactsTO
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
     * Retrieves a representation of a list of ApartmentConsumptionsFacts.
     *
     * @return a list of instance of PublicApartmentConsumptionFactsTO.
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
     * Retrieves representation of a ApartmentConsumptionFact
     *
     * @param id this id of the ApartmentConsumptionFact
     * @return an instance of PublicApartmentConsumptionFactsTO
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
    /**
     * Updates an ApartmentConsumptionFact
     *
     * @param updatedApartmentConsumptionFactsTO a TO containing the
     * ApartmentConsumptionFact data
     * @param id this id of the ApartmentConsumptionFact
     * @return an instance of PublicApartmentConsumptionFactsTO
     * @throws EntityNotFoundException
     */
    @PUT
    @Path("{id}")
    @Consumes({"application/json"})
    public Response Resource(PublicApartmentConsumptionFactsTO updatedApartmentConsumptionFactsTO, @PathParam("id") long id) throws EntityNotFoundException {
	ApartmentConsumptionFact consumptionToUpdate = apartmentConsumptionFactsManager.findById(id);
	apartmentConsumptionFactsTOService.updateApartmentConsumptionFactEntity(consumptionToUpdate, updatedApartmentConsumptionFactsTO);
	apartmentConsumptionFactsManager.update(consumptionToUpdate);
	return Response.ok().build();
    }

    /**
     * Deletes an ApartmentConsumptionFact
     *
     * @param id this id of the ApartmentConsumptionFact
     * @return an instance of PublicApartmentConsumptionFactsTO
     * @throws ch.heigvd.skeleton.exceptions.EntityNotFoundException
     */
    @DELETE
    @Path("{id}")
    public Response deleteResource(@PathParam("id") long id) throws EntityNotFoundException {
	apartmentConsumptionFactsManager.delete(id);
	return Response.ok().build();
    }
}
