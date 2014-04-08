package ch.heigvd.nrj.to;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * This is the transferable object for a plug.
 *
 * @XmlRootElement annotation at the class level.
 *
 * @author Option40
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
