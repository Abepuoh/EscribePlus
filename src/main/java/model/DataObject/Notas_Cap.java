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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import model.IDataObject.INotas_cap;

@Entity
@Table(name="Notas_Cap")
@NamedQueries({
	@NamedQuery(name="getAllNotas_Cap", query = "SELECT p FROM Notas_Cap p"),
        @NamedQuery(name="getNotas_CapFromCap", query = "SELECT p FROM Notas_Cap p WHERE p.capitulo.id=:idcapitulo")
})
public class Notas_Cap implements INotas_cap, Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	protected Long id;	
	@Column(name="text")
	protected String text;

	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="capitulo_id")
	protected Capitulo capitulo;
	
	public Notas_Cap() {
		this(-1L,"Por defecto");
	}
	public Notas_Cap(String text) {
		this(-1L,text);
	}
	public Notas_Cap(Long id, String text) {
		this.id = id;
		this.text = text;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Notas_Cap other = (Notas_Cap) obj;
		return Objects.equals(id, other.id) && Objects.equals(text, other.text);
	}
	
	@Override
	public String toString() {
		return "Notas_Cap [id=" + id + ", text=" + text + "]";
	}
}