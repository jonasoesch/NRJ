package ch.heigvd.nrj.services.crud;

import ch.heigvd.nrj.exceptions.EntityNotFoundException;
import ch.heigvd.nrj.model.Consumption;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * DAO service, implementing CRUD operations on the Consumption entity. The
 * class uses the JPA entity manager to interact with the DB. It returns JPA
 * entities to its clients.
 *
 * @author Olivier Liechti
 */
@Stateless
public class ConsumptionsManager implements ConsumptionsManagerLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public long create(Consumption consumptionData) {
        Consumption newConsumption = new Consumption(consumptionData);
        em.persist(newConsumption);
        return newConsumption.getId();
    }

    @Override
    public void update(Consumption newState) throws EntityNotFoundException {
        em.merge(newState);
    }

    @Override
    public void delete(long id) throws EntityNotFoundException {
        Consumption apartmentToDelete = findById(id);
        em.remove(apartmentToDelete);
    }

    @Override
    public Consumption findById(long id) throws EntityNotFoundException {
        Consumption existingConsumption = em.find(Consumption.class, id);
        if (existingConsumption == null) {
            throw new EntityNotFoundException();
        }
        return existingConsumption;
    }

    @Override
    public List<Consumption> findAll() {
// Note: the findAllConsumptions JPQL query is defined in the Consumption.java file
        List apartments = em.createNamedQuery("Consumption.findAllConsumptions").getResultList();
        return apartments;
    }
}
