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
 * This class is a JPA entity for a Consumption.
 *
 * @author Robin
 */
@NamedQueries(
        @NamedQuery(
        name = "Consumption.findAllConsumptions",
        query = "SELECT c FROM Consumption c"))
@Entity
public class Consumption implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date timestampMinute;
    private Double kW;

    @ManyToOne protected Room room;
    @ManyToOne protected Plug plug;

    public Consumption() {
        this.timestampMinute = new Date();
	this.kW = 0.0;
    }

    public Consumption(Consumption consumptionData) {
        this.timestampMinute = consumptionData.getTimestampMinute();
	this.kW = consumptionData.getkW();
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTimestampMinute() {
        return timestampMinute;
    }

    public void setTimestampMinute(Date timestampMinute) {
        this.timestampMinute = timestampMinute;
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
        if (!(object instanceof Consumption)) {
            return false;
        }
        Consumption other = (Consumption) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ch.heigvd.nrj.model.Consumption[ id=" + id + " ]";
    }
}
