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
import ch.heigvd.nrj.services.crud.HistoriesManagerLocal;
import ch.heigvd.nrj.services.crud.PlugConsumptionsFactsManagerLocal;
import ch.heigvd.nrj.services.crud.PlugsManagerLocal;
import ch.heigvd.nrj.services.crud.RoomConsumptionsFactsManagerLocal;
import ch.heigvd.nrj.services.crud.RoomsManagerLocal;
import ch.heigvd.nrj.services.crud.WarningsManagerLocal;
import java.text.ParseException;
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
    ApartmentConsumptionsFactsManagerLocal apartmentConsumptionsFactsManager;
    @EJB
    PlugConsumptionsFactsManagerLocal plugConsumptionsFactsManager;
    @EJB
    RoomConsumptionsFactsManagerLocal roomConsumptionsFactsManager;
    @EJB
    ConsumptionsObsManagerLocal consumptionObsManager;
    @EJB
    WarningsManagerLocal warningManager;

    @GET
    @Produces({"text/plain"})
    public String generateEmployees() throws ParseException {

        //Création de l'appartement
        Apartment a = new Apartment();
        a.setName("Appartement 511");
        a.setId(apartmentsManager.create(a));

        //Création de la pièce 1: Chambre de Barbie
        //Et l'ajoute à l'appartement
        Room m1 = new Room();
        m1.setName("Chambre de Barbie");
	m1.setApartment(a);
	m1.setId(roomsManager.create(m1));

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
        //Ajout history
        History h1 = new History();
        h1.setTimestampMinute(new Date());
        h1.setStatus(false);
        h1.setId(historiesManager.create(h1));
        p1.addHistory(h1);
        //Ajout warning
        Warning warningp1 = new Warning();
        warningp1.setMessage("Attention warning du plug1!");
        warningp1.setTimestampMinute(new Date());
        warningp1.setId(warningManager.create(warningp1));
        p1.addWarning(warningp1);

        //Création du plug 2: Télévision
        //Et l'ajoute à la pièce 1
        Plug p2 = new Plug();
        p2.setName("Télévision");
        p2.setAlwaysOn(false);
	p2.setRoom(m1);
        p2.setId(plugsManager.create(p2));
        //Ajout warning
        Warning warningp2 = new Warning();
        warningp2.setMessage("Attention warning du plug2!");
        warningp2.setTimestampMinute(new Date());
        warningp2.setId(warningManager.create(warningp2));
        p2.addWarning(warningp2);
        m2.addPlug(p2);
        //Ajout history
        History h2 = new History();
        h2.setTimestampMinute(new Date());
        h2.setStatus(false);
        h2.setId(historiesManager.create(h2));
        p2.addHistory(h2);

        //Création du plug 3: Lumière principale de la chambre de Barbie
        //Et l'ajoute à la pièce 1
        Plug p3 = new Plug();
        p3.setName("Lumière principale");
        p3.setAlwaysOn(true);
	p3.setRoom(m2);
        p3.setId(plugsManager.create(p3));
        //Ajout warning
        Warning warningp3 = new Warning();
        warningp3.setMessage("Attention warning du plug3!");
        warningp3.setTimestampMinute(new Date());
        warningp3.setId(warningManager.create(warningp3));
        p3.addWarning(warningp3);
        m1.addPlug(p3);
        //Ajout history
        History h3 = new History();
        h3.setTimestampMinute(new Date());
        h3.setStatus(true);
        h3.setId(historiesManager.create(h3));
        p3.addHistory(h3);

        //Création du fonctionnement du plug 1
        PlugConsumptionFact pc1 = new PlugConsumptionFact();
        pc1.setTimestampHour(new Date());
        pc1.setAvgKW(23.00);
        pc1.setId(plugConsumptionsFactsManager.create(pc1));
        p1.addPlugConsumptionFact(pc1);

        //Création de la consommation de la pièce 1
        RoomConsumptionFact rc1 = new RoomConsumptionFact();
        rc1.setAvgKW(1000.34);
        rc1.setTimestampHour(new Date());
        rc1.setId(roomConsumptionsFactsManager.create(rc1));
        m1.addRoomConsumptionFact(rc1);
        
        ApartmentConsumptionFact ac1 = new ApartmentConsumptionFact();
        ac1.setAvgKW(1000.34);
        ac1.setTimestampHour(new Date());
        ac1.setId(apartmentConsumptionsFactsManager.create(ac1));
        a.addApartmentConsumptionFact(ac1);

        //Création de la consommation du plug 1
//        ConsumptionObs co = new ConsumptionObs();
//        co.setTimestampHour(new Date());
//        co.setkW(50.00);
//        co.setId(consumptionObsManager.create(co));
//        p1.addConsumptionObs(co);

        
        return "Maman, j'ai finiiii!!! <3 LMAO - LMAO";
    }
}
