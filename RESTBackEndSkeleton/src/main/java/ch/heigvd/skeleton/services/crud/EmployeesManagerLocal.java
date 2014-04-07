package ch.heigvd.skeleton.services.crud;

import ch.heigvd.skeleton.exceptions.EntityNotFoundException;
import ch.heigvd.skeleton.model.Employee;
import java.util.List;
import javax.ejb.Local;

/**
 * The contract fulfilled by the EmployeesManager DAO. For now, we only
 * have a findById and a findAll methods, but we could have more finder
 * methods. We would use JPQL to implement these methods.
 * 
 * @author Olivier Liechti
 */
@Local
public interface EmployeesManagerLocal {

	long create(Employee employeeData);

	void update(Employee newState) throws EntityNotFoundException;

	void delete(long id) throws EntityNotFoundException;

	Employee findById(long id) throws EntityNotFoundException;

	List<Employee> findAll();

	
}
