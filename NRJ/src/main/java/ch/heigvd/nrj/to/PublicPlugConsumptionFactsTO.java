package ch.heigvd.nrj.to;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This is the transferable object for a consumption of a plug.
 *
 * @XmlRootElement annotation at the class level.
 *
 * @author Option40
 */
@XmlRootElement
public class PublicPlugConsumptionFactsTO {

    private Long plugConsumptionFactId;
    private Date timestampHour;
    private Double avgKW;
    private PublicPlugTO plug;

    /**
     * Build a PublicPlugConsumptionFactsTO empty.
     */
    public PublicPlugConsumptionFactsTO() {
	this.plug = new PublicPlugTO();
    }

    /**
     * Build a PublicPlugConsumptionFactsTO with data.
     *
     * @param plugConsumptionFactId
     * @param timestampHour
     * @param avgKW
     */
    public PublicPlugConsumptionFactsTO(long plugConsumptionFactId, Date timestampHour, Double avgKW) {
	this.plugConsumptionFactId = plugConsumptionFactId;
	this.timestampHour = timestampHour;
	this.avgKW = avgKW;
    }

    /**
     * Get the id of the PublicPlugConsumptionFactsTO
     *
     * @return the id.
     */
    public Long getPlugConsumptionFactId() {
	return this.plugConsumptionFactId;
    }

    /**
     * Set the id of the PublicPlugConsumptionFactsTO.
     *
     * @param plugConsumptionFactId
     */
    public void setPlugConsumptionFactId(Long plugConsumptionFactId) {
	this.plugConsumptionFactId = plugConsumptionFactId;
    }

    /**
     * Get the date of the PublicPlugConsumptionFactsTO.
     *
     * @return the date.
     */
    public Date getTimestampHour() {
	return timestampHour;
    }

    /**
     * Set the date of the PublicPlugConsumptionFactsTO.
     *
     * @param timestampHour
     */
    public void setTimestampHour(Date timestampHour) {
	this.timestampHour = timestampHour;
    }

    /**
     * Get the average kW of a PublicPlugConsumptionFactsTO
     *
     * @return the average kW.
     */
    public Double getAvgKW() {
	return avgKW;
    }

    /**
     * Set the average kW of a PublicPlugConsumptionFactsTO
     *
     * @param avgKW
     */
    public void setAvgKW(Double avgKW) {
	this.avgKW = avgKW;
    }

    /**
     * Get the PublicPlugTO link to a PublicPlugConsumptionFactsTO
     *
     * @return the PublicPlugTO. 
     */
    public PublicPlugTO getPlug() {
	return plug;
    }

    /**
     * Set the PublicPlugTO linked to a PublicPlugTO
     *
     * @param plug
     */
    public void setPlug(PublicPlugTO plug) {
	this.plug = plug;
    }
}
