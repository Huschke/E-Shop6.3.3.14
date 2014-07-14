package shop.ui.cui;


import java.io.BufferedReader; 
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import shop.domain.ArtikelListe;
import shop.domain.KundenManager;
import shop.domain.MitarbeiterManager;
import shop.domain.ShopManager;
import shop.valueobjects.Artikel;
import shop.valueobjects.Kunde;
import shop.valueobjects.Mitarbeiter;
import shop.valueobjects.Warenkorb;

public class ClientCUI {
	
	private ShopManager shopMgmt;
	private KundenManager kundenMgmt;
	private MitarbeiterManager mitarbeiterMgmt;
	
	Vector<Mitarbeiter> mitarbeiterListe = new Vector<Mitarbeiter>();
	List<Kunde> kundenListe = new Vector<Kunde>();
	
	private BufferedReader in;
	
	
	
	public ClientCUI() throws Exception{
		
		in = new BufferedReader(new InputStreamReader(System.in));
		
		shopMgmt = new ShopManager();
		
		mitarbeiterListe = mitarbeiterMgmt.getMitarbeiterliste();
			
		kundenListe = kundenMgmt.getKundenliste();
	}
	
	public String liesEingabe() throws IOException {
		return in.readLine();
	}	

	
	
	public void initialisiereShop() throws Exception {

		String input = ""; 
	
			try {
				input = bereichWaehlen();
				
				if(input.equals("1")){
					starteMitarbeiterbereich();
				}
				else if(input.equals("2")){
					kundenWahl();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
			
	}
		
	public void gibMenueAus() {
		System.out.print("<----Das Hauptmenü--->");
		System.out.print("\n Zu den Artikeln - 'a'");
		System.out.print("\n Warenkorb anzeigen - 'w'");
		System.out.print("\n Benutzerkonto anzeizen - 'k'");
		System.out.print("\n Programm beenden - 'q'");
		System.out.print("\n Kundenkartei anzeigen - 'd'");
		System.out.print("\n \n >");
		System.out.flush();
		
	}

	public String bereichWaehlen() throws IOException{
		
		String wahl;
		do{
			System.out.println("Anmelden als Mitarbeiter(1)");
			System.out.println("Anmelden als Kunden(2)");
			System.out.print("> ");
			
			wahl = liesEingabe();
			
		}while(!((wahl.equals("1") || wahl.equals("2"))));
		return wahl;
		
	}

	public void starteMitarbeiterbereich() {
		
		boolean logInOk = false;
		
		try{
			do{
				logInOk = kundenLogin();
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
	
	public boolean kundenLogin() throws IOException{
		
		String benutzername = "";
		String passwort = "";
		
		System.out.println("//----  Kunden-Login  ----//");
		System.out.print("Benutzername> ");
		benutzername = liesEingabe();
		
		System.out.print("Passwort> ");
		passwort = liesEingabe();
		
		Iterator<Kunde> iter = kundenListe.iterator();
		while(iter.hasNext()){
			Kunde k = (Kunde) iter.next();
			if(k.getBenutzername().equals(benutzername)&& k.getPasswort().equals(passwort)){
				return true;
			}
		}
		return false;
		
	}
	
	public boolean mitarbeiterLogin() throws IOException{
		
		String benutzername = "";
		String passwort = "";
		
		System.out.println("-Mitarbeiter Login-");
		System.out.print("Benutzername : ");
		benutzername = liesEingabe();
		
		System.out.print("Passwort : ");
		passwort = liesEingabe();
		
		Iterator<Mitarbeiter> iter = mitarbeiterListe.iterator();
		while(iter.hasNext()){
			Mitarbeiter m = (Mitarbeiter) iter.next();
			if(m.getBenutzername().equals(benutzername)&&m.getPasswort().equals(passwort)){
				return true;
			}
		}
		return false;
		
	}
	
	
	public void kundenWahl() throws Exception{
		
		String kundenInput;
		
		do{
			System.out.println("Neue Kunde Registrieren(1)");
			System.out.println("Kunden Einloggen(2)");
			System.out.println("Beenden (q)");
			System.out.print(": ");
			kundenInput = liesEingabe();
			
			if (kundenInput.equals("1")){
				
				System.out.print("Geben Sie den Benutzername ein > ");
				String benutzername = liesEingabe();
				System.out.print("Geben Sie Passwort ein > ");
				String passwort = liesEingabe();
				System.out.print("Geben Sie Ihre E-Mailadresse ein > ");
				String mail = liesEingabe();
				System.out.print("Geben Sie Ihren Vorname ein > ");
				String vorname = liesEingabe();
				System.out.print("Geben Sie Ihren Nachname ein > ");
				String nachname = liesEingabe();
				System.out.print("Geben Sie Ihre Strasse und Hausnummer ein > ");
				String strasseNummer = liesEingabe();
				System.out.print("Geben Sie Ihren Ort ein > ");
				String wohnort = liesEingabe();
				System.out.print("Geben Sie Ihre Postleizahl ein > ");
				int plz = Integer.parseInt(liesEingabe());
				float umsatz = 0;
				
				shopMgmt.fuegeKundeHinzu(benutzername, vorname, nachname, mail, passwort, strasseNummer, plz, wohnort, umsatz);
				
				shopMgmt.schreibeKunde();
				System.out.print("Registrieren erfolgreich!\n");
				
			}else if (kundenInput.equals("2")) {
				starteKundenbereich();
			}
			
		}while((kundenInput.equals("1") || kundenInput.equals("2")));
			
	}


	
	public void benutzerEingabe(String eingabe) throws IOException {
		
		if (eingabe.equals("a")) {
			System.out.println("Folgende Artikel sind im Angebot: ");
			zeigeArtikel(this.liste);
			
		} else if (eingabe.equals("w")) {
			boolean waren = false;
			System.out.println("Ihr Warenkorb: ");
			
			if(waren) {
				//warenkorb ausgeben!!!!!
			} else {
				System.out.println("\n Ihr Warenkorb ist leer! \n");
			}
			
			
		} else if (eingabe.equals("k")) {
			System.out.println("Ihr Kundenkonto: ");
			
			
		}else if (eingabe.equals("d")) {
			System.out.println("Die Kundenkartei! ");
			zeigeKunden(this.kuListe);
		}
	}
	public void zeigeKunden(List<Kunde> k){
		int  i = 1;
		
		
		for (Kunde kunde : k){
			//for zähl
		System.out.println("\n" + i++ + " Name: " + kunde.getNachname() + " Kundennummer: " + kunde.getBenutzername() + " Adresse: " + kunde.getStrasseNummer() );
		System.out.println("-----------------------------------");
	
		}
	}
	
	
	public Artikel zeigeArtikel(List<Artikel> l) {
		int i = 1;
		for (Artikel artikel : l) {
			
			System.out.println(i++ + " Artikelnummer: " + artikel.getArtikelID() + "Name: " + artikel.getArtName() + "Preis: " + artikel.getPreis());
			System.out.println("-----------------------------------");
			
		}
		
		System.out.println("\n >");
		
		return l.get(IO.readInt() -1);
		
	
	}
				

	
	
	public static void main(String[] args) throws Exception {
				
		//String input = null;
		
		
		ClientCUI meinShop = new ClientCUI();
					
			try {

			meinShop.initialisiereShop();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
}


	
	


