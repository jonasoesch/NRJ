package ch.heigvd.nrj.services.crud;

import ch.heigvd.nrj.exceptions.EntityNotFoundException;
import ch.heigvd.nrj.model.Apartment;
import ch.heigvd.nrj.model.ApartmentConsumptionFact;
import java.util.List;
import javax.ejb.Local;

/**
 * The contract fulfilled by the ApartmentConsumptionsManager DAO.
 * 
 * @author rschmutz
 */
@Local
public interface ApartmentConsumptionsFactsManagerLocal {

	long create(ApartmentConsumptionFact apartmentConsumptionFactData);

	void update(ApartmentConsumptionFact newState) throws EntityNotFoundException;

	void delete(long id) throws EntityNotFoundException;

	ApartmentConsumptionFact findById(long id) throws EntityNotFoundException;

	List<ApartmentConsumptionFact> findAll();

	ApartmentConsumptionFact getLastApartmentFact(Apartment apartment);
}
