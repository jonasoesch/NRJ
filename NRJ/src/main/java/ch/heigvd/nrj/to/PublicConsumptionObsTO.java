package ch.heigvd.nrj.to;

import ch.heigvd.nrj.model.Plug;
import ch.heigvd.nrj.model.Room;
import java.util.Date;
import javax.persistence.ManyToOne;
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
    private Plug plug;

    public PublicConsumptionObsTO() {
    }

    public PublicConsumptionObsTO(long consumptionObsId, Double kW, Date timestampMinute, Plug plug) {
	this.consumptionObsId = consumptionObsId;
	this.timestampMinute = timestampMinute;
	this.kW = kW;
	this.plug = plug;
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
    
    public Double getkW() {
	return kW;
    }

    public void setkW(Double kW) {
	this.kW = kW;
    }

    public Plug getPlug() {
	return this.plug;
    }

    public void setPlug(Plug plug) {
	this.plug = plug;
    }
}
