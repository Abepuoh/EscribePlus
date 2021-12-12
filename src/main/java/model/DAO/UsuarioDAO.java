package model.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.RollbackException;
import javax.persistence.TypedQuery;

import model.DataObject.Usuario;
import model.IDAO.IUsuarioDAO;
import utils.ConnectionUtil;

public class UsuarioDAO implements IUsuarioDAO {

	public static EntityManager createEM() {
		EntityManagerFactory emf = ConnectionUtil.getInstance();
		return emf.createEntityManager();
	}

	EntityManager em = createEM();


	@Override
	public void crear(Usuario aux) {
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
	public void editar(Usuario aux) { 
		try {
			em.getTransaction().begin();
			em.merge(aux);
			em.getTransaction().commit();
		} catch (IllegalStateException e) {
                    throw new IllegalStateException("Ya hay una transaccion activa");
                        
		} catch (RollbackException e) {
			throw new RollbackException("Error al editar el usuario deshaciendo la transaccion");
		}
	}

	@Override
	public void borrar(Long id) {
		Usuario delete = getById(id);
		try {
			em.getTransaction().begin();
			em.remove(delete);
			em.getTransaction().commit();
		} catch (IllegalStateException e) {
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			throw new RollbackException("Error al editar el usuario deshaciendo la transaccion");
		}
	}

	@Override
	public List<Usuario> getAll() {
		List<Usuario> result = new ArrayList<>();
		try {
			em.getTransaction().begin();
			TypedQuery<Usuario> q = em.createNamedQuery("getAllUsers", Usuario.class);
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
	public Usuario getById(Long id) {
		Usuario result = null;
		try {
			em.getTransaction().begin();
			TypedQuery<Usuario> q = em.createNamedQuery("getUserById", Usuario.class).setParameter("id", id);
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
	public Usuario getUserByName(String name) {
		Usuario result = null;
		try {
			em.getTransaction().begin();
			TypedQuery<Usuario> q = em.createNamedQuery("getUserByName", Usuario.class).setParameter("name", name);
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
	public Usuario getUserByEmail(String email) {
		Usuario result = null;
		try {
			em.getTransaction().begin();
			TypedQuery<Usuario> q = em.createNamedQuery("getUserByEmail", Usuario.class).setParameter("email", email);
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
	public Usuario logIn(String email, String contraseña) {
		Usuario result = new Usuario();
		try {
			em.getTransaction().begin();
			TypedQuery<Usuario> q = em.createNamedQuery("getUserByMailAndPass", Usuario.class);
			q.setParameter("email", email);
			q.setParameter("password", contraseña);
			result = q.getResultList().get(0);
			em.getTransaction().commit();
		} catch (IllegalStateException e) {
			throw new IllegalStateException("Ya hay una transaccion activa");
		} catch (RollbackException e) {
			throw new RollbackException("Error al crear el usuario deshaciendo la transaccion");
		}
		return result;
	}

}
