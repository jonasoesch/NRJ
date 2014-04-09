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

    public PublicConsumptionObsTO() {
    }

    public PublicConsumptionObsTO(long consumptionObsId, Date timestampMinute, Double kW) {
	this.consumptionObsId = consumptionObsId;
	this.timestampMinute = timestampMinute;
	this.kW = kW;
    }

    public long getConsumptionObsId() {
	return consumptionObsId;
    }

    public void setConsumptionObsId(long consumptionObsId) {
	this.consumptionObsId = consumptionObsId;
    }

    public Date getTimestampMinute() {
	return timestampMinute;
    }

    public void setTimestampMinute(Date timestampMinute) {
	this.timestampMinute = timestampMinute;
    }

    public Double getKW() {
	return kW;
    }

    public void setKW(Double kW) {
	this.kW = kW;
    }
}
