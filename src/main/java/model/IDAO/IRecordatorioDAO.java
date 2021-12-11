package model.IDAO;

import model.DataObject.Recordatorio;

public interface IRecordatorioDAO extends IDAO<Recordatorio, Long>{
	/**
	 * Metodo que busca un recordatorios por su libro
	 * 
	 * @param name Nombre del libro
	 * @return Recordatorio encontrado
	 */
	public Recordatorio getRecordatorioByBook(String bookName);
}