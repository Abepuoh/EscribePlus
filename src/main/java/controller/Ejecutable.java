package controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import model.DAO.CapituloDAO;
import model.DAO.LibroDAO;
import model.DAO.PartesDAO;
import model.DAO.PersonajeDAO;
import model.DAO.UsuarioDAO;
import model.DataObject.Capitulo;
import model.DataObject.Libro;
import model.DataObject.Partes;
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
        CapituloDAO c = new CapituloDAO();


        
        Libro book1 = l.getById(3L);
        Partes parte = pt.getById(3L);
        Capitulo cap = c.getById(1L);
       // parte.addCapitulo(cap);
       // pt.crear(parte);
    	//l.borrar(10L); 
       // System.out.println(parte.getId()+" "+parte+" "+parte.getLibro().getTitle()+" "+parte.getCapitulos());
        //pt.editar(parte);
        
        //imprimir todos las capitulos
        /*for (Capitulo string : c.getAll()) {
            System.out.println(string);

		}*/
        //imprimir todos las partes
        for (Partes string : pt.getAll()) {
            System.out.println(string.getId()+" "+string+" "+string.getLibro().getTitle()+" "+string.getCapitulos());

		}
        //imprimir todos los libros
        /*for (Libro string : l.getAll()) {
            System.out.println(string);

		}*/
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
