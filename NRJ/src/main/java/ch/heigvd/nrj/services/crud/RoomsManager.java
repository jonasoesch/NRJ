package ch.heigvd.nrj.services.crud;

import ch.heigvd.nrj.exceptions.EntityNotFoundException;
import ch.heigvd.nrj.model.Apartment;
import ch.heigvd.nrj.model.Room;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * DAO service, implementing CRUD operations on the Room entity.
 * The class uses the JPA entity manager to interact with the DB.
 * It returns JPA entities to its clients.
 * 
 * @author nicolas
 */
@Stateless
public class RoomsManager implements RoomsManagerLocal {

	@PersistenceContext
	private EntityManager em;

	@Override
	public long create(Room roomData) {
		Room newRoom = new Room(roomData);
		em.persist(newRoom);
		return newRoom.getId();
	}

	@Override
	public void update(Room newState) throws EntityNotFoundException {
		em.merge(newState);
	}

	@Override
	public void delete(long id) throws EntityNotFoundException {
		Room roomToDelete = findById(id);
		em.remove(roomToDelete);
	}

	@Override
	public Room findById(long id) throws EntityNotFoundException {
		Room existingRoom = em.find(Room.class, id);
		if (existingRoom == null) {
			throw new EntityNotFoundException();
		}
		return existingRoom;
	}

	@Override
	public List<Room> findAll() {
		// Note: the findAllRooms JPQL query is defined in the Room.java file
		List rooms = em.createNamedQuery("Room.findAllRooms").getResultList();
		return rooms;
	}
	
	@Override
	public List<Room> findAllByApartment(Apartment a) {
		// Note: the findAllRooms JPQL query is defined in the Room.java file
		List rooms = em.createQuery("Room.findAllByApartment").setParameter("apartment_id", a.getId()).getResultList();

		return rooms;
	}
	
}
