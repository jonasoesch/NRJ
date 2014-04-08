package ch.heigvd.nrj.services.crud;

import ch.heigvd.nrj.exceptions.EntityNotFoundException;
import ch.heigvd.nrj.model.Warning;
import java.util.List;
import javax.ejb.Local;

/**
 * The contract fulfilled by the WarningsManager DAO.
 * 
 * @author rschmutz
 */
@Local
public interface WarningsManagerLocal {

	long create(Warning warningData);

	void update(Warning newState) throws EntityNotFoundException;

	void delete(long id) throws EntityNotFoundException;

	Warning findById(long id) throws EntityNotFoundException;

	List<Warning> findAll();

	
}
