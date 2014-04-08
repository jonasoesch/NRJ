package ch.heigvd.nrj.services.crud;

import ch.heigvd.nrj.exceptions.EntityNotFoundException;
import ch.heigvd.nrj.model.Consumption;
import java.util.List;
import javax.ejb.Local;

/**
 * The contract fulfilled by the ConsumptionsManager DAO.
 * 
 * @author rschmutz
 */
@Local
public interface ConsumptionsManagerLocal {

	long create(Consumption consumptionData);

	void update(Consumption newState) throws EntityNotFoundException;

	void delete(long id) throws EntityNotFoundException;

	Consumption findById(long id) throws EntityNotFoundException;

	List<Consumption> findAll();

	
}
