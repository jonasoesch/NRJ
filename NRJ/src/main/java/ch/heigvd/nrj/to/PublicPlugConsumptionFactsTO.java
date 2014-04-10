/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.nrj.to;

import ch.heigvd.nrj.model.Plug;
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

    private Long plugConsumptionFactId;
    private Date timestampHour;
    private Double avgKW;
    private Plug plug;
    
    public PublicPlugConsumptionFactsTO() {
    }

    public PublicPlugConsumptionFactsTO(long plugConsumptionFactId, Date timestampHour, Double avgKW, Plug plug) {
	this.plugConsumptionFactId = plugConsumptionFactId;
	this.timestampHour = timestampHour;
	this.avgKW = avgKW;
	this.plug = plug;
    }

    public Long getPlugConsumptionFactId() {
	return this.plugConsumptionFactId;
    }

    public void setPlugConsumptionFactId(Long plugConsumptionFactId) {
	this.plugConsumptionFactId = plugConsumptionFactId;
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
    
    public Plug getPlug() {
	return plug;
    }

    public void setPlug(Plug plug) {
	this.plug = plug;
    }
}
