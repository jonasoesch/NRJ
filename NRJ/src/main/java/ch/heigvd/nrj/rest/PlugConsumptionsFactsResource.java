/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.heigvd.nrj.rest;

import ch.heigvd.nrj.exceptions.EntityNotFoundException;
import ch.heigvd.nrj.model.Plug;
import ch.heigvd.nrj.model.PlugConsumptionFact;
import ch.heigvd.nrj.services.crud.PlugConsumptionsFactsManagerLocal;
import ch.heigvd.nrj.services.to.PlugConsumptionsFactsTOServiceLocal;
import ch.heigvd.nrj.to.PublicPlugConsumptionFactsTO;
import ch.heigvd.nrj.to.PublicPlugTO;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

/**
 * This is the REST API endpoint for the Consumptions resource. When REST clients
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
 * @author nicolas
 */
@Stateless
@Path("plugConsumptionsFacts")
public class PlugConsumptionsFactsResource {

    @Context
    private UriInfo context;
    @EJB
    PlugConsumptionsFactsManagerLocal plugConsumptionsFactsManager;
    @EJB
    PlugConsumptionsFactsTOServiceLocal plugConsumptionsFactsTOService;

    /**
     * Creates a new instance of ConsumptionsResource
     */
    public PlugConsumptionsFactsResource() {
    }

    /**
     * Creates a new Consumption resource from the provided representation
     *
     * @param newPlugConsumptionObsTO
     * @return an instance of PublicPlugConsumptionObsTO
     * 
     */
    /*
    @POST
    @Consumes({"application/json"})
    public Response createResource(PublicPlugConsumptionObsTO newPlugConsumptionObsTO) {
        PlugConsumption newPlugConsumption = new PlugConsumption();
        plugConsumptionsFactsTOService.updatePlugConsumptionObsEntity(newPlugConsumption, newPlugConsumptionObsTO);
        long newPlugConsumptionId = plugConsumptionsFactsManager.create(newPlugConsumption);
        URI createdURI = context.getAbsolutePathBuilder().path(Long.toString(newPlugConsumptionId)).build();
        return Response.created(createdURI).build();
    }
*/
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
     * Retrieves representation of an ConsumptionsObs resource
     *
     * @param id this id of the consumption
     * @return an instance of PublicPlugConsumptionTO
     * @throws ch.heigvd.nrj.exceptions.EntityNotFoundException
     */
    @GET
    @Path("{id}")
    @Produces({"application/json", "application/xml"})
    public PublicPlugConsumptionFactsTO getResource(@PathParam("id") long id) throws EntityNotFoundException {
        PlugConsumptionFact plugConsumptionFacts = plugConsumptionsFactsManager.findById(id);
        PublicPlugConsumptionFactsTO plugConsumptionTO = plugConsumptionsFactsTOService.buildPublicPlugConsumptionFactTO(plugConsumptionFacts);
        return plugConsumptionTO;
    }

    /**
     * Updates an ConsumptionsObs resource
     *
     * @param id this id of the consumption
     * @param updatedConsumptionTO a TO containing the consumption data
     * @return an instance of PublicConsumptionTO
     * @throws ch.heigvd.skeleton.exceptions.EntityNotFoundException
     */
    /*@PUT
    @Path("{id}")
    @Consumes({"application/json"})
    public Response Resource(PublicPlugTO updatedPlugTO, @PathParam("id") long id) throws EntityNotFoundException {
        Plug plugToUpdate = plugsManager.findById(id);
        plugsTOService.updatePlugEntity(plugToUpdate, updatedPlugTO);
        consumptionsManager.update(consumptionToUpdate);
        return Response.ok().build();
    }*/

    /**
     * Deletes an ConsumptionsObs resource
     *
     * @param id this id of the consumption
     * @return an instance of PublicConsumptionTO
     * @throws ch.heigvd.skeleton.exceptions.EntityNotFoundException
     */
    /*@DELETE
    @Path("{id}")
    public Response deleteResource(@PathParam("id") long id) throws EntityNotFoundException {
        consumptionsManager.delete(id);
        return Response.ok().build();
    }*/
}