package shop.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.io.IOException;









import shop.exceptions.ArtikelExistiertBereitsException;
import shop.exceptions.ArtikelMitNummerNichtGefundenException;
import shop.exceptions.ArtikelNichtGefundenException;
import shop.persitence.FilePersistenceManager;
import shop.persitence.PersistenceManager;
import shop.persitence.log.FileLogPersistenceManager;
import shop.persitence.log.LogPersistenceManager;
import shop.valueobjects.Artikel;


/**
 * @author Adam Czogallik, Johannes Masur
 * @version 0.5.
 */
//TODO: JAVADOC

public class ArtikelListe {
	
	//Verwaltung des Artikelbestandes in einer ArrayList
	private ArrayList<Artikel> artikelListe  = new ArrayList<Artikel>();
	//Persistenz-SChnittstelle, die fuer die Details der Datenhaltung zuständig ist
	private LogPersistenceManager pm = new FileLogPersistenceManager();
	
	
	//Dateneinlesen
	/**
	 * Methode zum einlesen von Artikeldaten aus einer Datei
	 * @param datei - Enthält den einzulesenden Artikelbestand
	 * @throws IOException
	 */
	public void einlesenDaten(String datei) throws IOException {
		
		pm.openForReading(datei);
		
		Artikel einArtikel;
				
		do {
			einArtikel = pm.ladeArtikel();
			if (einArtikel != null) {				
					try {
						einfuegenArtikel(einArtikel);
					} catch (ArtikelExistiertBereitsException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}				
			}
			//System.out.println(einArtikel);
		} while (einArtikel != null);
	
		pm.close();
		
	}
	
	
	
	//daten schreiben
	/**
	 * Methode zum schreiben von Artikeldaten in eine Datei
	 * @param datei datei, der der Artikelbestand übergeben wird
	 * @throws IOException
	 */
	public void schreibeDaten(String datei) throws IOException  {
		//Persistenzmanager wird geöffnet zum SChreiben der Daten
		pm.openForWriting(datei);
		
		if(!artikelListe.isEmpty()) {
			Iterator<Artikel> iter = artikelListe.iterator();
			while (iter.hasNext()) {
				Artikel a = iter.next();
				pm.speichereArtikel(a);				
			}
		}
		//Schließen der PM-Schnittstelle
		pm.close();
		
	}



	/**
	 * Methode zum einfügen neuere Artikel in die Liste
	 * @param einArtikel
	 */
	public void einfuegenArtikel(final Artikel einArtikel) throws ArtikelExistiertBereitsException { 
		if(!artikelListe.contains(einArtikel)) {
			artikelListe.add(einArtikel);
		} else {
			throw new ArtikelExistiertBereitsException(einArtikel, " - in 'einfuegenArtikel()'");
		}
				
	}
	
	
	
	/**
	 * Methode zum Loeschen eines Artikel aus der Liste. 
	 * @param einArtikel
	 * @return boolean
	 */
	public boolean loeschenArtikel(final Artikel einArtikel) throws ArtikelNichtGefundenException { 
		if(artikelListe.contains(einArtikel)) {
			artikelListe.remove(einArtikel);
			return true;
		} else {
			throw new ArtikelNichtGefundenException(einArtikel, " - in 'loeschenArtikel()'");
		}
		
	}
	
	/**
	 * Methode die einen Artikel zuück gibt, nach dem mit der ID gesucht werden kann
	 * @param artikelID
	 * @throws ArtikelMitNummerNichtGefundenException   
	 */
	public Artikel getArtikel(String artikelID) throws ArtikelNichtGefundenException, ArtikelMitNummerNichtGefundenException {
		Iterator<Artikel> iter = artikelListe.iterator();
		while (iter.hasNext()) {
			Artikel artikel = iter.next();
			if(artikel.getArtikelID() == artikelID) {
				return artikel;
			}
		}
		throw new ArtikelMitNummerNichtGefundenException(artikelID, " - ind 'getArtikel'!");
	}
	
	
	
	/**
	 * Methode zum Suchen von artikeln anhand von einem Namen (String)
	 * @param name Name des Artikel
	 * @return ergebnis Liste von Ergebnissen
	 */
	public ArrayList<Artikel> sucheArtikel(final String name) {
		final ArrayList<Artikel> ergebnis = new ArrayList<Artikel>();
		for (final Artikel artikel : artikelListe) {
			if (artikel.getArtName().equals(name)) {				
			    ergebnis.add(artikel);
			}
		}
		return ergebnis;
	}
	
	
	/**
	 * Methode zum Suchen nach Artikeln anhand der Artikelnummer (Int)
	 * @param artikelNummer
	 * @return ergebnis
	 */
	public Vector<Artikel> sucheArtikel(final int artikelNummer) {
		final Vector<Artikel> ergebnis = new Vector<Artikel>();
		for (final Artikel artikel : artikelListe) {
			if (artikel.getArtikelID().equals(artikelNummer)) {
				ergebnis.add(artikel);
			}
		}				
		return ergebnis;
	}
		
	
	/**
	 * Methode zum rueckgeben des unsortierten Bestandes
	 * @return artikelListe
	 */
	public ArrayList<Artikel> getArtikelBestand() {
		return artikelListe;
	}
	
	/**
	 * Methode zum Zurueckgeben des nach der ID sortierten Bestandes
	 * @return
	 */
	public ArrayList<Artikel> getArtikelBestandIDSortiert() {
		final ArrayList<Artikel> ergebnis = new ArrayList<Artikel>();
				
		return null;
	}
	
	public ArrayList<Artikel> getArtikelBestandNameSortiert() {
		//TODO
		return null;
	}
	
	
	/**
	 * TODO
	 * TODO
	 * TODO
	 * TODO
	 * @return
	 */
	public boolean nachIDSortieren() {
		
		try{
		Collections.sort(artikelListe, new Comparator<Object>() {
			public int compare(Object t1, Object t2) {
				Artikel artikel1 = (Artikel) t1;
				Artikel artikel2 = (Artikel) t2;
				return (artikel1.getArtikelID()).compareTo((artikel2.getArtikelID()));
			}
		});
		return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	
	/**
	 *TODO
	 * @return
	 */
	public boolean nachNameSortieren() {
		
		try{
		Collections.sort(artikelListe, new Comparator<Object>() {
			public int compare(Object t1, Object t2) {
				Artikel artikel1 = (Artikel) t1;
				Artikel artikel2 = (Artikel) t2;
				return (artikel1.getArtName()).compareTo((artikel2.getArtName()));
			}
		});
		return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
}
	
	
	
	
