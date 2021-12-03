package model.DataObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Partes")
public class Partes {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Autoincrement
	@Column(name="Id")
	private int id;
	@Column(name="nombre")
	private String nombre;
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_libro")
	private Libro libro;
	public Partes(int id, String nombre, Libro libro) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.libro = libro;
	}
	
	public Partes(String nombre, Libro libro) {
		super();
		this.nombre = nombre;
		this.libro = libro;
	}

	public Partes() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Libro getLibro() {
		return libro;
	}
	public void setLibro(Libro libro) {
		this.libro = libro;
	}

}
