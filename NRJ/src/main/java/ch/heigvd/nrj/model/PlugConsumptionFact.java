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
 * This class is a JPA entity for a Plug Consumption.
 *
 * @author rschmutz
 */
@NamedQueries(
        @NamedQuery(
        name = "PlugConsumptionFact.findAllPlugConsumptionsFacts",
        query = "SELECT pc FROM PlugConsumptionFact pc"))
@Entity
public class PlugConsumptionFact implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date timestampHour;
    private Double avgKW;
    
    @ManyToOne protected Plug plug;

    public PlugConsumptionFact() {
        this.timestampHour = new Date();
        this.avgKW = 0.0;
    }
            
    public PlugConsumptionFact (PlugConsumptionFact plugConsumptionFactData) {
        this.timestampHour = plugConsumptionFactData.getTimestampHour();
        this.avgKW = plugConsumptionFactData.getAvgKW();
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

    @Override
    public int hashCode() {
	int hash = 0;
	hash += (id != null ? id.hashCode() : 0);
	return hash;
    }

    @Override
    public boolean equals(Object object) {
	// TODO: Warning - this method won't work in the case the id fields are not set
	if (!(object instanceof PlugConsumptionFact)) {
	    return false;
	}
	PlugConsumptionFact other = (PlugConsumptionFact) object;
	if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "ch.heigvd.nrj.model.PlugConsumptionFact[ id=" + id + " ]";
    }
}