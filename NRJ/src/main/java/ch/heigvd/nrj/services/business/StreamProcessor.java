package ch.heigvd.nrj.services.business;

import ch.heigvd.nrj.model.ConsumptionObs;
import ch.heigvd.nrj.services.crud.ConsumptionsObsManagerLocal;
import ch.heigvd.nrj.services.crud.RoomConsumptionsFactsManager;
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
    @EJB RoomConsumptionsFactsManager rcfm;

    @Override
    public void onConsumption(ConsumptionObs o) {
        
        
        // Recording observation
        consumptionObsManager.create(o);
        
        // Création des facts
        // Consommation moyenne de cette plug
        // Récupération des 60 dernières observations et calcul de la moyenne de consommation
        // Puis ajout à la BD
        
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
