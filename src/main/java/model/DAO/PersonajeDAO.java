
package model.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

import model.DataObject.Libro;
import model.DataObject.Personaje;
import model.DataObject.Usuario;
import model.IDAO.IPersonajeDAO;
import utils.ConnectionUtil;

public class PersonajeDAO implements IPersonajeDAO {

	public static EntityManager createEM() {
		EntityManagerFactory emf = ConnectionUtil.getInstance();
		return emf.createEntityManager();
	}

	EntityManager em = createEM();

//	// Queries
//	private final String getAllPersonajes = "SELECT * FROM Personaje";
//	private String getPersonajeByName = "SELECT p FROM Personaje p WHERE p.nombre = :nombrepersonaje";
//	private String getPersonajeFromBook = "SELECT p FROM Personaje p WHERE p.id_libro=:idlibro";
//	private String getPersonajeFromUser = "SELECT p FROM Personaje p JOIN Book b JOIN PersonajeLibro pl WHERE pl.personajeid = p.id AND pl.libroid = b.id AND b.id_user=:iduser";
//	// A la espera de usar la base de datos, para saber cual es el nombre
//	// de las variables idpersonaje e idlibro

	@Override
	public void crear(Personaje aux) {
		try {
			em.getTransaction().begin();
			em.persist(aux);
			em.getTransaction().commit();
		} catch (EntityExistsException e) {
			throw new EntityExistsException("El usuario ya existe");
		} catch (IllegalStateException e) {
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			throw new RollbackException("Error al crear el usuario deshaciendo la transaccion");
		}
	}

	@Override
	public void editar(Personaje aux) {
		try {
			em.getTransaction().begin();
			em.persist(aux);
			em.getTransaction().commit();
		} catch (IllegalStateException e) {
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			throw new RollbackException("Error al crear el usuario deshaciendo la transaccion");
		}
	}

	@Override
	public void borrar(Long id) {
		Personaje delete = getById(id);
		try {
			em.getTransaction().begin();
			em.remove(delete);
			em.getTransaction().commit();
		} catch (IllegalStateException e) {
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			throw new RollbackException("Error al crear el usuario deshaciendo la transaccion");
		}
	}

	@Override
	public List<Personaje> getAll() {
		List<Personaje> result = new ArrayList<>();
		try {
			em.getTransaction().begin();
			TypedQuery<Personaje> q = em.createNamedQuery("getAllPersonajes", Personaje.class);
			result = q.getResultList();
			em.getTransaction().commit();
			return result;
		} catch (IllegalStateException e) {
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			throw new RollbackException("Error al crear el usuario deshaciendo la transaccion");
		}
	}

	@Override
	public Personaje getById(Long id) {
		Personaje result = new Personaje();
		try {
			em.getTransaction().begin();
			result = em.find(Personaje.class, id);
			em.getTransaction().commit();
			return result;
		} catch (IllegalStateException e) {
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			throw new RollbackException("Error al crear el usuario deshaciendo la transaccion");
		}
	}

	public List<Personaje> mostrarPorLibro(Libro l) {
		List<Personaje> result = new ArrayList<>();
		try {
			em.getTransaction().begin();
			TypedQuery<Personaje> q = em.createNamedQuery("getPersonajeFromBook", Personaje.class).setParameter("idlibro", l.getId());
			result = q.getResultList();
			em.getTransaction().commit();
			return result;
		} catch (IllegalStateException e) {
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			throw new RollbackException("Error al crear el usuario deshaciendo la transaccion");
		}
	}

	@Override
	public Personaje getCharacterByName(String nombre) {
		Personaje result = null;
		try {
			em.getTransaction().begin();
			TypedQuery<Personaje> q = em.createNamedQuery("getPersonajeByName", Personaje.class).setParameter("nombrepersonaje",
					nombre);
			result = q.getResultList().get(0);
			em.getTransaction().commit();
			return result;
		} catch (IllegalStateException e) {
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			throw new RollbackException("Error al crear el usuario deshaciendo la transaccion");
		}
	}

	@Override
	public List<Personaje> getCharactersByUser(Usuario user) {
		List<Personaje> result = new ArrayList<>();
		try {
			em.getTransaction().begin();
			TypedQuery<Personaje> q = em.createNamedQuery("getPersonajeFromUser", Personaje.class).setParameter("iduser", user.getId());
			result = q.getResultList();
			em.getTransaction().commit();
			return result;
		} catch (IllegalStateException e) {
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			throw new RollbackException("Error al crear el usuario deshaciendo la transaccion");
		}
	}

}
