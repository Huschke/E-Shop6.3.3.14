package shop.exceptions;

import shop.valueobjects.Artikel;

public class ArtikelNichtGefundenException extends Exception{
	public ArtikelNichtGefundenException(Artikel einArtikel, String zusatzMsg) {
		super("Artikel mit der Bezeichung " + einArtikel.getArtName() + " und der ID " + einArtikel.getArtikelID() +
				" existiert bereits " + zusatzMsg);
		
	}

}
