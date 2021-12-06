package model.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.DataObject.Usuario;
import model.IDAO.DAOException;
import model.IDAO.IUsuarioDAO;

public class UsuarioDAO implements IUsuarioDAO{
	  
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("MySQL");
    EntityManager em = emf.createEntityManager();
    
    //Queries
    private final String getAll="SELECT * FROM User";
    private String getById="SELECT p FROM User p WHERE p.id = :idUsuario";
    private String getByName="SELECT p FROM User p WHERE p.nombre = :nombreUsuario";
    private String getByEmail="SELECT p FROM User p WHERE p.correo=:emailUsuario";

	@Override
	public void crear(Usuario aux) {
		em.getTransaction().begin();
        em.persist(aux);
        em.getTransaction().commit();
		
	}

	@Override
	public void editar(Usuario aux) {
		em.getTransaction().begin();
        em.persist(aux);
        em.getTransaction().commit();
		
	}

	@Override
	public void borrar(Long id) {
		Usuario delete = mostrarPorId(id);
		em.getTransaction().begin();
		em.remove(delete);
		em.getTransaction().commit();
	}

	@Override
	public List<Usuario> mostrarTodos() {
		List<Usuario> result = new ArrayList<>();
		em.getTransaction().begin();
		TypedQuery<Usuario> q = em.createQuery(getAll, Usuario.class);
		result = q.getResultList();
		em.getTransaction().commit();
		return result;
	}

	@Override
	public Usuario mostrarPorId(Long id) {
		Usuario result = null;
		em.getTransaction().begin();
		TypedQuery<Usuario> q = em.createQuery(getById, Usuario.class).setParameter("idUsuario", id);
		result = q.getResultList().get(0);
		em.getTransaction().commit();
		return result;
	}

	@Override
	public Usuario getUserByName(String name) throws DAOException {
		Usuario result = null;
		em.getTransaction().begin();
		TypedQuery<Usuario> q = em.createQuery(getByName, Usuario.class).setParameter("nombreUsuario", name);
		result = q.getResultList().get(0);
		em.getTransaction().commit();
		return result;
	}

	@Override
	public Usuario getUserByEmail(String email) throws DAOException {
		Usuario result = null;
		em.getTransaction().begin();
		TypedQuery<Usuario> q = em.createQuery(getByEmail, Usuario.class).setParameter("emailUsuario", email);
		result = q.getResultList().get(0);
		em.getTransaction().commit();
		return result;
	}

	@Override
	public boolean logIn(String nombre, String contraseña) throws DAOException {
		// TODO Auto-generated method stub
		return false;
	}

}
