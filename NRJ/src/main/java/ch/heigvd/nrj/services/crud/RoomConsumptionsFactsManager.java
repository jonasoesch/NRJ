package ch.heigvd.nrj.services.crud;

import ch.heigvd.nrj.exceptions.EntityNotFoundException;
import ch.heigvd.nrj.model.Room;
import ch.heigvd.nrj.model.RoomConsumptionFact;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * DAO service, implementing CRUD operations on the RoomConsumptionFact entity. 
 * The class uses the JPA entity manager to interact with the DB. 
 * It returns JPA entities to its clients.
 * 
 * @author nicolas
 */
@Stateless
public class RoomConsumptionsFactsManager implements RoomConsumptionsFactsManagerLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public long create(RoomConsumptionFact roomConsumptionFactData) {
            RoomConsumptionFact newRoomConsumptionFact = new RoomConsumptionFact(roomConsumptionFactData);
            em.persist(newRoomConsumptionFact);
            return newRoomConsumptionFact.getId();
    }

    @Override
    public void update(RoomConsumptionFact newState) throws EntityNotFoundException {
            em.merge(newState);
    }

    @Override
    public void delete(long id) throws EntityNotFoundException {
            RoomConsumptionFact roomConsumptionFactToDelete = findById(id);
            em.remove(roomConsumptionFactToDelete);
    }

    @Override
    public RoomConsumptionFact findById(long id) throws EntityNotFoundException {
            RoomConsumptionFact existingRoomConsumptionFact = em.find(RoomConsumptionFact.class, id);
            if (existingRoomConsumptionFact == null) {
                    throw new EntityNotFoundException();
            }
            return existingRoomConsumptionFact;
    }

    @Override
    public List<RoomConsumptionFact> findAll() {
            // Note: the findAllRoomConsumptionsObs JPQL query is defined in the RoomConsumptionFact.java file
            List roomConsumptionsFact = em.createNamedQuery("RoomConsumptionFact.findAllRoomConsumptionsFacts").getResultList();
            return roomConsumptionsFact;
    }

    public RoomConsumptionFact getlastRoomFact(Room room) {
        List<RoomConsumptionFact> roomConsumptionsFacts = em.createNamedQuery("RoomConsumptionFact.getLastFact").setParameter("room", room).setMaxResults(1).getResultList();

        if (roomConsumptionsFacts.isEmpty()){
            return null;
        } else {
            return roomConsumptionsFacts.get(0);
        }
    }
	
}
