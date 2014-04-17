package ch.heigvd.nrj.rest;

import ch.heigvd.nrj.exceptions.EntityNotFoundException;
import ch.heigvd.nrj.model.History;
import ch.heigvd.nrj.model.Plug;
import ch.heigvd.nrj.services.crud.HistoriesManagerLocal;
import ch.heigvd.nrj.services.crud.PlugsManagerLocal;
import ch.heigvd.nrj.services.to.HistoriesTOServiceLocal;
import ch.heigvd.nrj.services.to.PlugsTOServiceLocal;
import ch.heigvd.nrj.to.PublicHistoryTO;
import ch.heigvd.nrj.to.PublicPlugTO;
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
 * This is the REST API endpoint for the Plugs resource. When REST clients send
 * HTTP requests, they will be routed to this class (because of the
 *
 * @Path annotation). They will then be routed to the appropriate methods
 * (because of the
 * @GET,
 * @POST and other annotations).
 *
 * @author nicolas
 */
@Stateless
@Path("plugs")
public class PlugsResource {

    @Context
    private UriInfo context;
    @EJB
    PlugsManagerLocal plugsManager;
    @EJB
    PlugsTOServiceLocal plugsTOService;
    @EJB
    HistoriesManagerLocal historiesManager;
    @EJB
    HistoriesTOServiceLocal historiesTOService;

    /**
     * Creates a new instance of PlugsResource
     */
    public PlugsResource() {
    }

    /**
     * Creates a new Plug resource from the provided representation
     *
     * @param newPlugTO
     * @return an instance of PublicPlugTO
     */
    @POST
    @Consumes({"application/json"})
    public Response createResource(PublicPlugTO newPlugTO) {
        Plug newPlug = new Plug();
        plugsTOService.updatePlugEntity(newPlug, newPlugTO);
        long newPlugId = plugsManager.create(newPlug);
        URI createdURI = context.getAbsolutePathBuilder().path(Long.toString(newPlugId)).build();
        return Response.created(createdURI).build();
    }

    /**
     * Retrieves a representation of a list of Plug resources
     *
     * @return an instance of PublicPlugTO
     */
    @GET
    @Produces({"application/json"})
    public List<PublicPlugTO> getResourceList() {
        List<Plug> plugs = plugsManager.findAll();
        List<PublicPlugTO> result = new LinkedList<>();
        for (Plug plug : plugs) {
            result.add(plugsTOService.buildPublicPlugTO(plug));
        }
        return result;
    }

    /**
     * Retrieves a representation of a Plug resource
     *
     * @param id this id of the plug
     * @return an instance of PublicPlugTO
     * @throws ch.heigvd.skeleton.exceptions.EntityNotFoundException
     */
    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public PublicPlugTO getResource(@PathParam("id") long id) throws EntityNotFoundException {
        Plug plug = plugsManager.findById(id);
        PublicPlugTO plugTO = plugsTOService.buildPublicPlugTO(plug);
        return plugTO;
    }

    /**
     * Updates an Plug resource
     *
     * @param id this id of the Plug
     * @param updatedPlugTO a TO containing the Plug data
     * @return an instance of PublicPlugTO
     * @throws ch.heigvd.skeleton.exceptions.EntityNotFoundException
     */
    @PUT
    @Path("{id}")
    @Consumes({"application/json"})
    public Response Resource(PublicPlugTO updatedPlugTO, @PathParam("id") long id) throws EntityNotFoundException {
        Plug plugToUpdate = plugsManager.findById(id);
        plugsTOService.updatePlugEntity(plugToUpdate, updatedPlugTO);
        plugsManager.update(plugToUpdate);
        return Response.ok().build();
    }

    /**
     * Deletes an Plug resource
     *
     * @param id this id of the plug
     * @return an instance of PublicPlugTO
     * @throws ch.heigvd.skeleton.exceptions.EntityNotFoundException
     */
    @DELETE
    @Path("{id}")
    public Response deleteResource(@PathParam("id") long id) throws EntityNotFoundException {
        plugsManager.delete(id);
        return Response.ok().build();
    }

    /**
     * Retrieves the Status of a Plug
     *
     * @param id
     * @return the Status of a Plug
     * @throws EntityNotFoundException
     */
    @GET
    @Path("{id}/status")
    @Produces({"application/json"})
    public PublicHistoryTO getStatus(@PathParam("id") long id) throws EntityNotFoundException {
        History history = historiesManager.findLast();
        PublicHistoryTO historyTO = historiesTOService.buildPublicHistoryTO(history);
        return historyTO;
    }

    /**
     * Add a History to a Plug
     *
     * @param id, the Plug's id
     * @param newHistoryTO
     * @return the createdId of the added History
     * @throws EntityNotFoundException
     */
    @POST
    @Path("{id}/status")
    @Consumes({"application/json"})
    public Response setStatus(@PathParam("id") long id, PublicHistoryTO newHistoryTO) throws EntityNotFoundException {
        Plug plug = plugsManager.findById(id);
        History newHistory = new History();
        historiesTOService.updateHistoryEntity(newHistory, newHistoryTO);
        newHistory.setPlug(plug);
        long newHistoryId = historiesManager.create(newHistory);
        URI createdURI = context.getAbsolutePathBuilder().path(Long.toString(newHistoryId)).build();
        return Response.created(createdURI).build();
    }
}
