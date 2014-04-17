package ch.heigvd.nrj.services.to;

import ch.heigvd.nrj.model.Warning;
import ch.heigvd.nrj.to.PublicWarningTO;
import javax.ejb.Local;

/**
 * This interface defines the contract fulfilled by the WarningsTOService,
 *
 * @author rschmutz
 */
@Local
public interface WarningsTOServiceLocal {

    /**
     * This method builds a TO instance from a JPA entity instance.
     *
     * @param source the JPA entity
     * @return the TO
     */
    public PublicWarningTO buildPublicWarningTO(Warning source);

    /**
     * This method updates an existing JPA entity by merging the state of the
     * provided TO instance.
     *
     * @param existingEntity the existing entity that we want to update
     * @param newState a TO that contains new state (subset of the entity state)
     */
    public void updateWarningEntity(Warning existingEntity, PublicWarningTO newState);
}
