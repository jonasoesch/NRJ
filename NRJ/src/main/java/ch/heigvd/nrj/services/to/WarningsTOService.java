package ch.heigvd.nrj.services.to;

import ch.heigvd.nrj.model.Plug;
import ch.heigvd.nrj.model.Warning;
import ch.heigvd.nrj.to.PublicWarningTO;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * This class converts JPA entities into POJO transfer objects, and vice versa
 * 
 * @author rschmutz
 */
@Stateless
public class WarningsTOService implements WarningsTOServiceLocal {
	@EJB
	PlugsTOServiceLocal plugsTOService; 
	
	@Override
	public PublicWarningTO buildPublicWarningTO(Warning source) {
		PublicWarningTO to = new PublicWarningTO(source.getId(), source.getTimestampMinute(), source.getMessage());
		return to;
	}

	@Override
	public void updateWarningEntity(Warning existingEntity, PublicWarningTO newState) {
		existingEntity.setTimestampMinute(newState.getTimestampMinute());
		existingEntity.setMessage(newState.getMessage());
		if(newState.getPlug() != null){
		    Plug p = new Plug();
		    p.setId(newState.getPlug().getPlugId());
		    existingEntity.setPlug(p);
		}
	}
	
}
