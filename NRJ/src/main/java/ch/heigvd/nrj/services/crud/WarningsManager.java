package ch.heigvd.nrj.services.crud;

import ch.heigvd.nrj.exceptions.EntityNotFoundException;
import ch.heigvd.nrj.model.Warning;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * DAO service, implementing CRUD operations on the Warning entity. 
 * The class uses the JPA entity manager to interact with the DB. 
 * It returns JPA entities to its clients.
 * 
 * @author rschmutz
 */
@Stateless
public class WarningsManager implements WarningsManagerLocal {

	@PersistenceContext
	private EntityManager em;

	@Override
	public long create(Warning warningData) {
		Warning newWarning = new Warning(warningData);
		em.persist(newWarning);
		return newWarning.getId();
	}

	@Override
	public void update(Warning newState) throws EntityNotFoundException {
		em.merge(newState);
	}

	@Override
	public void delete(long id) throws EntityNotFoundException {
		Warning warningToDelete = findById(id);
		em.remove(warningToDelete);
	}

	@Override
	public Warning findById(long id) throws EntityNotFoundException {
		Warning existingWarning = em.find(Warning.class, id);
		if (existingWarning == null) {
			throw new EntityNotFoundException();
		}
		return existingWarning;
	}

	@Override
	public List<Warning> findAll() {
		// Note: the findAllPlugs JPQL query is defined in the Plug.java file
		List warning = em.createNamedQuery("Warning.findAllWarnings").getResultList();
		return warning;
	}
	
}
