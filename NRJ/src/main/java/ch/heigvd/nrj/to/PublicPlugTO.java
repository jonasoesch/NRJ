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

    /**
     * Constructs a PlugTO without data
     */
    public PublicPlugTO() {
        this.histories = new ArrayList<>();
        this.plugConsumptions = new ArrayList<>();
        this.warnings = new ArrayList<>();
    }

    /**
     * Constructs a PlugTO with data
     *
     * @param plugId
     * @param name
     * @param alwaysOn
     */
    public PublicPlugTO(long plugId, String name, boolean alwaysOn) {
        this.plugId = plugId;
        this.name = name;
        this.alwaysOn = alwaysOn;
        this.histories = new ArrayList<>();
        this.plugConsumptions = new ArrayList<>();
        this.warnings = new ArrayList<>();
    }

    /**
     * Get the id of this Plug
     *
     * @return
     */
    public long getPlugId() {
        return plugId;
    }

    /**
     * Set the id of this Plug
     *
     * @param plugId
     */
    public void setPlugId(long plugId) {
        this.plugId = plugId;
    }

    /**
     * Get the Name of this Plug
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of this Plug
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the alwaysOn boolean of this Plug
     *
     * @return a boolean
     */
    public boolean getAlwaysOn() {
        return alwaysOn;
    }

    /**
     * Set the alwaysOn boolean of this Plug
     *
     * @param alwaysOn
     */
    public void setAlwaysOn(boolean alwaysOn) {
        this.alwaysOn = alwaysOn;
    }

    /**
     * Get the Room linked with this Plug
     *
     * @return a Room
     */
    public PublicRoomTO getRoom() {
        return room;
    }

    /**
     * Set the Room linked with this Plug
     *
     * @param room
     */
    public void setRoom(PublicRoomTO room) {
        this.room = room;
    }

    /**
     * Get the Histories linked with this Plug
     *
     * @return a List of History
     */
    public List<PublicHistoryTO> getHistories() {
        return histories;
    }

    /**
     * Set the List of Histories for this Plug
     *
     * @param histories a List of History
     */
    public void setHistories(List<PublicHistoryTO> histories) {
        this.histories = histories;
    }

    /**
     * Adds a History to this Plug
     *
     * @param h a History
     */
    public void addHistory(PublicHistoryTO h) {
        this.histories.add(h);
    }

    /**
     * Get the ConsumptionsFacts of this Plug
     *
     * @return a List of PlugConsumptionFacts
     */
    public List<PublicPlugConsumptionFactsTO> getPlugConsumptions() {
        return plugConsumptions;
    }

    /**
     * Set the PlugConsumptionsFacts for this Plug
     *
     * @param plugConsumptions a List of PlugConsumptionFacts
     */
    public void setPlugConsumptions(List<PublicPlugConsumptionFactsTO> plugConsumptions) {
        this.plugConsumptions = plugConsumptions;
    }

    /**
     * Adds a PlugConsumptionFact to this Plug
     *
     * @param plugConsumption
     */
    public void addPlugConsumption(PublicPlugConsumptionFactsTO plugConsumption) {
        this.plugConsumptions.add(plugConsumption);
    }

    /**
     * Get the Warnings linked to this Plug
     *
     * @return a List of Warnings
     */
    public List<PublicWarningTO> getWarnings() {
        return warnings;
    }

    /**
     * Set the Warnings of this Plug
     *
     * @param warnings a List of Warnings
     */
    public void setWarnings(List<PublicWarningTO> warnings) {
        this.warnings = warnings;
    }

    /**
     * Adds a Warning to this Plug
     *
     * @param warning a Warning
     */
    public void addWarning(PublicWarningTO warning) {
        this.warnings.add(warning);
    }
}
