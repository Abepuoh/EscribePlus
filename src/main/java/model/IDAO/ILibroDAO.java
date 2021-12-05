package model.IDAO;

import java.util.List;

import model.DataObject.Libro;
import model.DataObject.Notas_Lib;
import model.IDataObject.ILibro;

public interface ILibroDAO  extends IDAO<ILibro, Long>{
    /**
     * Metodo que devuelve un libro a partir de su nombre
     * @param name Nombre del libro
     * @return el libro con el nombre pasado por parametro
     * @throws Exception Si no existe el libro
     */
    public Libro getBookByName(String name) throws DAOException;
    /**
     * Metodo
     * @param author
     * @return
     */
    public List<Libro> getBooksByAuthor(String author) throws DAOException;
    /**
     * Metodo que devuelve una lista de notas.
     * @return la listas de libros
     * @throws Exception Si no existe
     */
    public List<Notas_Lib> getNotes() throws DAOException;
    /**
     * Metodo que devuelve una nota buscada por id.
     * @return Nota deseada
     * @throws Exception Si no existe la nota
     */
    public Notas_Lib getNote(Long id) throws DAOException;


    

}
