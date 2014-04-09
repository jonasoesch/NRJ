package ch.heigvd.nrj.rest;


import ch.heigvd.nrj.model.Apartment;
import ch.heigvd.nrj.model.Employee;
import ch.heigvd.nrj.model.Plug;
import ch.heigvd.nrj.model.Room;
import ch.heigvd.nrj.services.crud.ApartmentsManagerLocal;
import ch.heigvd.nrj.services.crud.EmployeesManagerLocal;
import ch.heigvd.nrj.services.crud.PlugsManagerLocal;
import ch.heigvd.nrj.services.crud.RoomsManagerLocal;
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
        
	@GET
  @Produces({"text/plain"})
	public String generateEmployees() {
//		Apartment a = new Apartment();
//		a.setName("Appartement 511");
//		apartmentsManager.create(a);
		Room m = new Room();
                m.setName("Chambre de Barbie");
                m.setId(roomsManager.create(m));
		
		Employee e = new Employee();
		e.setFirstName("Elisa");
		e.setLastName("Touvomi");
		e.setEmail("elisa.touvomi@heig-vd.ch");
		e.setSalary(80000);
		e.setId(employeesManager.create(e));
//                
//                employeesManager.findAll();
                
                Plug p = new Plug();
                p.setName("frigo");
                p.setAlwaysOn(true);
		p.setRoom(m);
                plugsManager.create(p);
                
//                Apartment a = new Apartment();
//                p.setName("Chez Mc Cartney");
//                apartmentsManager.create(a);
                
		return "done";
	}
}
