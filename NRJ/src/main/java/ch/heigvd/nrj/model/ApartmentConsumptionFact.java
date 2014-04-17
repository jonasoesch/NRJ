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
 * This class is a JPA entity for a Apartment Consumption.
 *
 * @author rschmutz
 */
@NamedQueries({
    @NamedQuery(
        name = "ApartmentConsumptionFact.findAllApartmentConsumptionsFacts",
    query = "SELECT rc FROM ApartmentConsumptionFact rc"),
    @NamedQuery(
        name = "ApartmentConsumptionFact.getLastApartmentFact",
    query = "SELECT lac FROM ApartmentConsumptionFact lac WHERE lac.apartment = :apartment ORDER BY lac.timestampHour DESC")
})
@Entity
public class ApartmentConsumptionFact implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * The consumption fact's id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * The date (Hour precision) of the consumption Fact.
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date timestampHour;
    /**
     * The average kW.
     */
    private Double avgKW;
    /**
     * The apartment of the Consumption Fact.
     */
    @ManyToOne
    protected Apartment apartment;

    /**
     * Construct the apartment's consumption fact without data.
     */
    public ApartmentConsumptionFact() {
	this.timestampHour = new Date();
	this.avgKW = 0.0;
    }

    /**
     * Construct the apartment's consumption fact with data.
     * @param apartmentConsumptionFactData the consumption fact's datas.
     */
    public ApartmentConsumptionFact(ApartmentConsumptionFact apartmentConsumptionFactData) {
	this.timestampHour = apartmentConsumptionFactData.getTimestampHour();
	this.avgKW = apartmentConsumptionFactData.getAvgKW();
	this.apartment = apartmentConsumptionFactData.getApartment();
    }

    /**
     * Get the Consumption Fact's id.
     *
     * @return the Consumption Fact's id in long.
     */
    public Long getId() {
	return id;
    }

    /**
     * Set the Consumption Fact's id in long.
     *
     * @param id the new id in long.
     */
    public void setId(Long id) {
	this.id = id;
    }

    /**
     * Get the date of the consumption fact.
     *
     * @return the date of the consumption fact.
     */
    public Date getTimestampHour() {
	return timestampHour;
    }

    /**
     * Set the Date of the consumption fact.
     *
     * @param timestampHour the new Date.
     */
    public void setTimestampHour(Date timestampHour) {
	this.timestampHour = timestampHour;
    }

    /**
     * Get the average of de kW consumption fact.
     *
     * @return the average of the kw consumption fact.
     */
    public Double getAvgKW() {
	return avgKW;
    }

    /**
     * Set the average of the consumption fact.
     *
     * @param avgKW the new average.
     */
    public void setAvgKW(Double avgKW) {
	this.avgKW = avgKW;
    }

    /**
     * Get the apartment of the consumption fact.
     *
     * @return the apartment.
     */
    public Apartment getApartment() {
	return apartment;
    }

    /**
     * Set the apartment of a consumption fact.
     *
     * @param apartment
     */
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
	if (!(object instanceof ApartmentConsumptionFact)) {
	    return false;
	}
	ApartmentConsumptionFact other = (ApartmentConsumptionFact) object;
	if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "ch.heigvd.nrj.model.ApartmentConsumptionFact[ id=" + id + " ]";
    }
}
