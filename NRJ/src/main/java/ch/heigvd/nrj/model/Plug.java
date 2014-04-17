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
 * Cette classe permet de gérer des prises murales, avec leur propriété
 * alwaysOn.
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

    /**
     * The id of the Plug
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * The Name of the Plug
     */
    private String name;

    /**
     * Boolean to know if this Plug must stay on or not (ex : a fridge)
     */
    private boolean alwaysOn;

    /**
     * The Room containing this Plug
     */
    @ManyToOne
    protected Room room;

    /**
     * A List of Histories linked to this Plug
     */
    @OneToMany(mappedBy = "plug")
    protected List<History> histories;

    /**
     * A List of PlugConsumptionFacts linked to this Plug
     */
    @OneToMany(mappedBy = "plug")
    protected List<PlugConsumptionFact> plugConsumptionsFacts;

    /**
     * A List of ConsumptionObs linked to this Plug
     */
    @OneToMany(mappedBy = "plug")
    protected List<ConsumptionObs> consumptionsObs;

    /**
     * A List of Warnings linked to this Plug
     */
    @OneToMany(mappedBy = "plug")
    protected List<Warning> warnings;

    /**
     * Construc a new Plug without data
     */
    public Plug() {
        this.name = "UNDEF";
        this.alwaysOn = false;
        this.histories = new ArrayList<>();
        this.plugConsumptionsFacts = new ArrayList<>();
        this.consumptionsObs = new ArrayList<>();
        this.warnings = new ArrayList<>();
    }

    /**
     * Construct a new Pleg with data
     *
     * @param plugData
     */
    public Plug(Plug plugData) {
        this.name = plugData.getName();
        this.alwaysOn = plugData.getAlwaysOn();
        this.room = plugData.getRoom();
        this.histories = plugData.getHistories();
        this.plugConsumptionsFacts = plugData.getPlugConsumptionsFacts();
        this.consumptionsObs = plugData.getConsumptionsObs();
        this.warnings = plugData.getWarnings();
    }

    /**
     * Get the Plug's id
     *
     * @return
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the Plug's id
     *
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the Plug's name
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Set the Plug's name
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the alwaysOn boolean
     *
     * @return
     */
    public boolean getAlwaysOn() {
        return alwaysOn;
    }

    /**
     * Set the alwaysOn boolean. If a Plug has to stay always on, this boolean
     * must true.
     *
     * @param alwaysOn
     */
    public void setAlwaysOn(boolean alwaysOn) {
        this.alwaysOn = alwaysOn;
    }

    /**
     * Get the Room containing this Plug
     *
     * @return
     */
    public Room getRoom() {
        return room;
    }

    /**
     * Set the Room containing this Plug
     *
     * @param room
     */
    public void setRoom(Room room) {
        this.room = room;
    }

    /**
     * Get the List of Histories linked to this Plug
     *
     * @return
     */
    public List<History> getHistories() {
        return this.histories;
    }

    /**
     * Set the List of Histories linked to this Plug
     *
     * @param histories
     */
    public void setHistories(List<History> histories) {
        this.histories = histories;
    }

    /**
     * Add a history to this Plug
     *
     * @param history, the History to add
     */
    public void addHistory(History history) {
        this.histories.add(history);
    }

    /**
     * Get the List of Warnings linked to this Plug
     *
     * @return
     */
    public List<Warning> getWarnings() {
        return warnings;
    }

    /**
     * Set the List of Warnings linked to this Plug
     *
     * @param warnings
     */
    public void setWarnings(List<Warning> warnings) {
        this.warnings = warnings;
    }

    /**
     * Add a Warning to this Plug
     *
     * @param warning, the Warning to add
     */
    public void addWarning(Warning warning) {
        this.warnings.add(warning);
    }

    /**
     * Get the List of PlugConsumptionFacts linked to this Plug
     *
     * @return
     */
    public List<PlugConsumptionFact> getPlugConsumptionsFacts() {
        return this.plugConsumptionsFacts;
    }

    /**
     * Set the List of PlugConsumptionFacts linked to this Plug
     *
     * @param plugConsumptions
     */
    public void setPlugConsumptionsFacts(List<PlugConsumptionFact> plugConsumptions) {
        this.plugConsumptionsFacts = plugConsumptions;
    }

    /**
     * Add a plugConsumptionFact to this Plug
     *
     * @param plugConsumptionFact
     */
    public void addPlugConsumptionFact(PlugConsumptionFact plugConsumptionFact) {
        this.plugConsumptionsFacts.add(plugConsumptionFact);
    }

    /**
     * Get the List of ConsumptionObs linked to this Plug
     *
     * @return
     */
    public List<ConsumptionObs> getConsumptionsObs() {
        return this.consumptionsObs;
    }

    /**
     * Set the List of ConsumptionObs of this Plug
     *
     * @param consumptions
     */
    public void setConsumptionsObs(List<ConsumptionObs> consumptions) {
        this.consumptionsObs = consumptions;
    }

    /**
     * Add a ConsumptionObs to this Plug
     *
     * @param consumption
     */
    public void addConsumptionObs(ConsumptionObs consumption) {
        this.consumptionsObs.add(consumption);
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
