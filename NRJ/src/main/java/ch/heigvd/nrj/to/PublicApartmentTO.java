package ch.heigvd.nrj.to;

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
public class PublicApartmentTO {

    private long apartmentId;
    private String name;

    public PublicApartmentTO() {
    }

    public PublicApartmentTO(long apartmentId, String name) {
        this.apartmentId = apartmentId;
        this.name = name;
    }

    public long getApartmentId() {
        return apartmentId;
    }

    public void setApartmentId(long apartmentId) {
        this.apartmentId = apartmentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
