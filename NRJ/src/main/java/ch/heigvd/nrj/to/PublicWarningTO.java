package ch.heigvd.nrj.to;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rschmutz
 */
@XmlRootElement
public class PublicWarningTO {

    private Long warningId;
    private Date timestampMinute;
    private String message;

    public PublicWarningTO() {
    }

    public Long getWarningId() {
	return warningId;
    }

    public void setWarningId(Long warningId) {
	this.warningId = warningId;
    }

    public PublicWarningTO(long warningId, Date timestampMinute, String message) {
	this.warningId = warningId;
	this.timestampMinute = timestampMinute;
	this.message = message;
    }

    public Date getTimestampMinute() {
	return timestampMinute;
    }

    public void setTimestampMinute(Date timestampMinute) {
	this.timestampMinute = timestampMinute;
    }

    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }
}
