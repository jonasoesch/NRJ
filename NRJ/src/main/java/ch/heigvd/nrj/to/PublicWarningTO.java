/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

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
    private Date timeStamp;
    private String message;

    public PublicWarningTO() {
    }

    public PublicWarningTO(long warningId, Date timeStamp, String message) {
	    this.warningId = warningId;
	    this.timeStamp = timeStamp;
	    this.message = message;
    }
    
    public Date getTimeStamp() {
	return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
	this.timeStamp = timeStamp;
    }

    public String getMessage() {
	return message;
    }

    public void setMessage(String message) {
	this.message = message;
    }

    public Long getWarningId() {
	return warningId;
    }

    public void setWarningId(Long warningId) {
	this.warningId = warningId;
    }
}
