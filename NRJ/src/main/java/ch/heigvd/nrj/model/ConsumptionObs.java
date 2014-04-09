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
 * This class is a JPA entity for a ConsumptionObs.
 *
 * @author Robin
 */

@NamedQueries({
    @NamedQuery(name = "ConsumptionObs.findAllConsumptionsObs",
        query = "SELECT c FROM ConsumptionObs c")})
@Entity
public class ConsumptionObs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date timestampHour;
    private Double kW;

    @ManyToOne protected Room room;
    @ManyToOne protected Plug plug;

    public ConsumptionObs() {
        this.timestampHour = new Date();
	this.kW = 0.0;
    }

    public ConsumptionObs(ConsumptionObs consumptionObsData) {
        this.timestampHour = consumptionObsData.getTimestampMinute();
	this.kW = consumptionObsData.getkW();
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTimestampMinute() {
        return timestampHour;
    }

    public void setTimestampMinute(Date timestampMinute) {
        this.timestampHour = timestampMinute;
    }

    public Double getkW() {
        return kW;
    }

    public void setkW(Double kW) {
        this.kW = kW;
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
        if (!(object instanceof ConsumptionObs)) {
            return false;
        }
        ConsumptionObs other = (ConsumptionObs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ch.heigvd.nrj.model.ConsumptionObs[ id=" + id + " ]";
    }
}
