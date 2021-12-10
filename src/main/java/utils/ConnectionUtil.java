package utils;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConnectionUtil {

	private static EntityManagerFactory emf;
	public static boolean choice = true; // ----->TRUE LOCAL ------> FALSE NUBE
	private static final String LOCAL = "AplicacionH2";
	private static final String REMOTE = "aplicacionMariaDB";

	public static EntityManagerFactory getInstance(String name) {
		if (emf == null) {
			if (name.equals(LOCAL) || name.equals(REMOTE)) {
				emf = Persistence.createEntityManagerFactory(name);
			}
		}
		return emf;
	}
/*
	public static EntityManagerFactory getRemoteInstance() {
		if (emf == null) {
			emf = Persistence.createEntityManagerFactory(REMOTE);
		}
		return emf;
	}

	public static EntityManagerFactory getLocalInstance() {
		if (emf == null) {
			emf = Persistence.createEntityManagerFactory(LOCAL);
		}
		return emf;
	}
*/
	public static EntityManagerFactory getInstance() {
		if (emf == null) {
			if (choice) {
				emf = Persistence.createEntityManagerFactory(LOCAL);
			} else {
				emf = Persistence.createEntityManagerFactory(REMOTE);
			}
		}
		return emf;
	}
}
