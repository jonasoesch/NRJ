package ch.heigvd.nrj.services.crud;

import ch.heigvd.nrj.exceptions.EntityNotFoundException;
import ch.heigvd.nrj.model.PlugConsumptionFact;
import java.util.List;
import javax.ejb.Local;

/**
 * The contract fulfilled by the PlugConsumptionsManager DAO.
 * 
 * @author rschmutz
 */
@Local
public interface PlugConsumptionsFactsManagerLocal {

	long create(PlugConsumptionFact plugConsumptionFactData);

	void update(PlugConsumptionFact newState) throws EntityNotFoundException;

	void delete(long id) throws EntityNotFoundException;

	PlugConsumptionFact findById(long id) throws EntityNotFoundException;

	List<PlugConsumptionFact> findAll();

	
}