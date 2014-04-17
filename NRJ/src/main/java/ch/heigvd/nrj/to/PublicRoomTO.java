package ch.heigvd.nrj.to;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This is the transferable object for apartment Room.
 *
 * @XmlRootElement annotation at the class level.
 *
 * @author Option40
 */
@XmlRootElement
public class PublicRoomTO {

    private long roomId;
    private String name;
    private PublicApartmentTO apartment;
    private List<PublicRoomConsumptionFactsTO> roomConsumptionsFacts;
    private List<PublicPlugTO> plugs;

    /**
     * Constructs a PublicRoomTo without data
     */
    public PublicRoomTO() {
        this.plugs = new ArrayList<>();
        this.roomConsumptionsFacts = new ArrayList<>();
    }

    /**
     * Constructs a PublicRoomtTO with data
     *
     * @param roomId, the Room's id
     * @param name, the Name of the Room
     */
    public PublicRoomTO(long roomId, String name) {
        this.roomId = roomId;
        this.name = name;
        this.plugs = new ArrayList<>();
        this.roomConsumptionsFacts = new ArrayList<>();
    }

    /**
     * Get the Room's id
     *
     * @return the Room's id
     */
    public long getRoomId() {
        return roomId;
    }

    /**
     * Set the Room's id
     *
     * @param roomId the Room's id
     */
    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    /**
     * Get the name of this Room
     *
     * @return the Room's Name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the Room's Name
     *
     * @param name the Room's Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the partment linked with this Room
     *
     * @return an Apartment
     */
    public PublicApartmentTO getApartment() {
        return apartment;
    }

    /**
     * Set the Apartment linked with this Room
     *
     * @param apartment, an Apartment
     */
    public void setApartment(PublicApartmentTO apartment) {
        this.apartment = apartment;
    }

    /**
     * Get the list of RoomConsumptionFacts linked with this Room
     *
     * @return a List of RoomConsumptionFacts
     */
    public List<PublicRoomConsumptionFactsTO> getRoomConsumptionsFacts() {
        return roomConsumptionsFacts;
    }

    /**
     * Set the List of RoomConsumptionFacts for this Room
     *
     * @param roomConsumptionsFacts, a List of RoomConsumptionFacts
     */
    public void setRoomConsumptionsFacts(List<PublicRoomConsumptionFactsTO> roomConsumptionsFacts) {
        this.roomConsumptionsFacts = roomConsumptionsFacts;
    }

    /**
     * Add a RoomConsumptionfact to this Room
     *
     * @param roomConsumptionFact a RoomConsumptionFact
     */
    public void addRoomConsumptionFact(PublicRoomConsumptionFactsTO roomConsumptionFact) {
        this.roomConsumptionsFacts.add(roomConsumptionFact);
    }

    /**
     * Get the Plugs in this Room
     *
     * @return a List of Plugs
     */
    public List<PublicPlugTO> getPlugs() {
        return plugs;
    }

    /**
     * Set the Plugs in this Room
     *
     * @param plugs a List of Plugs
     */
    public void setPlugs(List<PublicPlugTO> plugs) {
        this.plugs = plugs;
    }

    /**
     * Adds a Plug in this Room
     *
     * @param plug a Plug
     */
    public void addPlug(PublicPlugTO plug) {
        this.plugs.add(plug);
    }
}
