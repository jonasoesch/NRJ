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
 * @author Ralf
 */
@NamedQueries({
    @NamedQuery(name = "PlugConsumptionFact.findAllPlugConsumptionsFacts",
            query = "SELECT pc FROM PlugConsumptionFact pc"),
    @NamedQuery(name = "PlugConsumptionFact.findAllPlugConsumptionsFactsForAPeriod",
            query = "SELECT pcp FROM PlugConsumptionFact pcp WHERE pcp.timestampHour BETWEEN :debut AND :fin"),
    @NamedQuery(name = "PlugConsumptionFact.getLastPlugFact",
            query = "SELECT lpcf FROM PlugConsumptionFact lpcf WHERE lpcf.plug = :plug ORDER BY lpcf.timestampHour DESC"),
    @NamedQuery(name = "PlugConsumptionFact.getConsumptionFactsAfterTime",
            query = "SELECT cfat FROM PlugConsumptionFact cfat WHERE cfat.plug = :plug AND cfat.timestampHour >= :time"),})

@Entity
public class PlugConsumptionFact implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * The id of a PlugConsumptionFact
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * The Timestamp of this PlugConsumptionFact
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date timestampHour;

    /**
     * The kiloWatts for this PlugConsumptionFact
     */
    private Double avgKW;

    /**
     * The Plug linked to this PlugConsumptionFact
     */
    @ManyToOne
    protected Plug plug;

    /**
     * Construct a new PlugConsumptionFact without data
     */
    public PlugConsumptionFact() {
        this.timestampHour = new Date();
        this.avgKW = 0.0;
    }

    /**
     * Construct a new PlugConsumptionFact with data
     *
     * @param plugConsumptionFactData
     */
    public PlugConsumptionFact(PlugConsumptionFact plugConsumptionFactData) {
        this.timestampHour = plugConsumptionFactData.getTimestampHour();
        this.avgKW = plugConsumptionFactData.getAvgKW();
        this.plug = plugConsumptionFactData.getPlug();
    }

    /**
     * Get the PlugConsumptionFact's id
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the new PlugConsumptionFact's id
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the timestamp of this PlugConsumptionFact
     *
     * @return
     */
    public Date getTimestampHour() {
        return timestampHour;
    }

    /**
     * Set the timestamp of this PlugConsumptionFact
     *
     * @param timestampHour
     */
    public void setTimestampHour(Date timestampHour) {
        this.timestampHour = timestampHour;
    }

    /**
     * Get the avgKW of this PlugConsumptionFact
     *
     * @return
     */
    public Double getAvgKW() {
        return avgKW;
    }

    /**
     * Set the avgKW of this PlugConsumptionFact
     *
     * @param avgKW
     */
    public void setAvgKW(Double avgKW) {
        this.avgKW = avgKW;
    }

    /**
     * Set the plug of this PlugConsumptionFact
     *
     * @param plug
     */
    public void setPlug(Plug plug) {
        this.plug = plug;
    }

    /**
     * Get the plug of this PlugConsumptionFact
     *
     * @return
     */
    public Plug getPlug() {
        return this.plug;
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
