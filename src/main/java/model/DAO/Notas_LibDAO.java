
package model.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

import model.DataObject.Notas_Lib;
import model.IDAO.INotas_libDAO;
import model.IDataObject.INotas_lib;
import utils.ConnectionUtil;

public class Notas_LibDAO implements INotas_libDAO {

	public static EntityManager createEM() {
		EntityManagerFactory emf = ConnectionUtil.getInstace();
		return emf.createEntityManager();
	}

	EntityManager em = createEM();

	// Queries
	private final String getAll = "Select * from Notas_Lib";

	@Override
	public void crear(Notas_Lib aux) {
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
	public void editar(Notas_Lib aux) {
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
		INotas_lib delete = mostrarPorId(id);
		try {
			em.getTransaction().begin();
			em.persist(delete);
			em.getTransaction().commit();
		} catch (IllegalStateException e) {
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			throw new RollbackException("Error al crear el usuario deshaciendo la transaccion");
		}
	}

	@Override
	public List<Notas_Lib> mostrarTodos() {
		List<Notas_Lib> result = new ArrayList<>();
		try {
			em.getTransaction().begin();
			TypedQuery<Notas_Lib> q = em.createQuery(getAll, Notas_Lib.class);
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
	public Notas_Lib mostrarPorId(Long id) {
		Notas_Lib result = new Notas_Lib();
		try {
			em.getTransaction().begin();
			result = em.find(Notas_Lib.class, id);
			em.getTransaction().commit();
			return result;
		} catch (IllegalStateException e) {
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			throw new RollbackException("Error al crear el usuario deshaciendo la transaccion");
		}
	}

}
