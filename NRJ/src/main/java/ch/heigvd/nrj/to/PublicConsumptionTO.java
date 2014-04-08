package ch.heigvd.nrj.to;

import java.util.Date;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This is an example for a Transfer Object, which will be used to send data to
 * the client. Instances of this class will be created from JPA entities, but
 * will not expose all attributes. In this example, we do not want to send
 * salary information to everyone, so we have stripped this information. In some
 * cases, a transfer object may also be used to aggregate properties from
 * several JPA entities.
 *
 * The class must have an empty constructor, as well as getters and setters for
 * properties (this is required for the JAXB marshalling to work properly). That
 * is also why we have the
 *
 * @XmlRootElement annotation at the class level.
 *
 * @author Olivier Liechti
 */
@XmlRootElement
public class PublicConsumptionTO {

    private long consumptionId;
    private Date timestampMinute;
    private Double kW;

    public PublicConsumptionTO() {
    }

    public PublicConsumptionTO(long consumptionId, Date timestampMinute, Double kW) {
        this.consumptionId = consumptionId;
        this.timestampMinute = timestampMinute;
        this.kW = kW;
    }

    public long getConsumptionId() {
        return consumptionId;
    }

    public void setConsumptionId(long consumptionId) {
        this.consumptionId = consumptionId;
    }

    public Date getTimeStamp() {
        return timestampMinute;
    }

    public void setTimeStamp(Date timestampMinute) {
        this.timestampMinute = timestampMinute;
    }
    
    public Double getKW() {
        return kW;
    }

    public void setKW(Double kW) {
        this.kW = kW;
    }
}
