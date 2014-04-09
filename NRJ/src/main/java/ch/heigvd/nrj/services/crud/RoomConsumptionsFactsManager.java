package ch.heigvd.nrj.services.crud;

import ch.heigvd.nrj.exceptions.EntityNotFoundException;
import ch.heigvd.nrj.model.RoomConsumptionFacts;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * DAO service, implementing CRUD operations on the RoomConsumptionFacts entity. 
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
	public long create(RoomConsumptionFacts roomConsumptionObsData) {
		RoomConsumptionFacts newRoomConsumptionObs = new RoomConsumptionFacts(roomConsumptionObsData);
		em.persist(newRoomConsumptionObs);
		return newRoomConsumptionObs.getId();
	}

	@Override
	public void update(RoomConsumptionFacts newState) throws EntityNotFoundException {
		em.merge(newState);
	}

	@Override
	public void delete(long id) throws EntityNotFoundException {
		RoomConsumptionFacts roomConsumptionObsToDelete = findById(id);
		em.remove(roomConsumptionObsToDelete);
	}

	@Override
	public RoomConsumptionFacts findById(long id) throws EntityNotFoundException {
		RoomConsumptionFacts existingRoomConsumptionObs = em.find(RoomConsumptionFacts.class, id);
		if (existingRoomConsumptionObs == null) {
			throw new EntityNotFoundException();
		}
		return existingRoomConsumptionObs;
	}

	@Override
	public List<RoomConsumptionFacts> findAll() {
		// Note: the findAllRoomConsumptionsObs JPQL query is defined in the RoomConsumptionFacts.java file
		List roomConsumptionsObs = em.createNamedQuery("RoomConsumptionObs.findAllRoomConsumptionsObs").getResultList();
		return roomConsumptionsObs;
	}
	
}
