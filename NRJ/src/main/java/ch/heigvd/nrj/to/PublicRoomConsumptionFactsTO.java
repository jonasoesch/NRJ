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
public class PublicRoomConsumptionFactsTO {

    private Long roomConsumptionFactId;
    private Date timestampHour;
    private Double avgKW;
    private PublicRoomTO room;

    /**
     * Constructs a PublicRoomConsumptionFactsTO without data
     */
    public PublicRoomConsumptionFactsTO() {
    }

    /**
     * Constructs a PublicRoomConsumptionFactsTO with data
     *
     * @param roomConsumptionFactId
     * @param timestampHour
     * @param avgKW
     */
    public PublicRoomConsumptionFactsTO(long roomConsumptionFactId, Date timestampHour, Double avgKW) {
        this.roomConsumptionFactId = roomConsumptionFactId;
        this.timestampHour = timestampHour;
        this.avgKW = avgKW;
    }

    /**
     * Get the RoomConsumptionFact's id
     *
     * @return the id
     */
    public Long getRoomConsumptionFactId() {
        return this.roomConsumptionFactId;
    }

    /**
     * Set the RoomConsumptionFact's id
     *
     * @param roomConsumptionFactId
     */
    public void setRoomConsumptionFactId(Long roomConsumptionFactId) {
        this.roomConsumptionFactId = roomConsumptionFactId;
    }

    /**
     * Get the timestamp of this RoomConsumptionFact
     *
     * @return a Date
     */
    public Date getTimestampHour() {
        return timestampHour;
    }

    /**
     * Set the timestamp of this RoomConsumptionFact
     *
     * @param timestampHour
     */
    public void setTimestampHour(Date timestampHour) {
        this.timestampHour = timestampHour;
    }

    /**
     * Get the avgKW of this RoomConsumptionFact
     *
     * @return the avgKW
     */
    public Double getAvgKW() {
        return avgKW;
    }

    /**
     * Set the avgKW of this RoomConsumptionFact
     *
     * @param avgKW
     */
    public void setAvgKW(Double avgKW) {
        this.avgKW = avgKW;
    }

    /**
     * Get the Room linked with this RoomConsumptionFact
     *
     * @return a Room
     */
    public PublicRoomTO getRoom() {
        return room;
    }

    /**
     * Set the Room linked with this RoomConsumptionFact
     *
     * @param room
     */
    public void setRoom(PublicRoomTO room) {
        this.room = room;
    }
}
