package ch.heigvd.nrj.services.crud;

import ch.heigvd.nrj.exceptions.EntityNotFoundException;
import ch.heigvd.nrj.model.RoomConsumptionObs;
import java.util.List;
import javax.ejb.Local;

/**
 * The contract fulfilled by the RoomConsumptionsManager DAO.
 * 
 * @author rschmutz
 */
@Local
public interface RoomConsumptionsObsManagerLocal {

	long create(RoomConsumptionObs roomConsumptionObsData);

	void update(RoomConsumptionObs newState) throws EntityNotFoundException;

	void delete(long id) throws EntityNotFoundException;

	RoomConsumptionObs findById(long id) throws EntityNotFoundException;

	List<RoomConsumptionObs> findAll();

	
}
