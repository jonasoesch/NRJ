package ch.heigvd.nrj.services.to;

import ch.heigvd.nrj.model.Apartment;
import ch.heigvd.nrj.model.ApartmentConsumptionFact;
import ch.heigvd.nrj.model.Plug;
import ch.heigvd.nrj.model.Room;
import ch.heigvd.nrj.to.PublicApartmentTO;
import ch.heigvd.nrj.to.PublicPlugTO;
import ch.heigvd.nrj.to.PublicPlugTO;
import ch.heigvd.nrj.to.PublicRoomTO;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * This class converts JPA entities into POJO transfer objects, and vice versa
 * 
 * @author rschmutz
 */
@Stateless
public class ApartmentsTOService implements ApartmentsTOServiceLocal {

	@EJB
	RoomsTOServiceLocal roomsTOService;
	
	@EJB
	ApartmentConsumptionsFactsTOServiceLocal apartmentConsumptionsFactsTOService;
	/*@Override
	public PublicApartmentTO buildPublicApartmentTO(Apartment source, boolean convertChilds) {
	    PublicApartmentTO to = new PublicApartmentTO(source.getId(), source.getName());
	    
		if (convertChilds) {
		    for (Room room : source.getRooms()) {
			to.getRooms.add(roomsTOService.buildPublicRoomTO(room, false));
		    }
		}	
		return to;
	}*/
	@Override
	public PublicApartmentTO buildPublicApartmentTO(Apartment source) {
	    PublicApartmentTO to = new PublicApartmentTO(source.getId(), source.getName());
	    for (Room room : source.getRooms()) {			
		to.addRoom(roomsTOService.buildPublicRoomTO(room));
	    }
	    for(ApartmentConsumptionFact acf : source.getApartmentConsumptionsFacts()){
		to.addApartmentConsumptionFact(apartmentConsumptionsFactsTOService.buildPublicApartmentConsumptionFactTO(acf));
	    }
	    return to;
	}

	@Override
	public void updateApartmentEntity(Apartment existingEntity, PublicApartmentTO newState) {
		existingEntity.setName(newState.getName());	
	}
	
}
