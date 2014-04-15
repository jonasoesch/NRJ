package ch.heigvd.nrj.rest;

import ch.heigvd.nrj.model.Apartment;
import ch.heigvd.nrj.model.ConsumptionObs;
import ch.heigvd.nrj.model.Plug;
import ch.heigvd.nrj.model.Room;
import ch.heigvd.nrj.services.crud.ApartmentsManagerLocal;
import ch.heigvd.nrj.services.crud.ConsumptionsObsManagerLocal;
import ch.heigvd.nrj.services.crud.EmployeesManagerLocal;
import ch.heigvd.nrj.services.crud.PlugConsumptionsFactsManagerLocal;
import ch.heigvd.nrj.services.crud.PlugsManagerLocal;
import ch.heigvd.nrj.services.crud.RoomsManagerLocal;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * OK, ce fichier sert à créer et tester des Observations ! Gooo !
 * @author nicolas
 */
@Stateless
@Path("test3")
public class TestDataGenerator3Resource {

    final static Logger LOG = Logger.getLogger(TestDataGenerator3Resource.class.getName());
    @EJB
    EmployeesManagerLocal employeesManager;
    @EJB
    PlugsManagerLocal plugsManager;
    @EJB
    RoomsManagerLocal roomsManager;
    @EJB
    ConsumptionsObsManagerLocal consumptionsManager;
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
	m1.setApartment(a);
	m1.setId(roomsManager.create(m1));

        Room m2 = new Room();
        m2.setName("Cuisine");
	m2.setApartment(a);
	m2.setId(roomsManager.create(m2));
        

//		Employee e = new Employee();
//		e.setFirstName("Elisa");
//		e.setLastName("Touvomi");
//		e.setEmail("elisa.touvomi@heig-vd.ch");
//		e.setSalary(80000);
//		e.setId(employeesManager.create(e));

        Plug p1 = new Plug();
        p1.setName("frigo");
        p1.setAlwaysOn(true);
        p1.setRoom(m2);
        p1.setId(plugsManager.create(p1));

	
        Plug p2 = new Plug();
        p2.setName("television");
        p2.setAlwaysOn(false);
        p2.setRoom(m1);
        p2.setId(plugsManager.create(p2));

	
        Plug p3 = new Plug();
        p3.setName("lumiere");
        p3.setAlwaysOn(true);
        p3.setRoom(m1);
        p3.setId(plugsManager.create(p3));
        
        ConsumptionObs c = new ConsumptionObs();
        c.setPlug(p3);
        c.setkW(2.2);
        c.setTimestampMinute(new Date());
        c.setId(consumptionsManager.create(c));
        
        return "done";
    }
}
