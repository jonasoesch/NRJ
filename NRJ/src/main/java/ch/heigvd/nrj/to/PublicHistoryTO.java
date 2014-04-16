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

    public PublicHistoryTO() {
    }

    public PublicHistoryTO(long historyId, Date timestampMinute, boolean status) {
	this.historyId = historyId;
	this.timestampMinute = timestampMinute;
	this.status = status;
    }

    public long getHistoryId() {
	return historyId;
    }

    public void setHistoryId(long historyId) {
	this.historyId = historyId;
    }

    public Date getTimestampMinute() {
	return timestampMinute;
    }

    public void setTimestampMinute(Date timestampMinute) {
	this.timestampMinute = timestampMinute;
    }

    public boolean getStatus() {
	return status;
    }

    public void setStatus(boolean status) {
	this.status = status;
    }
    
    public PublicPlugTO getPlug() {
	return plug;
    }

    public void setPlug(PublicPlugTO plug) {
	this.plug = plug;
    }
}
