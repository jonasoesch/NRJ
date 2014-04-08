package ch.heigvd.nrj.rest;

import ch.heigvd.nrj.exceptions.EntityNotFoundException;
import ch.heigvd.nrj.model.Plug;
import ch.heigvd.nrj.services.crud.PlugsManagerLocal;
import ch.heigvd.nrj.services.to.PlugsTOServiceLocal;
import ch.heigvd.nrj.to.PublicPlugTO;
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
 * This is the REST API endpoint for the Employees resource. When REST clients
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
 * @author Olivier Liechti
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

    /**
     * Creates a new instance of PlugsResource
     */
    public PlugsResource() {
    }

    /**
     * Creates a new Plug resource from the provided representation
     *
     * @return an instance of PublicEmployeeTO
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
    @Produces({"application/json", "application/xml"})
    public List<PublicPlugTO> getResourceList() {
        List<Plug> plugs = plugsManager.findAll();
        List<PublicPlugTO> result = new LinkedList<>();
        for (Plug plug : plugs) {
            result.add(plugsTOService.buildPublicPlugTO(plug));
        }
        return result;
    }

    /**
     * Retrieves representation of an Employee resource
     *
     * @param id this id of the employee
     * @return an instance of PublicEmployeeTO
     * @throws ch.heigvd.skeleton.exceptions.EntityNotFoundException
     */
    @GET
    @Path("{id}")
    @Produces({"application/json", "application/xml"})
    public PublicPlugTO getResource(@PathParam("id") long id) throws EntityNotFoundException {
        Plug plug = plugsManager.findById(id);
        PublicPlugTO plugTO = plugsTOService.buildPublicPlugTO(plug);
        return plugTO;
    }

    /**
     * Updates an Employee resource
     *
     * @param id this id of the employee
     * @param updatedEmployeeTO a TO containing the employee data
     * @return an instance of PublicEmployeeTO
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
     * Deletes an Employee resource
     *
     * @param id this id of the employee
     * @return an instance of PublicEmployeeTO
     * @throws ch.heigvd.skeleton.exceptions.EntityNotFoundException
     */
    @DELETE
    @Path("{id}")
    public Response deleteResource(@PathParam("id") long id) throws EntityNotFoundException {
        plugsManager.delete(id);
        return Response.ok().build();
    }
}
