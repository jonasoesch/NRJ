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
        name = "History.findAllHistories",
        query = "SELECT h FROM History h"))
@Entity
public class History implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date timestampMinute;
    private boolean status;
    
    @ManyToOne protected Plug plug;

    public History() {
        this.timestampMinute = new Date();
	this.status = false;
        
    }

    public History(History historyData) {
        this.timestampMinute = historyData.getTimestampMinute();
	this.status = historyData.getStatus();
	this.plug = historyData.getPlug();
    }
    
    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public void setTimestampMinute(Date timestampMinute) {
	this.timestampMinute = timestampMinute;
    }

    public Date getTimestampMinute() {
	return timestampMinute;
    }

    public boolean getStatus() {
	return status;
    }

    public void setStatus(boolean status) {
	this.status = status;
    }

    public Plug getPlug() {
	return plug;
    }
    
    public void setPlug(Plug plug){
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
