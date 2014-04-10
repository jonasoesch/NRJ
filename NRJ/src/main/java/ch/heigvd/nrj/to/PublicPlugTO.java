package ch.heigvd.nrj.to;

import ch.heigvd.nrj.model.ConsumptionObs;
import ch.heigvd.nrj.model.History;
import ch.heigvd.nrj.model.PlugConsumptionFact;
import ch.heigvd.nrj.model.Room;
import ch.heigvd.nrj.model.Warning;
import java.util.List;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This is the transferable object for a plug.
 *
 * @XmlRootElement annotation at the class level.
 *
 * @author Option40
 */
@XmlRootElement
public class PublicPlugTO {

    private long plugId;
    private String name;
    private boolean alwaysOn;
    private Room room;
    private List<History> histories;
    private List<PlugConsumptionFact> plugConsumptions;
    private List<ConsumptionObs> consumptions;
    private List<Warning> warnings;

    public PublicPlugTO() {
    }

    public PublicPlugTO(long plugId, String name, boolean alwaysOn, Room room, List<History> histories, List<PlugConsumptionFact> plugConsumptions, List<ConsumptionObs> consumptions, List<Warning> warnings) {
        this.plugId = plugId;
        this.name = name;
        this.alwaysOn = alwaysOn;
	this.room = room;
	this.histories = histories;
	this.plugConsumptions = plugConsumptions;
	this.consumptions = consumptions;
	this.warnings = warnings;
    }

    public long getPlugId() {
        return plugId;
    }

    public void setPlugId(long plugId) {
        this.plugId = plugId;
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
    
    public List<History> getHistories() {
	return histories;
    }

    public void setHistories(List<History> histories) {
	this.histories = histories;
    }

    public List<PlugConsumptionFact> getPlugConsumptions() {
	return plugConsumptions;
    }

    public void setPlugConsumptions(List<PlugConsumptionFact> plugConsumptions) {
	this.plugConsumptions = plugConsumptions;
    }

    public List<ConsumptionObs> getConsumptions() {
	return consumptions;
    }

    public void setConsumptions(List<ConsumptionObs> consumptions) {
	this.consumptions = consumptions;
    }

    public List<Warning> getWarnings() {
	return warnings;
    }

    public void setWarnings(List<Warning> warnings) {
	this.warnings = warnings;
    }
    
}
