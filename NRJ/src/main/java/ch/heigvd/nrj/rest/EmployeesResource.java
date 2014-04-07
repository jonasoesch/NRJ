package ch.heigvd.nrj.rest;

import ch.heigvd.nrj.exceptions.EntityNotFoundException;
import ch.heigvd.nrj.model.Employee;
import ch.heigvd.nrj.services.crud.EmployeesManagerLocal;
import ch.heigvd.nrj.services.to.EmployeesTOServiceLocal;
import ch.heigvd.nrj.to.PublicEmployeeTO;
import java.net.URI;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * This is the REST API endpoint for the Employees resource. When REST clients
 * send HTTP requests, they will be routed to this class (because of the @Path
 * annotation). They will then be routed to the appropriate methods (because of
 * the @GET, @POST and other annotations).
 * 
 * This class is a stateless session bean, which allows us to inject other stateless
 * session beans with annotations. That is practical, because when we receive
 * requests from REST clients, we can delegate most of the work to DAOs and
 * Transfer Object services.
 *
 * @author Olivier Liechti
 */
@Stateless
@Path("employees")
public class EmployeesResource {

	@Context
	private UriInfo context;
	
	@EJB
	EmployeesManagerLocal employeesManager;
	
	@EJB
	EmployeesTOServiceLocal employeesTOService;

	/**
	 * Creates a new instance of EmployeesResource
	 */
	public EmployeesResource() {
	}

	/**
	 * Creates a new Employee resource from the provided representation
	 * @return an instance of PublicEmployeeTO
	 */
	@POST
	@Consumes({"application/json"})
	public Response createResource(PublicEmployeeTO newEmployeeTO) {
			Employee newEmployee = new Employee();
			employeesTOService.updateEmployeeEntity(newEmployee,newEmployeeTO);
			long newEmployeeId = employeesManager.create(newEmployee);
			URI createdURI = context.getAbsolutePathBuilder().path(Long.toString(newEmployeeId)).build();
			return Response.created(createdURI).build();
	}

	/**
	 * Retrieves a representation of a list of Employee resources
	 * @return an instance of PublicEmployeeTO
	 */
	@GET
  @Produces({"application/json", "application/xml"})
	public List<PublicEmployeeTO> getResourceList() {
		List<Employee> employees = employeesManager.findAll();
		List<PublicEmployeeTO> result = new LinkedList<>();
		for(Employee employee : employees) {
			result.add(employeesTOService.buildPublicEmployeeTO(employee));
		}
		return result;
	}
	
	/**
	 * Retrieves representation of an Employee resource
	 * @param id this id of the employee
	 * @return an instance of PublicEmployeeTO
	 * @throws ch.heigvd.skeleton.exceptions.EntityNotFoundException
	 */
	@GET
	@Path("{id}")
  @Produces({"application/json", "application/xml"})
	public PublicEmployeeTO getResource(@PathParam("id") long id) throws EntityNotFoundException {
		Employee employee = employeesManager.findById(id);
		PublicEmployeeTO employeeTO = employeesTOService.buildPublicEmployeeTO(employee);
		return employeeTO;
	}

	/**
	 * Updates an Employee resource
   * @param id this id of the employee
	 * @param updatedEmployeeTO a TO containing the employee data
	 * @return an instance of PublicEmployeeTO
	 * @throws ch.heigvd.skeleton.exceptions.EntityNotFoundException
	 */
	@PUT
	@Path("{id}")
	@Consumes({"application/json"})
	public Response Resource(PublicEmployeeTO updatedEmployeeTO, @PathParam("id") long id) throws EntityNotFoundException {
		Employee employeeToUpdate = employeesManager.findById(id);
		employeesTOService.updateEmployeeEntity(employeeToUpdate, updatedEmployeeTO);
		employeesManager.update(employeeToUpdate);
		return Response.ok().build();
	}

	
	/**
	 * Deletes an Employee resource
   * @param id this id of the employee
	 * @return an instance of PublicEmployeeTO
	 * @throws ch.heigvd.skeleton.exceptions.EntityNotFoundException
	 */
	@DELETE
	@Path("{id}")
	public Response deleteResource(@PathParam("id") long id) throws EntityNotFoundException {
		employeesManager.delete(id);
		return Response.ok().build();
	}

}
