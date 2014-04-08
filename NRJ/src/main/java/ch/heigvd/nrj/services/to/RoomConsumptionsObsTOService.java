package ch.heigvd.nrj.services.to;

import ch.heigvd.nrj.model.RoomConsumptionObs;
import ch.heigvd.nrj.to.PublicRoomConsumptionObsTO;
import javax.ejb.Stateless;

/**
 * This class converts JPA entities into POJO transfer objects, and vice versa
 * 
 * @author rschmutz
 */
@Stateless
public class RoomConsumptionsObsTOService implements RoomConsumptionsObsTOServiceLocal {

	@Override
	public PublicRoomConsumptionObsTO buildPublicRoomConsumptionObsTO(RoomConsumptionObs source) {
		PublicRoomConsumptionObsTO to = new PublicRoomConsumptionObsTO(source.getId(), source.getTimestampHour(), source.getAvgKW());
		return to;
	}   

	@Override
	public void updateRoomConsumptionObsEntity(RoomConsumptionObs existingEntity, PublicRoomConsumptionObsTO newState) {
		existingEntity.setTimestampHour(newState.getTimestampHour());
		existingEntity.setAvgKW(newState.getAvgKW());
	}
	
}
