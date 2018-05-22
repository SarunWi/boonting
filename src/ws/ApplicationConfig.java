package ws;

import java.util.Set;

import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath("rest")
public class ApplicationConfig extends Application {
	@Override
	public Set<Class<?>> getClasses() {
		Set<Class<?>> resources = new java.util.HashSet<>();
		addResourcesClasses(resources);
		return resources;
	}

	private void addResourcesClasses(Set<Class<?>> resources) {
//		resources.add(ws.CategoryRestful.class);
		resources.add(ws.LoginRestful.class);
		resources.add(ws.SalesOrderRestful.class);
//		resources.add(ws.LocationRestful.class);
	}
}
