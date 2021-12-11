package controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import model.DAO.LibroDAO;
import model.DAO.PersonajeDAO;
import model.DAO.UsuarioDAO;
import model.DataObject.Libro;
import model.DataObject.Personaje;
import model.DataObject.Usuario;
import utils.ConnectionUtil;

public class Ejecutable {

	public static void main(String[] args) {
//		EntityManager em = createEM();
//		em.getTransaction().begin();

		UsuarioDAO u = new UsuarioDAO();
		LibroDAO l = new LibroDAO();
		PersonajeDAO p =new PersonajeDAO();
		
		Usuario aux = new Usuario("Juan", "jf@gmail.com", "1234", 654321987);
		u.crear(aux);
		
		Personaje p1 = new Personaje(1L,"Miguel",18,"por defecto","malo","string",null);
		p.crear(p1);
		
		Libro l1 = new Libro("Don Quijote", 2021, "Drama", "Loco medieval", aux);
		l1.addPersonaje(p1);
		l.crear(l1);
		

		
//		Recordatorio r1 = new Recordatorio("Chupala", l1);
//		RecordatorioDAO rec = new RecordatorioDAO();
//		rec.crear(r1);
//		System.out.println(up.getUserByName("Juan"));
//		System.out.println(up.getById(1L));
//		System.out.println(up.getUserByEmail("jf@gmail.com"));
//		System.out.println(up.getAll());
		
		System.out.println(l.getAll());
		
//		System.out.println(rec.getAll());

	/*	///////////////////////////////////////////////////////////////////////////
		// LOS CAMBIOS SON EFECTIVOS
		Usuario user1 = new Usuario("Miguel", "12345", "prueba@prueba.com");
		Personaje personaje = new Personaje(1L,"Miguel",18,"por defecto","malo","string",null);
		List<Personaje>person = new ArrayList<Personaje>();
		person.add(personaje);
		PersonajeDAO c = new PersonajeDAO();
		UsuarioDAO b = new UsuarioDAO();
		//List<Libro> books = new ArrayList<Libro>();
		LibroDAO a = new LibroDAO();
		//books.add(book1);
		//user1.setBooks(books);
		c.crear(personaje);
		Libro book1 = new Libro(1L, "Quijote",1995,"Un jambo que va to puesteo", "Fantasía", user1, person );
		b.crear(user1);
		a.crear(book1);
		//em.persist(user1);
		//em.getTransaction().commit();
	*/

	}

	public static EntityManager createEM() {
		EntityManagerFactory emf = ConnectionUtil.getInstance();
		return emf.createEntityManager();
	}
}
