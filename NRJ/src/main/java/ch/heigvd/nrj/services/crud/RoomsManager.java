package ch.heigvd.nrj.services.crud;

import ch.heigvd.nrj.exceptions.EntityNotFoundException;
import ch.heigvd.nrj.model.Apartment;
import ch.heigvd.nrj.model.Room;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * DAO service, implementing CRUD operations on the Room entity. The class uses
 * the JPA entity manager to interact with the DB. It returns JPA entities to
 * its clients.
 *
 * @author nicolas
 */
@Stateless
public class RoomsManager implements RoomsManagerLocal {

    @EJB
    ApartmentsManagerLocal apartmentsManager;

    @PersistenceContext
    private EntityManager em;

    /**
     * Adds a new Room in DB
     *
     * @param roomData
     * @return the id of the created Room
     */
    @Override
    public long create(Room roomData) {
        Room newRoom = new Room(roomData);
        Apartment a = newRoom.getApartment();
        try {
            // Rechercher l'appartement
            a = apartmentsManager.findById(a.getId());
        } catch (EntityNotFoundException ex) {
            Logger.getLogger(RoomsManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        newRoom.setApartment(a);
        em.persist(newRoom);
        a.addRoom(newRoom);
        em.flush();
        return newRoom.getId();
    }

    /**
     * Updates a Room from DB
     *
     * @param newState
     * @throws EntityNotFoundException
     */
    @Override
    public void update(Room newState) throws EntityNotFoundException {
        em.merge(newState);
    }

    /**
     * Deletes a Room from DB
     *
     * @param id, the Room's id
     * @throws EntityNotFoundException
     */
    @Override
    public void delete(long id) throws EntityNotFoundException {
        Room roomToDelete = findById(id);
        em.remove(roomToDelete);
    }

    /**
     * Return the Room with the given id from DB
     *
     * @param id, the Room's id
     * @return a Room
     * @throws EntityNotFoundException
     */
    @Override
    public Room findById(long id) throws EntityNotFoundException {
        Room existingRoom = em.find(Room.class, id);
        if (existingRoom == null) {
            throw new EntityNotFoundException();
        }
        return existingRoom;
    }

    /**
     * Returns all the Rooms from DB
     *
     * @return a List of Room
     */
    @Override
    public List<Room> findAll() {
        // Note: the findAllRooms JPQL query is defined in the Room.java file
        List rooms = em.createNamedQuery("Room.findAllRooms").getResultList();
        return rooms;
    }

    /**
     * Returns all Rooms from an Apartment
     *
     * @param a, an Apartment
     * @return a List of Rooms
     */
    @Override
    public List<Room> findAllByApartment(Apartment a) {
        // Note: the findAllRooms JPQL query is defined in the Room.java file
        List rooms = em.createQuery("Room.findAllByApartment").setParameter("apartment_id", a.getId()).getResultList();

        return rooms;
    }

}
