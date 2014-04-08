package ch.heigvd.nrj.services.to;

import ch.heigvd.nrj.model.PlugConsumptionObs;
import ch.heigvd.nrj.to.PublicPlugConsumptionTO;
import javax.ejb.Stateless;

/**
 * This class converts JPA entities into POJO transfer objects, and vice versa
 * 
 * @author rschmutz
 */
@Stateless
public class PlugConsumptionsTOService implements PlugConsumptionsTOServiceLocal {

	@Override
	public PublicPlugConsumptionTO buildPublicPlugConsumptionTO(PlugConsumptionObs source) {
		PublicPlugConsumptionTO to = new PublicPlugConsumptionTO(source.getId(), source.getTimestampHour(), source.getAvgKW());
		return to;
	}   

	@Override
	public void updatePlugConsumptionEntity(PlugConsumptionObs existingEntity, PublicPlugConsumptionTO newState) {
		existingEntity.setTimestampHour(newState.getTimestampHour());
		existingEntity.setAvgKW(newState.getAvgKW());
	}
	
}
