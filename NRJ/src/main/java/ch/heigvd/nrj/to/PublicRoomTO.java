package ch.heigvd.nrj.to;

import ch.heigvd.nrj.model.Apartment;
import ch.heigvd.nrj.model.Plug;
import ch.heigvd.nrj.model.RoomConsumptionFact;
import java.util.List;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This is the transferable object for a Room.
 *
 * @XmlRootElement annotation at the class level.
 *
 * @author Option40
 */
@XmlRootElement
public class PublicRoomTO {

    private long roomId;
    private String name;
    private Apartment apartment;
    private List<RoomConsumptionFact> roomConsumptionsFacts;
    private List<Plug> plugs;
    
    public PublicRoomTO() {
    }

    public PublicRoomTO(long roomId, String name, Apartment apartment, List<RoomConsumptionFact> roomConsumptionsFacts, List<Plug> plugs) {
        this.roomId = roomId;
        this.name = name;
	this.apartment = apartment;
	this.roomConsumptionsFacts = roomConsumptionsFacts;
	this.plugs = plugs;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Apartment getApartment() {
	return apartment;
    }

    public void setApartment(Apartment apartment) {
	this.apartment = apartment;
    }
    
    public List<RoomConsumptionFact> getRoomConsumptionsFacts() {
	return roomConsumptionsFacts;
    }

    public void setRoomConsumptionsFacts(List<RoomConsumptionFact> roomConsumptionsFacts) {
	this.roomConsumptionsFacts = roomConsumptionsFacts;
    }

    public List<Plug> getPlugs() {
	return plugs;
    }
    
    public void setPlugs(List<Plug> plugs) {
	this.plugs = plugs;
    }
}
