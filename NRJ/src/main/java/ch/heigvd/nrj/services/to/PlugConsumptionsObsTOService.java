package ch.heigvd.nrj.services.to;

import ch.heigvd.nrj.model.PlugConsumptionObs;
import ch.heigvd.nrj.to.PublicPlugConsumptionObsTO;
import javax.ejb.Stateless;

/**
 * This class converts JPA entities into POJO transfer objects, and vice versa
 * 
 * @author rschmutz
 */
@Stateless
public class PlugConsumptionsObsTOService implements PlugConsumptionsObsTOServiceLocal {

	@Override
	public PublicPlugConsumptionObsTO buildPublicPlugConsumptionObsTO(PlugConsumptionObs source) {
		PublicPlugConsumptionObsTO to = new PublicPlugConsumptionObsTO(source.getId(), source.getTimestampHour(), source.getAvgKW());
		return to;
	}   

	@Override
	public void updatePlugConsumptionObsEntity(PlugConsumptionObs existingEntity, PublicPlugConsumptionObsTO newState) {
		existingEntity.setTimestampHour(newState.getTimestampHour());
		existingEntity.setAvgKW(newState.getAvgKW());
	}
	
}
