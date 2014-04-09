package ch.heigvd.nrj.services.to;

import ch.heigvd.nrj.model.PlugConsumptionFacts;
import ch.heigvd.nrj.to.PublicPlugConsumptionFactsTO;
import javax.ejb.Stateless;

/**
 * This class converts JPA entities into POJO transfer objects, and vice versa
 * 
 * @author rschmutz
 */
@Stateless
public class PlugConsumptionsFactsTOService implements PlugConsumptionsFactsTOServiceLocal {

	@Override
	public PublicPlugConsumptionFactsTO buildPublicPlugConsumptionObsTO(PlugConsumptionFacts source) {
		PublicPlugConsumptionFactsTO to = new PublicPlugConsumptionFactsTO(source.getId(), source.getTimestampHour(), source.getAvgKW());
		return to;
	}   

	@Override
	public void updatePlugConsumptionObsEntity(PlugConsumptionFacts existingEntity, PublicPlugConsumptionFactsTO newState) {
		existingEntity.setTimestampHour(newState.getTimestampHour());
		existingEntity.setAvgKW(newState.getAvgKW());
	}
	
}
