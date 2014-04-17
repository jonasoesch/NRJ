package ch.heigvd.nrj.services.crud;

import ch.heigvd.nrj.exceptions.EntityNotFoundException;
import ch.heigvd.nrj.model.Apartment;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * DAO service, implementing CRUD operations on the Apartment entity. The class
 * uses the JPA entity manager to interact with the DB. It returns JPA entities
 * to its clients.
 *
 * @author nicolas
 */
@Stateless
public class ApartmentsManager implements ApartmentsManagerLocal {

    @PersistenceContext
    private EntityManager em;

    /**
     * Create an Apartment persistant in database
     *
     * @param apartmentData the Apartment to create.
     * @return the apartment's id generated
     */
    @Override
    public long create(Apartment apartmentData) {
	Apartment newApartment = new Apartment(apartmentData);
	em.persist(newApartment);
	em.flush();
	return newApartment.getId();
    }

    /**
     * Update a persistant apartment.
     *
     * @param newState the apartment to update.
     * @throws EntityNotFoundException
     */
    @Override
    public void update(Apartment newState) throws EntityNotFoundException {
	em.merge(newState);
    }

    /**
     * Delete an apartment persistant.
     *
     * @param id the apartment id.
     * @throws EntityNotFoundException
     */
    @Override
    public void delete(long id) throws EntityNotFoundException {
	Apartment apartmentToDelete = findById(id);
	em.remove(apartmentToDelete);
    }

    /**
     * Find an apartment with it's id.
     *
     * @param id the id of the apartment to find.
     * @return the apartment.
     * @throws EntityNotFoundException
     */
    @Override
    public Apartment findById(long id) throws EntityNotFoundException {
	Apartment existingApartment = em.find(Apartment.class, id);
	if (existingApartment == null) {
	    throw new EntityNotFoundException();
	}
	return existingApartment;
    }

    /**
     * Find all persistants apartments.
     *
     * @return list of the persistants apartments
     */
    @Override
    public List<Apartment> findAll() {
	// Note: the findAllApartments JPQL query is defined in the Apartment.java file
	List apartments = em.createNamedQuery("Apartment.findAllApartments").getResultList();
	return apartments;
    }
}
