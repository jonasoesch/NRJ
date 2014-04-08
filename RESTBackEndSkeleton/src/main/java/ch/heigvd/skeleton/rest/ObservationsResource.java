package ch.heigvd.skeleton.rest;

import ch.heigvd.skeleton.exceptions.EntityNotFoundException;
import ch.heigvd.skeleton.model.Observation;
import ch.heigvd.skeleton.services.crud.ObservationsManagerLocal;
import ch.heigvd.skeleton.services.to.ObservationsTOServiceLocal;
import ch.heigvd.skeleton.to.PublicObservationTO;
import java.net.URI;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * This is the REST API endpoint for the Observations resource. When REST clients
 * send HTTP requests, they will be routed to this class (because of the @Path
 * annotation). They will then be routed to the appropriate methods (because of
 * the @GET, @POST and other annotations).
 * 
 * This class is a stateless session bean, which allows us to inject other stateless
 * session beans with annotations. That is practical, because when we receive
 * requests from REST clients, we can delegate most of the work to DAOs and
 * Transfer Object services.
 *
 * @author Olivier Liechti
 */
@Stateless
@Path("observations")
public class ObservationsResource {

	@Context
	private UriInfo context;
	
	@EJB
	ObservationsManagerLocal observationsManager;
	
	@EJB
	ObservationsTOServiceLocal observationsTOService;

	/**
	 * Creates a new instance of ObservationsResource
	 */
	public ObservationsResource() {
	}

	/**
	 * Creates a new Observation resource from the provided representation
	 * @return an instance of PublicObservationTO
	 */
	@POST
	@Consumes({"application/json"})
	public Response createResource(PublicObservationTO newObservationTO) {
			Observation newObservation = new Observation();
			observationsTOService.updateObservationEntity(newObservation,newObservationTO);
			long newObservationId = observationsManager.create(newObservation);
			URI createdURI = context.getAbsolutePathBuilder().path(Long.toString(newObservationId)).build();
			return Response.created(createdURI).build();
	}

	/**
	 * Retrieves a representation of a list of Observation resources
	 * @return an instance of PublicObservationTO
	 */
	@GET
  @Produces({"application/json", "application/xml"})
	public List<PublicObservationTO> getResourceList() {
		List<Observation> observations = observationsManager.findAll();
		List<PublicObservationTO> result = new LinkedList<>();
		for(Observation observation : observations) {
			result.add(observationsTOService.buildPublicObservationTO(observation));
		}
		return result;
	}
	
	/**
	 * Retrieves representation of an Observation resource
	 * @param id this id of the observation
	 * @return an instance of PublicObservationTO
	 * @throws ch.heigvd.skeleton.exceptions.EntityNotFoundException
	 */
	@GET
	@Path("{id}")
  @Produces({"application/json", "application/xml"})
	public PublicObservationTO getResource(@PathParam("id") long id) throws EntityNotFoundException {
		Observation observation = observationsManager.findById(id);
		PublicObservationTO observationTO = observationsTOService.buildPublicObservationTO(observation);
		return observationTO;
	}

	/**
	 * Updates an Observation resource
   * @param id this id of the observation
	 * @param updatedObservationTO a TO containing the observation data
	 * @return an instance of PublicObservationTO
	 * @throws ch.heigvd.skeleton.exceptions.EntityNotFoundException
	 */
	@PUT
	@Path("{id}")
	@Consumes({"application/json"})
	public Response Resource(PublicObservationTO updatedObservationTO, @PathParam("id") long id) throws EntityNotFoundException {
		Observation observationToUpdate = observationsManager.findById(id);
		observationsTOService.updateObservationEntity(observationToUpdate, updatedObservationTO);
		observationsManager.update(observationToUpdate);
		return Response.ok().build();
	}

	
	/**
	 * Deletes an Observation resource
         * @param id this id of the observation
	 * @return an instance of PublicObservationTO
	 * @throws ch.heigvd.skeleton.exceptions.EntityNotFoundException
	 */
	@DELETE
	@Path("{id}")
	public Response deleteResource(@PathParam("id") long id) throws EntityNotFoundException {
		observationsManager.delete(id);
		return Response.ok().build();
	}
        
        

}
