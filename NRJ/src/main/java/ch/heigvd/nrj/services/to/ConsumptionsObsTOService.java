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
	public PublicConsumptionObsTO buildPublicConsumptionTO(ConsumptionObs source) {
		PublicConsumptionObsTO to = new PublicConsumptionObsTO(source.getId(), source.getTimestampMinute(), source.getkW());
		return to;
	}

	@Override
	public void updateConsumptionEntity(ConsumptionObs existingEntity, PublicConsumptionObsTO newState) {
		existingEntity.setTimestampMinute(newState.getTimestampMinute());
		existingEntity.setkW(newState.getKW());
	}
	
}
