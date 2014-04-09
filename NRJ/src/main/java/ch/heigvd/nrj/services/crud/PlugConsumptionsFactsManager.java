package ch.heigvd.nrj.services.crud;

import ch.heigvd.nrj.exceptions.EntityNotFoundException;
import ch.heigvd.nrj.model.PlugConsumptionFacts;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * DAO service, implementing CRUD operations on the PlugConsumptionFacts entity. 
 * The class uses the JPA entity manager to interact with the DB. 
 * It returns JPA entities to its clients.
 * 
 * @author nicolas
 */
@Stateless
public class PlugConsumptionsFactsManager implements PlugConsumptionsFactsManagerLocal {

	@PersistenceContext
	private EntityManager em;

	@Override
	public long create(PlugConsumptionFacts plugConsumptionObsData) {
		PlugConsumptionFacts newPlugConsumptionObs = new PlugConsumptionFacts(plugConsumptionObsData);
		em.persist(newPlugConsumptionObs);
		return newPlugConsumptionObs.getId();
	}

	@Override
	public void update(PlugConsumptionFacts newState) throws EntityNotFoundException {
		em.merge(newState);
	}

	@Override
	public void delete(long id) throws EntityNotFoundException {
		PlugConsumptionFacts plugConsumptionObsToDelete = findById(id);
		em.remove(plugConsumptionObsToDelete);
	}

	@Override
	public PlugConsumptionFacts findById(long id) throws EntityNotFoundException {
		PlugConsumptionFacts existingPlugConsumptionObs = em.find(PlugConsumptionFacts.class, id);
		if (existingPlugConsumptionObs == null) {
			throw new EntityNotFoundException();
		}
		return existingPlugConsumptionObs;
	}

	@Override
	public List<PlugConsumptionFacts> findAll() {
		// Note: the findAllPlugConsumptionsObs JPQL query is defined in the PlugConsumptionFacts.java file
		List plugConsumptionsObs = em.createNamedQuery("PlugConsumptionObs.findAllPlugConsumptionsObs").getResultList();
		return plugConsumptionsObs;
	}
	
}
