package ch.heigvd.nrj.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;

/**
 * This class is a JPA entity for a Room Consumption.
 *
 * @author rschmutz
 */
@NamedQueries({
    @NamedQuery(
            name = "RoomConsumptionFact.findAllRoomConsumptionsFacts",
            query = "SELECT rc FROM RoomConsumptionFact rc"),
    @NamedQuery(
            name = "RoomConsumptionFact.getLastRoomFact",
            query = "SELECT lrc FROM RoomConsumptionFact lrc WHERE lrc.room = :room ORDER BY lrc.timestampHour DESC")
})

@Entity
public class RoomConsumptionFact implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * The id of the RoomConsumptionfact
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * The timestamp of this consumptionFact
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date timestampHour;
    private Double avgKW;

    /**
     * The room linked with this consumptionFact
     */
    @ManyToOne
    protected Room room;

    /**
     * Construct a roomConsumptionFact without data
     */
    public RoomConsumptionFact() {
        this.timestampHour = new Date();
        this.avgKW = 0.0;
    }

    /**
     * Contruct a roomConsumptionFact with data
     *
     * @param roomConsumptionFactData
     */
    public RoomConsumptionFact(RoomConsumptionFact roomConsumptionFactData) {
        this.timestampHour = roomConsumptionFactData.getTimestampHour();
        this.avgKW = roomConsumptionFactData.getAvgKW();
        this.room = roomConsumptionFactData.getRoom();
    }

    /**
     * Get the id of this consumptionFact
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the new id of this consumptionFact
     *
     * @param id, the new id of this consumptionFact
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the timestamp of this consumptionFact
     *
     * @return a Date
     */
    public Date getTimestampHour() {
        return timestampHour;
    }

    /**
     * Set the timestamp of this consumptionFact
     *
     * @param timestampHour
     */
    public void setTimestampHour(Date timestampHour) {
        this.timestampHour = timestampHour;
    }

    /**
     * Get the avgKW
     *
     * @return
     */
    public Double getAvgKW() {
        return avgKW;
    }

    /**
     * Set the avgKw
     *
     * @param avgKW
     */
    public void setAvgKW(Double avgKW) {
        this.avgKW = avgKW;
    }

    /**
     * Get the room linked with this consumptionFact
     *
     * @return
     */
    public Room getRoom() {
        return room;
    }

    /**
     * Set the Room linked with consumptionFact
     *
     * @param room
     */
    public void setRoom(Room room) {
        this.room = room;
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
        if (!(object instanceof RoomConsumptionFact)) {
            return false;
        }
        RoomConsumptionFact other = (RoomConsumptionFact) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ch.heigvd.nrj.model.RoomConsumptionFact[ id=" + id + " ]";
    }
}
