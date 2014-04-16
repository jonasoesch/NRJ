package ch.heigvd.nrj.to;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This is the transferable object for apartment Room.
 *
 * @XmlRootElement annotation at the class level.
 *
 * @author Option40
 */
@XmlRootElement
public class PublicRoomTO {

    private long roomId;
    private String name;
    private PublicApartmentTO apartment;
    private List<PublicRoomConsumptionFactsTO> roomConsumptionsFacts;
    private List<PublicPlugTO> plugs;
    
    public PublicRoomTO() {
	this.plugs = new ArrayList<>();
	this.roomConsumptionsFacts = new ArrayList<>();
    }

    public PublicRoomTO(long roomId, String name) {
        this.roomId = roomId;
        this.name = name;
	this.plugs = new ArrayList<>();
	this.roomConsumptionsFacts = new ArrayList<>();
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
    
    public PublicApartmentTO getApartment() {
	return apartment;
    }

    public void setApartment(PublicApartmentTO apartment) {
	this.apartment = apartment;
    }
    
    public List<PublicRoomConsumptionFactsTO> getRoomConsumptionsFacts() {
	return roomConsumptionsFacts;
    }

    public void setRoomConsumptionsFacts(List<PublicRoomConsumptionFactsTO> roomConsumptionsFacts) {
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
