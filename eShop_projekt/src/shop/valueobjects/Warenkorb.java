package shop.valueobjects;

import java.util.ArrayList;
import java.util.List;
//import java.util.Vector;




public class  Warenkorb {
	
	
	public final List<Artikel> neuerWarenkorb = new ArrayList<Artikel>(); 
	
	
	public void hinzufuegenArt (final Artikel artikel) {
		neuerWarenkorb.add(artikel);
	
	}
	
	public List<Artikel> gibWarenAus (final String name) {
		final List<Artikel> inhalt = new ArrayList<Artikel>();
		
		for (final Artikel artikel: neuerWarenkorb) {
			if (artikel.getArtName().equals(name)) {
				inhalt.add(artikel);
			}
		}
		return inhalt;
	}
	
}
	

