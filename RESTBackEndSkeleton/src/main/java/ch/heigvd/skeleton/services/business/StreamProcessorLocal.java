/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ch.heigvd.skeleton.services.business;

import ch.heigvd.skeleton.model.Observation;
import javax.ejb.Local;

/**
 *
 * @author Yannick
 */
@Local
public interface StreamProcessorLocal {
    
    void onObservation(Observation o);
    
    //void onObservation(ObservationType2 o);
    
}
