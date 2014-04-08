package ch.heigvd.nrj.services.crud;

import ch.heigvd.nrj.exceptions.EntityNotFoundException;
import ch.heigvd.nrj.model.Apartment;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * This is an example for a DAO service, implementing CRUD operations on the
 * Apartment entity. The class uses the JPA entity manager to
 * interact with the DB. It returns JPA entities to its clients.
 * 
 * @author Olivier Liechti
 */
@Stateless
public class ApartmentsManager implements ApartmentsManagerLocal {

	@PersistenceContext
	private EntityManager em;

	@Override
	public long create(Apartment apartmentData) {
		Apartment newApartment = new Apartment(apartmentData);
		em.persist(newApartment);
		return newApartment.getId();
	}

	@Override
	public void update(Apartment newState) throws EntityNotFoundException {
		em.merge(newState);
	}

	@Override
	public void delete(long id) throws EntityNotFoundException {
		Apartment apartmentToDelete = findById(id);
		em.remove(apartmentToDelete);
	}

	@Override
	public Apartment findById(long id) throws EntityNotFoundException {
		Apartment existingApartment = em.find(Apartment.class, id);
		if (existingApartment == null) {
			throw new EntityNotFoundException();
		}
		return existingApartment;
	}

	@Override
	public List<Apartment> findAll() {
		// Note: the findAllApartments JPQL query is defined in the Apartment.java file
		List apartments = em.createNamedQuery("findAllApartments").getResultList();
		return apartments;
	}
	
}
