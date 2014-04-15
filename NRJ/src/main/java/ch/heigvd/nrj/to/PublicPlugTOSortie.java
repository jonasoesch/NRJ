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
public class PublicPlugTOSortie {

    private long plugId;
    private String name;
    private boolean alwaysOn;
    private List<History> histories;
    private List<PlugConsumptionFact> plugConsumptions;
    private List<Warning> warnings;

    public PublicPlugTOSortie() {
    }

    public PublicPlugTOSortie(long plugId, String name, boolean alwaysOn, List<History> histories, List<PlugConsumptionFact> plugConsumptionsFact, List<Warning> warnings) {
        this.plugId = plugId;
        this.name = name;
        this.alwaysOn = alwaysOn;
	this.histories = histories;
	this.plugConsumptions = plugConsumptionsFact;
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

    public List<Warning> getWarnings() {
	return warnings;
    }

    public void setWarnings(List<Warning> warnings) {
	this.warnings = warnings;
    }
    
}
