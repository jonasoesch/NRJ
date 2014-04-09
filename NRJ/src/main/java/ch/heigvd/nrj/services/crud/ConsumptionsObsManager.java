package ch.heigvd.nrj.services.crud;

import ch.heigvd.nrj.exceptions.EntityNotFoundException;
import ch.heigvd.nrj.model.ConsumptionObs;
import ch.heigvd.nrj.model.PlugConsumptionFact;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * DAO service, implementing CRUD operations on the ConsumptionObs entity. The
 * class uses the JPA entity manager to interact with the DB. It returns JPA
 * entities to its clients.
 *
 * @author Olivier Liechti
 */
@Stateless
public class ConsumptionsObsManager implements ConsumptionsObsManagerLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public long create(ConsumptionObs consumptionData) {
        ConsumptionObs newConsumption = new ConsumptionObs(consumptionData);
        em.persist(newConsumption);
        return newConsumption.getId();
    }

    @Override
    public void update(ConsumptionObs newState) throws EntityNotFoundException {
        em.merge(newState);
    }

    @Override
    public void delete(long id) throws EntityNotFoundException {
        ConsumptionObs apartmentToDelete = findById(id);
        em.remove(apartmentToDelete);
    }

    @Override
    public ConsumptionObs findById(long id) throws EntityNotFoundException {
        ConsumptionObs existingConsumption = em.find(ConsumptionObs.class, id);
        if (existingConsumption == null) {
            throw new EntityNotFoundException();
        }
        return existingConsumption;
    }

    @Override
    public List<ConsumptionObs> findAll() {
// Note: the findAllConsumptions JPQL query is defined in the ConsumptionObs.java file
        List apartments = em.createNamedQuery("ConsumptionObs.findAllConsumptionsObs").getResultList();
        return apartments;
    }
}
