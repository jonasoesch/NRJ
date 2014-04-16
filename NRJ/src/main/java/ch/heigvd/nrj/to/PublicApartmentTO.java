package ch.heigvd.nrj.to;

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
    private List<PublicRoomTO> rooms;
    private List<PublicApartmentConsumptionFactsTO> apartmentConsumptionFacts; 
    
    public PublicApartmentTO() {
	this.rooms = new ArrayList<>();
	this.apartmentConsumptionFacts = new ArrayList<>();
    }

    public PublicApartmentTO(long apartmentId, String name) {
	this.rooms = new ArrayList<>();
	this.apartmentConsumptionFacts = new ArrayList<>();
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
    
    public List<PublicRoomTO> getRooms() {
	return rooms;
    }

    public void setRooms(List<PublicRoomTO> rooms) {
	this.rooms = rooms;
    }
    
    public void addRoom(PublicRoomTO room) {
	this.rooms.add(room);
    }
    
    public List<PublicApartmentConsumptionFactsTO> getApartmentConsumptionFacts() {
	return apartmentConsumptionFacts;
    }

    public void setApartmentConsumptionFacts(List<PublicApartmentConsumptionFactsTO> ApartmentConsumptionFacts) {
	this.apartmentConsumptionFacts = ApartmentConsumptionFacts;
    }
    public void addApartmentConsumptionFact(PublicApartmentConsumptionFactsTO ApartmentConsumptionFact) {
	this.apartmentConsumptionFacts.add(ApartmentConsumptionFact);
    }
    
}
