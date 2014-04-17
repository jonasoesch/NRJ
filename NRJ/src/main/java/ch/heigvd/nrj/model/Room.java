package ch.heigvd.nrj.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * This class is a JPA entity for a Room.
 *
 * @author rschmutz
 */
@NamedQueries(
        @NamedQuery(
                name = "Room.findAllRooms",
                query = "SELECT r FROM Room r")
)
@Entity
public class Room implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * The id of a Room
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * The name of the Room
     */
    private String name;

    /**
     * The Apartment linked with this Room
     */
    @ManyToOne
    protected Apartment apartment;

    /**
     * The List of ConsumptionFacts linked to this Room
     */
    @OneToMany(mappedBy = "room")
    protected List<RoomConsumptionFact> roomConsumptionsFacts;

    /**
     * The List of Plugs linked with this Room
     */
    @OneToMany(mappedBy = "room")
    protected List<Plug> plugs;

    /**
     * Construct a Room without data
     */
    public Room() {
        this.name = "UNDEF";
        this.roomConsumptionsFacts = new ArrayList<>();
        this.plugs = new ArrayList<>();
    }

    /**
     * Construct a Room with data
     *
     * @param roomData
     */
    public Room(Room roomData) {
        this.name = roomData.getName();
        this.apartment = roomData.getApartment();
        this.roomConsumptionsFacts = roomData.getRoomConsumptionsFacts();
        this.plugs = roomData.getPlugs();
    }

    /**
     * Get the Room's id
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the new Room's id
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the Room's name
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Set the Room's name
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the Apartment linked to this Room
     *
     * @return
     */
    public Apartment getApartment() {
        return apartment;
    }

    /**
     * Set the Apartment linked to this Room
     *
     * @param apartment
     */
    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    /**
     * Get the List of RoomConsumptionFacts linked to this Room
     *
     * @return
     */
    public List<RoomConsumptionFact> getRoomConsumptionsFacts() {
        return roomConsumptionsFacts;
    }

    /**
     * Set the List of RoomConsumptionfacts for this Room
     *
     * @param roomConsumptions
     */
    public void setRoomConsumptionsFacts(List<RoomConsumptionFact> roomConsumptions) {
        this.roomConsumptionsFacts = roomConsumptions;
    }

    /**
     * Add a new RoomConsumptionFact for this Room
     *
     * @param rcf, a RoomConsumptionFact to add
     */
    public void addRoomConsumptionFact(RoomConsumptionFact rcf) {
        this.roomConsumptionsFacts.add(rcf);
    }

    /**
     * Get the List of the Plugs in this Room
     *
     * @return
     */
    public List<Plug> getPlugs() {
        return plugs;
    }

    /**
     * Set the List a Plugs in this Room
     *
     * @param plugs
     */
    public void setPlugs(List<Plug> plugs) {
        this.plugs = plugs;
    }

    /**
     * Add a Plug to this Room
     *
     * @param p, the Plug to add in this Room
     */
    public void addPlug(Plug p) {
        this.plugs.add(p);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Room)) {
            return false;
        }
        Room other = (Room) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ch.heigvd.nrj.model.Room[ id=" + id + " ]";
    }
}
