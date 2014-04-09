package ch.heigvd.nrj.services.crud;

import ch.heigvd.nrj.exceptions.EntityNotFoundException;
import ch.heigvd.nrj.model.PlugConsumptionFact;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * DAO service, implementing CRUD operations on the PlugConsumptionFact entity. 
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
	public long create(PlugConsumptionFact plugConsumptionFactData) {
		PlugConsumptionFact newPlugConsumptionFact = new PlugConsumptionFact(plugConsumptionFactData);
		em.persist(newPlugConsumptionFact);
		return newPlugConsumptionFact.getId();
	}

	@Override
	public void update(PlugConsumptionFact newState) throws EntityNotFoundException {
		em.merge(newState);
	}

	@Override
	public void delete(long id) throws EntityNotFoundException {
		PlugConsumptionFact plugConsumptionFactToDelete = findById(id);
		em.remove(plugConsumptionFactToDelete);
	}

	@Override
	public PlugConsumptionFact findById(long id) throws EntityNotFoundException {
		PlugConsumptionFact existingPlugConsumptionFact = em.find(PlugConsumptionFact.class, id);
		if (existingPlugConsumptionFact == null) {
			throw new EntityNotFoundException();
		}
		return existingPlugConsumptionFact;
	}

	@Override
	public List<PlugConsumptionFact> findAll() {
		// Note: the findAllPlugConsumptionsObs JPQL query is defined in the PlugConsumptionFact.java file
		List plugConsumptionsFacts = em.createNamedQuery("PlugConsumptionFact.findAllPlugConsumptionsFacts").getResultList();
		return plugConsumptionsFacts;
	}
	
}
