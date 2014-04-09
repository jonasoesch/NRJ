package ch.heigvd.nrj.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * This class is a JPA entity for a Room.
 *
 * @author rschmutz
 */
@NamedQueries({
        @NamedQuery(
        name = "Room.findAllRooms",
        query = "SELECT r FROM Room r"),
	@NamedQuery(
        name = "Room.findAllByApartment",
        query = "SELECT r FROM Room r WHERE r.apartment.id = :apartment_id")})
@Entity
public class Room implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    
    @ManyToOne
    protected Apartment apartment;

    @OneToMany (mappedBy = "room")
    protected Collection<ConsumptionObs> consumptions;
    @OneToMany (mappedBy = "room")
    protected Collection<RoomConsumptionFact> roomConsumptions;
    @OneToMany (mappedBy = "room")
    protected Collection<Plug> plugs;

    public Room() {
        this.name = "UNDEF";
    }
            
    public Room (Room roomData) {
        this.name = roomData.getName();
	// this.apartment = roomData.getApartment();
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
