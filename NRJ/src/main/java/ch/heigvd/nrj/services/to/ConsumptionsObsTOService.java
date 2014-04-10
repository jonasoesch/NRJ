package ch.heigvd.nrj.services.to;

import ch.heigvd.nrj.exceptions.EntityNotFoundException;
import ch.heigvd.nrj.model.ConsumptionObs;
import ch.heigvd.nrj.model.Plug;
import ch.heigvd.nrj.services.crud.PlugsManagerLocal;
import ch.heigvd.nrj.to.PublicConsumptionObsTO;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * This class converts JPA entities into POJO transfer objects, and vice versa
 * 
 * @author rschmutz
 */
@Stateless
public class ConsumptionsObsTOService implements ConsumptionsObsTOServiceLocal {
    
    @EJB
    PlugsManagerLocal plugsManager;

	@Override
	public PublicConsumptionObsTO buildPublicConsumptionObsTO(ConsumptionObs source) {

            
            PublicConsumptionObsTO to = new PublicConsumptionObsTO(source.getId(), source.getkW(), source.getPlug().getId());
		return to;
	}

	@Override
	public void updateConsumptionObsEntity(ConsumptionObs existingEntity, PublicConsumptionObsTO newState) {
                
                Plug plug;
                try {
                        long plugId = newState.getPlugId();
                       plug = plugsManager.findById(plugId);
                } catch (EntityNotFoundException ex) {
                        plug = null;
                        Logger.getLogger(ConsumptionsObsTOService.class.getName()).log(Level.SEVERE, null, ex);
                }
          
		existingEntity.setTimestampMinute(new Date());
		existingEntity.setkW(newState.getkW());
                existingEntity.setPlug(plug);
	}
        
        
	
}
