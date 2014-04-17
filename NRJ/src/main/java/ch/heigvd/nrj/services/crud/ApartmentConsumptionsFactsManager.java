package ch.heigvd.nrj.services.crud;

import ch.heigvd.nrj.exceptions.EntityNotFoundException;
import ch.heigvd.nrj.model.Apartment;
import ch.heigvd.nrj.model.ApartmentConsumptionFact;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * DAO service, implementing CRUD operations on the ApartmentConsumptionFact
 * entity. The class uses the JPA entity manager to interact with the DB. It
 * returns JPA entities to its clients.
 *
 * @author nicolas
 */
@Stateless
public class ApartmentConsumptionsFactsManager implements ApartmentConsumptionsFactsManagerLocal {

    @PersistenceContext
    private EntityManager em;
    @EJB
    ApartmentsManagerLocal apartmentsManager;

    /**
     * Create an ApartmentConsumptionFact in the persistance database.
     *
     * @param apartmentConsumptionFactData the ApartmentConsumptionFact to
     * persiste.
     * @return the id generated
     */
    @Override
    public long create(ApartmentConsumptionFact apartmentConsumptionFactData) {
	ApartmentConsumptionFact newApartmentConsumptionFact = new ApartmentConsumptionFact(apartmentConsumptionFactData);
	Apartment a = newApartmentConsumptionFact.getApartment();
	try {
	    // Rechercher l'appartement
	    a = apartmentsManager.findById(a.getId());
	} catch (EntityNotFoundException ex) {
	    Logger.getLogger(RoomsManager.class.getName()).log(Level.SEVERE, null, ex);
	}
	newApartmentConsumptionFact.setApartment(a);
	em.persist(newApartmentConsumptionFact);
	a.addApartmentConsumptionFact(newApartmentConsumptionFact);
	em.flush();
	return newApartmentConsumptionFact.getId();
    }

    /**
     * Update the ApartmentConsumptionFact persistant
     *
     * @param newState the ApartmentConsumptionFact with update data.
     * @throws EntityNotFoundException
     */
    @Override
    public void update(ApartmentConsumptionFact newState) throws EntityNotFoundException {
	em.merge(newState);
    }

    /**
     * Delete a ApartmentConsumptionFact in persistance.
     *
     * @param id the id of the ApartmentConsumptionFact in persistance.
     * @throws EntityNotFoundException
     */
    @Override
    public void delete(long id) throws EntityNotFoundException {
	ApartmentConsumptionFact apartmentConsumptionFactToDelete = findById(id);
	em.remove(apartmentConsumptionFactToDelete);
    }

    /**
     * Find a ApartmentConsumptionFact in the persistance with it's id.
     *
     * @param id the id of the ApartmentConsumptionFact to find.
     * @return the ApartmentConsumptionFact find.
     * @throws EntityNotFoundException
     */
    @Override
    public ApartmentConsumptionFact findById(long id) throws EntityNotFoundException {
	ApartmentConsumptionFact existingApartmentConsumptionFact = em.find(ApartmentConsumptionFact.class, id);
	if (existingApartmentConsumptionFact == null) {
	    throw new EntityNotFoundException();
	}
	return existingApartmentConsumptionFact;
    }

    /**
     * Find all ApartmentConsumptionFact persitants in database.
     *
     * @return a list of all ApartmentConsumptionFact persistant.
     */
    @Override
    public List<ApartmentConsumptionFact> findAll() {
	// Note: the findAllApartmentConsumptionsObs JPQL query is defined in the ApartmentConsumptionFact.java file
	List apartmentConsumptionsFact = em.createNamedQuery("ApartmentConsumptionFact.findAllApartmentConsumptionsFacts").getResultList();
	return apartmentConsumptionsFact;
    }

    /**
     * Get the last fact for an apartment.
     *
     * @param apartment the apartment.
     * @return the last fact.
     */
    @Override
    public ApartmentConsumptionFact getLastApartmentFact(Apartment apartment) {
	List<ApartmentConsumptionFact> apartmentConsumptionsFacts = em.createNamedQuery("ApartmentConsumptionFact.getLastApartmentFact").setParameter("apartment", apartment).setMaxResults(1).getResultList();

	if (apartmentConsumptionsFacts.isEmpty()) {
	    return null;
	} else {
	    return apartmentConsumptionsFacts.get(0);
	}
    }
}
