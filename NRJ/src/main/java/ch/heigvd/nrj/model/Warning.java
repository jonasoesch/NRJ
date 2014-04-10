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
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date timestampMinute;
    private String message;

    @ManyToOne
    protected Plug plug;
    
    public Warning() {
        this.timestampMinute = new Date();
        this.message = "UNDEF";
    }
            
    public Warning (Warning warningData) {
        this.timestampMinute = warningData.getTimestampMinute();
        this.message = warningData.getMessage();
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

    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }
    
    public Plug getPlug() {
        return plug;
    }

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
}
