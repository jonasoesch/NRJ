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
public class PublicHistoryTO {

    private long historyId;
    private Date timestampMinute;

    public PublicHistoryTO() {
    }

    public PublicHistoryTO(long historyId, Date timestampMinute) {
        this.historyId = historyId;
        this.timestampMinute = timestampMinute;
    }

    public long getHistoryId() {
        return historyId;
    }

    public void setHistoryId(long historyId) {
        this.historyId = historyId;
    }

    public Date getTimeStamp() {
        return timestampMinute;
    }

    public void setTimeStamp(Date timestampMinute) {
        this.timestampMinute = timestampMinute;
    }
}
