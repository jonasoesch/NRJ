package ch.heigvd.nrj.to;

import ch.heigvd.nrj.model.Room;
import java.util.ArrayList;
import java.util.List;
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
    private List<PublicRoomTOSortie> rooms;

    public PublicApartmentTO() {
	this.rooms = new ArrayList<>();
    }

    public PublicApartmentTO(long apartmentId, String name) {
	this.rooms = new ArrayList<>();
        this.apartmentId = apartmentId;
        this.name = name;
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
    
    public List<PublicRoomTOSortie> getRooms() {
	return rooms;
    }

    public void setRooms(List<PublicRoomTOSortie> rooms) {
	this.rooms = rooms;
    }
    
    public void addRoom(PublicRoomTOSortie room) {
	this.rooms.add(room);
    }
    
}
