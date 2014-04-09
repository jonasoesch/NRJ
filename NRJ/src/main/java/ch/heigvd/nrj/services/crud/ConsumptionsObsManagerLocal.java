package ch.heigvd.nrj.services.crud;

import ch.heigvd.nrj.exceptions.EntityNotFoundException;
import ch.heigvd.nrj.model.ConsumptionObs;
import java.util.List;
import javax.ejb.Local;

/**
 * The contract fulfilled by the ConsumptionsManager DAO.
 * 
 * @author rschmutz
 */
@Local
public interface ConsumptionsObsManagerLocal {

	long create(ConsumptionObs consumptionData);

	void update(ConsumptionObs newState) throws EntityNotFoundException;

	void delete(long id) throws EntityNotFoundException;

	ConsumptionObs findById(long id) throws EntityNotFoundException;

	List<ConsumptionObs> findAll();

	
}
