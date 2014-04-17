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

    /**
     * Build an empty PublicApartmentConsumptionFactsTO.
     */
    public PublicApartmentConsumptionFactsTO() {
    }

    /**
     * Build a PublicApartmentConsumptionFactsTO with data.
     *
     * @param apartmentConsumptionFactId
     * @param timestampHour
     * @param avgKW
     */
    public PublicApartmentConsumptionFactsTO(long apartmentConsumptionFactId, Date timestampHour, Double avgKW) {
	this.apartmentConsumptionFactId = apartmentConsumptionFactId;
	this.timestampHour = timestampHour;
	this.avgKW = avgKW;
    }

    /**
     * Get the apartmentConsumptionFactId
     *
     * @return the id
     */
    public Long getApartmentConsumptionFactId() {
	return this.apartmentConsumptionFactId;
    }

    /**
     * Set the apartmentConsumptionFactId
     *
     * @param apartmentConsumptionFactId
     */
    public void setApartmentConsumptionFactId(Long apartmentConsumptionFactId) {
	this.apartmentConsumptionFactId = apartmentConsumptionFactId;
    }

    /**
     * Get the date of the apartmentConsumptionFact
     *
     * @return the date
     */
    public Date getTimestampHour() {
	return timestampHour;
    }

    /**
     * Set the date of the apartmentConsumptionFact
     *
     * @param timestampHour
     */
    public void setTimestampHour(Date timestampHour) {
	this.timestampHour = timestampHour;
    }

    /**
     * Get the average kW of the apartmentConsumptionFact
     *
     * @return te average kW
     */
    public Double getAvgKW() {
	return avgKW;
    }

    /**
     * Set the average kW of the apartmentConsumptionFact
     *
     * @param avgKW
     */
    public void setAvgKW(Double avgKW) {
	this.avgKW = avgKW;
    }

    /**
     * Get the apartment of the apartmentConsumptionFact
     *
     * @return the apartment
     */
    public PublicApartmentTO getApartment() {
	return apartment;
    }

    /**
     * Set the apartment of the apartmentConsumptionFact
     *
     * @param apartment
     */
    public void setApartment(PublicApartmentTO apartment) {
	this.apartment = apartment;
    }
}
