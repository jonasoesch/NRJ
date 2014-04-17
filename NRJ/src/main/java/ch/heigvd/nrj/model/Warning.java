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
 * This class is a JPA entity for a Warning about a plug.
 *
 * @author rschmutz
 */
@NamedQueries(
        @NamedQuery(
                name = "Warning.findAllWarnings",
                query = "SELECT w FROM Warning w"))
@Entity
public class Warning implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * The entity id for a warning
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * The timestamp of the Warning
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date timestampMinute;

    /**
     * The String message of the Warning
     */
    private String message;

    /**
     * The Plug linked with this Warning
     */
    @ManyToOne
    protected Plug plug;

    /**
     * Construct a Warning without data
     */
    public Warning() {
        this.timestampMinute = new Date();
        this.message = "UNDEF";
    }

    /**
     * Construct a Warning with data
     *
     * @param warningData
     */
    public Warning(Warning warningData) {
        this.timestampMinute = warningData.getTimestampMinute();
        this.message = warningData.getMessage();
        this.plug = warningData.getPlug();
    }

    /**
     * Get the id
     *
     * @return id, the warning's id
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the id of a Warning
     *
     * @param id, the new Warning's id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the timestamp of this Warning
     *
     * @return a Date
     */
    public Date getTimestampMinute() {
        return timestampMinute;
    }

    /**
     * Set the timestamp of this Warning
     *
     * @param timestampMinute
     */
    public void setTimestampMinute(Date timestampMinute) {
        this.timestampMinute = timestampMinute;
    }

    /**
     * Get the message of this Warning
     *
     * @return a String message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set the message of this Warning
     *
     * @param message a String
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Get the Plug linked with this Warning
     *
     * @return the Plug of this Warning
     */
    public Plug getPlug() {
        return plug;
    }

    /**
     * Set the Plug linked with this Warning
     *
     * @param plug
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
        if (!(object instanceof Warning)) {
            return false;
        }
        Warning other = (Warning) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ch.heigvd.nrj.model.Warning[ id=" + id + " ]";
    }
} // Warning
