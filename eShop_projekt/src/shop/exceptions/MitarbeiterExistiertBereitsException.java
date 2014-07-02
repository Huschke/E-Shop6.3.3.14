package shop.exceptions;

import shop.valueobjects.Mitarbeiter;

/**
 * @exception Fehler auffangen - Person ist bereits vorhanden
 **/

public class MitarbeiterExistiertBereitsException extends Exception{
	
	public MitarbeiterExistiertBereitsException (Mitarbeiter m , String zusatzMsg) {
		
		super("Mitarbeiter mit Benutzername " + m.getBenutzername() + " und Name " + m.getNachname() 
				+ " existiert bereits" + zusatzMsg);
	}

}