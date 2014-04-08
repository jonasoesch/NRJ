package ch.heigvd.nrj.services.crud;

import ch.heigvd.nrj.exceptions.EntityNotFoundException;
import ch.heigvd.nrj.model.PlugConsumptionObs;
import java.util.List;
import javax.ejb.Local;

/**
 * The contract fulfilled by the PlugConsumptionsManager DAO.
 * 
 * @author rschmutz
 */
@Local
public interface PlugConsumptionsObsManagerLocal {

	long create(PlugConsumptionObs plugConsumptionObsData);

	void update(PlugConsumptionObs newState) throws EntityNotFoundException;

	void delete(long id) throws EntityNotFoundException;

	PlugConsumptionObs findById(long id) throws EntityNotFoundException;

	List<PlugConsumptionObs> findAll();

	
}
