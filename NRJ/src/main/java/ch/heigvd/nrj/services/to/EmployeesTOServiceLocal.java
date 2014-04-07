package ch.heigvd.nrj.services.to;

import ch.heigvd.nrj.model.Employee;
import ch.heigvd.nrj.to.PublicEmployeeTO;
import javax.ejb.Local;

/**
 * This interface defines the contract fulfilled by the EmployeesTOService,
 * which is used to convert employee entities (JPA) into employee transfer
 * objects (POJO), and vice versa.
 * 
 * @author Olivier Liechti
 */
@Local
public interface EmployeesTOServiceLocal {
	
	/**
	 * This method builds a TO instance from a JPA entity instance. In this particular
	 * case, the only thing that we do is get rid of the salary property (which is
	 * available in the JPA entity but should not be communicated to clients).
	 * 
	 * @param source the JPA entity
	 * @return the TO
	 */
	public PublicEmployeeTO buildPublicEmployeeTO(Employee source);
	
	/**
	 * This method updates an existing JPA entity by merging the state of the
	 * provided TO instance. We do not touch the salary property, but replace
	 * the other properties.
	 * @param existingEntity the existing entity that we want to update
	 * @param newState a TO that contains new state (subset of the entity state)
	 */
	public void updateEmployeeEntity(Employee existingEntity, PublicEmployeeTO newState);
	
}
