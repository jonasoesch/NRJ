package ch.heigvd.nrj.services.crud;

import ch.heigvd.nrj.exceptions.EntityNotFoundException;
import ch.heigvd.nrj.model.Apartment;
import ch.heigvd.nrj.model.ApartmentConsumptionFact;
import ch.heigvd.nrj.model.RoomConsumptionFact;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * DAO service, implementing CRUD operations on the ApartmentConsumptionFact entity. 
 * The class uses the JPA entity manager to interact with the DB. 
 * It returns JPA entities to its clients.
 * 
 * @author nicolas
 */
@Stateless
public class ApartmentConsumptionsFactsManager implements ApartmentConsumptionsFactsManagerLocal {

	@PersistenceContext
	private EntityManager em;

	@Override
	public long create(ApartmentConsumptionFact apartmentConsumptionFactData) {
		ApartmentConsumptionFact newApartmentConsumptionFact = new ApartmentConsumptionFact(apartmentConsumptionFactData);
		em.persist(newApartmentConsumptionFact);
                em.flush();
		return newApartmentConsumptionFact.getId();
	}

	@Override
	public void update(ApartmentConsumptionFact newState) throws EntityNotFoundException {
		em.merge(newState);
	}

	@Override
	public void delete(long id) throws EntityNotFoundException {
		ApartmentConsumptionFact apartmentConsumptionFactToDelete = findById(id);
		em.remove(apartmentConsumptionFactToDelete);
	}

	@Override
	public ApartmentConsumptionFact findById(long id) throws EntityNotFoundException {
		ApartmentConsumptionFact existingApartmentConsumptionFact = em.find(ApartmentConsumptionFact.class, id);
		if (existingApartmentConsumptionFact == null) {
			throw new EntityNotFoundException();
		}
		return existingApartmentConsumptionFact;
	}

	@Override
	public List<ApartmentConsumptionFact> findAll() {
		// Note: the findAllApartmentConsumptionsObs JPQL query is defined in the ApartmentConsumptionFact.java file
		List apartmentConsumptionsFact = em.createNamedQuery("ApartmentConsumptionFact.findAllApartmentConsumptionsFacts").getResultList();
		return apartmentConsumptionsFact;
	}
        
        @Override
        public ApartmentConsumptionFact getLastApartmentFact(Apartment apartment) {
        List<ApartmentConsumptionFact> apartmentConsumptionsFacts = em.createNamedQuery("ApartmentConsumptionFact.getLastApartmentFact").setParameter("apartment", apartment).setMaxResults(1).getResultList();

        if (apartmentConsumptionsFacts.isEmpty()){
            return null;
        } else {
            return apartmentConsumptionsFacts.get(0);
        }
    }
	
}
