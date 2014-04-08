package ch.heigvd.nrj.services.to;

import ch.heigvd.nrj.model.History;
import ch.heigvd.nrj.to.PublicHistoryTO;
import javax.ejb.Stateless;

/**
 * This class converts JPA entities into POJO transfer objects, and vice versa
 * 
 * @author rschmutz
 */
@Stateless
public class HistoriesTOService implements HistoriesTOServiceLocal {

	@Override
	public PublicHistoryTO buildPublicHistoryTO(History source) {
		PublicHistoryTO to = new PublicHistoryTO(source.getId(), source.getTimestampMinute(), source.getStatus());
		return to;
	}

	@Override
	public void updateHistoryEntity(History existingEntity, PublicHistoryTO newState) {
		existingEntity.setTimestampMinute(newState.getTimestampMinute());
		existingEntity.setStatus(newState.getStatus());
	}
	
}
