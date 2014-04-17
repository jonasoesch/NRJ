package ch.heigvd.nrj.services.to;

import ch.heigvd.nrj.model.History;
import ch.heigvd.nrj.model.Plug;
import ch.heigvd.nrj.model.PlugConsumptionFact;
import ch.heigvd.nrj.model.Room;
import ch.heigvd.nrj.model.Warning;
import ch.heigvd.nrj.to.PublicHistoryTO;
import ch.heigvd.nrj.to.PublicPlugConsumptionFactsTO;
import ch.heigvd.nrj.to.PublicPlugTO;
import ch.heigvd.nrj.to.PublicWarningTO;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 * This class converts JPA entities into POJO transfer objects, and vice versa
 *
 * @author rschmutz
 */
@Stateless
public class PlugsTOService implements PlugsTOServiceLocal {

    @EJB
    RoomsTOServiceLocal roomsTOService;
    @EJB
    PlugConsumptionsFactsTOServiceLocal plugConsumptionFactsService;
    @EJB
    WarningsTOServiceLocal warningsService;
    @EJB
    HistoriesTOServiceLocal historiesService;

    @Override
    public PublicPlugTO buildPublicPlugTO(Plug source) {
	PublicPlugTO to = new PublicPlugTO(source.getId(), source.getName(), source.getAlwaysOn());
	// PlugConsumptions
	for (PlugConsumptionFact pcf : source.getPlugConsumptionsFacts()) {
	    PublicPlugConsumptionFactsTO ppcf = plugConsumptionFactsService.buildPublicPlugConsumptionFactTO(pcf);
	    to.addPlugConsumption(ppcf);
	}

	// Warnings
	for (Warning w : source.getWarnings()) {
	    PublicWarningTO pw = warningsService.buildPublicWarningTO(w);
	    to.addWarning(pw);
	}

	// Histories
	for (History h : source.getHistories()) {
	    PublicHistoryTO ph = historiesService.buildPublicHistoryTO(h);
	    to.addHistory(ph);
	}

	return to;
    }

    @Override
    public void updatePlugEntity(Plug existingEntity, PublicPlugTO newState) {

	existingEntity.setName(newState.getName());
	existingEntity.setAlwaysOn(newState.getAlwaysOn());

	if (newState.getRoom() != null) {
	    Room r = new Room();
	    roomsTOService.updateRoomEntity(r, newState.getRoom());
	    existingEntity.setRoom(r);
	}

	for (PublicHistoryTO h : newState.getHistories()) {
	    History newHistory = new History();
	    historiesService.updateHistoryEntity(newHistory, h);
	    existingEntity.addHistory(newHistory);
	}
	for (PublicWarningTO w : newState.getWarnings()) {
	    Warning newWarning = new Warning();
	    warningsService.updateWarningEntity(newWarning, w);
	    existingEntity.addWarning(newWarning);
	}
	for (PublicPlugConsumptionFactsTO pcf : newState.getPlugConsumptions()) {
	    PlugConsumptionFact newPCF = new PlugConsumptionFact();
	    plugConsumptionFactsService.updatePlugConsumptionFactEntity(newPCF, pcf);
	    existingEntity.addPlugConsumptionFact(newPCF);
	}
    }
}
