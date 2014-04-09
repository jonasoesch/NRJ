package ch.heigvd.nrj.services.to;

import ch.heigvd.nrj.model.ConsumptionObs;
import ch.heigvd.nrj.to.PublicConsumptionObsTO;
import javax.ejb.Local;

/**
 * This interface defines the contract fulfilled by the ConsumptionsTOService,
 * 
 * @author rschmutz
 */
@Local
public interface ConsumptionsObsTOServiceLocal {
	
	/**
	 * This method builds a TO instance from a JPA entity instance. 
	 * 
	 * @param source the JPA entity
	 * @return the TO
	 */
	public PublicConsumptionObsTO buildPublicConsumptionTO(ConsumptionObs source);
	
	/**
	 * This method updates an existing JPA entity by merging the state of the
	 * provided TO instance. 
	 * 
	 * @param existingEntity the existing entity that we want to update
	 * @param newState a TO that contains new state (subset of the entity state)
	 */
	public void updateConsumptionEntity(ConsumptionObs existingEntity, PublicConsumptionObsTO newState);
}
