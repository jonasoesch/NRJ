package ch.heigvd.nrj.services.to;

import ch.heigvd.nrj.model.Apartment;
import ch.heigvd.nrj.to.PublicApartmentTO;
import javax.ejb.Stateless;

/**
 * This class converts JPA entities into POJO transfer objects, and vice versa
 * 
 * @author rschmutz
 */
@Stateless
public class ApartmentsTOService implements ApartmentsTOServiceLocal {

	@Override
	public PublicApartmentTO buildPublicApartmentTO(Apartment source) {
		PublicApartmentTO to = new PublicApartmentTO(source.getId(), source.getName(), source.getRooms());
		return to;
	}

	@Override
	public void updateApartmentEntity(Apartment existingEntity, PublicApartmentTO newState) {
		existingEntity.setName(newState.getName());	
		existingEntity.setRooms(newState.getRooms());
	}
	
}
