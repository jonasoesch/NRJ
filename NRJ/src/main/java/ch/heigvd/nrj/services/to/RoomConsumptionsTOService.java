package ch.heigvd.nrj.services.to;

import ch.heigvd.nrj.model.RoomConsumptionObs;
import ch.heigvd.nrj.to.PublicRoomConsumptionTO;
import javax.ejb.Stateless;

/**
 * This class converts JPA entities into POJO transfer objects, and vice versa
 * 
 * @author rschmutz
 */
@Stateless
public class RoomConsumptionsTOService implements RoomConsumptionsTOServiceLocal {

	@Override
	public PublicRoomConsumptionTO buildPublicRoomConsumptionTO(RoomConsumptionObs source) {
		PublicRoomConsumptionTO to = new PublicRoomConsumptionTO(source.getId(), source.getTimestampHour(), source.getAvgKW());
		return to;
	}   

	@Override
	public void updateRoomConsumptionEntity(RoomConsumptionObs existingEntity, PublicRoomConsumptionTO newState) {
		existingEntity.setTimestampHour(newState.getTimestampHour());
		existingEntity.setAvgKW(newState.getAvgKW());
	}
	
}
