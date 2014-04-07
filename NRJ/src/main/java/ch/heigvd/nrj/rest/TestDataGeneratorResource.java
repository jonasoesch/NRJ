package ch.heigvd.nrj.rest;


import ch.heigvd.nrj.model.Employee;
import ch.heigvd.nrj.services.crud.EmployeesManagerLocal;
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

	@GET
  @Produces({"text/plain"})
	public String generateEmployees() {
		Employee e = new Employee();
		e.setFirstName("John");
		e.setLastName("Doe");
		e.setEmail("john.doe@heig-vd.ch");
		e.setSalary(80000);
		employeesManager.create(e);
		return "done";
	}
}
