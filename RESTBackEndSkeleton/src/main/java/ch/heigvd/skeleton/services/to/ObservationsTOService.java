package ch.heigvd.skeleton.services.to;

import ch.heigvd.skeleton.model.Observation;
import ch.heigvd.skeleton.to.PublicObservationTO;
import javax.ejb.Stateless;

/**
 * This class converts JPA entities into POJO transfer objects, and vice versa
 * 
 * @author Olivier Liechti
 */
@Stateless
public class ObservationsTOService implements ObservationsTOServiceLocal {

	@Override
	public PublicObservationTO buildPublicObservationTO(Observation source) {
            System.out.println("SALUT!!");
		PublicObservationTO to = new PublicObservationTO(source.getId(), source.getValue(), source.getTimestamp());
		return to;
	}

	@Override
	public void updateObservationEntity(Observation existingEntity, PublicObservationTO newState) {
		existingEntity.setValue(newState.getValue());
                existingEntity.setTimestamp(newState.getTimestamp());
	}
	
}
