package ch.heigvd.nrj.services.to;

import ch.heigvd.nrj.model.RoomConsumptionFact;
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
    public PublicRoomConsumptionFactsTO buildPublicRoomConsumptionFactTO(RoomConsumptionFact source) {
	PublicRoomConsumptionFactsTO to = new PublicRoomConsumptionFactsTO(source.getId(), source.getTimestampHour(), source.getAvgKW());
	return to;
    }

    @Override
    public void updateRoomConsumptionFactEntity(RoomConsumptionFact existingEntity, PublicRoomConsumptionFactsTO newState) {
	existingEntity.setTimestampHour(newState.getTimestampHour());
	existingEntity.setAvgKW(newState.getAvgKW());
	// existingEntity.setRoom(newState.getRoom());
    }
}
