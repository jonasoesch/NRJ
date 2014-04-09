package ch.heigvd.nrj.services.to;

import ch.heigvd.nrj.model.RoomConsumptionFacts;
import ch.heigvd.nrj.to.PublicRoomConsumptionFactsTO;
import javax.ejb.Stateless;

/**
 * This class converts JPA entities into POJO transfer objects, and vice versa
 * 
 * @author rschmutz
 */
@Stateless
public class RoomConsumptionsFactsTOService implements RoomConsumptionsFactsTOServiceLocal {

	@Override
	public PublicRoomConsumptionFactsTO buildPublicRoomConsumptionObsTO(RoomConsumptionFacts source) {
		PublicRoomConsumptionFactsTO to = new PublicRoomConsumptionFactsTO(source.getId(), source.getTimestampHour(), source.getAvgKW());
		return to;
	}   

	@Override
	public void updateRoomConsumptionObsEntity(RoomConsumptionFacts existingEntity, PublicRoomConsumptionFactsTO newState) {
		existingEntity.setTimestampHour(newState.getTimestampHour());
		existingEntity.setAvgKW(newState.getAvgKW());
	}
	
}
