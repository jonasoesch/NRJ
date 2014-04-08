package ch.heigvd.nrj.services.to;

import ch.heigvd.nrj.model.Consumption;
import ch.heigvd.nrj.to.PublicConsumptionTO;
import javax.ejb.Stateless;

/**
 * This class converts JPA entities into POJO transfer objects, and vice versa
 * 
 * @author rschmutz
 */
@Stateless
public class ConsumptionsTOService implements ConsumptionsTOServiceLocal {

	@Override
	public PublicConsumptionTO buildPublicConsumptionTO(Consumption source) {
		PublicConsumptionTO to = new PublicConsumptionTO(source.getId(), source.getTimestampMinute(), source.getkW());
		return to;
	}

	@Override
	public void updateConsumptionEntity(Consumption existingEntity, PublicConsumptionTO newState) {
		existingEntity.setTimestampMinute(newState.getTimestampMinute());
		existingEntity.setkW(newState.getKW());
	}
	
}
