package ch.heigvd.nrj.services.to;

import ch.heigvd.nrj.model.Plug;
import ch.heigvd.nrj.model.PlugConsumptionFact;
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
    public PublicPlugConsumptionFactsTO buildPublicPlugConsumptionFactTO(PlugConsumptionFact source) {
	PublicPlugConsumptionFactsTO to = new PublicPlugConsumptionFactsTO(source.getId(), source.getTimestampHour(), source.getAvgKW());
	return to;
    }

    @Override
    public void updatePlugConsumptionFactEntity(PlugConsumptionFact existingEntity, PublicPlugConsumptionFactsTO newState) {

	existingEntity.setTimestampHour(newState.getTimestampHour());
	existingEntity.setAvgKW(newState.getAvgKW());
	if (newState.getPlug() != null) {
	    Plug p = new Plug();
	    p.setId(newState.getPlug().getPlugId());
	    existingEntity.setPlug(p);
	}
    }
}
