package model.DataObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import model.IDataObject.ILibro;


@Entity
@Table(name="Book")

public class Libro implements ILibro, Serializable  {

	private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    protected Long id;
    @Column(name="tittle")
    protected String titulo;
    @Column(name="year")
    protected int año;
    @Column(name="genre")
    protected String genero;
    @Column(name="description")
    protected String descripcion;
    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
    

    public Libro() {
        this(-1L,"Por defecto",-1,"Por defecto","Por defecto");
    }
    
    public Libro(Long id, String title, int year, String genre, String description, int id_user, List<Notas_Lib> notas_libro, List<Partes> partes) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.description = description;
        this.id_user = id_user;
        this.book_notes = notas_libro;
        this.parts = partes;
    }
    
    public Libro(String title, int year, String genre, String description, int id_user, List<Notas_Lib> notas_libro, List<Partes> partes) {
        this.id = -1L;
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.description = description;
        this.id_user = id_user;
        this.book_notes = notas_libro;
        this.parts = partes;
    }
    
    public Libro(Long id, String title, int year, String genre, String description) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.genre = genre;
        this.description = description;
    }
 
    
    
    
    @Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Libro other = (Libro) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.getId());
        return hash;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the year
     */
    public int getYear() {
        return year;
    }

    /**
     * @param year the year to set
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * @return the genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * @param genre the genre to set
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the id_user
     */
    public int getId_user() {
        return id_user;
    }

    /**
     * @param id_user the id_user to set
     */
    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    /**
     * @return the notas_libro
  
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Libro other = (Libro) obj;
		return Objects.equals(id, other.id) && Objects.equals(titulo, other.titulo);
	}

	/**
     * @return the usuario
     */
    public List<Notas_Lib> getBook_notes() {
        return book_notes;
    }

    /**
     * @param notas_libro the notas_libro to set
     */
    public void setNotas_libro(List<Notas_Lib> notas_libro) {
        this.book_notes = notas_libro;
    }

    /**
     * @return the partes
     */
    public List<Partes> getParts() {
        return parts;
    }

    /**
     * @param partes the partes to set
     */
    public void setPartes(List<Partes> parts) {
        this.parts = parts;
    }







}		
