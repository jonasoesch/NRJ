package ch.heigvd.nrj.services.to;

import ch.heigvd.nrj.model.RoomConsumptionFact;
import ch.heigvd.nrj.to.PublicRoomConsumptionFactsTO;
import javax.ejb.Local;

/**
 * This interface defines the contract fulfilled by the
 * RoomConsumptionsTOService,
 *
 * @author rschmutz
 */
@Local
public interface RoomConsumptionsFactsTOServiceLocal {

    /**
     * This method builds a TO instance from a JPA entity instance.
     *
     * @param source the JPA entity
     * @return the TO
     */
    public PublicRoomConsumptionFactsTO buildPublicRoomConsumptionFactTO(RoomConsumptionFact source);

    /**
     * This method updates an existing JPA entity by merging the state of the
     * provided TO instance.
     *
     * @param existingEntity the existing entity that we want to update
     * @param newState a TO that contains new state (subset of the entity state)
     */
    public void updateRoomConsumptionFactEntity(RoomConsumptionFact existingEntity, PublicRoomConsumptionFactsTO newState);
}
