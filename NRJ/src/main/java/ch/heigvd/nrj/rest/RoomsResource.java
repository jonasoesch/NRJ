package ch.heigvd.nrj.rest;

import ch.heigvd.nrj.exceptions.EntityNotFoundException;
import ch.heigvd.nrj.model.Apartment;
import ch.heigvd.nrj.model.Room;
import ch.heigvd.nrj.services.crud.ApartmentsManagerLocal;
import ch.heigvd.nrj.services.crud.RoomsManagerLocal;
import ch.heigvd.nrj.services.to.RoomsTOServiceLocal;
import ch.heigvd.nrj.to.PublicRoomTOEntree;
import ch.heigvd.nrj.to.PublicRoomTOSortie;
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
@Path("rooms")
public class RoomsResource {
    @Context
    private UriInfo context;
    @EJB
    RoomsManagerLocal roomsManager;
    @EJB
    ApartmentsManagerLocal apartmentsManager;
    @EJB
    RoomsTOServiceLocal roomsTOService;

    /**
     * Creates a new instance of RoomsResource
     */
    public RoomsResource() {
    }

    /**
     * Creates a new Room resource from the provided representation
     *
     * @return an instance of PublicEmployeeTO
     */
    @POST
    @Consumes({"application/json"})
    public Response createResource(PublicRoomTOEntree newRoomTO) throws EntityNotFoundException {
        Room newRoom = new Room();
        roomsTOService.updateRoomEntity(newRoom, newRoomTO);
	// Rechercher l'appartement
	Apartment a = newRoom.getApartment();
	a = apartmentsManager.findById(a.getId());
	newRoom.setApartment(a);
        long newRoomId = this.roomsManager.create(newRoom);
        URI createdURI = context.getAbsolutePathBuilder().path(Long.toString(newRoomId)).build();
        return Response.created(createdURI).build();
    }

    /**
     * Retrieves a representation of a list of Room resources
     *
     * @return an instance of PublicRoomTOSortie
     */
    @GET
    @Produces({"application/json"})
    public List<PublicRoomTOSortie> getResourceList() {
        List<Room> rooms = roomsManager.findAll();
        List<PublicRoomTOSortie> result = new LinkedList<>();
        for (Room room : rooms) {
            result.add(roomsTOService.buildPublicRoomTO(room));
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
    @Produces({"application/json"})
    public PublicRoomTOSortie getResource(@PathParam("id") long id) throws EntityNotFoundException {
        Room room = roomsManager.findById(id);
        PublicRoomTOSortie roomTO = roomsTOService.buildPublicRoomTO(room);
        return roomTO;
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
    public Response Resource(PublicRoomTOEntree updatedRoomTO, @PathParam("id") long id) throws EntityNotFoundException {
        Room roomToUpdate = roomsManager.findById(id);
        roomsTOService.updateRoomEntity(roomToUpdate, updatedRoomTO);
        roomsManager.update(roomToUpdate);
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
        roomsManager.delete(id);
        return Response.ok().build();
    }
}
