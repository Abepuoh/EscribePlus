package controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import model.DAO.LibroDAO;
import model.DAO.PartesDAO;
import model.DAO.PersonajeDAO;
import model.DAO.UsuarioDAO;
import model.DataObject.Libro;
import model.DataObject.Partes;
import model.DataObject.Personaje;
import model.DataObject.Usuario;
import utils.ConnectionUtil;

public class Ejecutable {

	public static void main(String[] args) {
		EntityManager em = createEM();
		em.getTransaction().begin();
		// LOS CAMBIOS SON EFECTIVOS
		UsuarioDAO a = new UsuarioDAO();
		LibroDAO l = new LibroDAO();
		PersonajeDAO p = new PersonajeDAO();
		PartesDAO pt = new PartesDAO();
		
		/*Usuario user1 = new Usuario("Antonio", "1234", "prueba@prueba.com");
		List<Personaje> characters = new ArrayList<Personaje>();
		Libro book1 = new Libro("Quijote",1995,"Un jambo que va to puesteo", "Fantasía", user1, null);
		List<Libro> books = new ArrayList<Libro>();
		Personaje character1 = new Personaje("Sancho",18,"Señor con panza","Malo" ,"Notas", books);
		characters.add(character1);

		book1.setPersonajes(characters);
		books.add(book1);
		user1.setBooks(books);*/

		//p.crear(character1);
		//a.crear(user1);
		//l.crear(book1);

		// em.persist(user1);

		//Usuario aux = em.find(Usuario.class, 1L);
		
		//Partes x = pt.getById(1L); 
		Libro q = l.getBookByName("Quijote");
		//q.addParte(new Partes("Primera parte", q));
		//l.editar(q);
		System.out.println(q.getParts());
		/*System.out.println(user1);
		System.out.println(aux);
		System.out.println(book1);*/

		// FINAL DE LOS CAMBIOS
		///////////////////////////////////////////////////////////////////////////
		em.getTransaction().commit();


	}

	public static EntityManager createEM() {
		EntityManagerFactory emf = ConnectionUtil.getInstance();
		return emf.createEntityManager();
	}
}
