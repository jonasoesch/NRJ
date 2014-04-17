package ch.heigvd.nrj.rest;

import ch.heigvd.nrj.exceptions.EntityNotFoundException;
import ch.heigvd.nrj.model.History;
import ch.heigvd.nrj.services.crud.HistoriesManagerLocal;
import ch.heigvd.nrj.services.to.HistoriesTOServiceLocal;
import ch.heigvd.nrj.to.PublicHistoryTO;
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
 * This is the REST API endpoint for the Histories resource. When REST clients
 * send HTTP requests, they will be routed to this class (because of the
 *
 * @Path annotation). They will then be routed to the appropriate methods
 * (because of the
 * @GET,
 * @POST and other annotations).
 *
 * @author nicolas
 */
@Stateless
@Path("histories")
public class HistoriesResource {

    @Context
    private UriInfo context;
    @EJB
    HistoriesManagerLocal historiesManager;
    @EJB
    HistoriesTOServiceLocal historiesTOService;

    /**
     * Creates a new instance of HistorysResource
     */
    public HistoriesResource() {
    }

    /**
     * Creates a new History resource from the provided representation
     *
     * @return an instance of PublicHistoryTO
     */
    @POST
    @Consumes({"application/json"})
    public Response createResource(PublicHistoryTO newHistoryTO) {
	History newHistory = new History();
	historiesTOService.updateHistoryEntity(newHistory, newHistoryTO);
	long newHistoryId = historiesManager.create(newHistory);
	URI createdURI = context.getAbsolutePathBuilder().path(Long.toString(newHistoryId)).build();
	return Response.created(createdURI).build();
    }

    /**
     * Retrieves a representation of a list of History resources
     *
     * @return an instance of PublicHistoryTO
     */
    @GET
    @Produces({"application/json"})
    public List<PublicHistoryTO> getResourceList() {
	List<History> histories = historiesManager.findAll();
	List<PublicHistoryTO> result = new LinkedList<>();
	for (History history : histories) {
	    result.add(historiesTOService.buildPublicHistoryTO(history));
	}
	return result;
    }

    /**
     * Retrieves representation of an History resource
     *
     * @param id this id of the history
     * @return an instance of PublicHistoryTO
     * @throws ch.heigvd.skeleton.exceptions.EntityNotFoundException
     */
    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public PublicHistoryTO getResource(@PathParam("id") long id) throws EntityNotFoundException {
	History history = historiesManager.findById(id);
	PublicHistoryTO historyTO = historiesTOService.buildPublicHistoryTO(history);
	return historyTO;
    }

    /**
     * Updates an History resource
     *
     * @param id this id of the history
     * @param updatedHistoryTO a TO containing the history data
     * @return an instance of PublicHistoryTO
     * @throws ch.heigvd.skeleton.exceptions.EntityNotFoundException
     */
    @PUT
    @Path("{id}")
    @Consumes({"application/json"})
    public Response Resource(PublicHistoryTO updatedHistoryTO, @PathParam("id") long id) throws EntityNotFoundException {
	History historyToUpdate = historiesManager.findById(id);
	historiesTOService.updateHistoryEntity(historyToUpdate, updatedHistoryTO);
	historiesManager.update(historyToUpdate);
	return Response.ok().build();
    }

    /**
     * Deletes an History resource
     *
     * @param id this id of the history
     * @return an instance of PublicHistoryTO
     * @throws ch.heigvd.skeleton.exceptions.EntityNotFoundException
     */
    @DELETE
    @Path("{id}")
    public Response deleteResource(@PathParam("id") long id) throws EntityNotFoundException {
	historiesManager.delete(id);
	return Response.ok().build();
    }
}
