package ch.heigvd.nrj.to;

import ch.heigvd.nrj.model.Apartment;
import ch.heigvd.nrj.model.Plug;
import ch.heigvd.nrj.model.Room;
import ch.heigvd.nrj.model.RoomConsumptionFact;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This is the transferable object for apartment Room.
 *
 * @XmlRootElement annotation at the class level.
 *
 * @author Option40
 */
@XmlRootElement
public class PublicRoomTOEntree {

    private long roomId;
    private String name;
    private Apartment apartment;
    
    public PublicRoomTOEntree() {
    }

    public PublicRoomTOEntree(long roomId, String name, Apartment a) {
        this.roomId = roomId;
        this.name = name;
	this.apartment = a;
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
}
