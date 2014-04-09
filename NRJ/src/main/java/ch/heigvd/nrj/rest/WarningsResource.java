package ch.heigvd.nrj.rest;

import ch.heigvd.nrj.exceptions.EntityNotFoundException;
import ch.heigvd.nrj.model.Warning;
import ch.heigvd.nrj.services.crud.WarningsManagerLocal;
import ch.heigvd.nrj.services.to.WarningsTOServiceLocal;
import ch.heigvd.nrj.to.PublicWarningTO;
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
 *
 * @author nicolas
 */
@Stateless
@Path("warnings")
public class WarningsResource {

    @Context
    private UriInfo context;
    @EJB
    WarningsManagerLocal warningsManager;
    @EJB
    WarningsTOServiceLocal warningsTOService;

    /**
     * Creates a new instance of WarningsResource
     */
    public WarningsResource() {
    }

    /**
     * Creates a new Warning resource from the provided representation
     *
     * @return an instance of PublicWarningTO
     */
    @POST
    @Consumes({"application/json"})
    public Response createResource(PublicWarningTO newWarningTO) {
        Warning newWarning = new Warning();
        warningsTOService.updateWarningEntity(newWarning, newWarningTO);
        long newWarningId = warningsManager.create(newWarning);
        URI createdURI = context.getAbsolutePathBuilder().path(Long.toString(newWarningId)).build();
        return Response.created(createdURI).build();
    }

    /**
     * Retrieves a representation of a list of Warning resources
     *
     * @return an instance of PublicWarningTO
     */
    @GET
    @Produces({"application/json"})
    public List<PublicWarningTO> getResourceList() {
        List<Warning> warnings = warningsManager.findAll();
        List<PublicWarningTO> result = new LinkedList<>();
        for (Warning warning : warnings) {
            result.add(warningsTOService.buildPublicWarningTO(warning));
        }
        return result;
    }

    /**
     * Retrieves representation of an Warning resource
     *
     * @param id this id of the warning
     * @return an instance of PublicWarningTO
     * @throws ch.heigvd.skeleton.exceptions.EntityNotFoundException
     */
    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public PublicWarningTO getResource(@PathParam("id") long id) throws EntityNotFoundException {
        Warning warning = warningsManager.findById(id);
        PublicWarningTO warningTO = warningsTOService.buildPublicWarningTO(warning);
        return warningTO;
    }

    /**
     * Updates an Warning resource
     *
     * @param id this id of the warning
     * @param updatedWarningTO a TO containing the warning data
     * @return an instance of PublicWarningTO
     * @throws ch.heigvd.skeleton.exceptions.EntityNotFoundException
     */
    @PUT
    @Path("{id}")
    @Consumes({"application/json"})
    public Response Resource(PublicWarningTO updatedWarningTO, @PathParam("id") long id) throws EntityNotFoundException {
        Warning warningToUpdate = warningsManager.findById(id);
        warningsTOService.updateWarningEntity(warningToUpdate, updatedWarningTO);
        warningsManager.update(warningToUpdate);
        return Response.ok().build();
    }

    /**
     * Deletes an Warning resource
     *
     * @param id this id of the warning
     * @return an instance of PublicWarningTO
     * @throws ch.heigvd.skeleton.exceptions.EntityNotFoundException
     */
    @DELETE
    @Path("{id}")
    public Response deleteResource(@PathParam("id") long id) throws EntityNotFoundException {
        warningsManager.delete(id);
        return Response.ok().build();
    }
}
