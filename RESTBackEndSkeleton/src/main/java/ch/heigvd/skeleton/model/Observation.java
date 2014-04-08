package ch.heigvd.skeleton.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * This class is an example for a simple JPA entity. Notice that there is a
 * 'salary' property, which is sensitive and which we do not want to expose to
 * everyone. That is one reason why we do not want to use this class directly
 * from the REST tier (and instead use specific Transfer Objects).
 *
 * @author Olivier Liechti
 */
@NamedQueries(
    @NamedQuery(
        name = "findAllObservations",
        query = "SELECT e FROM Observation e"
    )
)

@Entity
public class Observation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long timestamp;
    private int value;

    public Observation() {
        value = 0;
    }

    public Observation(Observation observationData) {
        this.value = observationData.value;
        this.timestamp = observationData.timestamp;
    }

    public Long getId() {
            return id;
    }

    public void setId(Long id) {
            this.id = id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public int hashCode() {
            int hash = 0;
            hash += (id != null ? id.hashCode() : 0);
            return hash;
    }

    @Override
    public boolean equals(Object object) {
            // TODO: Warning - this method won't work in the case the id fields are not set
            if (!(object instanceof Observation)) {
                    return false;
            }
            Observation other = (Observation) object;
            if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
                    return false;
            }
            return true;
    }

    @Override
    public String toString() {
            return "ch.heigvd.skeleton.model.Observation[ id=" + id + " ]";
    }

}
