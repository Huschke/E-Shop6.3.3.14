package shop.domain;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;









import shop.exceptions.ArtikelExistiertBereitsException;
import shop.exceptions.ArtikelMitNummerNichtGefundenException;
import shop.exceptions.ArtikelNichtGefundenException;
import shop.valueobjects.Artikel;
import shop.valueobjects.Mitarbeiter;

public class ShopManager {

	
	SimpleDateFormat df = new SimpleDateFormat( "dd.MM.yyyy HH:mm:ss" );
	private String date = df.format(new Date());	
	
	
	private String kundendatei = "SHOP_Kunden.ser";
	private String mitarbeiterdatei = "SHOP_Mitarbeiter.ser";
	private String artikeldatei = "SHOP_Artikel.ser";
	
	private ArtikelListe listeArtikel;
	private KundenManager kundenMgmt;
	private MitarbeiterManager mitarbeiterMgmt;
	
	
	
	public ShopManager(String datei) throws IOException {
		 
		
		
		listeArtikel = new ArtikelListe();
		listeArtikel.einlesenDaten(artikeldatei);
		
		kundenMgmt = new KundenManager();
		kundenMgmt.liesDaten(kundendatei);
		
		mitarbeiterMgmt = new MitarbeiterManager();
		mitarbeiterMgmt.liesDaten(mitarbeiterdatei);
		
		//daten auslesen aus ereigis TODO
		
	}
	
	
	public void fuegeEinenArtikelEin(Mitarbeiter mitarbeiter, String artikelId, String artikelName, float preis, int bestand, boolean verfuegbar) throws ArtikelExistiertBereitsException {
		Artikel artikel = new Artikel(artikelId, artikelName, preis, bestand, verfuegbar);
		listeArtikel.einfuegenArtikel(artikel);
		
		//TODO: EREIGNISSE
	}
	
	public void fuegeMassengutEin() {
		//TODO Massenartikel
	}
	
	public Artikel gibEinenArtikel(String artikelID) throws ArtikelNichtGefundenException, ArtikelMitNummerNichtGefundenException {
		
		return listeArtikel.getArtikel(artikelID);
	}
	
	public ArrayList<Artikel> gibAlleArtikel() {
		return listeArtikel.getArtikelBestand();
	}
	
		
	public void schreibeArtikel() throws IOException {
		
		listeArtikel.schreibeDaten(datei+"_Artikel.csv");
		
	}
	
	//public void schreibeMitarbeiter() throws IOException {
	//	
	//	mitarbeiterVt.schreibeDaten(datei+"Shop_Mitarbeiter_Liste.txt");
	//			
	//}
	
	
	//public void schreibeKunde() throws IOException {
	//	  
	//	kundenVt.schreibeDaten(datei+"Shop_Kunden_Liste.txt");
	//	
	//}
	
	public void artikelIDSortieren() throws IOException {
		
		boolean ok = listeArtikel.nachIDSortieren();
		
		if(ok){
			schreibeArtikel();	
		}
		else{
			System.out.println("Error beim Sortieren");
		}
	}
	
	public 
	
	public void starteMitarbeiterbereich() {
		
		boolean logInOk = false;
		
		try{
			do{
				logInOk = mitarbeiterMgmt.kundenLogin();
					if(logInOk){
						KundenClientCUI k = new KundenClientCUI(""); //TODO: clienCUI!!!!				
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
				logInOk = kundenVt.kundenLogin();
				if(logInOk){
					KundenClientCUI k = new KundenClientCUI("Bier");				
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
