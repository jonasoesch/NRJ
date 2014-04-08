package ch.heigvd.nrj.services.crud;

import ch.heigvd.nrj.exceptions.EntityNotFoundException;
import ch.heigvd.nrj.model.PlugConsumption;
import java.util.List;
import javax.ejb.Local;

/**
 * The contract fulfilled by the PlugConsumptionsManager DAO.
 * 
 * @author rschmutz
 */
@Local
public interface PlugConsumptionsManagerLocal {

	long create(PlugConsumption plugConsumptionData);

	void update(PlugConsumption newState) throws EntityNotFoundException;

	void delete(long id) throws EntityNotFoundException;

	PlugConsumption findById(long id) throws EntityNotFoundException;

	List<PlugConsumption> findAll();

	
}
