/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.heigvd.skeleton.services.business;

import ch.heigvd.skeleton.model.Observation;
import ch.heigvd.skeleton.services.crud.ObservationsManagerLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Asynchronous;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Yannick
 */
@Stateless
@Asynchronous
public class StreamProcessor implements StreamProcessorLocal {
    
    @EJB ObservationsManagerLocal observationsManager;

    @Override
    public void onObservation(Observation o) {
        
        // Recording observation
        observationsManager.create(o);
        
        try {
            // Then implement your logic to create facts
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            Logger.getLogger(StreamProcessor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /*@Override
    public void onObservation(ObservationType2 o) {
        
        // Recording observation
        observationsType2Manager.create(o);
        
        // Then implement your logic to create facts
        
    }*/
    
}
