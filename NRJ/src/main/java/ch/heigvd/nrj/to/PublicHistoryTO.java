package ch.heigvd.nrj.to;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This is the transferable object for an History of a plug.
 *
 * @XmlRootElement annotation at the class level.
 *
 * @author Option40
 */
@XmlRootElement
public class PublicHistoryTO {

    private long historyId;
    private Date timestampMinute;
    private boolean status;
    private PublicPlugTO plug;

    /**
     * Build a PublicHistoryTO empty. 
     */
    public PublicHistoryTO() {
    }
/**
 * Build a PublicHistoryTO with data. 
 * @param historyId
 * @param timestampMinute
 * @param status 
 */
    public PublicHistoryTO(long historyId, Date timestampMinute, boolean status) {
	this.historyId = historyId;
	this.timestampMinute = timestampMinute;
	this.status = status;
    }
/**
 * Get the id of PublicHistoryTO. 
 * @return the id. 
 */
    public long getHistoryId() {
	return historyId;
    }
/**
 * Set the history id. 
 * @param historyId 
 */
    public void setHistoryId(long historyId) {
	this.historyId = historyId;
    }
/**
 * Get the date of an history. 
 * @return the date.
 */
    public Date getTimestampMinute() {
	return timestampMinute;
    }
/**
 * Set the date of an history. 
 * @param timestampMinute 
 */
    public void setTimestampMinute(Date timestampMinute) {
	this.timestampMinute = timestampMinute;
    }
/**
 * Get the status of an history. 
 * @return the status. 
 */
    public boolean getStatus() {
	return status;
    }
/**
 * Set the status of an history. 
 * @param status 
 */
    public void setStatus(boolean status) {
	this.status = status;
    }
    /**
     * Get the plug link to an history. 
     * @return the plug linked. 
     */
    public PublicPlugTO getPlug() {
	return plug;
    }
/**
 * Set the plug linked to an history. 
 * @param plug 
 */
    public void setPlug(PublicPlugTO plug) {
	this.plug = plug;
    }
}
