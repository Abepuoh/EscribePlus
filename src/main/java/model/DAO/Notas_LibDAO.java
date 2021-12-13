
package model.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

import model.DataObject.Libro;
import model.DataObject.Notas_Lib;
import model.IDAO.INotas_libDAO;
import model.IDataObject.INotas_lib;
import utils.ConnectionUtil;

public class Notas_LibDAO implements INotas_libDAO {

	public static EntityManager createEM() {
		EntityManagerFactory emf = ConnectionUtil.getInstance();
		return emf.createEntityManager();
	}

	EntityManager em = createEM();

	// Queries
	//private final String getAll = "Select * from Notas_Lib";

	@Override
	public void crear(Notas_Lib aux) {
		try {
			em.getTransaction().begin();
			em.persist(aux);
			em.getTransaction().commit();
		} catch (EntityExistsException e) {
			e.printStackTrace();
			throw new EntityExistsException("El usuario ya existe");
		} catch (IllegalStateException e) {
			e.printStackTrace();
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			e.printStackTrace();
			throw new RollbackException("Error al crear el recordatorio deshaciendo la transaccion");
		}
	}

	@Override
	public void editar(Notas_Lib aux) {
		try {
			em.getTransaction().begin();
			em.merge(aux);
			em.getTransaction().commit();
		} catch (IllegalStateException e) {
			e.printStackTrace();
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			e.printStackTrace();
			throw new RollbackException("Error al crear el recordatorio deshaciendo la transaccion");
		}
	}

	@Override
	public void borrar(Long id) {
		INotas_lib delete = getById(id);
		try {
			em.getTransaction().begin();
			em.remove(delete);
			em.getTransaction().commit();
		} catch (IllegalStateException e) {
			e.printStackTrace();
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			e.printStackTrace();
			throw new RollbackException("Error al crear el recordatorio deshaciendo la transaccion");
		}
	}

	@Override
	public List<Notas_Lib> getAll() {
		List<Notas_Lib> result = new ArrayList<>();
		try {
			em.getTransaction().begin();
			TypedQuery<Notas_Lib> q = em.createNamedQuery("getAllNotas_Lib", Notas_Lib.class);
			result = q.getResultList();
			em.getTransaction().commit();
		} catch (IllegalStateException e) {
			e.printStackTrace();
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			e.printStackTrace();
			throw new RollbackException("Error al crear el recordatorio deshaciendo la transaccion");
		}
		return result;
	}

	@Override
	public Notas_Lib getById(Long id) {
		Notas_Lib result = new Notas_Lib();
		try {
			em.getTransaction().begin();
			result = em.find(Notas_Lib.class, id);
			em.getTransaction().commit();
		} catch (IllegalStateException e) {
			e.printStackTrace();
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			e.printStackTrace();
			throw new RollbackException("Error al crear el recordatorio deshaciendo la transaccion");
		}
		return result;
	}
	public List<Notas_Lib> getFromBook(Libro l){
        List <Notas_Lib> result = new ArrayList<Notas_Lib>();
		try {
			em.getTransaction().begin();
			TypedQuery<Notas_Lib> q = em.createNamedQuery("getNotasfromLibro", Notas_Lib.class).setParameter("idlibro", l.getId());
						result = q.getResultList();
			em.getTransaction().commit();
		} catch (IllegalStateException e) {
			e.printStackTrace();
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			e.printStackTrace();
			throw new RollbackException("Error al crear el recordatorio deshaciendo la transaccion");
		}
		return result;
    }
}
