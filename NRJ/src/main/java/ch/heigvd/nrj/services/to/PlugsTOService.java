package ch.heigvd.nrj.services.to;

import ch.heigvd.nrj.model.Plug;
import ch.heigvd.nrj.to.PublicPlugTO;
import javax.ejb.Stateless;

/**
 * This class converts JPA entities into POJO transfer objects, and vice versa
 * 
 * @author rschmutz
 */
@Stateless
public class PlugsTOService implements PlugsTOServiceLocal {

	@Override
	public PublicPlugTO buildPublicPlugTO(Plug source) {
		PublicPlugTO to = new PublicPlugTO(source.getId(), source.getName(), source.getAlwaysOn(), source.getRoom(), source.getHistories(), source.getPlugConsumptionsFacts(), source.getConsumptionsObs(), source.getWarnings());
		return to;
	}

	@Override
	public void updatePlugEntity(Plug existingEntity, PublicPlugTO newState) {
		existingEntity.setName(newState.getName());
		existingEntity.setAlwaysOn(newState.getAlwaysOn());	
		existingEntity.setRoom(newState.getRoom());
		existingEntity.setHistories(newState.getHistories());
		existingEntity.setConsumptionsObs(newState.getConsumptions());	
		existingEntity.setPlugConsumptionsFacts(newState.getPlugConsumptions());
		existingEntity.setWarnings(newState.getWarnings());
	}
	
}
