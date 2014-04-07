package ch.heigvd.skeleton.rest;

import java.util.HashSet;
import java.util.Set;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * This class enlists all REST API endpoints.
 * 
 * @author Olivier Liechti
 */
@ApplicationPath("/api")
public class RESTAPI extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		final Set<Class<?>> classes = new HashSet<>();
		// register root resources/providers
		classes.add(ApplicationExceptionMapper.class);
		classes.add(EmployeesResource.class);
		classes.add(TestDataGeneratorResource.class);
		return classes;
	}
}
