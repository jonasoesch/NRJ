package ch.heigvd.nrj.services.business;

import ch.heigvd.nrj.model.ConsumptionObs;
import ch.heigvd.nrj.model.History;
import ch.heigvd.nrj.model.Plug;
import ch.heigvd.nrj.model.PlugConsumptionFact;
import ch.heigvd.nrj.model.Room;
import ch.heigvd.nrj.model.Warning;
import ch.heigvd.nrj.services.crud.ConsumptionsObsManagerLocal;
import ch.heigvd.nrj.services.crud.HistoriesManagerLocal;
import ch.heigvd.nrj.services.crud.PlugConsumptionsFactsManagerLocal;
import ch.heigvd.nrj.services.crud.WarningsManagerLocal;
import java.util.Date;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author jonas
 */
@Stateless
@Asynchronous
public class StreamProcessor implements StreamProcessorLocal {
    
    @EJB ConsumptionsObsManagerLocal consumptionObsManager;
    @EJB WarningsManagerLocal warningsManager;
    @EJB HistoriesManagerLocal historiesManager;
    @EJB PlugConsumptionsFactsManagerLocal plugConsumptionsFactsManager;
    @EJB ConsumptionsObsManagerLocal consumptionsObsManager;

    @Override
    public void onConsumption(ConsumptionObs o) {
        
        // Recording observation
        consumptionObsManager.create(o);
        
        // Retrieve info from plug
        Plug plug = o.getPlug();
        Room room = plug.getRoom();
        
        History history = new History();
        history.setTimestampMinute(o.getTimestampMinute());
        plug.addHistory(history);
        
        // KW positif, l'appareil est utilisé
        if(o.getkW() > 0){
            history.setStatus(true);
        } 
        else { // pas de consommation sur l'appareil
            
            if( plug.getAlwaysOn() ){ // si l'appareil doit rester allumé, on envoie une alerte
                Warning warning = new Warning();
                warning.setTimestampMinute(o.getTimestampMinute());
                plug.addWarnings(warning);
                warning.setMessage("The plug is not ON but should be.");
                warning.setId(warningsManager.create(warning));
                
            } // if
        } // else
        
        historiesManager.create(history);
        
        // Check last fact for this plug
        PlugConsumptionFact plugConsumptionFact = plugConsumptionsFactsManager.getlastFact(plug);
        
        // We found a fact
        if (plugConsumptionFact != null){
            Date lastFactHour = plugConsumptionFact.getTimestampHour();
            
            
        }
        
        // Check number of obs for this plug
        
        // Consommation moyenne de cette pièce
        // Récupération du dernier fait et ajout de la consommation courante à cette table
    }
    
    /*@Override
    public void onObservation(ObservationType2 o) {
        
        // Recording observation
        observationsType2Manager.create(o);
        
        // Then implement your logic to create facts
        
    }*/
    
}
