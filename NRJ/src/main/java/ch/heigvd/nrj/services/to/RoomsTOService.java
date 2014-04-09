package ch.heigvd.nrj.services.to;

import ch.heigvd.nrj.model.Room;
import ch.heigvd.nrj.to.PublicRoomTO;
import javax.ejb.Stateless;

/**
 * This class converts JPA entities into POJO transfer objects, and vice versa
 * 
 * @author rschmutz
 */
@Stateless
public class RoomsTOService implements RoomsTOServiceLocal {

	@Override
	public PublicRoomTO buildPublicRoomTO(Room source) {
		PublicRoomTO to = new PublicRoomTO(source.getId(), source.getName(), source.getApartment());
		return to;
	}

	@Override
	public void updateRoomEntity(Room existingEntity, PublicRoomTO newState) {
		existingEntity.setName(newState.getName());
		existingEntity.setApartment(newState.getApartment());
	}
	
}
