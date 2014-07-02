package shop.exceptions;

import shop.valueobjects.Mitarbeiter;

public class MitarbeiterNichtGefundenException extends Exception{
	
	public MitarbeiterNichtGefundenException (Mitarbeiter m, String zusatzMsg) {
		super("Mitarbeiter mit dem Namen " + m.getVorname() + " und dem Benutzernamen " + m.getBenutzername() + 
				" wurde nicht gefunden " + zusatzMsg);
	}

}
