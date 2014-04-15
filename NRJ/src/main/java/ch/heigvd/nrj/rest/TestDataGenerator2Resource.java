package ch.heigvd.nrj.rest;

import ch.heigvd.nrj.model.Apartment;
import ch.heigvd.nrj.model.Plug;
import ch.heigvd.nrj.model.PlugConsumptionFact;
import ch.heigvd.nrj.model.Room;
import ch.heigvd.nrj.services.crud.ApartmentsManagerLocal;
import ch.heigvd.nrj.services.crud.EmployeesManagerLocal;
import ch.heigvd.nrj.services.crud.PlugConsumptionsFactsManager;
import ch.heigvd.nrj.services.crud.PlugConsumptionsFactsManagerLocal;
import ch.heigvd.nrj.services.crud.PlugsManagerLocal;
import ch.heigvd.nrj.services.crud.RoomsManagerLocal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 *
 * @author Olivier Liechti
 */
@Stateless
@Path("test2")
public class TestDataGenerator2Resource {

    final static Logger LOG = Logger.getLogger(TestDataGenerator2Resource.class.getName());
    @EJB
    EmployeesManagerLocal employeesManager;
    @EJB
    PlugsManagerLocal plugsManager;
    @EJB
    RoomsManagerLocal roomsManager;
    @EJB
    ApartmentsManagerLocal apartmentsManager;
    @EJB
    PlugConsumptionsFactsManagerLocal plugConsumptionsFactsManager;

    @GET
    @Produces({"text/plain"})
    public String generateEmployees() throws ParseException {
        Apartment a = new Apartment();
        a.setName("Appartement 511");
        a.setId(apartmentsManager.create(a));
	
	Room m1 = new Room();
        m1.setName("Chambre de Barbie");
        m1.setId(roomsManager.create(m1));
	a.addRoom(m1);

        Room m2 = new Room();
        m2.setName("Cuisine");
        m2.setId(roomsManager.create(m2));
	
	a.addRoom(m2);
        

//		Employee e = new Employee();
//		e.setFirstName("Elisa");
//		e.setLastName("Touvomi");
//		e.setEmail("elisa.touvomi@heig-vd.ch");
//		e.setSalary(80000);
//		e.setId(employeesManager.create(e));

        Plug p1 = new Plug();
        p1.setName("frigo");
        p1.setAlwaysOn(true);
        
	p1.setId(plugsManager.create(p1));
        m2.addPlug(p1);

	
        Plug p2 = new Plug();
        p2.setName("television");
        p2.setAlwaysOn(false);
        p2.setId(plugsManager.create(p2));
        m1.addPlug(p2);

	
        Plug p3 = new Plug();
        p3.setName("lumiere");
        p3.setAlwaysOn(true);
        p3.setId(plugsManager.create(p3));
        m1.addPlug(p3);

        return "done";
    }
}
