package shared;

import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class WebappListener implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		try {
			Enumeration<Driver> enumer = DriverManager.getDrivers();
			while (enumer.hasMoreElements()) {
				System.out.println("Forms: deregisterd driver");
				DriverManager.deregisterDriver(enumer.nextElement());
			}
		} catch (java.sql.SQLException se) {
			se.printStackTrace();
		}
		shared.HibernateHelper.closeFactory();
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
	}

}
