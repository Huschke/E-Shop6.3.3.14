package shop.persitence.log;

import java.io.IOException;

import shop.valueobjects.Artikel;
import shop.valueobjects.Ereignis;
import shop.valueobjects.Kunde;
import shop.valueobjects.Mitarbeiter;
import shop.valueobjects.ShopEreignis;

//TODO
public interface LogPersistenceManager {
	
	public void openForReading(String datenquelle) throws IOException;
	
	public void openForWriting(String datenquelle) throws IOException;
	
	public boolean close();

	
	/**
	 * Interfaceschnitschtelle für Valueobjectklasse ShopEreignis
	 * 
	 * @param e
	 * @return
	 * @throws IOException
	 */
	public boolean speichereEreignis(ShopEreignis e) throws IOException;
	
	public Ereignis ladeEreignis(ShopEreignis e) throws IOException;
	
	public boolean speichereArtikel(Artikel a) throws IOException;
	
	public Artikel ladeArtikel() throws IOException;
	
	public boolean speichereKunde(Kunde k) throws IOException;
	
	public Kunde ladeKunde() throws IOException;
	
	public boolean speichereMitarbeiter (Mitarbeiter m) throws IOException;

	public Mitarbeiter ladeMitarbeiter() throws IOException;
	
	


}
