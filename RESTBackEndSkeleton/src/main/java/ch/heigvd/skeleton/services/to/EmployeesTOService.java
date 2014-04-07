package ch.heigvd.skeleton.services.to;

import ch.heigvd.skeleton.model.Employee;
import ch.heigvd.skeleton.to.PublicEmployeeTO;
import javax.ejb.Stateless;

/**
 * This class converts JPA entities into POJO transfer objects, and vice versa
 * 
 * @author Olivier Liechti
 */
@Stateless
public class EmployeesTOService implements EmployeesTOServiceLocal {

	@Override
	public PublicEmployeeTO buildPublicEmployeeTO(Employee source) {
		PublicEmployeeTO to = new PublicEmployeeTO(source.getId(), source.getFirstName(), source.getLastName(), source.getEmail());
		return to;
	}

	@Override
	public void updateEmployeeEntity(Employee existingEntity, PublicEmployeeTO newState) {
		existingEntity.setFirstName(newState.getFirstName());
		existingEntity.setLastName(newState.getLastName());
		existingEntity.setEmail(newState.getEmail());		
	}
	
}
