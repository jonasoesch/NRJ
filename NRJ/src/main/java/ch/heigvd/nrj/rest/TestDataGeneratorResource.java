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
@Path("test")
public class TestDataGeneratorResource {

    final static Logger LOG = Logger.getLogger(TestDataGeneratorResource.class.getName());
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

        Room m = new Room();
        m.setName("Chambre de Barbie");
        m.setApartment(a);
        m.setId(roomsManager.create(m));

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

        Plug p = new Plug();
        p.setName("frigo");
        p.setAlwaysOn(true);
        p.setRoom(m2);
        plugsManager.create(p);

        Plug p2 = new Plug();
        p2.setName("television");
        p2.setAlwaysOn(false);
        p2.setRoom(m);
        plugsManager.create(p2);
        
        Plug p3 = new Plug();
        p3.setName("lumiere");
        p3.setAlwaysOn(true);
        p3.setRoom(m);
        plugsManager.create(p3);



//        SimpleDateFormat sdf = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
//        String dateInString = "31-08-1982 10:20:56";
//        Date date = sdf.parse(dateInString);
//
//        PlugConsumptionFact pcf1 = new PlugConsumptionFact();
//        pcf1.setPlug(p2);
//        pcf1.setTimestampHour(date);
//        pcf1.setAvgKW(23.00);
//        plugConsumptionsFactsManager.create(pcf1);

        return "done";
    }
}
