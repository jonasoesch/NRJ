package ch.heigvd.nrj.services.crud;

import ch.heigvd.nrj.exceptions.EntityNotFoundException;
import ch.heigvd.nrj.model.Plug;
import java.util.List;
import javax.ejb.Local;

/**
 * The contract fulfilled by the PlugsManager DAO.
 * 
 * @author Olivier Liechti
 */
@Local
public interface PlugsManagerLocal {

	long create(Plug plugData);

	void update(Plug newState) throws EntityNotFoundException;

	void delete(long id) throws EntityNotFoundException;

	Plug findById(long id) throws EntityNotFoundException;

	List<Plug> findAll();

	
}
