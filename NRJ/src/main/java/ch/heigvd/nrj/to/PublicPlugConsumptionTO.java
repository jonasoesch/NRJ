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
    private Date timestampHour;
    private Double avgKW;

    public PublicPlugConsumptionTO() {
    }

    public PublicPlugConsumptionTO(long plugConsumptionId, Date timestampHour, Double avgKW) {
	this.plugConsumptionId = plugConsumptionId;
	this.timestampHour = timestampHour;
	this.avgKW = avgKW;
    }

    public Long getPlugConsumptionId() {
	return this.plugConsumptionId;
    }

    public void setPlugConsumptionId(Long plugConsumptionId) {
	this.plugConsumptionId = plugConsumptionId;
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
