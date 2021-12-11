package model.DataObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import model.IDataObject.ILibro;

@Entity
@Table(name = "Book")
@NamedQueries({
	@NamedQuery(name="getAllBooks", query = "SELECT p FROM Libro p"),
	@NamedQuery(name="getBookById", query = "SELECT p FROM Libro p "),
	@NamedQuery(name="getBookByName", query = "SELECT p FROM Libro p "),
	@NamedQuery(name="getAllBookNotes", query = "SELECT p.book_notes FROM Libro p"),
	@NamedQuery(name="getBookFromAuthor", query = "SELECT p FROM Libro p ")
})
public class Libro implements ILibro, Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	protected Long id;
	@Column(name = "title")
	protected String title;
	@Column(name = "year")
	protected int year;
	@Column(name = "genre")
	protected String genre;
	@Column(name = "description")
	protected String description;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="user_id")
	protected Usuario user;
	
	@OneToMany(mappedBy = "libro", cascade = { CascadeType.ALL }, orphanRemoval = true)
	protected List<Notas_Lib> book_notes = new ArrayList<Notas_Lib>();
	
	@OneToMany(mappedBy = "libro", cascade = { CascadeType.ALL }, orphanRemoval = true)
	protected List<Recordatorio> recordatorios = new ArrayList<Recordatorio>();
	
	@OneToMany(mappedBy = "libro", cascade = { CascadeType.ALL }, orphanRemoval = true)
	protected List<Partes> parts = new ArrayList<Partes>();
	
//	 @ManyToMany(mappedBy = "libroRef")
//	 private List<Personaje> personajes;

	public Libro() {
        this(-1L,"",-1,"","", new Usuario(), new ArrayList<Notas_Lib>(), new ArrayList<Recordatorio>(), new ArrayList<Partes>());
	}
	public Libro(String title, int year, String genre, String description, Usuario id_user) {
		this.id = -1L;
		this.title = title;
		this.year = year;
		this.genre = genre;
		this.description = description;
		this.user = id_user;
		this.book_notes = new ArrayList<Notas_Lib>();
		this.recordatorios =  new ArrayList<Recordatorio>();
		this.parts = new ArrayList<Partes>();
	}
	public Libro(String title, int year, String genre, String description, Usuario id_user, List<Notas_Lib> notas_libro,
			List<Partes> partes) {
		this.id = -1L;
		this.title = title;
		this.year = year;
		this.genre = genre;
		this.description = description;
		this.user = id_user;
		this.book_notes = notas_libro;
		this.recordatorios =  new ArrayList<Recordatorio>();
		this.parts = partes;
	}

	public Libro(Long id, String title, int year, String genre, String description) {
		this.id = id;
		this.title = title;
		this.year = year;
		this.genre = genre;
		this.description = description;
		this.user = new Usuario();
		this.book_notes = new ArrayList<Notas_Lib>();
		this.recordatorios =  new ArrayList<Recordatorio>();
		this.parts = new ArrayList<Partes>();
	}

	public Libro(Long id, String title, int year, String genre, String description, Usuario id_user,
			List<Notas_Lib> book_notes, List<Recordatorio> recordatorio, List<Partes> parts) {
		this.id = id;
		this.title = title;
		this.year = year;
		this.genre = genre;
		this.description = description;
		this.user = id_user;
		this.book_notes = book_notes;
		this.recordatorios = recordatorio;
		this.parts = parts;
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
	public Usuario getId_user() {
		return user;
	}
	public void setId_user(Usuario id_user) {
		this.user = id_user;
	}
	/**
	 * @return the notas_libro 
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
	public void setParts(List<Partes> parts) {
		this.parts = parts;
	}
	
	
	public List<Recordatorio> getRecordatorios() {
		return recordatorios;
	}
	public void setRecordatorios(List<Recordatorio> recordatorios) {
		this.recordatorios = recordatorios;
	}
	public void setBook_notes(List<Notas_Lib> book_notes) {
		this.book_notes = book_notes;
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
		return Objects.equals(title, other.title);
	}
	@Override
	public String toString() {
		return "Libro [id=" + id + ", title=" + title + ", year=" + year + ", genre=" + genre + ", description="
				+ description + ", id_user=" + user + ", book_notes=" + book_notes + ", recordatorios="
				+ recordatorios + ", parts=" + parts + "]";
	}

	
}
