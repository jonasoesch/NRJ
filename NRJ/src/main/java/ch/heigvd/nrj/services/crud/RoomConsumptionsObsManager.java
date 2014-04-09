package ch.heigvd.nrj.services.crud;

import ch.heigvd.nrj.exceptions.EntityNotFoundException;
import ch.heigvd.nrj.model.RoomConsumption;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * DAO service, implementing CRUD operations on the RoomConsumption entity. 
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
	public long create(RoomConsumption roomConsumptionObsData) {
		RoomConsumption newRoomConsumptionObs = new RoomConsumption(roomConsumptionObsData);
		em.persist(newRoomConsumptionObs);
		return newRoomConsumptionObs.getId();
	}

	@Override
	public void update(RoomConsumption newState) throws EntityNotFoundException {
		em.merge(newState);
	}

	@Override
	public void delete(long id) throws EntityNotFoundException {
		RoomConsumption roomConsumptionObsToDelete = findById(id);
		em.remove(roomConsumptionObsToDelete);
	}

	@Override
	public RoomConsumption findById(long id) throws EntityNotFoundException {
		RoomConsumption existingRoomConsumptionObs = em.find(RoomConsumption.class, id);
		if (existingRoomConsumptionObs == null) {
			throw new EntityNotFoundException();
		}
		return existingRoomConsumptionObs;
	}

	@Override
	public List<RoomConsumption> findAll() {
		// Note: the findAllRoomConsumptionsObs JPQL query is defined in the RoomConsumption.java file
		List roomConsumptionsObs = em.createNamedQuery("RoomConsumptionObs.findAllRoomConsumptionsObs").getResultList();
		return roomConsumptionsObs;
	}
	
}
