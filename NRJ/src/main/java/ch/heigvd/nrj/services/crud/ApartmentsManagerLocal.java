package ch.heigvd.nrj.services.crud;

import ch.heigvd.nrj.exceptions.EntityNotFoundException;
import ch.heigvd.nrj.model.Apartment;
import java.util.List;
import javax.ejb.Local;

/**
 * The contract fulfilled by the ApartmentManager DAO. 
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
