package ch.heigvd.nrj.to;

import ch.heigvd.nrj.model.Plug;
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
    private Plug plug;

    public PublicHistoryTO() {
    }

    public PublicHistoryTO(long historyId, Date timestampMinute, boolean status, Plug plug) {
	this.historyId = historyId;
	this.timestampMinute = timestampMinute;
	this.status = status;
	this.plug = plug;
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
    
    public Plug getPlug() {
	return plug;
    }

    public void setPlug(Plug plug) {
	this.plug = plug;
    }
}
