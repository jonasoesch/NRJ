/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.nrj.rest;

import ch.heigvd.nrj.model.Employee;
import ch.heigvd.nrj.model.Plug;
import ch.heigvd.nrj.services.crud.PlugsManagerLocal;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Lauric
 */
@Stateless
@Path("testPlug")
public class DataGeneratorPlugs {
    
    final static Logger LOG = Logger.getLogger(TestDataGeneratorResource.class.getName());
    
    
    @EJB
    PlugsManagerLocal plugsManager;
    
    @GET
    @Produces({"text/plain"})
    public String generateFridgeData() {

            Plug p = new Plug();
            p.setName("frigo");
            p.setAlwaysOn(true);
            plugsManager.create(p);

            
            
            return "done";
    }
    
    
}
