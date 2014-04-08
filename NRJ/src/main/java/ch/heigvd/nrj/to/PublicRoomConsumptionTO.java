package ch.heigvd.nrj.to;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This is the transferable object for a consumption of a room.
 *
 * @XmlRootElement annotation at the class level.
 *
 * @author Option40
 */
@XmlRootElement
public class PublicRoomConsumptionTO {

    private Long roomConsumptionId;
    private Date timestampHour;
    private Double avgKW;

    public PublicRoomConsumptionTO() {
    }

    public PublicRoomConsumptionTO(long roomConsumptionId, Date timestampHour, Double avgKW) {
	this.roomConsumptionId = roomConsumptionId;
	this.timestampHour = timestampHour;
	this.avgKW = avgKW;
    }

    public Long getRoomConsumptionId() {
	return this.roomConsumptionId;
    }

    public void setRoomConsumptionId(Long roomConsumptionId) {
	this.roomConsumptionId = roomConsumptionId;
    }

    public Date getTimestampHour() {
	return timestampHour;
    }

    public void setTimestampHour(Date timestampHour) {
	this.timestampHour = timestampHour;
    }

    public Double getAvgKW() {
	return avgKW;
    }

    public void setAvgKW(Double avgKW) {
	this.avgKW = avgKW;
    }
}
