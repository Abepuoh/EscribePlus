package utils;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import model.DataObject.Libro;
import model.DataObject.Notas_Cap;
import model.DataObject.Notas_Lib;
import model.DataObject.Partes;
import model.DataObject.Personaje;
import model.DataObject.Recordatorio;
import model.DataObject.Usuario;

public class ConnectionUtil {

	private static EntityManagerFactory emf;
	public static boolean choice = false; // ----->TRUE NUBE ------> FALSE LOCAL
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
	 * public static EntityManagerFactory getRemoteInstance() { if (emf == null) {
	 * emf = Persistence.createEntityManagerFactory(REMOTE); } return emf; }
	 * 
	 * public static EntityManagerFactory getLocalInstance() { if (emf == null) {
	 * emf = Persistence.createEntityManagerFactory(LOCAL); } return emf; }
	 */
	public static EntityManagerFactory getInstance() {
		if (emf == null) {
			if (choice) {
				emf = Persistence.createEntityManagerFactory(REMOTE);
			} else {
				emf = Persistence.createEntityManagerFactory(LOCAL);
			}
		}
		return emf;
	}

	public static void transferH2ToMariaDB() throws IllegalStateException {
		if (emf != null) {
			EntityManagerFactory temp = null;
			try {
				temp = Persistence.createEntityManagerFactory("aplicacionMariaDB");
			} catch (PersistenceException e) {
				throw new IllegalStateException(e.getMessage());
			}
			EntityManager t = temp.createEntityManager();
			t.getTransaction().begin();

			for (Usuario userAux : getAllUsers(emf)) {
				userAux = t.merge(userAux);
				t.persist(userAux);
			}
			for (Libro libroAux : getAllBooks(emf)) {
				libroAux = t.merge(libroAux);
				t.persist(libroAux);
			}
			for (Partes partesAux : getAllPartes(emf)) {
				partesAux = t.merge(partesAux);
				t.persist(partesAux);
			}
			for (Notas_Cap notCapAux: getAllNotas_Cap(emf)) {
				notCapAux = t.merge(notCapAux);
				t.persist(notCapAux);
			}
			for (Notas_Lib notLibAux : getAllNotas_Lib(emf)) {
				notLibAux = t.merge(notLibAux);
				t.persist(notLibAux);
			}
			for (Personaje personajeAux : getAllPersonajes(emf)) {
				personajeAux = t.merge(personajeAux);
				t.persist(personajeAux);
			}
			for (Recordatorio recordatorioAux : getAllRecordatorios(emf)) {
				recordatorioAux = t.merge(recordatorioAux);
				t.persist(recordatorioAux);
			}
			t.getTransaction().commit();
			t.close();
			temp.close();
		}
	}
	
	private static List<Usuario> getAllUsers(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Usuario> q = em.createNamedQuery("getAllUsers", Usuario.class);
		List<Usuario> result = new ArrayList<>(q.getResultList());
		em.getTransaction().commit();
		return result;
	}

	public static List<Libro> getAllBooks(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Libro> q = em.createQuery("getAllBooks", Libro.class);
		List<Libro> result = new ArrayList<>(q.getResultList());
		em.getTransaction().commit();
		return result;
	}
	public static List<Partes> getAllPartes(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Partes> q = em.createQuery("getAllPartes", Partes.class);
		List<Partes> result = new ArrayList<>(q.getResultList());
		em.getTransaction().commit();
		return result;
	}
	public static List<Notas_Cap> getAllNotas_Cap(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Notas_Cap> q = em.createQuery("getAllNotas_Cap", Notas_Cap.class);
		List<Notas_Cap> result = new ArrayList<>(q.getResultList());
		em.getTransaction().commit();
		return result;
	}
	public static List<Notas_Lib> getAllNotas_Lib(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Notas_Lib> q = em.createQuery("getAllNotas_Lib", Notas_Lib.class);
		List<Notas_Lib> result = new ArrayList<>(q.getResultList());
		em.getTransaction().commit();
		return result;
	}
	public static List<Personaje> getAllPersonajes(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Personaje> q = em.createQuery("getAllPersonajes", Personaje.class);
		List<Personaje> result = new ArrayList<>(q.getResultList());
		em.getTransaction().commit();
		return result;
	}
	public static List<Recordatorio> getAllRecordatorios(EntityManagerFactory emf) {
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<Recordatorio> q = em.createQuery("getAllRecordatorios", Recordatorio.class);
		List<Recordatorio> result = new ArrayList<>(q.getResultList());
		em.getTransaction().commit();
		return result;
	}
}
