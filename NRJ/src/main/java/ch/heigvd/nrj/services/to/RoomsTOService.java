package ch.heigvd.nrj.services.to;

import ch.heigvd.nrj.model.Plug;
import ch.heigvd.nrj.model.Room;
import ch.heigvd.nrj.to.PublicPlugTO;
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
		PublicRoomTO to = new PublicRoomTO(source.getId(), source.getName(), source.getRoomConsumptionsFacts());
		for (Plug plug : source.getPlugs()) {
		    PublicPlugTO plugTO = new PublicPlugTO(plug.getId(), plug.getName(), plug.getAlwaysOn(), plug.getRoom(), plug.getHistories(), plug.getPlugConsumptionsFacts(), plug.getWarnings());
		    to.addPlug(plugTO);
		}
		
		return to;
	}

	@Override
	public void updateRoomEntity(Room existingEntity, PublicRoomTO newState) {
		existingEntity.setName(newState.getName());
		existingEntity.setRoomConsumptionsFacts(newState.getRoomConsumptionsFacts());
	}
	
}
