package ch.heigvd.nrj.services.to;

import ch.heigvd.nrj.model.Apartment;
import ch.heigvd.nrj.model.Plug;
import ch.heigvd.nrj.model.Room;
import ch.heigvd.nrj.model.RoomConsumptionFact;
import ch.heigvd.nrj.to.PublicPlugTO;
import ch.heigvd.nrj.to.PublicRoomConsumptionFactsTO;
import ch.heigvd.nrj.to.PublicRoomTO;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * This class converts JPA entities into POJO transfer objects, and vice versa
 * 
 * @author rschmutz
 */
@Stateless
public class RoomsTOService implements RoomsTOServiceLocal {
    
    @EJB ApartmentsTOServiceLocal apartmentsTOService;
    
    @EJB
    PlugsTOServiceLocal plugsTOService;
    
    @EJB RoomConsumptionsFactsTOServiceLocal roomConsumptionsFactsTOService;
    
    @Override
    public PublicRoomTO buildPublicRoomTO(Room source) {
	PublicRoomTO to = new PublicRoomTO(source.getId(), source.getName());
	for (Plug plug : source.getPlugs()) {
	    PublicPlugTO ppt = plugsTOService.buildPublicPlugTO(plug);
	    to.addPlug(ppt);
	}
	for (RoomConsumptionFact rcf : source.getRoomConsumptionsFacts()) {
	    PublicRoomConsumptionFactsTO prcf = roomConsumptionsFactsTOService.buildPublicRoomConsumptionFactTO(rcf);
	    to.addRoomConsumptionFact(prcf);
	}
	return to;
    }
    
    @Override
    public void updateRoomEntity(Room existingEntity, PublicRoomTO newState) {
	    existingEntity.setName(newState.getName());
	    if(newState.getApartment() != null){
		Apartment a = new Apartment();
		apartmentsTOService.updateApartmentEntity(a, newState.getApartment());
		existingEntity.setApartment(a);
	    }
    }
}
