package ch.heigvd.nrj.services.crud;

import ch.heigvd.nrj.exceptions.EntityNotFoundException;
import ch.heigvd.nrj.model.Plug;
import ch.heigvd.nrj.model.Warning;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * DAO service, implementing CRUD operations on the Warning entity. The class
 * uses the JPA entity manager to interact with the DB. It returns JPA entities
 * to its clients.
 *
 * @author rschmutz
 */
@Stateless
public class WarningsManager implements WarningsManagerLocal {

    @EJB
    PlugsManagerLocal plugsManager;

    @PersistenceContext
    private EntityManager em;

    /**
     * Adds a new Warning in DB
     *
     * @param warningData
     * @return the id of the created Warning
     */
    @Override
    public long create(Warning warningData) {
        Warning newWarning = new Warning(warningData);

        // Set la plug
        Plug p = newWarning.getPlug();
        try {
            // Rechercher la plug
            p = plugsManager.findById(p.getId());
        } catch (EntityNotFoundException ex) {
            Logger.getLogger(RoomsManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        newWarning.setPlug(p);
        em.persist(newWarning);
        p.addWarning(newWarning);
        em.flush();
        return newWarning.getId();
    }

    /**
     * Updates an existant Warning from DB
     *
     * @param newState, le new State of the Warning
     * @throws EntityNotFoundException
     */
    @Override
    public void update(Warning newState) throws EntityNotFoundException {
        em.merge(newState);
    }

    /**
     * Deletes a Warning from DB
     *
     * @param id, the id of the Warning to delete
     * @throws EntityNotFoundException
     */
    @Override
    public void delete(long id) throws EntityNotFoundException {
        Warning warningToDelete = findById(id);
        em.remove(warningToDelete);
    }

    /**
     * Returns a Warning with the given id from DB
     *
     * @param id, the Warning's id
     * @return a Warning
     * @throws EntityNotFoundException
     */
    @Override
    public Warning findById(long id) throws EntityNotFoundException {
        Warning existingWarning = em.find(Warning.class, id);
        if (existingWarning == null) {
            throw new EntityNotFoundException();
        }
        return existingWarning;
    }

    /**
     * Returns a List of all the Warnings in DB
     *
     * @return a List of Warnings
     */
    @Override
    public List<Warning> findAll() {
        // Note: the findAllPlugs JPQL query is defined in the Plug.java file
        List warning = em.createNamedQuery("Warning.findAllWarnings").getResultList();
        return warning;
    }

}
