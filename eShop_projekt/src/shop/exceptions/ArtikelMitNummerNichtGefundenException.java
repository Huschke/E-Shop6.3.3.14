package shop.exceptions;

import shop.valueobjects.Artikel;

public class ArtikelMitNummerNichtGefundenException extends Exception{
	public ArtikelMitNummerNichtGefundenException(String artikelID, String zusatzMsg) {
		super("Artikel mit der ID " + artikelID +
				" existiert nicht " + zusatzMsg);
		
	}
}
