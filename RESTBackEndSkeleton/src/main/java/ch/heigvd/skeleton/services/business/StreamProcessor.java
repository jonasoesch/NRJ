/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.heigvd.skeleton.services.business;

import ch.heigvd.skeleton.model.Observation;
import ch.heigvd.skeleton.services.crud.ObservationsManagerLocal;
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
        
        // Then implement your logic to create facts
        
    }
    
    /*@Override
    public void onObservation(ObservationType2 o) {
        
        // Recording observation
        observationsType2Manager.create(o);
        
        // Then implement your logic to create facts
        
    }*/
    
}
