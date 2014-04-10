package ch.heigvd.nrj.services.business;

import ch.heigvd.nrj.model.ConsumptionObs;
import ch.heigvd.nrj.services.crud.ConsumptionsObsManagerLocal;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author jonas
 */
@Stateless
@Asynchronous
public class StreamProcessor implements StreamProcessorLocal {
    
    @EJB ConsumptionsObsManagerLocal consumptionObsManager;

    @Override
    public void onConsumption(ConsumptionObs o) {
        
        // Recording observation
        consumptionObsManager.create(o);
        
        // Then implement your logic to create facts
        
    }
    
    /*@Override
    public void onObservation(ObservationType2 o) {
        
        // Recording observation
        observationsType2Manager.create(o);
        
        // Then implement your logic to create facts
        
    }*/
    
}
