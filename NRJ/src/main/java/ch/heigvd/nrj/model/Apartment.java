package ch.heigvd.nrj.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * This class is a JPA entity for an Apartment.
 *
 * @author Chris
 */
@NamedQueries(
        @NamedQuery(
        name = "Apartment.findAllApartments",
        query = "SELECT a FROM Apartment a")
        // query = "SELECT a, r FROM Apartment a JOIN a.rooms r")
)
@Entity
public class Apartment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    
    @OneToMany(mappedBy="apartment")
    protected List<Room> rooms;

    public Apartment() {
        this.name = "UNDEF";
	this.rooms = new ArrayList<>();
    }

    public Apartment(Apartment apartmentData) {
        this.name = apartmentData.getName();
	this.rooms = apartmentData.getRooms();
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
    
    public List<Room> getRooms() {
	return rooms;
    }

    public void setRooms(List<Room> rooms) {
	this.rooms = rooms;
    }
    public void addRoom(Room room) {
	this.rooms.add(room);
	room.setApartment(this);
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
        if (!(object instanceof Apartment)) {
            return false;
        }
        Apartment other = (Apartment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ch.heigvd.nrj.model.Apartment[ id=" + id + " ]";
    }
}
