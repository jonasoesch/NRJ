package ch.heigvd.nrj.to;

import ch.heigvd.nrj.model.Apartment;
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

    public PublicRoomTO() {
    }

    public PublicRoomTO(long roomId, String name, Apartment apartment) {
        this.roomId = roomId;
        this.name = name;
	this.apartment = apartment;
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
