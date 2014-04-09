/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.nrj.to;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This is the transferable object for a consumption of a plug.
 *
 * @XmlRootElement annotation at the class level.
 *
 * @author Option40
 */
@XmlRootElement
public class PublicPlugConsumptionFactsTO {

    private Long plugConsumptionObsId;
    private Date timestampHour;
    private Double avgKW;

    public PublicPlugConsumptionFactsTO() {
    }

    public PublicPlugConsumptionFactsTO(long plugConsumptionObsId, Date timestampHour, Double avgKW) {
	this.plugConsumptionObsId = plugConsumptionObsId;
	this.timestampHour = timestampHour;
	this.avgKW = avgKW;
    }

    public Long getPlugConsumptionObsId() {
	return this.plugConsumptionObsId;
    }

    public void setPlugConsumptionObsId(Long plugConsumptionObsId) {
	this.plugConsumptionObsId = plugConsumptionObsId;
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
