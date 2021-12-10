package model.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

import model.DataObject.Recordatorio;
import model.IDAO.IRecordatorioDAO;
import utils.ConnectionUtil;

public class RecordatorioDAO implements IRecordatorioDAO {

	public static EntityManager createEM() {
		EntityManagerFactory emf = ConnectionUtil.getInstace();
		return emf.createEntityManager();
	}

	EntityManager em = createEM();

	private final String getAll = "SELECT * FROM Recordatorio";

	@Override
	public void crear(Recordatorio aux) {
		try {
			em.getTransaction().begin();
			em.persist(aux);
			em.getTransaction().commit();
		} catch (EntityExistsException e) {
			throw new EntityExistsException("El recordatorio ya existe");
		} catch (IllegalStateException e) {
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			throw new RollbackException("Error al crear el recordatorio deshaciendo la transaccion");
		}
	}

	@Override
	public void editar(Recordatorio aux) {
		try {
			em.getTransaction().begin();
			em.persist(aux);
			em.getTransaction().commit();
		} catch (IllegalStateException e) {
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			throw new RollbackException("Error al crear el recordatorio deshaciendo la transaccion");
		}
	}

	@Override
	public void borrar(Long id) {
		Recordatorio delete = mostrarPorId(id);
		try {
			em.getTransaction().begin();
			em.remove(delete);
			em.getTransaction().commit();
		} catch (IllegalStateException e) {
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			throw new RollbackException("Error al crear el recordatorio deshaciendo la transaccion");
		}
	}

	@Override
	public List<Recordatorio> mostrarTodos() {
		List<Recordatorio> result = new ArrayList<>();
		try {
			em.getTransaction().begin();
			TypedQuery<Recordatorio> q = em.createQuery(getAll, Recordatorio.class);
			result = q.getResultList();
			em.getTransaction().commit();
		} catch (IllegalStateException e) {
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			throw new RollbackException("Error al crear el recordatorio deshaciendo la transaccion");
		}
		return result;
	}

	@Override
	public Recordatorio mostrarPorId(Long id) {
		Recordatorio result = null;
		try {
			em.getTransaction().begin();
			result = em.find(Recordatorio.class, id);
			em.getTransaction().commit();
		} catch (IllegalStateException e) {
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			throw new RollbackException("Error al crear el recordatorio deshaciendo la transaccion");
		}
		return result;
	}

}