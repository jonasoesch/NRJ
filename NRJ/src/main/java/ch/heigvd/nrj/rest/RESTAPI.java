package ch.heigvd.nrj.rest;

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
		classes.add(ApartmentsResource.class);
		classes.add(ConsumptionsObsResource.class);
		classes.add(HistoriesResource.class);
		classes.add(PlugConsumptionsFactsResource.class);
		classes.add(PlugsResource.class);
		classes.add(RoomConsumptionsFactsResource.class);
		classes.add(RoomsResource.class);
		classes.add(WarningsResource.class);
                classes.add(ConsumptionsObsResource.class);
		classes.add(TestDataGeneratorResource.class);
		return classes;
	}
}
