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
        // LOS CAMBIOS SON EFECTIVOS
        UsuarioDAO a = new UsuarioDAO();
        /**LibroDAO l = new LibroDAO();
        PersonajeDAO p = new PersonajeDAO();
        PartesDAO pt = new PartesDAO();*/

       // Usuario user1 = new Usuario("Jesus","test@test.com","1234");
        /**
         * List<Personaje> characters = new ArrayList<Personaje>();
        Libro book1 = new Libro("Quijote",1995,"Fantasía", "Un jambo que va to puesteo", user1, characters);
        List<Libro> books = new ArrayList<Libro>();
        Personaje character1 = new Personaje("Sancho",18,"Señor con panza","Malo" ,"Notas", books);
        characters.add(character1);

        book1.setPersonajes(characters);
        books.add(book1);
        user1.setBooks(books);
			*/
       //a.crear(user1);
        System.out.println(a.getAll());
		// FINAL DE LOS CAMBIOS
		///////////////////////////////////////////////////////////////////////////
		em.getTransaction().commit();
		

//	ConnectionUtil.transferH2ToMariaDB();

	}

	public static EntityManager createEM() {
		EntityManagerFactory emf = ConnectionUtil.getInstance();
		return emf.createEntityManager();
	}
}
