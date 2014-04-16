package ch.heigvd.nrj.rest;

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
import ch.heigvd.nrj.services.crud.ApartmentsManagerLocal;
import ch.heigvd.nrj.services.crud.ConsumptionsObsManagerLocal;
import ch.heigvd.nrj.services.crud.EmployeesManagerLocal;
import ch.heigvd.nrj.services.crud.PlugConsumptionsFactsManager;
import ch.heigvd.nrj.services.crud.PlugConsumptionsFactsManagerLocal;
import ch.heigvd.nrj.services.crud.PlugsManagerLocal;
import ch.heigvd.nrj.services.crud.RoomConsumptionsFactsManagerLocal;
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
    WarningsManagerLocal warningsManager;
    @EJB
    ApartmentsManagerLocal apartmentsManager;
    @EJB
    ApartmentConsumptionsFactsManagerLocal apartmentsConsumptionsFactsManager;
    @EJB
    RoomConsumptionsFactsManagerLocal roomsConsumptionsFactsManager;
    @EJB
    PlugConsumptionsFactsManagerLocal plugConsumptionsFactsManager;
    @EJB
    ConsumptionsObsManagerLocal consumptionsObsManager;
    
    @GET
    @Produces({"text/plain"})
    public String generateEmployees() throws ParseException {
        //Création de l'appartement
        Apartment a = new Apartment();
        a.setName("Appartement 511");
        a.setId(apartmentsManager.create(a));
	
	ApartmentConsumptionFact acf = new ApartmentConsumptionFact();
	acf.setApartment(a);
	acf.setAvgKW(20.5);
	acf.setTimestampHour(new Date());
        acf.setId(apartmentsConsumptionsFactsManager.create(acf));

        //Création de la pièce 1: Chambre de Barbie
        //Et l'ajoute à l'appartement
        Room m1 = new Room();
        m1.setName("Chambre de Barbie");
	m1.setApartment(a);
	m1.setId(roomsManager.create(m1));
	
	RoomConsumptionFact rcf = new RoomConsumptionFact();
	rcf.setRoom(m1);
	rcf.setAvgKW(25.5);
	rcf.setTimestampHour(new Date());
        rcf.setId(roomsConsumptionsFactsManager.create(rcf));

        //Création de la pièce 2: Cuisine
        //Et l'ajoute à l'appartement
        Room m2 = new Room();
        m2.setName("Cuisine");
	m2.setApartment(a);
	m2.setId(roomsManager.create(m2));
	
	//Création du plug 1: Frigo
        //Et l'ajoute à la pièce 1
        Plug p1 = new Plug();
        p1.setName("Frigo");
        p1.setAlwaysOn(true);
	p1.setRoom(m1);
        p1.setId(plugsManager.create(p1));
	
	ConsumptionObs c1 = new ConsumptionObs();
        c1.setTimestampMinute(new Date());
	c1.setkW(22.2);
	c1.setPlug(p1);
	c1.setId(consumptionsObsManager.create(c1));
	
	Warning w1 = new Warning();
        w1.setTimestampMinute(new Date());
	w1.setMessage("AIIIIIIIIIE AIE AIE");
	w1.setPlug(p1);
	w1.setId(consumptionsObsManager.create(c1));
	
	History h1 = new History();
        h1.setTimestampMinute(new Date());
	h1.setStatus(true);
	h1.setPlug(p1);
	h1.setId(consumptionsObsManager.create(c1));
	
	Plug p2 = new Plug();
        p2.setName("Télévision");
        p2.setAlwaysOn(false);
	p2.setRoom(m1);
        p2.setId(plugsManager.create(p2));
	
	Plug p3 = new Plug();
        p3.setName("Lumière principale");
        p3.setAlwaysOn(true);
	p3.setRoom(m2);
        p3.setId(plugsManager.create(p3));
        return "done";
    }
}
