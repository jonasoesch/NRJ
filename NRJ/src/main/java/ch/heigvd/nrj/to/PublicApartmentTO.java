package ch.heigvd.nrj.to;

import ch.heigvd.nrj.model.Room;
import java.util.Collection;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This is the transferable object for an Apartment.
 *
 * @XmlRootElement annotation at the class level.
 *
 * @author Option40
 */
@XmlRootElement
public class PublicApartmentTO {

    private long apartmentId;
    private String name;
    private Collection<Room> rooms;

    public PublicApartmentTO() {
    }

    public PublicApartmentTO(long apartmentId, String name, Collection<Room> rooms) {
        this.apartmentId = apartmentId;
        this.name = name;
	this.rooms = rooms;
    }

    public long getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(long apartmentId) {
        this.apartmentId = apartmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public Collection<Room> getRooms() {
	return rooms;
    }

    public void setRooms(Collection<Room> rooms) {
	this.rooms = rooms;
    }
}
