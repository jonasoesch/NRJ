package ch.heigvd.skeleton.to;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * This is an example for a Transfer Object, which will be used to send data to the client.
 * Instances of this class will be created from JPA entities, but will not expose all attributes.
 * In this example, we do not want to send salary information to everyone, so we have stripped
 * this information. In some cases, a transfer object may also be used to aggregate properties from
 * several JPA entities.
 * 
 * The class must have an empty constructor, as well as getters and setters for properties (this
 * is required for the JAXB marshalling to work properly). That is also why we have the @XmlRootElement
 * annotation at the class level.
 * 
 * @author Olivier Liechti
 */
@XmlRootElement
public class PublicObservationTO {
	
	private  long id;
	
	private  int value;
        
	private  long timestamp;
	
	public PublicObservationTO() {
	}

	public PublicObservationTO(long id, int value, long timestamp) {
		this.id = id;
		this.value = value;                
		this.timestamp = timestamp;
	}

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
        
        
}
