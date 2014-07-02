package shop.exceptions;

import shop.valueobjects.Kunde;

public class KundeExistiertBereitsException extends Exception{
		
		public KundeExistiertBereitsException (Kunde k , String zusatzMsg) {
			
			super("Kunde mit Benutzername " + k.getBenutzername() + " und Name " + k.getNachname() 
					+ " existiert bereits" + zusatzMsg);
		}

}

