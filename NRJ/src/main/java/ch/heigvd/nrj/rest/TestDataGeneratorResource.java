package ch.heigvd.nrj.rest;

import ch.heigvd.nrj.model.Apartment;
import ch.heigvd.nrj.model.History;
import ch.heigvd.nrj.model.Plug;
import ch.heigvd.nrj.model.PlugConsumptionFact;
import ch.heigvd.nrj.model.Room;
import ch.heigvd.nrj.model.Warning;
import ch.heigvd.nrj.services.crud.ApartmentsManagerLocal;
import ch.heigvd.nrj.services.crud.EmployeesManagerLocal;
import ch.heigvd.nrj.services.crud.HistoriesManagerLocal;
import ch.heigvd.nrj.services.crud.PlugConsumptionsFactsManager;
import ch.heigvd.nrj.services.crud.PlugConsumptionsFactsManagerLocal;
import ch.heigvd.nrj.services.crud.PlugsManagerLocal;
import ch.heigvd.nrj.services.crud.RoomsManagerLocal;
import ch.heigvd.nrj.services.crud.WarningsManagerLocal;
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
    HistoriesManagerLocal historiesManager;
    @EJB
    RoomsManagerLocal roomsManager;
    @EJB
    ApartmentsManagerLocal apartmentsManager;
    @EJB
    PlugConsumptionsFactsManagerLocal plugConsumptionsFactsManager;
    @EJB
    WarningsManagerLocal warningManager;

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
	m1.addPlug(p1);
	
	History h1 = new History();
        h1.setTimestampMinute(new Date());
        h1.setStatus(false);
        h1.setId(historiesManager.create(h1));
	p1.addHistory(h1);
	
        Warning warningp1 = new Warning();
        warningp1.setMessage("Attention warning du plug1!");
        warningp1.setTimestampMinute(new Date());
        warningp1.setId(warningManager.create(warningp1));
        p1.addWarnings(warningp1);

        Plug p2 = new Plug();
        p2.setName("television");
        p2.setAlwaysOn(false);
        p2.setId(plugsManager.create(p2));
        Warning warningp2 = new Warning();
        warningp2.setMessage("Attention warning du plug2!");
        warningp2.setTimestampMinute(new Date());
        warningp2.setId(warningManager.create(warningp2));
        m2.addPlug(p2);
	
	History h2 = new History();
        h2.setTimestampMinute(new Date());
        h2.setStatus(false);
        h2.setId(historiesManager.create(h2));
	p2.addHistory(h2);
        
        Plug p3 = new Plug();
        p3.setName("lumiere");
        p3.setAlwaysOn(true);
        p3.setId(plugsManager.create(p3));
        Warning warningp3 = new Warning();
        warningp3.setMessage("Attention warning du plug3!");
        warningp3.setTimestampMinute(new Date());
        warningp3.setId(warningManager.create(warningp3));
        m1.addPlug(p3);
	
	History h3 = new History();
        h3.setTimestampMinute(new Date());
        h3.setStatus(true);
        h3.setId(historiesManager.create(h3));
	p3.addHistory(h3);
	
        //Current day time
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date nowDate = new Date();

        PlugConsumptionFact pc1 = new PlugConsumptionFact();
        pc1.setTimestampHour(nowDate);
        pc1.setAvgKW(23.00);
        pc1.setPlug(p1);
        pc1.setId(plugConsumptionsFactsManager.create(pc1));
        p1.addPlugConsumptionFact(pc1);

        return "done";
    }
}
