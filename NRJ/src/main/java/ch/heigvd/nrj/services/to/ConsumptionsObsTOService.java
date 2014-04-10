package ch.heigvd.nrj.services.to;

import ch.heigvd.nrj.model.ConsumptionObs;
import ch.heigvd.nrj.to.PublicConsumptionObsTO;
import javax.ejb.Stateless;

/**
 * This class converts JPA entities into POJO transfer objects, and vice versa
 * 
 * @author rschmutz
 */
@Stateless
public class ConsumptionsObsTOService implements ConsumptionsObsTOServiceLocal {

	@Override
	public PublicConsumptionObsTO buildPublicConsumptionObsTO(ConsumptionObs source) {
		PublicConsumptionObsTO to = new PublicConsumptionObsTO(source.getId(), source.getTimestampHour(), source.getkW(), source.getPlug());
		return to;
	}

	@Override
	public void updateConsumptionObsEntity(ConsumptionObs existingEntity, PublicConsumptionObsTO newState) {
		existingEntity.setTimestampMinute(newState.getTimestampMinute());
		existingEntity.setkW(newState.getkW());
		existingEntity.setPlug(newState.getPlug());
	}
	
}
