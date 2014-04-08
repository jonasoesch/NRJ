package ch.heigvd.nrj.services.crud;

import ch.heigvd.nrj.exceptions.EntityNotFoundException;
import ch.heigvd.nrj.model.History;
import java.util.List;
import javax.ejb.Local;

/**
 * The contract fulfilled by the HistoriesManager DAO.
 * 
 * @author rschmutz
 */
@Local
public interface HistoriesManagerLocal {

	long create(History historyData);

	void update(History newState) throws EntityNotFoundException;

	void delete(long id) throws EntityNotFoundException;

	History findById(long id) throws EntityNotFoundException;

	List<History> findAll();

	
}
