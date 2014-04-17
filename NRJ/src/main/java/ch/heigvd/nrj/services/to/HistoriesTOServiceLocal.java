package ch.heigvd.nrj.services.to;

import ch.heigvd.nrj.model.History;
import ch.heigvd.nrj.to.PublicHistoryTO;
import javax.ejb.Local;

/**
 * This interface defines the contract fulfilled by the HistoriesTOService,
 *
 * @author rschmutz
 */
@Local
public interface HistoriesTOServiceLocal {

    /**
     * This method builds a TO instance from a JPA entity instance.
     *
     * @param source the JPA entity
     * @return the TO
     */
    public PublicHistoryTO buildPublicHistoryTO(History source);

    /**
     * This method updates an existing JPA entity by merging the state of the
     * provided TO instance.
     *
     * @param existingEntity the existing entity that we want to update
     * @param newState a TO that contains new state (subset of the entity state)
     */
    public void updateHistoryEntity(History existingEntity, PublicHistoryTO newState);
}
