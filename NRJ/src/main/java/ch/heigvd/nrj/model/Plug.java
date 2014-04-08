package ch.heigvd.nrj.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Cette classe permet de gérer des prises murales, avec leur propriété alwaysOn.
 * 
 * @author nicolas
 */
@NamedQueries(
        @NamedQuery(
        name = "Plug.findAllPlugs",
        query = "SELECT p FROM Plug p"))
@Entity
@XmlRootElement
public class Plug implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private boolean alwaysOn;
    
    @ManyToOne protected Room room;
    
    @OneToMany(mappedBy="plug") protected Collection<History> histories;
    @OneToMany(mappedBy="plug") protected Collection<PlugConsumptionObs> plugConsumptions;
    @OneToMany(mappedBy="plug") protected Collection<Consumption> consumptions;

    public Plug() {
        this.name = "UNDEF";
        this.alwaysOn = false;
    }
            
    public Plug (Plug plugData) {
        this.name = plugData.getName();
        this.alwaysOn = plugData.getAlwaysOn();
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getAlwaysOn() {
        return alwaysOn;
    }

    public void setAlwaysOn(boolean alwaysOn) {
        this.alwaysOn = alwaysOn;
    }
    
    public Room getRoom() {
	return room;
    }

    public void setRoom(Room room) {
	this.room = room;
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
        if (!(object instanceof Plug)) {
            return false;
        }
        Plug other = (Plug) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ch.heigvd.nrj.model.Plug[ id=" + id + " ]";
    }
    
}
