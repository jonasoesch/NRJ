package ch.heigvd.nrj.services.crud;

import ch.heigvd.nrj.exceptions.EntityNotFoundException;
import ch.heigvd.nrj.model.Apartment;
import ch.heigvd.nrj.model.Room;
import java.util.List;
import javax.ejb.Local;

/**
 * The contract fulfilled by the RoomsManager DAO.
 * 
 * @author rschmutz
 */
@Local
public interface RoomsManagerLocal {

	long create(Room roomData);

	void update(Room newState) throws EntityNotFoundException;

	void delete(long id) throws EntityNotFoundException;

	Room findById(long id) throws EntityNotFoundException;

	List<Room> findAll();
	
	List<Room> findAllByApartment(Apartment a);

	
}
