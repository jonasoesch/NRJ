package ch.heigvd.nrj.to;

import ch.heigvd.nrj.model.Apartment;
import ch.heigvd.nrj.model.Plug;
import ch.heigvd.nrj.model.Room;
import ch.heigvd.nrj.model.RoomConsumptionFact;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This is the transferable object for a Room.
 *
 * @XmlRootElement annotation at the class level.
 *
 * @author Option40
 */
@XmlRootElement
public class PublicRoomTOSortie {

    private long roomId;
    private String name;
    private List<RoomConsumptionFact> roomConsumptionsFacts;
    private List<PublicPlugTO> plugs;
    
    public PublicRoomTOSortie() {
	this.plugs = new ArrayList<>();
    }

    public PublicRoomTOSortie(long roomId, String name, List<RoomConsumptionFact> roomConsumptionsFacts) {
	this.plugs = new ArrayList<>();
        this.roomId = roomId;
        this.name = name;
	this.roomConsumptionsFacts = roomConsumptionsFacts;
    }

    public long getRoomId() {
        return roomId;
    }

    public void setRoomId(long roomId) {
        this.roomId = roomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public List<RoomConsumptionFact> getRoomConsumptionsFacts() {
	return roomConsumptionsFacts;
    }

    public void setRoomConsumptionsFacts(List<RoomConsumptionFact> roomConsumptionsFacts) {
	this.roomConsumptionsFacts = roomConsumptionsFacts;
    }

    public List<PublicPlugTO> getPlugs() {
	return plugs;
    }
    
    public void setPlugs(List<PublicPlugTO> plugs) {
	this.plugs = plugs;
    }
    
    public void addPlug(PublicPlugTO plug) {
	this.plugs.add(plug);
    }
}
