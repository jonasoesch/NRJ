package ch.heigvd.nrj.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
    
    @OneToMany(mappedBy="plug") protected List<History> histories;
    @OneToMany(mappedBy="plug") protected List<PlugConsumptionFact> plugConsumptions;
    @OneToMany(mappedBy="plug") protected List<ConsumptionObs> consumptions;
    @OneToMany(mappedBy="plug") protected List<Warning> warnings;

    public Plug() {
        this.name = "UNDEF";
        this.alwaysOn = false;
        this.histories = new ArrayList<>();
        this.plugConsumptions = new ArrayList<>();
        this.consumptions = new ArrayList<>();
    }
            
    public Plug (Plug plugData) {
        this.name = plugData.getName();
        this.alwaysOn = plugData.getAlwaysOn();
        this.room = plugData.getRoom();
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

    public List<History> getHistories(){
        return this.histories;
    }
    
    public void setHistories(List<History> histories){
        this.histories = histories;
    }
    
    public void addHistory(History history){
        history.setPlug(this);
        this.histories.add(history);
    }
    
    public List<Warning> getWarnings() {
        return warnings;
    }

    public void setWarnings(List<Warning> warnings) {
        this.warnings = warnings;
    }
    
    public void addWarnings(Warning warning) {
        warning.setPlug(this);
        this.warnings.add(warning);
    }
    
    public List<PlugConsumptionFact> getPlugConsumptions(){
        return this.plugConsumptions;
    }
    
    public void setPlugConsumptions(List<PlugConsumptionFact> plugConsumptions){
        this.plugConsumptions = plugConsumptions;
    }
    
    public void addPlugConsumption(PlugConsumptionFact plugConsumption){
        this.plugConsumptions.add(plugConsumption);
        plugConsumption.setPlug(this);
    }
    
    public List<ConsumptionObs> getConsumptions(){
        return this.consumptions;
    }
    
    public void setConsumptions(List<ConsumptionObs> consumptions){
        this.consumptions = consumptions;
    }
    
    public void addConsumption(ConsumptionObs consumption){
        this.consumptions.add(consumption);
        
        
        //---------------------------
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
