package ch.heigvd.nrj.services.business;

import ch.heigvd.nrj.exceptions.EntityNotFoundException;
import ch.heigvd.nrj.model.Apartment;
import ch.heigvd.nrj.model.ApartmentConsumptionFact;
import ch.heigvd.nrj.model.ConsumptionObs;
import ch.heigvd.nrj.model.History;
import ch.heigvd.nrj.model.Plug;
import ch.heigvd.nrj.model.PlugConsumptionFact;
import ch.heigvd.nrj.model.Room;
import ch.heigvd.nrj.model.RoomConsumptionFact;
import ch.heigvd.nrj.model.Warning;
import ch.heigvd.nrj.services.crud.ApartmentConsumptionsFactsManagerLocal;
import ch.heigvd.nrj.services.crud.ConsumptionsObsManagerLocal;
import ch.heigvd.nrj.services.crud.HistoriesManagerLocal;
import ch.heigvd.nrj.services.crud.PlugConsumptionsFactsManagerLocal;
import ch.heigvd.nrj.services.crud.RoomConsumptionsFactsManagerLocal;
import ch.heigvd.nrj.services.crud.WarningsManagerLocal;
import java.net.URI;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.Response;

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
    @EJB RoomConsumptionsFactsManagerLocal roomConsumptionsFactsManager;
    @EJB ApartmentConsumptionsFactsManagerLocal apartmentConsumptionsFactsManager;
    @EJB ConsumptionsObsManagerLocal consumptionsObsManager;
    @PersistenceContext private EntityManager em;

    
    @Override
    public void onConsumption(ConsumptionObs o) {
        
        // Recording observation
        //  consumptionObsManager.create(o);
        
        
        // Retrieve info from plug
        Plug plug = o.getPlug();
        Room room = plug.getRoom();
        
        // create History for this Plug
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
        
        
        /*                  PLUG consumption                     */
        // Check last fact for this plug
        PlugConsumptionFact lastPlugConsumptionFact = plugConsumptionsFactsManager.getLastPlugFact(plug);
        
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
            
        } else { // no consumptionFact found, let's crete a new one
            createNewPlugConsumptionFact(o);
        } // if lastPlugFact
        
        /*                  ROOM consumption                     */
        // Check last fact for this room
        RoomConsumptionFact lastRoomConsumptionFact = roomConsumptionsFactsManager.getLastRoomFact(room);
        
        // We found a fact for this room
        if (lastRoomConsumptionFact != null){
            
            // Récupération de l'heure de la derière Fact pour ce Plug
            Date lastFactHour = lastRoomConsumptionFact.getTimestampHour();
            
            // Si l'heure de l'obs est inférieure à l'heure du dernier Fact, on update le Fact en ajoutant les kw de cette Obs
            if ( o.getTimestampMinute().compareTo(lastFactHour) < 0){
                
                updateRoomConsumptionFact(o);
                
            } else { // La date de l'obs est supérieure à la Last Fact, on crée un nouveau Fact.
                createNewRoomConsumptionFact(o);
            }
            
        } else { // no roomConsumptionFact found, let's crete a new one
            createNewRoomConsumptionFact(o);
        } // if lastRoomFact
        
        /*                  APARTMENT consumption                     */
        // Check last fact for this apartment
        Apartment apartment = room.getApartment();
        ApartmentConsumptionFact lastApartmentConsumptionFact = apartmentConsumptionsFactsManager.getLastApartmentFact(apartment);
        
        // We found a fact for this room
        if (lastApartmentConsumptionFact != null){
            
            // Récupération de l'heure de la derière Fact pour ce Plug
            Date lastFactHour = lastApartmentConsumptionFact.getTimestampHour();
            
            // Si l'heure de l'obs est inférieure à l'heure du dernier Fact, on update le Fact en ajoutant les kw de cette Obs
            if ( o.getTimestampMinute().compareTo(lastFactHour) < 0){
                
                updateApartmentConsumptionFact(o);
                
            } else { // La date de l'obs est supérieure à la Last Fact, on crée un nouveau Fact.
                createNewApartmentConsumptionFact(o);
            }
            
        } else { // no roomConsumptionFact found, let's crete a new one
            createNewApartmentConsumptionFact(o);
        } // if lastRoomFact
        
    } // onConsumption
    
    
    /**
     * Update the last ApartmentConsumptionFact to add the new observationKW
     * @param o 
     */
    public void updateApartmentConsumptionFact(ConsumptionObs o){
        Apartment apartment = o.getPlug().getRoom().getApartment();
        
        ApartmentConsumptionFact lastApartmentConsumptionFact = apartmentConsumptionsFactsManager.getLastApartmentFact(apartment);
        
        ApartmentConsumptionFact newApartmentConsumptionFact = lastApartmentConsumptionFact;
        
        newApartmentConsumptionFact.setAvgKW(lastApartmentConsumptionFact.getAvgKW() + o.getkW());
        
        // Try to update lastApartmentConsumptionFact
        try {
            apartmentConsumptionsFactsManager.update(newApartmentConsumptionFact);
        } catch (EntityNotFoundException ex) {
            Logger.getLogger(StreamProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    /**
     * Update the last RoomConsumptionFact to add the new observationKW
     * @param o 
     */
    public void updateRoomConsumptionFact(ConsumptionObs o){
        Room room = o.getPlug().getRoom();
        
        RoomConsumptionFact lastRoomConsumptionFact = roomConsumptionsFactsManager.getLastRoomFact(room);
        
        RoomConsumptionFact newRoomConsumptionFact = lastRoomConsumptionFact;
        
        newRoomConsumptionFact.setAvgKW(lastRoomConsumptionFact.getAvgKW() + o.getkW());
        
        // Try to update lastRoomConsumptionFact
        try {
            roomConsumptionsFactsManager.update(newRoomConsumptionFact);
        } catch (EntityNotFoundException ex) {
            Logger.getLogger(StreamProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     * Create a new ApartmentConsumptionFact
     * @param o a ConsumptionObesrvation
     */
    public void createNewApartmentConsumptionFact(ConsumptionObs o){
        Plug plug = o.getPlug();
        Room room = plug.getRoom();
        Apartment apartment = room.getApartment();
        
        ApartmentConsumptionFact newApartmentConsumptionFact = new ApartmentConsumptionFact();
        apartment.addApartmentConsumptionFact(newApartmentConsumptionFact);
        
        Date endDate = new Date(o.getTimestampMinute().getTime() + 3600000);
        newApartmentConsumptionFact.setTimestampHour(endDate);
        
        newApartmentConsumptionFact.setAvgKW(o.getkW());
        
        long createdId = apartmentConsumptionsFactsManager.create(newApartmentConsumptionFact);
        
        newApartmentConsumptionFact.setId(createdId);
    }
    
    /**
     * Create a new RoomConsumptionFact
     * @param o 
     */
    public void createNewRoomConsumptionFact(ConsumptionObs o){
        Plug plug = o.getPlug();
        Room room = plug.getRoom();
        
        // Séquence ?! add empty roomConsoFact ?
        RoomConsumptionFact newRoomConsumptionFact = new RoomConsumptionFact();
        room.addRoomConsumptionFact(newRoomConsumptionFact);
        
        Date endDate = new Date(o.getTimestampMinute().getTime() + 3600000);
        newRoomConsumptionFact.setTimestampHour(endDate);
        
        newRoomConsumptionFact.setAvgKW(o.getkW());
        
        roomConsumptionsFactsManager.create(newRoomConsumptionFact);
    }
    
    /**
     * Create a new PlugConsumptionFact
     */
    public void createNewPlugConsumptionFact( ConsumptionObs o ){
        PlugConsumptionFact plugConsumptionFactData = new PlugConsumptionFact();
        
        Plug plug = o.getPlug();
        
        plugConsumptionFactData.setAvgKW(o.getkW());
        plug.addPlugConsumptionFact(plugConsumptionFactData);

        // Ajoute une heure à la première plugConsumption détectée
        Date endDate = new Date( o.getTimestampMinute().getTime() + 3600000 );
        plugConsumptionFactData.setTimestampHour(endDate);

        // Création du Fact
        long createdId = plugConsumptionsFactsManager.create(plugConsumptionFactData);
        plugConsumptionFactData.setId(createdId);
    
    } // createConsumptionFact
    
    
} // class
