package shop.persitence;

import java.io.IOException;

import shop.valueobjects.Artikel;
import shop.valueobjects.Kunde;
import shop.valueobjects.Mitarbeiter;
import shop.valueobjects.ShopEreignis;
/**
 * Realisierung eines Interfaces fuer den Zugriff auf auf ein externes Speichermedium.
 * Zum Speichern und Auslesen von Kundenkennwörtern
 * @author Adam Czogallik und Johannes Masur
 *
 */
public interface PersistenceManager {
	
	/**
	 * Methoden zum oeffnen und schliessen einer externen Datenquelle
	 * 
	 * @param datenquelle
	 * @throws IOException
	 */
	public void openForReading(String datenquelle) throws IOException;
	
	public void openForWriting(String datenquelle) throws IOException;
	
	public boolean close();
	
	public Artikel ladeArtikel() throws IOException;
	
	public void speichereArtikel(Artikel a) throws IOException;
	
	public Mitarbeiter ladeMitarbeiter() throws IOException;
	
	public void speichereMitarbeiter(Mitarbeiter m) throws IOException;
	
	public Kunde ladeKunde() throws IOException;
	
	public void speichereKunde(Kunde k) throws IOException;
	

}

