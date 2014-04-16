package ch.heigvd.nrj.services.crud;

import ch.heigvd.nrj.exceptions.EntityNotFoundException;
import ch.heigvd.nrj.model.History;
import ch.heigvd.nrj.model.Plug;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * DAO service, implementing CRUD operations on the History entity. The class
 * uses the JPA entity manager to interact with the DB. It returns JPA entities
 * to its clients.
 *
 * @author Olivier Liechti
 */
@Stateless
public class HistoriesManager implements HistoriesManagerLocal {

    @PersistenceContext
    private EntityManager em;
    
    @EJB
    PlugsManagerLocal plugsManager;

    @Override
    public long create(History historyData) {
        History newHistory = new History(historyData);

        // Set la plug
        Plug p = newHistory.getPlug();
        try {
            // Rechercher la plug
            p = plugsManager.findById(p.getId());
        } catch (EntityNotFoundException ex) {
            Logger.getLogger(RoomsManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        newHistory.setPlug(p);
        em.persist(newHistory);
        p.addHistory(newHistory);
        em.flush();
        return newHistory.getId();
    }

    @Override
    public void update(History newState) throws EntityNotFoundException {
        em.merge(newState);
    }

    @Override
    public void delete(long id) throws EntityNotFoundException {
        History apartmentToDelete = findById(id);
        em.remove(apartmentToDelete);
    }

    @Override
    public History findById(long id) throws EntityNotFoundException {
        History existingHistorie = em.find(History.class, id);
        if (existingHistorie == null) {
            throw new EntityNotFoundException();
        }
        return existingHistorie;
    }

    @Override
    public List<History> findAll() {
// Note: the findAllHistories JPQL query is defined in the Historie.java file
        List apartments = em.createNamedQuery("Histories.findAllHistories").getResultList();
        return apartments;
    }

    public History findLast() {
        List<History> last = em.createNamedQuery("Histories.findLast").getResultList();
        return last.get(0);
    }
}
