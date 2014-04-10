package ch.heigvd.nrj.services.to;

import ch.heigvd.nrj.model.Apartment;
import ch.heigvd.nrj.model.Plug;
import ch.heigvd.nrj.model.Room;
import ch.heigvd.nrj.to.PublicApartmentTO;
import ch.heigvd.nrj.to.PublicPlugTO;
import ch.heigvd.nrj.to.PublicRoomTO;
import javax.ejb.Stateless;

/**
 * This class converts JPA entities into POJO transfer objects, and vice versa
 * 
 * @author rschmutz
 */
@Stateless
public class ApartmentsTOService implements ApartmentsTOServiceLocal {

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
			PublicRoomTO roomTO = new PublicRoomTO(room.getId(), room.getName(), room.getRoomConsumptionsFacts());
			System.out.println("Robin VS Grizzly : " + roomTO);
			to.addRoom(roomTO);
			for (Plug plug : room.getPlugs()) {
			    PublicPlugTO plugTO = new PublicPlugTO(plug.getId(), plug.getName(), plug.getAlwaysOn(), plug.getRoom(), plug.getHistories(), plug.getPlugConsumptionsFacts(), plug.getWarnings());
			    roomTO.addPlug(plugTO);
			}
		    }
		return to;
	}

	@Override
	public void updateApartmentEntity(Apartment existingEntity, PublicApartmentTO newState) {
		existingEntity.setName(newState.getName());	
	}
	
}
