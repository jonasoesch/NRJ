package ch.heigvd.nrj.services.crud;

import ch.heigvd.nrj.exceptions.EntityNotFoundException;
import ch.heigvd.nrj.model.RoomConsumption;
import java.util.List;
import javax.ejb.Local;

/**
 * The contract fulfilled by the RoomConsumptionsManager DAO.
 * 
 * @author rschmutz
 */
@Local
public interface RoomConsumptionsObsManagerLocal {

	long create(RoomConsumption roomConsumptionObsData);

	void update(RoomConsumption newState) throws EntityNotFoundException;

	void delete(long id) throws EntityNotFoundException;

	RoomConsumption findById(long id) throws EntityNotFoundException;

	List<RoomConsumption> findAll();

	
}
