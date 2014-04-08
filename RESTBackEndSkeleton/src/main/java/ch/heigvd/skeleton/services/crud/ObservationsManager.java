package ch.heigvd.skeleton.services.crud;

import ch.heigvd.skeleton.exceptions.EntityNotFoundException;
import ch.heigvd.skeleton.model.Observation;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * 
 * @author Olivier Liechti
 */
@Stateless
public class ObservationsManager implements ObservationsManagerLocal {

	@PersistenceContext
	private EntityManager em;

	@Override
	public long create(Observation observationData) {
		Observation newObservation = new Observation(observationData);
		em.persist(newObservation);
		return newObservation.getId();
	}

	@Override
	public void update(Observation newState) throws EntityNotFoundException {
		em.merge(newState);
	}

	@Override
	public void delete(long id) throws EntityNotFoundException {
		Observation observationToDelete = findById(id);
		em.remove(observationToDelete);
	}

	@Override
	public Observation findById(long id) throws EntityNotFoundException {
		Observation existingObservation = em.find(Observation.class, id);
		if (existingObservation == null) {
			throw new EntityNotFoundException();
		}
		return existingObservation;
	}

	@Override
	public List<Observation> findAll() {
		// Note: the findAllObservations JPQL query is defined in the Observation.java file
		List observations = em.createNamedQuery("findAllObservations").getResultList();
		return observations;
	}
	
}
