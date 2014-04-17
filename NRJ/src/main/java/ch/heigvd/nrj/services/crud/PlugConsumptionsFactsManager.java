package ch.heigvd.nrj.services.crud;

import ch.heigvd.nrj.exceptions.EntityNotFoundException;
import ch.heigvd.nrj.model.Plug;
import ch.heigvd.nrj.model.PlugConsumptionFact;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * DAO service, implementing CRUD operations on the PlugConsumptionFact entity.
 * The class uses the JPA entity manager to interact with the DB. It returns JPA
 * entities to its clients.
 *
 * @author nicolas
 */
@Stateless
public class PlugConsumptionsFactsManager implements PlugConsumptionsFactsManagerLocal {

    @PersistenceContext
    private EntityManager em;

    @EJB
    PlugsManagerLocal plugsManager;

    /**
     * Adds a new PlugConsumptionsFacts in DB
     *
     * @param plugConsumptionFactData
     * @return the id of the created PlugConsumptionsFacts
     */
    @Override
    public long create(PlugConsumptionFact plugConsumptionFactData) {
        PlugConsumptionFact newPlugConsumptionFact = new PlugConsumptionFact(plugConsumptionFactData);

        // Set la plug
        Plug p = newPlugConsumptionFact.getPlug();
        try {
            // Rechercher la plug
            p = plugsManager.findById(p.getId());
        } catch (EntityNotFoundException ex) {
            Logger.getLogger(RoomsManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        newPlugConsumptionFact.setPlug(p);
        em.persist(newPlugConsumptionFact);
        p.addPlugConsumptionFact(newPlugConsumptionFact);

        em.persist(newPlugConsumptionFact);
        em.flush();
        return newPlugConsumptionFact.getId();
    }

    /**
     * Updates a PlugConsumptionsFacts from DB
     *
     * @param newState
     * @throws EntityNotFoundException
     */
    @Override
    public void update(PlugConsumptionFact newState) throws EntityNotFoundException {
        em.merge(newState);
    }

    /**
     * Deletes a PlugConsumptionsFacts with the given id
     *
     * @param id, the PlugConsumptionsFacts's id
     * @throws EntityNotFoundException
     */
    @Override
    public void delete(long id) throws EntityNotFoundException {
        PlugConsumptionFact plugConsumptionFactToDelete = findById(id);
        em.remove(plugConsumptionFactToDelete);
    }

    /**
     * Returns a PlugConsumptionsFacts from DB with the given id
     *
     * @param id, the PlugConsumptionsFacts's id
     * @return a PlugConsumptionsFacts
     * @throws EntityNotFoundException
     */
    @Override
    public PlugConsumptionFact findById(long id) throws EntityNotFoundException {
        PlugConsumptionFact existingPlugConsumptionFact = em.find(PlugConsumptionFact.class, id);
        if (existingPlugConsumptionFact == null) {
            throw new EntityNotFoundException();
        }
        return existingPlugConsumptionFact;
    }

    /**
     * Returns all the PlugConsumptionsFacts from DB
     *
     * @return a List of PlugConsumptionsFacts
     */
    @Override
    public List<PlugConsumptionFact> findAll() {
        // Note: the findAllPlugConsumptionsObs JPQL query is defined in the PlugConsumptionFact.java file
        List plugConsumptionsFacts = em.createNamedQuery("PlugConsumptionFact.findAllPlugConsumptionsFacts").getResultList();
        return plugConsumptionsFacts;
    }

    /**
     * Returns the PlugConsumptionsFacts in a Period
     *
     * @param debut, a Date of start of Period
     * @param fin, a Date of end of Period
     * @return a List of PlugConsumptionsFacts
     * @throws EntityNotFoundException
     */
    @Override
    public List<PlugConsumptionFact> findByPeriod(Date debut, Date fin) throws EntityNotFoundException {
        List<PlugConsumptionFact> plugConsumptionsFacts = em.createNamedQuery("PlugConsumptionFact.findAllPlugConsumptionsFactsForAPeriod").setParameter("debut", debut).setParameter("fin", fin).getResultList();

        return plugConsumptionsFacts;
    }

    /**
     * Returns the last PlugConsumptionsFacts of the given Plug
     *
     * @param plug, a Plug
     * @return a List of PlugConsumptionsFacts
     */
    @Override
    public PlugConsumptionFact getLastPlugFact(Plug plug) {
        List<PlugConsumptionFact> plugConsumptionsFacts = em.createNamedQuery("PlugConsumptionFact.getLastFact").setParameter("plug", plug).setMaxResults(1).getResultList();

        if (plugConsumptionsFacts.isEmpty()) {
            return null;
        } else {
            return plugConsumptionsFacts.get(0);
        }

    } //getLastFact

    /**
     * Returns all the PlugConsumptionsFacts after a given Date
     *
     * @param plug, a Plug
     * @param timestamp, a Time to look after
     * @return
     */
    @Override
    public List<PlugConsumptionFact> getConsumptionFactsAfterTime(Plug plug, Date timestamp) {
        List<PlugConsumptionFact> factsList = new ArrayList<>();

        factsList = em.createNamedQuery("PlugConsumptionFact.getConsumptionFactsAfterTime").setParameter("plug", plug).setParameter("time", timestamp).setMaxResults(1).getResultList();

        if (factsList.isEmpty()) {
            return null;
        } else {
            return factsList;
        }

    }

}
