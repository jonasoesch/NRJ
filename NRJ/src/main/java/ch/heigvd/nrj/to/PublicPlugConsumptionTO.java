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
public class PublicPlugConsumptionTO {
    private Long plugConsumptionId;
    private Date timeStamp;
    private Double avgKW;
    
    public PublicPlugConsumptionTO() {
    }

    public PublicPlugConsumptionTO(long plugConsumptionId, Date timeStamp, Double avgKW) {
	    this.plugConsumptionId = plugConsumptionId;
	    this.timeStamp = timeStamp;
	    this.avgKW = avgKW;
    }
    
    public Date getTimeStamp() {
	return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
	this.timeStamp = timeStamp;
    }

    public Double getAvgKW() {
	return avgKW;
    }

    public void setAvgKW(Double avgKW) {
	this.avgKW = avgKW;
    }

    public Long getPlugConsumptionId() {
	return this.plugConsumptionId;
    }

    public void setPlugConsumptionId(Long plugConsumptionId) {
	this.plugConsumptionId = plugConsumptionId;
    }

}
