package ch.heigvd.nrj.to;

import java.util.ArrayList;
import java.util.List;
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
    private PublicRoomTO room;
    private List<PublicHistoryTO> histories;
    private List<PublicPlugConsumptionFactsTO> plugConsumptions;
    private List<PublicWarningTO> warnings;

    public PublicPlugTO() {
        this.histories = new ArrayList<>();
        this.plugConsumptions = new ArrayList<>();
        this.warnings = new ArrayList<>();
    }

    public PublicPlugTO(long plugId, String name, boolean alwaysOn) {
	this.plugId = plugId;
	this.name = name;
	this.alwaysOn = alwaysOn;
	this.histories = new ArrayList<>();
        this.plugConsumptions = new ArrayList<>();
        this.warnings = new ArrayList<>();
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

    public PublicRoomTO getRoom() {
	return room;
    }

    public void setRoom(PublicRoomTO room) {
	this.room = room;
    }

    public List<PublicHistoryTO> getHistories() {
	return histories;
    }

    public void setHistories(List<PublicHistoryTO> histories) {
	this.histories = histories;
    }

    public void addHistory(PublicHistoryTO h) {
	this.histories.add(h);
    }

    public List<PublicPlugConsumptionFactsTO> getPlugConsumptions() {
	return plugConsumptions;
    }

    public void setPlugConsumptions(List<PublicPlugConsumptionFactsTO> plugConsumptions) {
	this.plugConsumptions = plugConsumptions;
    }

    public void addPlugConsumption(PublicPlugConsumptionFactsTO plugConsumption) {
	this.plugConsumptions.add(plugConsumption);
    }

    public List<PublicWarningTO> getWarnings() {
	return warnings;
    }

    public void setWarnings(List<PublicWarningTO> warnings) {
	this.warnings = warnings;
    }

    public void addWarning(PublicWarningTO warning) {
	this.warnings.add(warning);
    }
}
