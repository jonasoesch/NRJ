package ch.heigvd.nrj.services.crud;

import ch.heigvd.nrj.exceptions.EntityNotFoundException;
import ch.heigvd.nrj.model.PlugConsumptionFacts;
import java.util.List;
import javax.ejb.Local;

/**
 * The contract fulfilled by the PlugConsumptionsManager DAO.
 * 
 * @author rschmutz
 */
@Local
public interface PlugConsumptionsFactsManagerLocal {

	long create(PlugConsumptionFacts plugConsumptionObsData);

	void update(PlugConsumptionFacts newState) throws EntityNotFoundException;

	void delete(long id) throws EntityNotFoundException;

	PlugConsumptionFacts findById(long id) throws EntityNotFoundException;

	List<PlugConsumptionFacts> findAll();

	
}
