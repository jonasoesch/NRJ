package ch.heigvd.nrj.to;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This is the transferable object for a Warning.
 *
 * @XmlRootElement annotation at the class level.
 *
 * @author Option40
 */
@XmlRootElement
public class PublicWarningTO {

    private Long warningId;
    private Date timestampMinute;
    private String message;
    private PublicPlugTO plug;

    /**
     * Constructs a PublicWarningTO without data
     */
    public PublicWarningTO() {
    }

    /**
     * Constructs a PublicWarningTO with data
     *
     * @param warningId, the Warning's id
     * @param timestampMinute the timestamp of the Warning
     * @param message the Message of the Warning
     */
    public PublicWarningTO(long warningId, Date timestampMinute, String message) {
        this.warningId = warningId;
        this.timestampMinute = timestampMinute;
        this.message = message;
    }

    /**
     * Get the Warning's id
     *
     * @return the Warning's id
     */
    public Long getWarningId() {
        return warningId;
    }

    /**
     * Set the Warning's id
     *
     * @param warningId
     */
    public void setWarningId(Long warningId) {
        this.warningId = warningId;
    }

    /**
     * Get the Tomestamp of this Warning
     *
     * @return a Date
     */
    public Date getTimestampMinute() {
        return timestampMinute;
    }

    /**
     * Set the timestamp of this Warning
     *
     * @param timestampMinute
     */
    public void setTimestampMinute(Date timestampMinute) {
        this.timestampMinute = timestampMinute;
    }

    /**
     * Get the message of this Warning
     *
     * @return a Message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set the Message of this Warning
     *
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Get the Plug linked to this Warning
     *
     * @return a Plug
     */
    public PublicPlugTO getPlug() {
        return plug;
    }

    /**
     * Set the Plug linked to this Warning
     *
     * @param plug
     */
    public void setPlug(PublicPlugTO plug) {
        this.plug = plug;
    }
}
