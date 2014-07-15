package shop.domain;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;




















import shop.exceptions.ArtikelExistiertBereitsException;
import shop.exceptions.ArtikelMitNummerNichtGefundenException;
import shop.exceptions.ArtikelNichtGefundenException;
import shop.exceptions.KundeExistiertBereitsException;
import shop.exceptions.KundeNichtGefundenException;
import shop.exceptions.MitarbeiterExistiertBereitsException;
import shop.exceptions.MitarbeiterNichtGefundenException;
import shop.ui.cui.ClientCUI;
import shop.ui.cui.KundenClientCUI;
import shop.valueobjects.Artikel;
import shop.valueobjects.Kunde;
import shop.valueobjects.Mitarbeiter;
import shop.valueobjects.Person;

public class ShopManager {

	
	SimpleDateFormat df = new SimpleDateFormat( "dd.MM.yyyy HH:mm:ss" );
	private String date = df.format(new Date());	
	
	
	private String kundendatei = "SHOP_Kunden.txt";
	private String mitarbeiterdatei = "SHOP_Mitarbeiter.txt";
	private String artikeldatei = "SHOP_Artikel.txt";
	
	private ArtikelListe listeArtikel;
	private KundenManager kundenMgmt;
	private MitarbeiterManager mitarbeiterMgmt;
	
	public ClientCUI cui = new ClientCUI();
	
	
	
	public ShopManager() throws IOException {
		 
		
		
		listeArtikel = new ArtikelListe();
		listeArtikel.einlesenDaten(artikeldatei);
		
		kundenMgmt = new KundenManager();
		kundenMgmt.liesDaten(kundendatei);
		
		mitarbeiterMgmt = new MitarbeiterManager();
		mitarbeiterMgmt.liesDaten(mitarbeiterdatei);
		
		//daten auslesen aus ereigis TODO
		
	}
	
	//
	//Artikel-Methoden
	//
	
	
	
	/**
	 * Methode zum Einfuegen eines Artikels in die Bestandsliste. Zusätzlich wird ein neues Ereigniss
	 * in die Datei geschrieben
	 * @param mitarbeiter
	 * @param artikelId
	 * @param artikelName
	 * @param preis
	 * @param bestand
	 * @param verfuegbar
	 * @throws ArtikelExistiertBereitsException
	 */
	public void fuegeEinenArtikelEin(Mitarbeiter mitarbeiter, String artikelId, String artikelName,
			float preis, int bestand, boolean verfuegbar) throws ArtikelExistiertBereitsException {
		Artikel artikel = new Artikel(artikelId, artikelName, preis, bestand, verfuegbar);
		listeArtikel.einfuegenArtikel(artikel);
		
		//TODO: EREIGNISSE
	}
	
	
	/**
	 * 
	 */
	public void fuegeMassengutEin() {
		//TODO Massenartikel
	}
	
	public void artikelBestandVeraendern (Mitarbeiter mitarbeiter, String artikelId, String artikelName, float preis, int bestand, boolean verfuegbar) {
		
	}
	
	/**
	 * Methode zum Rueckegeben eines Artikel-Obektes
	 * @param artikelID
	 * @return Artikel-Objekt
	 * @throws ArtikelNichtGefundenException
	 * @throws ArtikelMitNummerNichtGefundenException
	 */
	public Artikel gibEinenArtikel(String artikelID) throws ArtikelNichtGefundenException, ArtikelMitNummerNichtGefundenException {
		
		return listeArtikel.getArtikel(artikelID);
	}
	
	/**
	 * Gibt den gesamten Artikelbestand zurueck
	 * @return Artikelbestand
	 */
	public ArrayList<Artikel> gibAlleArtikel() {
		return listeArtikel.getArtikelBestand();
	}
	
	/**
	 * Gibt eine Liste der Artikel zurueck mit namen
	 * @param artikelID
	 * @return Liste der gesuchten Artikel
	 */
	public ArrayList<Artikel> sucheArtikel(String artikelID) {
		return listeArtikel.sucheArtikel(artikelID);
	}
	
	/**
	 * Shreibst den Artikelbestand in eine externe Datei	
	 * @throws IOException
	 */
	public void schreibeArtikel() throws IOException {
		
		listeArtikel.schreibeDaten(artikeldatei);
		
	}
	
	public void artikelIDSortieren() throws IOException {
		
		boolean ok = listeArtikel.nachIDSortieren();
		
		if(ok){
			schreibeArtikel();	
		}
		else{
			System.out.println("Error beim Sortieren");
		}
	}
	
	
	//
	//Mitarbeiter-Methoden
	//
	
	/**
	 * Speichert die Liste der Mitarbieter in eine externe Datei
	 * @throws IOException
	 */
	public void schreibeMitarbeiter() throws IOException {
		
		mitarbeiterMgmt.schreibeDaten(mitarbeiterdatei);
				
	}
	
	
	/**
	 * Methode, die die aktuelle Liste aller Mitarbeiter zurueckgibt.
	 * @return Vector mit allen Mitarbeitern
	 */
	public Vector<Mitarbeiter> gibAlleMitarbeiter() {
		return mitarbeiterMgmt.getMitarbeiterliste();
	}
	
	
	/**
	 * Loescht einen Mitarbeiter aus der Liste
	 * @param m
	 * @throws MitarbeiterNichtGefundenException
	 */
	public void mitarbeiterLoeschen (Mitarbeiter m) throws MitarbeiterNichtGefundenException {
		mitarbeiterMgmt.loescheMitarbeiter(m);
	}
	
	
	/**
	 * Erstellt eine neue Mitarbieter Instanz und uebergiebt sie dem Mitarbeiter-Manager
	 * @param benutzername
	 * @param vorname
	 * @param nachname
	 * @param passwort
	 * @throws MitarbeiterExistiertBereitsException
	 */
	public void fuegeMitarbeiterHinzu(String benutzername, String vorname, String nachname, String passwort) throws MitarbeiterExistiertBereitsException {
		Mitarbeiter m = new Mitarbeiter(benutzername, vorname, nachname, passwort);
		mitarbeiterMgmt.einfuegenMitarbeiter(m);

	}
	
	//
	//Kunden-Methoden
	//
	
	
	public Vector<Kunde> sucheKunde (String benutzername) {
		return kundenMgmt.sucheKunde(benutzername);
	}
	
	public void schreibeKunde() throws IOException {
		  
		kundenMgmt.schreibeDaten(kundendatei);	
	}
	
	public List<Kunde> gibAlleKunden() {
		return kundenMgmt.getKundenliste();
	}
	
	public void loescheKunde(Kunde k) throws KundeNichtGefundenException {
		kundenMgmt.loescheKunde(k);
	}
	
	public void fuegeKundeHinzu(String benutzername, String vorname, String nachname, String mail, String passwort, String strasseNummer, int plz, String wohnort, float umsatz) throws KundeExistiertBereitsException {
		Kunde k = new Kunde(benutzername, vorname, nachname, mail, passwort, strasseNummer, plz, wohnort, umsatz);
		kundenMgmt.einfuegenKunde(k);

	}
	
	public Person ueberpruefeLogin(String benutzername, String passwort) {
		Person p = null;
		
		Iterator<Kunde> iterKunde = kundenMgmt.getKundenliste().iterator();
		while(iterKunde.hasNext()){
			p = iterKunde.next();
			if (p.getBenutzername().equals(benutzername) && p.getPasswort().equals(passwort)) {
				return p;
			}
		}
		Iterator<Mitarbeiter> iterMit = mitarbeiterMgmt.getMitarbeiterliste().iterator();
		while(iterMit.hasNext()) {
			p = iterMit.next();
			if (p.getBenutzername().equals(benutzername) && p.getPasswort().equals(passwort)) {
				return p;
			}
		}
		return null;		
	}
	
	
	
	public void starteMitarbeiterbereich() {
		
		boolean logInOk = false;
		
		try{
			do{
				logInOk = mitarbeiterMgmt.kundenLogin();
					if(logInOk){
						KundenClientCUI k = new KundenClientCUI();			
						k.kClRun();
					}	
					else{
						System.out.println("Benutzername oder Passwort ist falsch!");
					}
			
			
			}while(!logInOk);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void starteKundenbereich () {
		
		boolean logInOk = false;
		
		try{
			do{
				logInOk = kundenMgmt.kundenLogin();
				if(logInOk){
					KundenClientCUI k = new KundenClientCUI();				
					k.kClRun();
				} else {	
					System.out.println("Benutzername oder Passwort ist falsch!");
				}
			
			}while(!logInOk);
		}catch(Exception e){
			e.printStackTrace();
		}	
	}		
}
