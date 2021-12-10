package controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import model.DataObject.Usuario;
import utils.ConnectionUtil;

public class Ejecutable {
	
	public static void main(String[] args) {
		EntityManager em = createEM();

		em.getTransaction().begin();
		Usuario aux = new Usuario("Juan", "jf@gmail.com", "1234", 654321987);
		em.persist(aux);
		em.getTransaction().commit();
		//us.crear(u);
	}
	
	public static EntityManager createEM () {
		EntityManagerFactory emf = ConnectionUtil.getInstance();
		return emf.createEntityManager();
	}
}
