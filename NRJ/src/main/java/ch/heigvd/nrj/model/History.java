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
 * This class is a JPA entity for an History of a plug.
 *
 * @author Robin
 */
@NamedQueries(
@NamedQuery(
        name = "Histories.findAllHistories",
query = "SELECT h FROM History h"))
@NamedQuery(
        name = "Histories.findLast",
query = "SELECT h from History h where h.id=(SELECT MAX(h2.id) from History h2)")
@Entity
public class History implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * The history's id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * The date of the history.
     */
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date timestampMinute;
    /**
     * the boolean status of an history.
     */
    private boolean status;
    /**
     * The plug link to an history.
     */
    @ManyToOne
    protected Plug plug;

    /**
     * Construct a history without data.
     */
    public History() {
	this.timestampMinute = new Date();
	this.status = false;

    }

    /**
     * Construct a history with data.
     *
     * @param historyData the history datas.
     */
    public History(History historyData) {
	this.timestampMinute = historyData.getTimestampMinute();
	this.status = historyData.getStatus();
	this.plug = historyData.getPlug();
    }

    /**
     * Get the id of an history.
     *
     * @return the history's id.
     */
    public Long getId() {
	return id;
    }

    /**
     * Set the id of an history.
     *
     * @param id the new history's id.
     */
    public void setId(Long id) {
	this.id = id;
    }

    /**
     * Set the date of an history.
     *
     * @param timestampMinute the new history's date.
     */
    public void setTimestampMinute(Date timestampMinute) {
	this.timestampMinute = timestampMinute;
    }

    /**
     * Get the date of an history.
     *
     * @return Get the history's date.
     */
    public Date getTimestampMinute() {
	return timestampMinute;
    }

    /**
     * Get the status of the history.
     *
     * @return the history's status.
     */
    public boolean getStatus() {
	return status;
    }

    /**
     * Set the status of the history.
     *
     * @param status the new history's status.
     */
    public void setStatus(boolean status) {
	this.status = status;
    }

    /**
     * Get the plug link to the history.
     *
     * @return the history's plug.
     */
    public Plug getPlug() {
	return plug;
    }

    /**
     * Set the plug link to the history.
     *
     * @param plug the history's plug.
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
	if (!(object instanceof History)) {
	    return false;
	}
	History other = (History) object;
	if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "ch.heigvd.nrj.model.History[ id=" + id + " ]";
    }
}
