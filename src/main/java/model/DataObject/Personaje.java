package model.DataObject;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import model.IDataObject.IPersonaje;

@Entity
@Table (name = "Personaje")
public class Personaje implements IPersonaje, Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long id;
	@Column(name = "nombre")
	private String nombre;
	@Column(name = "edad")
	private int edad;
	@Column(name = "descripcion")
	private String descripcion;
	@Column(name = "alineamiento")
	private String alineamiento;
	@Column(name = "foto")
	private String foto;
	@ManyToMany
	private Libro libroRef;
	
	public Personaje() {
		this(-1L,"Por defecto",-1,"Por defecto","Por defecto","Por defecto", new Libro());
	}
	
	public Personaje(Long id, String nombre, int edad, String descripcion, String alineamiento, String foto, Libro libroRef) {
		
		this.id = id;
		this.nombre = nombre;
		this.edad = edad;
		this.descripcion = descripcion;
		this.alineamiento = alineamiento;
		this.foto = foto;
		this.libroRef = libroRef;
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
	public Libro getLibroRef() {
		return libroRef;
	}
	@Override
	public void setLibroRef(Libro libroRef) {
		this.libroRef = libroRef;
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
		return "Personaje [id=" + id + ", nombre=" + nombre + ", edad=" + edad + ", descripcion=" + descripcion
				+ ", alineamiento=" + alineamiento + ", foto=" + foto + ", libroRef=" + libroRef + "]";
	}	
	
}
