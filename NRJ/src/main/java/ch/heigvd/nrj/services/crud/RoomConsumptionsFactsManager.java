package ch.heigvd.nrj.services.crud;

import ch.heigvd.nrj.exceptions.EntityNotFoundException;
import ch.heigvd.nrj.model.Room;
import ch.heigvd.nrj.model.RoomConsumptionFact;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * DAO service, implementing CRUD operations on the RoomConsumptionFact entity.
 * The class uses the JPA entity manager to interact with the DB. It returns JPA
 * entities to its clients.
 *
 * @author nicolas
 */
@Stateless
public class RoomConsumptionsFactsManager implements RoomConsumptionsFactsManagerLocal {

    @PersistenceContext
    private EntityManager em;

    @EJB
    RoomsManagerLocal roomsManager;

    /**
     * Adds a new RoomConsumptionFact in DB
     *
     * @param roomConsumptionFactData
     * @return the id of the created RoomConsumptionFact
     */
    @Override
    public long create(RoomConsumptionFact roomConsumptionFactData) {
        RoomConsumptionFact newRoomConsumptionFact = new RoomConsumptionFact(roomConsumptionFactData);
        Room r = newRoomConsumptionFact.getRoom();
        try {
            // Rechercher l'appartement
            r = roomsManager.findById(r.getId());
        } catch (EntityNotFoundException ex) {
            Logger.getLogger(RoomsManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        newRoomConsumptionFact.setRoom(r);
        em.persist(newRoomConsumptionFact);
        r.addRoomConsumptionFact(newRoomConsumptionFact);
        em.persist(newRoomConsumptionFact);
        return newRoomConsumptionFact.getId();
    }

    /**
     * Updates a RoomConsumptionFact in DB
     *
     * @param newState
     * @throws EntityNotFoundException
     */
    @Override
    public void update(RoomConsumptionFact newState) throws EntityNotFoundException {
        em.merge(newState);
    }

    /**
     * Deletes a RoomConsumptionFact from DB
     *
     * @param id, the RoomConsumptionFact's id
     * @throws EntityNotFoundException
     */
    @Override
    public void delete(long id) throws EntityNotFoundException {
        RoomConsumptionFact roomConsumptionFactToDelete = findById(id);
        em.remove(roomConsumptionFactToDelete);
    }

    /**
     * Returns a RoomConsumptionFact with the given id
     *
     * @param id, the RoomConsumptionFact's id
     * @return a RoomConsumptionFact
     * @throws EntityNotFoundException
     */
    @Override
    public RoomConsumptionFact findById(long id) throws EntityNotFoundException {
        RoomConsumptionFact existingRoomConsumptionFact = em.find(RoomConsumptionFact.class, id);
        if (existingRoomConsumptionFact == null) {
            throw new EntityNotFoundException();
        }
        return existingRoomConsumptionFact;
    }

    /**
     * Returns all RoomConsumptionFact form DB
     *
     * @return a List of RoomConsumptionFact
     */
    @Override
    public List<RoomConsumptionFact> findAll() {
        // Note: the findAllRoomConsumptionsObs JPQL query is defined in the RoomConsumptionFact.java file
        List roomConsumptionsFact = em.createNamedQuery("RoomConsumptionFact.findAllRoomConsumptionsFacts").getResultList();
        return roomConsumptionsFact;
    }

    /**
     * Returns the last RoomConsumptionFact from a Room
     *
     * @param room, a Room
     * @return
     */
    public RoomConsumptionFact getLastRoomFact(Room room) {
        List<RoomConsumptionFact> roomConsumptionsFacts = em.createNamedQuery("RoomConsumptionFact.getLastRoomFact").setParameter("room", room).setMaxResults(1).getResultList();

        if (roomConsumptionsFacts.isEmpty()) {
            return null;
        } else {
            return roomConsumptionsFacts.get(0);
        }
    }

}
