package model.DataObject;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import model.IDataObject.INotas_cap;

public class Notas_Cap implements INotas_cap, Serializable{


	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	protected Long id;	
	@Column(name="text")
	protected String text;
	
	
	public Notas_Cap() {
		this(-1L,"");
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		return result;
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Notas_Cap [id=" + id + ", text=" + text + "]";
	}
	
	
}
