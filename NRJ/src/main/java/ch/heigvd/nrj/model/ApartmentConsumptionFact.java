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
        query = "SELECT lac FROM ApartmentConsumptionFact lac WHERE lac.apartment = :apartment ORDER BY lac.timestampHour DESC"
        )
})
@Entity
public class ApartmentConsumptionFact implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date timestampHour;
    private Double avgKW;
    
    @ManyToOne
    protected Apartment apartment;

    public ApartmentConsumptionFact() {
        this.timestampHour = new Date();
        this.avgKW = 0.0;
    }
            
    public ApartmentConsumptionFact (ApartmentConsumptionFact apartmentConsumptionFactData) {
        this.timestampHour = apartmentConsumptionFactData.getTimestampHour();
        this.avgKW = apartmentConsumptionFactData.getAvgKW();
	this.apartment = apartmentConsumptionFactData.getApartment();
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
