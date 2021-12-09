package utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionUtil {

	private static final String PUN = "ApplicationMariaDB";
	private static EntityManagerFactory emf;

	public static EntityManagerFactory getInstace(String name) {
		if (emf == null) {
			emf = Persistence.createEntityManagerFactory(name);
		}
		return emf;
	}

	public static EntityManagerFactory getInstace() {
		if (emf == null) {
			emf = Persistence.createEntityManagerFactory(PUN);
		}
		return emf;
	}
}