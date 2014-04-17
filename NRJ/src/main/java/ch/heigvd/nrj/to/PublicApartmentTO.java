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

    /**
     * Build a PublicApartmentTO empty.
     */
    public PublicApartmentTO() {
	this.rooms = new ArrayList<>();
	this.apartmentConsumptionFacts = new ArrayList<>();
    }

    /**
     * Build a PublicApartmentTO with data.
     *
     * @param apartmentId
     * @param name
     */
    public PublicApartmentTO(long apartmentId, String name) {
	this.rooms = new ArrayList<>();
	this.apartmentConsumptionFacts = new ArrayList<>();
	this.apartmentId = apartmentId;
	this.name = name;
    }

    /**
     * Get the id of PublicApartmentTO
     *
     * @return the id
     */
    public long getApartmentId() {
	return apartmentId;
    }

    /**
     * Set the id of PublicApartmentTO
     *
     * @param apartmentId
     */
    public void setApartmentId(long apartmentId) {
	this.apartmentId = apartmentId;
    }

    /**
     * Get the name of the PublicApartmentTO
     *
     * @return the name
     */
    public String getName() {
	return name;
    }

    /**
     * Set the name of the PublicApartmentTO.
     *
     * @param name
     */
    public void setName(String name) {
	this.name = name;
    }

    /**
     * Get the rooms of an apartment.
     *
     * @return list of rooms
     */
    public List<PublicRoomTO> getRooms() {
	return rooms;
    }

    /**
     * Set the rooms of an apartment.
     *
     * @param rooms
     */
    public void setRooms(List<PublicRoomTO> rooms) {
	this.rooms = rooms;
    }

    /**
     * Add a room to the list of apartment's rooms
     *
     * @param room
     */
    public void addRoom(PublicRoomTO room) {
	this.rooms.add(room);
    }

    /**
     * Get the list of apartmentConsumptionFacts
     *
     * @return list of apartmentConsumptionFacts
     */
    public List<PublicApartmentConsumptionFactsTO> getApartmentConsumptionFacts() {
	return apartmentConsumptionFacts;
    }

    /**
     * Set the apartmentConsumptionFacts
     *
     * @param ApartmentConsumptionFacts
     */
    public void setApartmentConsumptionFacts(List<PublicApartmentConsumptionFactsTO> ApartmentConsumptionFacts) {
	this.apartmentConsumptionFacts = ApartmentConsumptionFacts;
    }

    /**
     * Add a apartmentConsumptionFact in the apartment's
     * apartmentConsumptionFacts
     *
     * @param ApartmentConsumptionFact
     */
    public void addApartmentConsumptionFact(PublicApartmentConsumptionFactsTO ApartmentConsumptionFact) {
	this.apartmentConsumptionFacts.add(ApartmentConsumptionFact);
    }
}
