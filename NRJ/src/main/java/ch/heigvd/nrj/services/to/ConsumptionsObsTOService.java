package ch.heigvd.nrj.services.to;

import ch.heigvd.nrj.exceptions.EntityNotFoundException;
import ch.heigvd.nrj.model.ConsumptionObs;
import ch.heigvd.nrj.model.Plug;
import ch.heigvd.nrj.services.crud.PlugsManagerLocal;
import ch.heigvd.nrj.to.PublicConsumptionObsTO;
import ch.heigvd.nrj.to.PublicPlugTO;
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
        PublicConsumptionObsTO to = new PublicConsumptionObsTO(source.getId(), source.getkW(), source.getTimestampMinute());
	if (source.getPlug() != null) {
	    PublicPlugTO plug = new PublicPlugTO();
	    to.setPlug(plug);
	}
        return to;
    }

    @Override
    public void updateConsumptionObsEntity(ConsumptionObs existingEntity, PublicConsumptionObsTO newState) {
        existingEntity.setTimestampMinute(newState.getTimestampMinute());
        existingEntity.setkW(newState.getkW());
    }

}
