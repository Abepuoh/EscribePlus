package controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import model.DAO.UsuarioDAO;
import model.DataObject.Usuario;
import utils.ConnectionUtil;

public class Ejecutable {
	
	public static void main(String[] args) {
		EntityManager em = createEM();

		em.getTransaction().begin();
		Usuario aux = new Usuario("Juan", "jf@gmail.com", "1234", 654321987);
		em.persist(aux);
		em.getTransaction().commit();
		
		UsuarioDAO up = new UsuarioDAO();
		System.out.println(up.getUserByName("Juan"));
	}
	
	public static EntityManager createEM () {
		EntityManagerFactory emf = ConnectionUtil.getInstance();
		return emf.createEntityManager();
	}
}
