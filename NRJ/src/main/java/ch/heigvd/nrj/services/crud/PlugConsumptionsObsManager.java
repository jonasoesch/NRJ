package ch.heigvd.nrj.services.crud;

import ch.heigvd.nrj.exceptions.EntityNotFoundException;
import ch.heigvd.nrj.model.PlugConsumptionObs;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * DAO service, implementing CRUD operations on the PlugConsumptionObs entity. 
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
	public long create(PlugConsumptionObs plugConsumptionObsData) {
		PlugConsumptionObs newPlugConsumptionObs = new PlugConsumptionObs(plugConsumptionObsData);
		em.persist(newPlugConsumptionObs);
		return newPlugConsumptionObs.getId();
	}

	@Override
	public void update(PlugConsumptionObs newState) throws EntityNotFoundException {
		em.merge(newState);
	}

	@Override
	public void delete(long id) throws EntityNotFoundException {
		PlugConsumptionObs plugConsumptionObsToDelete = findById(id);
		em.remove(plugConsumptionObsToDelete);
	}

	@Override
	public PlugConsumptionObs findById(long id) throws EntityNotFoundException {
		PlugConsumptionObs existingPlugConsumptionObs = em.find(PlugConsumptionObs.class, id);
		if (existingPlugConsumptionObs == null) {
			throw new EntityNotFoundException();
		}
		return existingPlugConsumptionObs;
	}

	@Override
	public List<PlugConsumptionObs> findAll() {
		// Note: the findAllPlugConsumptionsObs JPQL query is defined in the PlugConsumptionObs.java file
		List plugConsumptionsObs = em.createNamedQuery("PlugConsumptionObs.findAllPlugConsumptionsObs").getResultList();
		return plugConsumptionsObs;
	}
	
}
