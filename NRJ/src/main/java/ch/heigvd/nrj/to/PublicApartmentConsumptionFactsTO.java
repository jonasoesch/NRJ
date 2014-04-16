package ch.heigvd.nrj.to;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This is the transferable object for a consumption of a apartment.
 *
 * @XmlRootElement annotation at the class level.
 *
 * @author Option40
 */
@XmlRootElement
public class PublicApartmentConsumptionFactsTO {

    private Long apartmentConsumptionFactId;
    private Date timestampHour;
    private Double avgKW;
    private PublicApartmentTO apartment;

    public PublicApartmentConsumptionFactsTO() {
    }

    public PublicApartmentConsumptionFactsTO(long apartmentConsumptionFactId, Date timestampHour, Double avgKW) {
	this.apartmentConsumptionFactId = apartmentConsumptionFactId;
	this.timestampHour = timestampHour;
	this.avgKW = avgKW;
    }

    public Long getApartmentConsumptionFactId() {
	return this.apartmentConsumptionFactId;
    }

    public void setApartmentConsumptionFactId(Long apartmentConsumptionFactId) {
	this.apartmentConsumptionFactId = apartmentConsumptionFactId;
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
    
    public PublicApartmentTO getApartment() {
	return apartment;
    }

    public void setApartment(PublicApartmentTO apartment) {
	this.apartment = apartment;
    }
}
