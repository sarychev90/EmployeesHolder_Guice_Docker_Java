package best.project.guice.listener;

import javax.servlet.annotation.WebListener;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.GuiceServletContextListener;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;

import best.project.guice.controller.EmployeeController;

@WebListener
public class ApplicationGuiceListener extends GuiceServletContextListener {
	@Override
	protected Injector getInjector() {
		Injector injector = Guice.createInjector(new JerseyServletModule() {
			@Override
			protected void configureServlets() {
				bind(EmployeeController.class).in(Singleton.class);
				serve("/*").with(GuiceContainer.class);
			}
		}, new JpaPersistModule("db_manager"));
		injector.getInstance(JpaInitializer.class);
		return injector;
	}
}
