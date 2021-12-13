package model.DataObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import model.IDataObject.IPersonaje;

@Entity
@Table(name = "Personaje")
@NamedQueries({ @NamedQuery(name = "getAllPersonajes", query = "SELECT p FROM Personaje p"),
		@NamedQuery(name = "getPersonajeByName", query = "SELECT p FROM Personaje p WHERE p.nombre = :nombrepersonaje"),
		@NamedQuery(name = "getPersonajeFromBook", query = "SELECT p FROM Personaje p "),
		@NamedQuery(name = "getPersonajeFromUser", query = "SELECT p FROM Personaje p "),
		//@NamedQuery(name = "removePersonajeFromBook", query = "DELETE FROM Personaje p WHERE p.libroRef.id =: libro ")
		})
public class Personaje implements IPersonaje, Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	@Column(name = "nombre")
	protected String nombre;
	@Column(name = "edad")
	protected int edad;
	@Column(name = "descripcion")
	protected String descripcion;
	@Column(name = "alineamiento")
	protected String alineamiento;
	@Column(name = "foto")
	protected String foto;
	@ManyToMany(mappedBy = "personajes")
	private List<Libro> libroRef;

	public Personaje() {
		this(-1L, "Por defecto", -1, "Por defecto", "Por defecto", "Por defecto", new ArrayList<Libro>());
	}
	
	public Personaje(String nombre, String descripcion, String alineamiento) {
		this(-1L, nombre, -1, descripcion, alineamiento, "Por defecto", new ArrayList<Libro>());
	} 
	public Personaje(Long id, String nombre, int edad, String descripcion, String alineamiento, String foto,
			List<Libro> libroRef) {

		this.id = id;
		this.nombre = nombre;
		this.edad = edad;
		this.descripcion = descripcion;
		this.alineamiento = alineamiento;
		this.foto = foto;
		//this.libroRef = libroRef;
	}

	public Personaje(String nombre, int edad, String descripcion, String alineamiento, String foto,
			List<Libro> libroRef) {
		this(-1L,nombre,edad,descripcion,alineamiento,foto,libroRef);
	}
	/**
	 * Añadir libro a la lista de libros del personaje
	 */
	public void addLibro(Libro libro) {
		if (libroRef == null) {
			libroRef = new ArrayList<Libro>();
		}
		libroRef.add(libro);
	}

	@Override
	public String getAlineamiento() {
		return alineamiento;
	}

	@Override
	public void setAlineamiento(String alineamiento) {
		this.alineamiento = alineamiento;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String getNombre() {
		return nombre;
	}

	@Override
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public int getEdad() {
		return edad;
	}

	@Override
	public void setEdad(int edad) {
		this.edad = edad;
	}

	@Override
	public String getDescripcion() {
		return descripcion;
	}

	@Override
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override 
	public String getFoto() {
		return foto;
	}

	@Override
	public void setFoto(String foto) {
		this.foto = foto;
	}

	@Override
	public List<Libro> getLibroRef() {
		//return libroRef;
		return null;
	}

	@Override
	public void setLibroRef(List<Libro> libroRef) {
		//this.libroRef = libroRef;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Personaje other = (Personaje) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return nombre;
	}

}
