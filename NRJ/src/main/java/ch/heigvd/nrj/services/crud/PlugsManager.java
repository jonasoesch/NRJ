package ch.heigvd.nrj.services.crud;

import ch.heigvd.nrj.exceptions.EntityNotFoundException;
import ch.heigvd.nrj.model.Plug;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * DAO service, implementing CRUD operations on the Plug entity. 
 * The class uses the JPA entity manager to interact with the DB. 
 * It returns JPA entities to its clients.
 * 
 * @author nicolas
 */
@Stateless
public class PlugsManager implements PlugsManagerLocal {

	@PersistenceContext
	private EntityManager em;

	@Override
	public long create(Plug plugData) {
		Plug newPlug = new Plug(plugData);
		em.persist(newPlug);
		return newPlug.getId();
	}

	@Override
	public void update(Plug newState) throws EntityNotFoundException {
		em.merge(newState);
	}

	@Override
	public void delete(long id) throws EntityNotFoundException {
		Plug plugToDelete = findById(id);
		em.remove(plugToDelete);
	}

	@Override
	public Plug findById(long id) throws EntityNotFoundException {
		Plug existingPlug = em.find(Plug.class, id);
		if (existingPlug == null) {
			throw new EntityNotFoundException();
		}
		return existingPlug;
	}

	@Override
	public List<Plug> findAll() {
		// Note: the findAllPlugs JPQL query is defined in the Plug.java file
		List plugs = em.createNamedQuery("findAllPlugs").getResultList();
		return plugs;
	}
	
}
