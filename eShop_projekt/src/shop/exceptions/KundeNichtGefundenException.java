package shop.exceptions;

import shop.valueobjects.Kunde;

public class KundeNichtGefundenException extends Exception{
	public KundeNichtGefundenException (Kunde k, String zusatzMsg) {
		super("Kunde " + k.getBenutzername() + " mit Namen " + k.getVorname() + " nicht gefunden " + zusatzMsg);
	}
}
