package ch.heigvd.nrj.services.crud;

import ch.heigvd.nrj.exceptions.EntityNotFoundException;
import ch.heigvd.nrj.model.Room;
import ch.heigvd.nrj.model.RoomConsumptionFact;
import java.util.List;
import javax.ejb.Local;

/**
 * The contract fulfilled by the RoomConsumptionsManager DAO.
 * 
 * @author rschmutz
 */
@Local
public interface RoomConsumptionsFactsManagerLocal {

	long create(RoomConsumptionFact roomConsumptionFactData);

	void update(RoomConsumptionFact newState) throws EntityNotFoundException;

	void delete(long id) throws EntityNotFoundException;

	RoomConsumptionFact findById(long id) throws EntityNotFoundException;

	List<RoomConsumptionFact> findAll();
        
        RoomConsumptionFact getLastRoomFact(Room room);

	
}
