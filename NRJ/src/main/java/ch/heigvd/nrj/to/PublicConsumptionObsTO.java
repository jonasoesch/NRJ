package ch.heigvd.nrj.to;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This is the transferable object for a Consumption.
 *
 * @XmlRootElement annotation at the class level.
 *
 * @author Option40
 */
@XmlRootElement
public class PublicConsumptionObsTO {

    private long consumptionObsId;
    private Date timestampMinute;
    private Double kW;
    private PublicPlugTO plug;

    /**
     * Build an empty PublicConsumptionObsTO.
     */
    public PublicConsumptionObsTO() {
    }

    /**
     * Build a PublicConsumptionObsTO with data.
     *
     * @param consumptionObsId
     * @param kW
     * @param timestampMinute
     */
    public PublicConsumptionObsTO(long consumptionObsId, Double kW, Date timestampMinute) {
	this.consumptionObsId = consumptionObsId;
	this.timestampMinute = timestampMinute;
	this.kW = kW;
    }

    /**
     * Get the id of consumptionObs.
     *
     * @return the id.
     */
    public long getConsumptionObsId() {
	return consumptionObsId;
    }

    /**
     * Set the id of the consumption.
     *
     * @param consumptionObsId
     */
    public void setConsumptionObsId(long consumptionObsId) {
	this.consumptionObsId = consumptionObsId;
    }

    /**
     * Get the date of the consumption.
     *
     * @return the date of the consumption.
     */
    public Date getTimestampMinute() {
	return timestampMinute;
    }

    /**
     * Set the date of the consumption.
     *
     * @param timestampMinute
     */
    public void setTimestampMinute(Date timestampMinute) {
	this.timestampMinute = timestampMinute;
    }

    /**
     * Get the kW consumption.
     *
     * @return the kW consumption.
     */
    public Double getkW() {
	return kW;
    }

    /**
     * Set the kW consumption.
     *
     * @param kW
     */
    public void setkW(Double kW) {
	this.kW = kW;
    }

    /**
     * Get the plug link to the consumption.
     *
     * @return the plug linked.
     */
    public PublicPlugTO getPlug() {
	return this.plug;
    }

    /**
     * Set the plug link to the consumption.
     *
     * @param plug
     */
    public void setPlug(PublicPlugTO plug) {
	this.plug = plug;
    }
}
