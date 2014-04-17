package ch.heigvd.nrj.services.to;

import ch.heigvd.nrj.model.ApartmentConsumptionFact;
import ch.heigvd.nrj.to.PublicApartmentConsumptionFactsTO;
import javax.ejb.Stateless;

/**
 * This class converts JPA entities into POJO transfer objects, and vice versa
 * 
 * @author rschmutz
 */
@Stateless
public class ApartmentConsumptionsFactsTOService implements ApartmentConsumptionsFactsTOServiceLocal {
	@Override
	public PublicApartmentConsumptionFactsTO buildPublicApartmentConsumptionFactTO(ApartmentConsumptionFact source) {
		PublicApartmentConsumptionFactsTO to = new PublicApartmentConsumptionFactsTO(source.getId(), source.getTimestampHour(), source.getAvgKW());
		return to;
	}   

	@Override
	public void updateApartmentConsumptionFactEntity(ApartmentConsumptionFact existingEntity, PublicApartmentConsumptionFactsTO newState) {
		existingEntity.setTimestampHour(newState.getTimestampHour());
		existingEntity.setAvgKW(newState.getAvgKW());
		
		// existingEntity.setApartment(newState.getApartment());
	}
	
}
