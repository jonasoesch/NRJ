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
        PlugConsumptionFact lastPlugConsumptionFact = plugConsumptionsFactsManager.getlastFact(plug);
        
        // We found a fact
        if (lastPlugConsumptionFact != null){
            
            // Récupération de l'heure de la derière Fact pour ce Plug
            Date lastFactHour = lastPlugConsumptionFact.getTimestampHour();
            
            // Si l'heure de l'obs est inférieure à l'heure du dernier Fact, on update le Fact en ajoutant les kw de cette Obs
            if ( o.getTimestampMinute().compareTo(lastFactHour) < 0){
                
                // La date de l'Obs est inférieure à la date de la Fact
                
            } else { // La date de l'obs est supérieure à la Last Fact, on crée un nouveau Fact.
                
            }
            
            //List<PlugConsumptionFact> consumptionList = plugConsumptionsFactsManager.getConsumptionFactsAfterTime(plug, lastFactHour);
            
            
            
        } else { // no consumptionFact found, let's prepare a new one
            
            PlugConsumptionFact plugConsumptionFactData = new PlugConsumptionFact();
            
            plug.addPlugConsumptionFact(plugConsumptionFactData);
            plugConsumptionFactData.setAvgKW(o.getkW());
            
            // Ajoute une heure à la première plugConsumption détectée
            Date endDate = new Date( o.getTimestampMinute().getTime() + 3600000 );
            plugConsumptionFactData.setTimestampHour(endDate);
            
            // Création du Fact
            long createdId = plugConsumptionsFactsManager.create(plugConsumptionFactData);
            
        }
        
        // Check number of obs for this plug
        
        // Consommation moyenne de cette pièce
        
        // Récupération du dernier fait et ajout de la consommation courante à cette table
    }
    
    
    
}
