package ch.heigvd.nrj.services.business;

import ch.heigvd.nrj.exceptions.EntityNotFoundException;
import ch.heigvd.nrj.model.ConsumptionObs;
import ch.heigvd.nrj.model.History;
import ch.heigvd.nrj.model.Plug;
import ch.heigvd.nrj.model.PlugConsumptionFact;
import ch.heigvd.nrj.model.Room;
import ch.heigvd.nrj.model.RoomConsumptionFact;
import ch.heigvd.nrj.model.Warning;
import ch.heigvd.nrj.services.crud.ConsumptionsObsManagerLocal;
import ch.heigvd.nrj.services.crud.HistoriesManagerLocal;
import ch.heigvd.nrj.services.crud.PlugConsumptionsFactsManagerLocal;
import ch.heigvd.nrj.services.crud.WarningsManagerLocal;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        if( o.getkW() > 0 ){
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
        PlugConsumptionFact lastPlugConsumptionFact = plugConsumptionsFactsManager.getlastPlugFact(plug);
        
        // We found a fact
        if (lastPlugConsumptionFact != null){
            
            // Récupération de l'heure de la derière Fact pour ce Plug
            Date lastFactHour = lastPlugConsumptionFact.getTimestampHour();
            
            // Si l'heure de l'obs est inférieure à l'heure du dernier Fact, on update le Fact en ajoutant les kw de cette Obs
            if ( o.getTimestampMinute().compareTo(lastFactHour) < 0){
                
                // Update du lastFact
                PlugConsumptionFact newPlugConsumptionFact = new PlugConsumptionFact();
                
                double avgKw = lastPlugConsumptionFact.getAvgKW() + o.getkW();
                
                newPlugConsumptionFact.setAvgKW(avgKw);
                
                // Try to update lastFact with new KW
                try {
                    plugConsumptionsFactsManager.update(newPlugConsumptionFact);
                } catch (EntityNotFoundException ex) {
                    Logger.getLogger(StreamProcessor.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            } else { // La date de l'obs est supérieure à la Last Fact, on crée un nouveau Fact.
                createNewPlugConsumptionFact(o);
            }
            
            updateRoomConsumptionFact(o);
            
        } else { // no consumptionFact found, let's crete a new one
            createNewPlugConsumptionFact(o);
        }
        
    } // onConsumption
    
    public void updateRoomConsumptionFact(ConsumptionObs o){
        Room room = o.getPlug().getRoom();
        
        RoomConsumptionFact newRoomConsumptionFact = new RoomConsumptionFact();
        
        newRoomConsumptionFact.setAvgKW(Double.NaN);
        
    }
    
    public void createNewRoomConsumptionFact(ConsumptionObs o){
        Plug plug = o.getPlug();
        Room room = plug.getRoom();
        
        RoomConsumptionFact newRoomConsumptionFact = new RoomConsumptionFact();
        room.addRoomConsumptionFact(newRoomConsumptionFact);
        
        Date endDate = new Date(o.getTimestampMinute().getTime() + 3600000);
        newRoomConsumptionFact.setTimestampHour(endDate);
        
        newRoomConsumptionFact.setAvgKW(o.getkW());
    }
    
    /**
     * Create a new ConsumptionFact
     */
    public void createNewPlugConsumptionFact( ConsumptionObs o ){
        PlugConsumptionFact plugConsumptionFactData = new PlugConsumptionFact();
        
        Plug plug = o.getPlug();
        
        plug.addPlugConsumptionFact(plugConsumptionFactData);
        plugConsumptionFactData.setAvgKW(o.getkW());

        // Ajoute une heure à la première plugConsumption détectée
        Date endDate = new Date( o.getTimestampMinute().getTime() + 3600000 );
        plugConsumptionFactData.setTimestampHour(endDate);

        // Création du Fact
        long createdId = plugConsumptionsFactsManager.create(plugConsumptionFactData);
    
    } // createConsumptionFact
    
    
} // class
