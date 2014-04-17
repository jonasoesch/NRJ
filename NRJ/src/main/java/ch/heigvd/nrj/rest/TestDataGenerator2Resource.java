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
    HistoriesManagerLocal historiesManager;
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
        a.setName("Appartement de la famille Vonsurry");
        a.setId(apartmentsManager.create(a));

        ApartmentConsumptionFact acf = new ApartmentConsumptionFact();
        acf.setApartment(a);
        acf.setAvgKW(20.5);
        acf.setTimestampHour(new Date());
        acf.setId(apartmentsConsumptionsFactsManager.create(acf));

        //Création de la pièce 1: Chambre d'Anna
        //Et l'ajoute à l'appartement
        Room annaCh = new Room();
        annaCh.setName("Chambre d'Anna");
        annaCh.setApartment(a);
        annaCh.setId(roomsManager.create(annaCh));

        RoomConsumptionFact rcf1 = new RoomConsumptionFact();
        rcf1.setRoom(annaCh);
        rcf1.setAvgKW(25.5);
        rcf1.setTimestampHour(new Date());
        rcf1.setId(roomsConsumptionsFactsManager.create(rcf1));

        //Création de la pièce 1: Chambre de Thierry
        //Et l'ajoute à l'appartement
        Room thierryCh = new Room();
        thierryCh.setName("Chambre de Thierry");
        thierryCh.setApartment(a);
        thierryCh.setId(roomsManager.create(thierryCh));

        RoomConsumptionFact rcf2 = new RoomConsumptionFact();
        rcf2.setRoom(thierryCh);
        rcf2.setAvgKW(25.5);
        rcf2.setTimestampHour(new Date());
        rcf2.setId(roomsConsumptionsFactsManager.create(rcf2));

        //Création de la pièce 2: Cuisine
        //Et l'ajoute à l'appartement
        Room cuisine = new Room();
        cuisine.setName("Cuisine");
        cuisine.setApartment(a);
        cuisine.setId(roomsManager.create(cuisine));

        RoomConsumptionFact rcf3 = new RoomConsumptionFact();
        rcf3.setRoom(cuisine);
        rcf3.setAvgKW(100.3);
        rcf3.setTimestampHour(new Date());
        rcf3.setId(roomsConsumptionsFactsManager.create(rcf3));

        //Création de la pièce 2: Cuisine
        //Et l'ajoute à l'appartement
        Room parentCh = new Room();
        parentCh.setName("Chambre de parents");
        parentCh.setApartment(a);
        parentCh.setId(roomsManager.create(parentCh));

        RoomConsumptionFact rcf4 = new RoomConsumptionFact();
        rcf4.setRoom(parentCh);
        rcf4.setAvgKW(10.00);
        rcf4.setTimestampHour(new Date());
        rcf4.setId(roomsConsumptionsFactsManager.create(rcf4));

        //Création de la pièce 2: Cuisine
        //Et l'ajoute à l'appartement
        Room sejour = new Room();
        sejour.setName("Séjour");
        sejour.setApartment(a);
        sejour.setId(roomsManager.create(sejour));

        RoomConsumptionFact rcf5 = new RoomConsumptionFact();
        rcf5.setRoom(parentCh);
        rcf5.setAvgKW(21.06);
        rcf5.setTimestampHour(new Date());
        rcf5.setId(roomsConsumptionsFactsManager.create(rcf5));

        //Création du plug 1: Frigo
        //Et l'ajoute à la pièce 1
        Plug p1 = new Plug();
        p1.setName("Frigo");
        p1.setAlwaysOn(true);
        p1.setRoom(cuisine);
        p1.setId(plugsManager.create(p1));

        ConsumptionObs c1 = new ConsumptionObs();
        c1.setTimestampMinute(new Date());
        c1.setkW(22.2);
        c1.setPlug(p1);
        c1.setId(consumptionsObsManager.create(c1));

        Warning w1 = new Warning();
        w1.setTimestampMinute(new Date());
        w1.setMessage("Problème sur le Frigo de la cuisine");
        w1.setPlug(p1);
        w1.setId(warningsManager.create(w1));

        History h1 = new History();
        h1.setTimestampMinute(new Date());
        h1.setStatus(true);
        h1.setPlug(p1);
        h1.setId(historiesManager.create(h1));

        PlugConsumptionFact pcf1 = new PlugConsumptionFact();
        pcf1.setTimestampHour(new Date());
        pcf1.setAvgKW(20.2);
        pcf1.setPlug(p1);
        pcf1.setId(plugConsumptionsFactsManager.create(pcf1));

        //Création du plug 1: Cuisinière
        //Et l'ajoute à la pièce 1
        Plug p4 = new Plug();
        p4.setName("Cuisinière");
        p4.setAlwaysOn(true);
        p4.setRoom(cuisine);
        p4.setId(plugsManager.create(p4));

        ConsumptionObs c2 = new ConsumptionObs();
        c2.setTimestampMinute(new Date());
        c2.setkW(12.2);
        c2.setPlug(p4);
        c2.setId(consumptionsObsManager.create(c2));

        Warning w2 = new Warning();
        w2.setTimestampMinute(new Date());
        w2.setMessage("Problème sur la cuisinière");
        w2.setPlug(p4);
        w2.setId(warningsManager.create(w2));

        History h2 = new History();
        h2.setTimestampMinute(new Date());
        h2.setStatus(true);
        h2.setPlug(p4);
        h2.setId(historiesManager.create(h2));

        PlugConsumptionFact pcf2 = new PlugConsumptionFact();
        pcf2.setTimestampHour(new Date());
        pcf2.setAvgKW(13.4);
        pcf2.setPlug(p4);
        pcf2.setId(plugConsumptionsFactsManager.create(pcf2));

        //Télévision dans chambre Anna
        Plug p2 = new Plug();
        p2.setName("Télévision");
        p2.setAlwaysOn(false);
        p2.setRoom(annaCh);
        p2.setId(plugsManager.create(p2));

        ConsumptionObs c3 = new ConsumptionObs();
        c3.setTimestampMinute(new Date());
        c3.setkW(5.2);
        c3.setPlug(p2);
        c3.setId(consumptionsObsManager.create(c3));

        Warning w3 = new Warning();
        w3.setTimestampMinute(new Date());
        w3.setMessage("Problème sur la télévision de la chambre d'Anna");
        w3.setPlug(p2);
        w3.setId(warningsManager.create(w3));

        History h3 = new History();
        h3.setTimestampMinute(new Date());
        h3.setStatus(true);
        h3.setPlug(p2);
        h3.setId(historiesManager.create(h3));

        PlugConsumptionFact pcf3 = new PlugConsumptionFact();
        pcf3.setTimestampHour(new Date());
        pcf3.setAvgKW(3.4);
        pcf3.setPlug(p2);
        pcf3.setId(plugConsumptionsFactsManager.create(pcf3));

        //Télévision dans séjour
        Plug p5 = new Plug();
        p5.setName("Télévision");
        p5.setAlwaysOn(false);
        p5.setRoom(sejour);
        p5.setId(plugsManager.create(p5));

        ConsumptionObs c4 = new ConsumptionObs();
        c4.setTimestampMinute(new Date());
        c4.setkW(0.8);
        c4.setPlug(p5);
        c4.setId(consumptionsObsManager.create(c4));

        Warning w4 = new Warning();
        w4.setTimestampMinute(new Date());
        w4.setMessage("Problème sur la télévision du séjour");
        w4.setPlug(p5);
        w4.setId(warningsManager.create(w4));

        History h4 = new History();
        h4.setTimestampMinute(new Date());
        h4.setStatus(true);
        h4.setPlug(p5);
        h4.setId(historiesManager.create(h4));

        PlugConsumptionFact pcf4 = new PlugConsumptionFact();
        pcf4.setTimestampHour(new Date());
        pcf4.setAvgKW(3.4);
        pcf4.setPlug(p5);
        pcf4.setId(plugConsumptionsFactsManager.create(pcf4));

        //Lumière principale
        Plug p3 = new Plug();
        p3.setName("Lumière principale");
        p3.setAlwaysOn(false);
        p3.setRoom(sejour);
        p3.setId(plugsManager.create(p3));

        ConsumptionObs c5 = new ConsumptionObs();
        c5.setTimestampMinute(new Date());
        c5.setkW(0.8);
        c5.setPlug(p3);
        c5.setId(consumptionsObsManager.create(c5));

        Warning w5 = new Warning();
        w5.setTimestampMinute(new Date());
        w5.setMessage("Problème sur la lumière principale du séjour");
        w5.setPlug(p3);
        w5.setId(warningsManager.create(w5));

        History h5 = new History();
        h5.setTimestampMinute(new Date());
        h5.setStatus(true);
        h5.setPlug(p3);
        h5.setId(historiesManager.create(h5));

        PlugConsumptionFact pcf5 = new PlugConsumptionFact();
        pcf5.setTimestampHour(new Date());
        pcf5.setAvgKW(1.4);
        pcf5.setPlug(p3);
        pcf5.setId(plugConsumptionsFactsManager.create(pcf5));
        
        //PS3 thierry
        Plug p6 = new Plug();
        p6.setName("PS3");
        p6.setAlwaysOn(false);
        p6.setRoom(thierryCh);
        p6.setId(plugsManager.create(p6));

        ConsumptionObs c6 = new ConsumptionObs();
        c6.setTimestampMinute(new Date());
        c6.setkW(1.4);
        c6.setPlug(p6);
        c6.setId(consumptionsObsManager.create(c6));

        Warning w6 = new Warning();
        w6.setTimestampMinute(new Date());
        w6.setMessage("Problème sur la PS3 de la chambre de thierry");
        w6.setPlug(p6);
        w6.setId(warningsManager.create(w6));

        History h6 = new History();
        h6.setTimestampMinute(new Date());
        h6.setStatus(true);
        h6.setPlug(p6);
        h6.setId(historiesManager.create(h6));

        PlugConsumptionFact pcf6 = new PlugConsumptionFact();
        pcf6.setTimestampHour(new Date());
        pcf6.setAvgKW(2.7);
        pcf6.setPlug(p6);
        pcf6.setId(plugConsumptionsFactsManager.create(pcf6));
        
        //Télévision thierry
        Plug p7 = new Plug();
        p7.setName("Télévision");
        p7.setAlwaysOn(false);
        p7.setRoom(thierryCh);
        p7.setId(plugsManager.create(p7));

        ConsumptionObs c7 = new ConsumptionObs();
        c7.setTimestampMinute(new Date());
        c7.setkW(2.3);
        c7.setPlug(p7);
        c7.setId(consumptionsObsManager.create(c7));

        Warning w7 = new Warning();
        w7.setTimestampMinute(new Date());
        w7.setMessage("Problème sur la télévision de la chambre de thierry");
        w7.setPlug(p7);
        w7.setId(warningsManager.create(w7));

        History h7 = new History();
        h7.setTimestampMinute(new Date());
        h7.setStatus(true);
        h7.setPlug(p7);
        h7.setId(historiesManager.create(h7));

        PlugConsumptionFact pcf7 = new PlugConsumptionFact();
        pcf7.setTimestampHour(new Date());
        pcf7.setAvgKW(2.1);
        pcf7.setPlug(p7);
        pcf7.setId(plugConsumptionsFactsManager.create(pcf7));
        
        //Appareil mystère
        Plug p8 = new Plug();
        p8.setName("Appareil Mystère");
        p8.setAlwaysOn(false);
        p8.setRoom(parentCh);
        p8.setId(plugsManager.create(p8));

        ConsumptionObs c8 = new ConsumptionObs();
        c8.setTimestampMinute(new Date());
        c8.setkW(0.3);
        c8.setPlug(p8);
        c8.setId(consumptionsObsManager.create(c8));

        Warning w8 = new Warning();
        w8.setTimestampMinute(new Date());
        w8.setMessage("Problème sur l'appareil mystère de la chambre des parents");
        w8.setPlug(p8);
        w8.setId(warningsManager.create(w8));

        History h8 = new History();
        h8.setTimestampMinute(new Date());
        h8.setStatus(true);
        h8.setPlug(p8);
        h8.setId(historiesManager.create(h8));

        PlugConsumptionFact pcf8 = new PlugConsumptionFact();
        pcf8.setTimestampHour(new Date());
        pcf8.setAvgKW(1.7);
        pcf8.setPlug(p8);
        pcf8.setId(plugConsumptionsFactsManager.create(pcf8));

        return "done";
    }
}
