package ch.heigvd.nrj.services.crud;

import ch.heigvd.nrj.exceptions.EntityNotFoundException;
import ch.heigvd.nrj.model.Apartment;
import ch.heigvd.nrj.model.ConsumptionObs;
import ch.heigvd.nrj.model.Plug;
import ch.heigvd.nrj.model.Room;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
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

    @EJB
    PlugsManagerLocal plugsManager;
    @PersistenceContext
    private EntityManager em;

    @Override
    public long create(ConsumptionObs consumption) {
        // Add la consommation à la plug
        Plug plug = consumption.getPlug();
        try {
            // Rechercher l'appartement
            plug = plugsManager.findById(plug.getId());
        } catch (EntityNotFoundException ex) {
            Logger.getLogger(RoomsManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        // TODO ? em.persist(plug);

        // Add la plug à cette consommation
        //consumption.setPlug(plug);
        consumption.setPlug(plug);
        em.persist(consumption);
        plug.addConsumptionObs(consumption);
        em.flush();
        return consumption.getId();
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
        List consumptionsObs = em.createNamedQuery("ConsumptionObs.findAllConsumptionsObs").getResultList();
        return consumptionsObs;
    }
}
