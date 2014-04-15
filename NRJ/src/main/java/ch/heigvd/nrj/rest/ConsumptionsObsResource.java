/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.heigvd.nrj.rest;

import ch.heigvd.nrj.exceptions.EntityNotFoundException;
import ch.heigvd.nrj.model.ConsumptionObs;
import ch.heigvd.nrj.model.Plug;
import ch.heigvd.nrj.services.business.StreamProcessorLocal;
import ch.heigvd.nrj.services.crud.ConsumptionsObsManagerLocal;
import ch.heigvd.nrj.services.crud.PlugsManager;
import ch.heigvd.nrj.services.to.ConsumptionsObsTOServiceLocal;
import ch.heigvd.nrj.to.PublicConsumptionObsTO;
import java.net.URI;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
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
 * @author Olivier Liechti ======= import ch.heigvd.nrj.model.ConsumptionObs; import
 javax.ejb.Stateless; import javax.ws.rs.Path;
 *
 * @author nicolas
 */
@Stateless
@Path("consumptionsObs")
public class ConsumptionsObsResource {

    @Context
    private UriInfo context;
    @EJB
    ConsumptionsObsManagerLocal consumptionsManager;
    @EJB
    ConsumptionsObsTOServiceLocal consumptionsTOService;
    
    @EJB
    PlugsManager plugsManager;
    
    @EJB
    StreamProcessorLocal streamProcessor;

    /**
     * Creates a new instance of ConsumptionsResource
     */
    public ConsumptionsObsResource() {
    }

    /**
     * Creates a new ConsumptionObs resource from the provided representation
     *
     *   
     * @return an instance of PublicConsumptionObsTO
     */
    @POST
    @Consumes({"application/json"})
    public Response createResource(PublicConsumptionObsTO newConsumptionTO) throws EntityNotFoundException {
        ConsumptionObs newConsumption = new ConsumptionObs();
        // Recherche la plug liée et la lie à la consumption
        Plug p = newConsumption.getPlug();
        p = plugsManager.findById(p.getId());
        newConsumption.setPlug(p);
        long newPlugId = this.plugsManager.create(p);
        URI createdURI = context.getAbsolutePathBuilder().path(Long.toString(newPlugId)).build();
        
        consumptionsTOService.updateConsumptionObsEntity(newConsumption, newConsumptionTO);
        streamProcessor.onConsumption(newConsumption);
        if(newConsumption.getPlug() == null) {
            return Response.status(500).build();
        } else {
            return Response.ok().build();
        }
    }

    /**
     * Retrieves a representation of a list of Plug resources
     *
     * @return an instance of PublicPlugTO
     */
    /*@GET
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
     * Retrieves representation of an ConsumptionsObs resource
     *
     * @param id this id of the consumption
     * @return an instance of PublicConsumptionObsTO
     * @throws ch.heigvd.skeleton.exceptions.EntityNotFoundException
     */
//    @GET
//    @Path("{id}")
//    @Produces({"application/json"})
//    public PublicConsumptionObsTO getResource(@PathParam("id") long id) throws EntityNotFoundException {
//        ConsumptionObs consumption = consumptionsManager.findById(id);
//        PublicConsumptionObsTO consumptionTO = consumptionsTOService.buildPublicConsumptionObsTO(consumption);
//        return consumptionTO;
//    }
    
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