package controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import model.DAO.LibroDAO;
import model.DAO.PartesDAO;
import model.DAO.PersonajeDAO;
import model.DAO.UsuarioDAO;
import model.DataObject.Libro;
import model.DataObject.Usuario;
import utils.ConnectionUtil;

public class Ejecutable {

	public static void main(String[] args) {
		/*EntityManager em = createEM();
        em.getTransaction().begin();*/
        // LOS CAMBIOS SON EFECTIVOS
        UsuarioDAO a = new UsuarioDAO();
        LibroDAO l = new LibroDAO();
        PersonajeDAO p = new PersonajeDAO();
        PartesDAO pt = new PartesDAO();


        
        /*Libro book1 = l.getById(9L);
        book1.setYear(2000);*/
    	l.borrar(10L);    	
        for (Libro string : l.getAll()) {
            System.out.println(string);

		}
		// FINAL DE LOS CAMBIOS
		///////////////////////////////////////////////////////////////////////////
		//em.getTransaction().commit();
		

//	ConnectionUtil.transferH2ToMariaDB();

	}

	public static EntityManager createEM() {
		EntityManagerFactory emf = ConnectionUtil.getInstance();
		return emf.createEntityManager();
	}
}
