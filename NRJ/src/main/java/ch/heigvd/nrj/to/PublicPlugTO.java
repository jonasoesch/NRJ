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
public class PublicPlugTO {

    private long plugId;
    private String name;
    private boolean alwaysOn;

    public PublicPlugTO() {
    }

    public PublicPlugTO(long plugId, String name, boolean alwaysOn) {
        this.plugId = plugId;
        this.name = name;
        this.alwaysOn = alwaysOn;
    }

    public long getPlugId() {
        return plugId;
    }

    public void setPlugId(long plugId) {
        this.plugId = plugId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getAlwaysOn() {
        return alwaysOn;
    }

    public void setAlwaysOn(boolean alwaysOn) {
        this.alwaysOn = alwaysOn;
    }
}
