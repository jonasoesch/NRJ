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
public class PublicConsumptionTO {

    private long consumptionId;
    private Date timestampMinute;
    private Double kW;

    public PublicConsumptionTO() {
    }

    public PublicConsumptionTO(long consumptionId, Date timestampMinute, Double kW) {
	this.consumptionId = consumptionId;
	this.timestampMinute = timestampMinute;
	this.kW = kW;
    }

    public long getConsumptionId() {
	return consumptionId;
    }

    public void setConsumptionId(long consumptionId) {
	this.consumptionId = consumptionId;
    }

    public Date getTimeStamp() {
	return timestampMinute;
    }

    public void setTimeStamp(Date timestampMinute) {
	this.timestampMinute = timestampMinute;
    }

    public Double getKW() {
	return kW;
    }

    public void setKW(Double kW) {
	this.kW = kW;
    }
}
