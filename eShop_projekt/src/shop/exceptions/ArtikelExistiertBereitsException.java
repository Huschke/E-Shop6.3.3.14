package shop.exceptions;

import shop.valueobjects.Artikel;

public class ArtikelExistiertBereitsException extends Exception {
	
	public ArtikelExistiertBereitsException(Artikel a , String zusatzMsg) {
			super("Artikel " + a.getArtName() + " und ID " + a.getArtikelID() 
				+ " existiert bereits" + zusatzMsg);
	}
}
