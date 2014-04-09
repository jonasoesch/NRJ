package ch.heigvd.nrj.services.crud;

import ch.heigvd.nrj.exceptions.EntityNotFoundException;
import ch.heigvd.nrj.model.RoomConsumptionObs;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * DAO service, implementing CRUD operations on the RoomConsumptionObs entity. 
 * The class uses the JPA entity manager to interact with the DB. 
 * It returns JPA entities to its clients.
 * 
 * @author nicolas
 */
@Stateless
public class RoomConsumptionsObsManager implements RoomConsumptionsObsManagerLocal {

	@PersistenceContext
	private EntityManager em;

	@Override
	public long create(RoomConsumptionObs roomConsumptionObsData) {
		RoomConsumptionObs newRoomConsumptionObs = new RoomConsumptionObs(roomConsumptionObsData);
		em.persist(newRoomConsumptionObs);
		return newRoomConsumptionObs.getId();
	}

	@Override
	public void update(RoomConsumptionObs newState) throws EntityNotFoundException {
		em.merge(newState);
	}

	@Override
	public void delete(long id) throws EntityNotFoundException {
		RoomConsumptionObs roomConsumptionObsToDelete = findById(id);
		em.remove(roomConsumptionObsToDelete);
	}

	@Override
	public RoomConsumptionObs findById(long id) throws EntityNotFoundException {
		RoomConsumptionObs existingRoomConsumptionObs = em.find(RoomConsumptionObs.class, id);
		if (existingRoomConsumptionObs == null) {
			throw new EntityNotFoundException();
		}
		return existingRoomConsumptionObs;
	}

	@Override
	public List<RoomConsumptionObs> findAll() {
		// Note: the findAllRoomConsumptionsObs JPQL query is defined in the RoomConsumptionObs.java file
		List roomConsumptionsObs = em.createNamedQuery("RoomConsumptionObs.findAllRoomConsumptionsObs").getResultList();
		return roomConsumptionsObs;
	}
	
}
