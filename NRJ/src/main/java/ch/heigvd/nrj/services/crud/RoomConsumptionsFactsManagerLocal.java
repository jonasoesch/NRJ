package ch.heigvd.nrj.services.crud;

import ch.heigvd.nrj.exceptions.EntityNotFoundException;
import ch.heigvd.nrj.model.RoomConsumptionFacts;
import java.util.List;
import javax.ejb.Local;

/**
 * The contract fulfilled by the RoomConsumptionsManager DAO.
 * 
 * @author rschmutz
 */
@Local
public interface RoomConsumptionsFactsManagerLocal {

	long create(RoomConsumptionFacts roomConsumptionObsData);

	void update(RoomConsumptionFacts newState) throws EntityNotFoundException;

	void delete(long id) throws EntityNotFoundException;

	RoomConsumptionFacts findById(long id) throws EntityNotFoundException;

	List<RoomConsumptionFacts> findAll();

	
}
