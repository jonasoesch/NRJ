/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.nrj.rest;

import ch.heigvd.nrj.exceptions.EntityNotFoundException;
import ch.heigvd.nrj.model.ConsumptionObs;
import ch.heigvd.nrj.model.Plug;
import ch.heigvd.nrj.model.PlugConsumptionFact;
import ch.heigvd.nrj.services.crud.PlugConsumptionsFactsManagerLocal;
import ch.heigvd.nrj.services.to.PlugConsumptionsFactsTOService;
import ch.heigvd.nrj.services.to.PlugConsumptionsFactsTOServiceLocal;
import ch.heigvd.nrj.to.PublicConsumptionObsTO;
import ch.heigvd.nrj.to.PublicPlugConsumptionFactsTO;
import ch.heigvd.nrj.to.PublicPlugTO;
import java.net.URI;
import java.util.Date;
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
 * This class is a stateless session bean, which allows us to inject other
 * stateless session beans with annotations. That is practical, because when we
 * receive requests from REST clients, we can delegate most of the work to DAOs
 * and Transfer Object services.
 *
 * @author Olivier Liechti ======= import ch.heigvd.nrj.model.Plug; import
 * javax.ejb.Stateless; import javax.ws.rs.Path;
 *
 * /**
 *
 * @author nicolas
 */
@Stateless
@Path("roomConsumptions")
public class PlugConsumptionsFactsResource {

    @Context
    private UriInfo context;
    @EJB
    PlugConsumptionsFactsManagerLocal plugConsumptionsFactsManager;
    @EJB
    PlugConsumptionsFactsTOServiceLocal plugConsumptionsFactsTOService;

    /**
     * Creates a new instance of PlugsResource
     */
    public PlugConsumptionsFactsResource() {
    }

    /**
     * Creates a new Plug resource from the provided representation
     *
     * @return an instance of PublicPlugTO
     */
    @POST
    @Consumes({"application/json"})
    public Response createResource(PublicPlugConsumptionFactsTO newPlugConsumptionFactTO) {
        PlugConsumptionFact newPlugConsumptionFact = new PlugConsumptionFact();
        plugConsumptionsFactsTOService.updatePlugConsumptionFactEntity(newPlugConsumptionFact, newPlugConsumptionFactTO);
        long newPlugConsumptionFactId = plugConsumptionsFactsManager.create(newPlugConsumptionFact);
        URI createdURI = context.getAbsolutePathBuilder().path(Long.toString(newPlugConsumptionFactId)).build();
        return Response.created(createdURI).build();
    }
    
    /**
     * Retrieves a representation of a list of Plug resources
     *
     * @return an instance of PublicPlugTO
     */
    @GET
    @Produces({"application/json", "application/xml"})
    public List<PublicPlugConsumptionFactsTO> getResourceList() {
        List<PlugConsumptionFact> plugConsumptionsFacts = plugConsumptionsFactsManager.findAll();
        List<PublicPlugConsumptionFactsTO> result = new LinkedList<>();
        for (PlugConsumptionFact plugConsumptionFact : plugConsumptionsFacts) {
            result.add(plugConsumptionsFactsTOService.buildPublicPlugConsumptionFactTO(plugConsumptionFact));
        }
        return result;
    }

    /**
     * Retrieves a representation of a list of Plug resources by periode
     *
     * @param debut 
     * @param fin
     * @return an instance of PublicPlugTO
     */
    @GET
    @Produces({"application/json", "application/xml"})
    public List<PublicPlugConsumptionFactsTO> getResourceList(Date debut, Date fin) throws EntityNotFoundException {
        List<PlugConsumptionFact> plugConsumptionsFacts = plugConsumptionsFactsManager.findByPeriod(debut,fin);
        List<PublicPlugConsumptionFactsTO> result = new LinkedList<>();
        for (PlugConsumptionFact plugConsumptionFact : plugConsumptionsFacts) {
            result.add(plugConsumptionsFactsTOService.buildPublicPlugConsumptionFactTO(plugConsumptionFact));
        }
        return result;
    }

    /**
     * Retrieves representation of an Plug resource
     *
     * @param id this id of the plug
     * @return an instance of PublicPlugTO
     * @throws ch.heigvd.skeleton.exceptions.EntityNotFoundException
     */
    @GET
    @Path("{id}")
    @Produces({"application/json", "application/xml"})
    public PublicPlugConsumptionFactsTO getResource(@PathParam("id") long id) throws EntityNotFoundException {
        PlugConsumptionFact plugConsumptionFact = plugConsumptionsFactsManager.findById(id);
        PublicPlugConsumptionFactsTO plugConsumptionFactTO = plugConsumptionsFactsTOService.buildPublicPlugConsumptionFactTO(plugConsumptionFact);
        return plugConsumptionFactTO;
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
    public Response Resource(PublicPlugConsumptionFactsTO updatedConsumptionsFactsPlugTO, @PathParam("id") long id) throws EntityNotFoundException {
        PlugConsumptionFact plugConsumptionFactToUpdate = plugConsumptionsFactsManager.findById(id);
        plugConsumptionsFactsTOService.updatePlugConsumptionFactEntity(plugConsumptionFactToUpdate, updatedConsumptionsFactsPlugTO);
        plugConsumptionsFactsManager.update(plugConsumptionFactToUpdate);
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
        plugConsumptionsFactsManager.delete(id);
        return Response.ok().build();
    }
}
