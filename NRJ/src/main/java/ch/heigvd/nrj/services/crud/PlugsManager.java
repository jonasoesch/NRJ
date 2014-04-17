package ch.heigvd.nrj.services.crud;

import ch.heigvd.nrj.exceptions.EntityNotFoundException;
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
 * DAO service, implementing CRUD operations on the Plug entity. The class uses
 * the JPA entity manager to interact with the DB. It returns JPA entities to
 * its clients.
 *
 * @author nicolas
 */
@Stateless
public class PlugsManager implements PlugsManagerLocal {

    @EJB
    RoomsManagerLocal roomsManager;

    @PersistenceContext
    private EntityManager em;

    /**
     * Adds a new Plug in DB
     *
     * @param plugData
     * @return the id of the created Plug
     */
    @Override
    public long create(Plug plugData) {
        Plug newPlug = new Plug(plugData);
        Room r = newPlug.getRoom();
        try {
            // Rechercher la piece
            r = roomsManager.findById(r.getId());
        } catch (EntityNotFoundException ex) {
            Logger.getLogger(RoomsManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        newPlug.setRoom(r);
        em.persist(newPlug);
        r.addPlug(newPlug);
        em.flush();
        return newPlug.getId();
    }

    /**
     * Updates a Plug in DB
     *
     * @param newState
     * @throws EntityNotFoundException
     */
    @Override
    public void update(Plug newState) throws EntityNotFoundException {
        em.merge(newState);
    }

    /**
     * Deletes a Plug from DB
     *
     * @param id, the Plug's id
     * @throws EntityNotFoundException
     */
    @Override
    public void delete(long id) throws EntityNotFoundException {
        Plug plugToDelete = findById(id);
        em.remove(plugToDelete);
    }

    /**
     * Returns the Plug from DB with the given id
     *
     * @param id, the Plug's id
     * @return a Plug
     * @throws EntityNotFoundException
     */
    @Override
    public Plug findById(long id) throws EntityNotFoundException {
        if (id == 0) {
            throw new NullPointerException();
        }

        Plug existingPlug = em.find(Plug.class, id);
        if (existingPlug == null) {
            throw new EntityNotFoundException();
        }
        return existingPlug;
    }

    /**
     * Returns all the Plugs from Db
     *
     * @return a List of Plugs
     */
    @Override
    public List<Plug> findAll() {
        // Note: the findAllPlugs JPQL query is defined in the Plug.java file
        List plugs = em.createNamedQuery("Plug.findAllPlugs").getResultList();
        return plugs;
    }

}
