package ch.heigvd.nrj.services.business;

import ch.heigvd.nrj.model.ConsumptionObs;
import javax.ejb.Local;

/**
 *
 * @author jonas
 */
@Local
public interface StreamProcessorLocal {
    
    void onConsumption(ConsumptionObs c);
    
}
