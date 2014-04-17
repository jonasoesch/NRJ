package ch.heigvd.nrj.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
query = "SELECT a FROM Apartment a"))
@Entity
public class Apartment implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * The entity id for an Apartment.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * The name of an Apartment.
     */
    private String name;
    /**
     * The list of Consumptions Fact for an Apartment.
     */
    @OneToMany(mappedBy = "apartment")
    protected List<ApartmentConsumptionFact> apartmentConsumptionsFacts;
    /**
     * The list of Rooms for an Apartment.
     */
    @OneToMany(mappedBy = "apartment")
    protected List<Room> rooms;

    /**
     * Construct the apartment without data.
     */
    public Apartment() {
	this.name = "UNDEF";
	this.apartmentConsumptionsFacts = new ArrayList<>();
	this.rooms = new ArrayList<>();
    }

    /**
     * Construct the apartment with data.
     * @param apartmentData the apartment's data. 
     */
    public Apartment(Apartment apartmentData) {
	this.name = apartmentData.getName();
	this.rooms = apartmentData.getRooms();
	this.apartmentConsumptionsFacts = apartmentData.getApartmentConsumptionsFacts();
    }

    /**
     * Get the Apartment's id in long.
     *
     * @return id, the apartment's id in long.
     */
    public Long getId() {
	return id;
    }

    /**
     * Set the value of the Apartment's id in long.
     *
     * @param id the new Apartment's id in long.
     */
    public void setId(Long id) {
	this.id = id;
    }

    /**
     * Get the Apartment's name in String.
     *
     * @return the Apartment's name in String.
     */
    public String getName() {
	return name;
    }

    /**
     * Set the value of the Apartment's name.
     *
     * @param name the new Apartment's name in String.
     */
    public void setName(String name) {
	this.name = name;
    }

    /**
     * Get the list of Apartment's Room.
     *
     * @return an ArrayList of Apartment's Room.
     */
    public List<Room> getRooms() {
	return rooms;
    }

    /**
     * Set the list of the Apartment's name.
     *
     * @param rooms a new ArrayList of the Apartment's Room.
     */
    public void setRooms(List<Room> rooms) {
	this.rooms = rooms;
    }

    /**
     * Add a room in the Apartment's list of rooms.
     *
     * @param room the new room added.
     */
    public void addRoom(Room room) {
	this.rooms.add(room);
    }

    /**
     * Get the list of Apartment's Consumption fact.
     *
     * @return an ArrayList of Apartment's Consumption Fact.
     */
    public List<ApartmentConsumptionFact> getApartmentConsumptionsFacts() {
	return apartmentConsumptionsFacts;
    }

    /**
     * Set the list of the Apartment's Consumption Fact.
     *
     * @param apartmentConsumptions a new ArrayList of the Apartment's
     * Consumption Fact.
     */
    public void setApartmentConsumptionsFacts(List<ApartmentConsumptionFact> apartmentConsumptions) {
	this.apartmentConsumptionsFacts = apartmentConsumptions;
    }

    /**
     * Add a Consumption Fact in the Apartment's list of Consumptions Facts.
     *
     * @param acf the new Consumption Fact added.
     */
    public void addApartmentConsumptionFact(ApartmentConsumptionFact acf) {
	this.apartmentConsumptionsFacts.add(acf);
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
