package model.DataObject;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import model.IDataObject.INotas_lib;

@Entity
@Table (name = "Notas_Lib")
public class Notas_Lib implements INotas_lib, Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@Column(name = "texto")
	private String texto;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name = "id_Libro")
	private Libro libro;
	
	public Notas_Lib() {
		this(-1L,"Por defecto", new Libro());
	}

	public Notas_Lib(Long id, String texto, Libro libro) {
		this.id = id;
		this.texto = texto;
		this.libro = libro;
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
	public String getTexto() {
		return texto;
	}
	@Override
	public void setTexto(String texto) {
		this.texto = texto;
	}
	@Override
	public Libro getLibro() {
		return libro;
	}
	@Override
	public void setLibro(Libro libro) {
		this.libro = libro;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Notas_Lib other = (Notas_Lib) obj;
		return Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Notas_Lib [id=" + id + ", texto=" + texto + ", libro=" + libro + "]";
	}	

}
