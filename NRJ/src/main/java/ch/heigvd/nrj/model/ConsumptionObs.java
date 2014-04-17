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
    /**
     * The consumption's id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * The consumption's date (Minute precision)
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date timestampMinute;
    /**
     * The kW consumption.
     */
    private Double kW;
    /**
     * The plug link to the consumption.
     */
    @ManyToOne
    protected Plug plug;

    /**
     * Construct a consumption without data.
     */
    public ConsumptionObs() {
	this.timestampMinute = new Date();
	this.kW = 0.0;
    }

    /**
     * Construct the consumption with data.
     *
     * @param consumptionObsData the consumption datas.
     */
    public ConsumptionObs(ConsumptionObs consumptionObsData) {
	this.timestampMinute = consumptionObsData.getTimestampMinute();
	this.kW = consumptionObsData.getkW();
	this.plug = consumptionObsData.getPlug();
    }

    /**
     * Get the id of a consumption.
     *
     * @return the consumption's id.
     */
    public Long getId() {
	return id;
    }

    /**
     * Set the id of a consumption.
     *
     * @param id the new consumption's id
     */
    public void setId(Long id) {
	this.id = id;
    }

    /**
     * Get the date of a consumption.
     *
     * @return the consumption's date.
     */
    public Date getTimestampMinute() {
	return timestampMinute;
    }

    /**
     * Set the dateof a consumption.
     *
     * @param timestampMinute the new consumption's date.
     */
    public void setTimestampMinute(Date timestampMinute) {
	this.timestampMinute = timestampMinute;
    }

    /**
     * Get de kW of the consumption.
     *
     * @return the kW of the consumption.
     */
    public Double getkW() {
	return kW;
    }

    /**
     * Set the kW consumption.
     *
     * @param kW the new kW consumption.
     */
    public void setkW(Double kW) {
	this.kW = kW;
    }

    /**
     * Get the plug link to a consumption.
     *
     * @return the plug linked.
     */
    public Plug getPlug() {
	return plug;
    }

    /**
     * Set the plug link to a consumption.
     *
     * @param plug the new plug linked.
     */
    public void setPlug(Plug plug) {
	this.plug = plug;
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
