package model.IDAO;

import java.util.List;

import model.DataObject.Libro;
import model.DataObject.Notas_Lib;
import model.DataObject.Usuario;

public interface ILibroDAO  extends IDAO<Libro, Long>{
    /**
     * Metodo que devuelve un libro a partir de su nombre
     * @param name Nombre del libro
     * @return el libro con el nombre pasado por parametro
     * @throws Exception Si no existe el libro
     */
    public Libro getBookByName(String name);
    /**
     * Metodo que devuelve un libro introduciendo el autor del mismo
     * @param author del libro
     * @return
     */
    public List<Libro> getBooksByAuthor(Usuario author);
    /**
     * Metodo que devuelve una lista de notas.
     * @return la listas de libros
     * @throws Exception Si no existe
     */
    public List<Notas_Lib> getNotesOfBook(String title);
}
