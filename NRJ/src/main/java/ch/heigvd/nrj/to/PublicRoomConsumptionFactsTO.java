package ch.heigvd.nrj.to;

import ch.heigvd.nrj.model.Room;
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
public class PublicRoomConsumptionFactsTO {

    private Long roomConsumptionFactId;
    private Date timestampHour;
    private Double avgKW;
    private PublicRoomTO room;

    public PublicRoomConsumptionFactsTO() {
    }

    public PublicRoomConsumptionFactsTO(long roomConsumptionFactId, Date timestampHour, Double avgKW) {
	this.roomConsumptionFactId = roomConsumptionFactId;
	this.timestampHour = timestampHour;
	this.avgKW = avgKW;
    }

    public Long getRoomConsumptionFactId() {
	return this.roomConsumptionFactId;
    }

    public void setRoomConsumptionFactId(Long roomConsumptionFactId) {
	this.roomConsumptionFactId = roomConsumptionFactId;
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
    
    public PublicRoomTO getRoom() {
	return room;
    }

    public void setRoom(PublicRoomTO room) {
	this.room = room;
    }
}
