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
public class PublicRoomConsumptionObsTO {

    private Long roomConsumptionObsId;
    private Date timestampHour;
    private Double avgKW;

    public PublicRoomConsumptionObsTO() {
    }

    public PublicRoomConsumptionObsTO(long roomConsumptionObsId, Date timestampHour, Double avgKW) {
	this.roomConsumptionObsId = roomConsumptionObsId;
	this.timestampHour = timestampHour;
	this.avgKW = avgKW;
    }

    public Long getRoomConsumptionId() {
	return this.roomConsumptionObsId;
    }

    public void setRoomConsumptionId(Long roomConsumptionObsId) {
	this.roomConsumptionObsId = roomConsumptionObsId;
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
