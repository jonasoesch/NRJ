/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.heigvd.nrj.rest;

import ch.heigvd.nrj.exceptions.EntityNotFoundException;
import ch.heigvd.nrj.model.Apartment;
import ch.heigvd.nrj.services.crud.ApartmentsManagerLocal;
import ch.heigvd.nrj.services.crud.RoomsManagerLocal;
import ch.heigvd.nrj.services.to.ApartmentsTOServiceLocal;
import ch.heigvd.nrj.to.PublicApartmentTO;
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
@Path("apartments")
public class ApartmentsResource {
	@Context
	private UriInfo context;
	
	@EJB
	ApartmentsManagerLocal apartmentsManager;
	@EJB
	RoomsManagerLocal roomsManager;
	@EJB
	ApartmentsTOServiceLocal apartmentsTOService;

	/**
	 * Creates a new instance of ApartmentsResource
	 */
	public ApartmentsResource() {
	}

	/**
	 * Creates a new Apartment resource from the provided representation
	 * @return an instance of PublicApartmentTO
	 */
	@POST
	@Consumes({"application/json"})
	public Response createResource(PublicApartmentTO newApartmentTO) {
	    Apartment newApartment = new Apartment();
	    apartmentsTOService.updateApartmentEntity(newApartment,newApartmentTO);
	    long newApartmentId = apartmentsManager.create(newApartment);
	    URI createdURI = context.getAbsolutePathBuilder().path(Long.toString(newApartmentId)).build();
	    return Response.created(createdURI).build();
	}

	/**
	 * Retrieves a representation of a list of Apartment resources
	 * @return an instance of PublicApartmentTO
	 */
	@GET
	@Produces({"application/json"})
	public List<PublicApartmentTO> getResourceList() {
		List<Apartment> apartments = apartmentsManager.findAll();
		List<PublicApartmentTO> result = new LinkedList<>();
		for(Apartment apartment : apartments) {
			result.add(apartmentsTOService.buildPublicApartmentTO(apartment));
		}
		return result;
	}
	
	/**
	 * Retrieves representation of an Apartment resource
	 * @param id this id of the apartment
	 * @return an instance of PublicApartmentTO
	 * @throws ch.heigvd.skeleton.exceptions.EntityNotFoundException
	 */
	@GET
	@Path("{id}")
  @Produces({"application/json"})
	public PublicApartmentTO getResource(@PathParam("id") long id) throws EntityNotFoundException {
		Apartment apartment = apartmentsManager.findById(id);
		PublicApartmentTO apartmentTO = apartmentsTOService.buildPublicApartmentTO(apartment);
		return apartmentTO;
	}

	/**
	 * Updates an Apartment resource
   * @param id this id of the apartment
	 * @param updatedApartmentTO a TO containing the apartment data
	 * @return an instance of PublicApartmentTO
	 * @throws ch.heigvd.skeleton.exceptions.EntityNotFoundException
	 */
	@PUT
	@Path("{id}")
	@Consumes({"application/json"})
	public Response Resource(PublicApartmentTO updatedApartmentTO, @PathParam("id") long id) throws EntityNotFoundException {
		Apartment apartmentToUpdate = apartmentsManager.findById(id);
		apartmentsTOService.updateApartmentEntity(apartmentToUpdate, updatedApartmentTO);
		apartmentsManager.update(apartmentToUpdate);
		return Response.ok().build();
	}

	
	/**
	 * Deletes an Apartment resource
   * @param id this id of the apartment
	 * @return an instance of PublicApartmentTO
	 * @throws ch.heigvd.skeleton.exceptions.EntityNotFoundException
	 */
	@DELETE
	@Path("{id}")
	public Response deleteResource(@PathParam("id") long id) throws EntityNotFoundException {
		apartmentsManager.delete(id);
		return Response.ok().build();
	}
}
