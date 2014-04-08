package ch.heigvd.skeleton.services.crud;

import ch.heigvd.skeleton.exceptions.EntityNotFoundException;
import ch.heigvd.skeleton.model.Observation;
import java.util.List;
import javax.ejb.Local;

/**
 * 
 * @author Olivier Liechti
 */
@Local
public interface ObservationsManagerLocal {

	long create(Observation observationData);

	void update(Observation newState) throws EntityNotFoundException;

	void delete(long id) throws EntityNotFoundException;

	Observation findById(long id) throws EntityNotFoundException;

	List<Observation> findAll();

	
}
