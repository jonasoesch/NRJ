package ch.heigvd.nrj.services.crud;

import ch.heigvd.nrj.exceptions.EntityNotFoundException;
import ch.heigvd.nrj.model.PlugConsumption;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * DAO service, implementing CRUD operations on the PlugConsumption entity. 
 * The class uses the JPA entity manager to interact with the DB. 
 * It returns JPA entities to its clients.
 * 
 * @author nicolas
 */
@Stateless
public class PlugConsumptionsObsManager implements PlugConsumptionsObsManagerLocal {

	@PersistenceContext
	private EntityManager em;

	@Override
	public long create(PlugConsumption plugConsumptionObsData) {
		PlugConsumption newPlugConsumptionObs = new PlugConsumption(plugConsumptionObsData);
		em.persist(newPlugConsumptionObs);
		return newPlugConsumptionObs.getId();
	}

	@Override
	public void update(PlugConsumption newState) throws EntityNotFoundException {
		em.merge(newState);
	}

	@Override
	public void delete(long id) throws EntityNotFoundException {
		PlugConsumption plugConsumptionObsToDelete = findById(id);
		em.remove(plugConsumptionObsToDelete);
	}

	@Override
	public PlugConsumption findById(long id) throws EntityNotFoundException {
		PlugConsumption existingPlugConsumptionObs = em.find(PlugConsumption.class, id);
		if (existingPlugConsumptionObs == null) {
			throw new EntityNotFoundException();
		}
		return existingPlugConsumptionObs;
	}

	@Override
	public List<PlugConsumption> findAll() {
		// Note: the findAllPlugConsumptionsObs JPQL query is defined in the PlugConsumption.java file
		List plugConsumptionsObs = em.createNamedQuery("PlugConsumptionObs.findAllPlugConsumptionsObs").getResultList();
		return plugConsumptionsObs;
	}
	
}
