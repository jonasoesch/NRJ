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
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date timestampHour;
    private Double avgKW;
    
    @ManyToOne
    protected Room room;

    public RoomConsumptionFact() {
        this.timestampHour = new Date();
        this.avgKW = 0.0;
    }
            
    public RoomConsumptionFact (RoomConsumptionFact roomConsumptionFactData) {
        this.timestampHour = roomConsumptionFactData.getTimestampHour();
        this.avgKW = roomConsumptionFactData.getAvgKW();
	this.room = roomConsumptionFactData.getRoom();
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTimestampHour() {
        return timestampHour;
    }

    public void setTimestampHour(Date timestampHour) {
        this.timestampHour = timestampHour;
    }

    public Double getAvgKW() {
        return avgKW;
    }

    public void setAvgKW(Double avgKW) {
        this.avgKW = avgKW;
    }
    
    public Room getRoom() {
	return room;
    }

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
