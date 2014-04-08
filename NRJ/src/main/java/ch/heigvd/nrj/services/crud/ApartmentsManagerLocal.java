package ch.heigvd.nrj.services.crud;

import ch.heigvd.nrj.exceptions.EntityNotFoundException;
import ch.heigvd.nrj.model.Apartment;
import java.util.List;
import javax.ejb.Local;

/**
 * The contract fulfilled by the ApartmentManager DAO. For now, we only
 * have a findById and a findAll methods, but we could have more finder
 * methods. We would use JPQL to implement these methods.
 * 
 * @author Olivier Liechti
 */
@Local
public interface ApartmentsManagerLocal {

	long create(Apartment apartmentData);

	void update(Apartment newState) throws EntityNotFoundException;

	void delete(long id) throws EntityNotFoundException;

	Apartment findById(long id) throws EntityNotFoundException;

	List<Apartment> findAll();

	
}
