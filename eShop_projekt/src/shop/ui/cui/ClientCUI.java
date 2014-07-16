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
import shop.exceptions.KundeExistiertBereitsException;
import shop.valueobjects.Artikel;
import shop.valueobjects.Kunde;
import shop.valueobjects.Mitarbeiter;
import shop.valueobjects.Person;
import shop.valueobjects.Warenkorb;

public class ClientCUI {


		private ShopManager shopMgmt;
		private BufferedReader in;
		private Person p;
		
		public ClientCUI() throws IOException {
			shopMgmt = new ShopManager();
			in = new BufferedReader(new InputStreamReader(System.in));
		}
		
		private void anmeldungMenue() {
			System.out.print("Befehle: \n  Anmelden >                          'a'");
			System.out.print("         \n  Registrieren >                      'r'");
			System.out.print("       \n  Username oder Passwort vergessen? > 'v'");
			System.out.println("         \n  Beenden >                           'q'");		
			System.out.print("> ");
			System.out.flush();				
		}
		
		private void mitarbeiterMenue() {
			System.out.print("Befehle: \n  Artikel einfuegen:           'e'");
			System.out.print("         \n  Artikel ausgeben nach Nr:    'a'");
			System.out.print("         \n  Artikel ausgeben nach Bez.:  'b'");
			System.out.print("         \n  Artikel Bestand erhöhen:     'c'");
			System.out.print("         \n  Artikel suche nach Nr:       'f'");
			System.out.print("         \n  Artikel suche nach Bez.:     'g'");
			System.out.print("         \n  Artikel löschen nach Nr:     'k'");
			System.out.print("         \n                                   ");
			System.out.print("         \n  Bestandshistorie ausgeben    'bh'");
			System.out.print("         \n                                   ");
			System.out.print("         \n  Mitarbeiter einfuegen:       'me'");
			System.out.print("         \n  Mitarbeiter ausgeben:        'ma'");
			System.out.print("         \n  Mitarbeiter suche nach ID:   'mf'");
			System.out.print("         \n  Mitarbeiter löschen nach ID: 'ml'");
			System.out.print("         \n                                   ");
			System.out.print("         \n  Kunden ausgeben:             'ka'");
			System.out.print("         \n  Kunden suche nach ID:        'kf'");
			System.out.print("         \n  Kunden löschen nach ID:      'kl'");
			System.out.print("         \n                                   ");	
			System.out.println("         \n  Beenden:                     'q'");
			System.out.println("> ");
			System.out.flush();		
		}
		
		private void kundenMenue() {
			System.out.print("Befehle: \n  Account loeschen:            'al'");
			System.out.print("         \n                                   ");
			System.out.print("         \n  Artikel ausgeben nach Nr:    'a'");
			System.out.print("         \n  Artikel ausgeben nach Bez.:  'b'");
			System.out.print("         \n                                   ");
			System.out.print("         \n  Warenkorb Artikel hinzufügen:'wh'");
			System.out.print("         \n  Warenkorb Artikel entfernen: 'we'");
			System.out.print("         \n  Warenkorb anzeigen:          'wa'");
			System.out.print("         \n  Warenkorb leeren:            'wl'");
			System.out.print("         \n  Warenkorb kaufen:            'wk'");
			System.out.print("         \n                                   ");			
			System.out.println("         \n  Beenden:                     'q'");
			System.out.print("> ");
			System.out.flush();		
		}

		private String liesEingabe() throws IOException {
			return in.readLine();
		}

		private void logIn() throws IOException {
			System.out.println("Login > ");
			System.out.print("Username > ");
			String username = liesEingabe();
			System.out.print("Passwort > ");
			String password = liesEingabe();
			p = shop.pruefeLogin(username, password);
//			system prints aus shopverwaltung einf¸gen
		}
		
		private void verarbeiteAnmeldungEingabe(String line) throws IOException {
			if (line.equals("r")) {
				System.out.println("Registrierung >");
				System.out.println("Username >");
				String benutzername = liesEingabe();
				System.out.println();
				String passwort = liesEingabe();
				System.out.println("Vorname >");
				String vorname = liesEingabe();
				System.out.println("Nachname >");
				String nachname = liesEingabe();
				System.out.println("Nachname >");
				String mail = liesEingabe();
				System.out.println("Strasse >");
				String strasseNummer = liesEingabe();
				System.out.println("Postleitzahl >");
				String strPlz = liesEingabe();
				int plz = Integer.parseInt(strPlz);
				System.out.println("Wohnort >");
				String wohnort = liesEingabe();
				float umsatz = 0;
				try {
					shopMgmt.fuegeKundeHinzu(benutzername, vorname, nachname, mail, passwort, strasseNummer, plz, wohnort, umsatz);
					System.out.println("Kunde wurde hinzugef¸gt!");
				} catch (KundeExistiertBereitsException e) {
					
				} 
			} else if (line.equals("v")) {
				//TODO
			}
		}	
		
		private void verarbeiteMitarbeiterEingabe(String line) throws IOException {
			if (line.equals("e")) { 
				try {
//					
					Mitarbeiter m = shopMgmt.sucheMitarbeiter(p.getBenutzername());
					System.out.print("Artikelnummer > ");
					int nummer = Integer.parseInt(liesEingabe());
					System.out.print("Bezeichnung > ");
					String bezeichnung = liesEingabe();
					System.out.print("Preis > ");
					double preis = Double.parseDouble(liesEingabe());
					System.out.print("Massengutartikel oder Einzelartikel? (m/e) > ");
					String groesse = "";
					if (liesEingabe().toLowerCase().equals("m")) {
						System.out.print("Packungsgröße > ");
						groesse = liesEingabe();
					} 
					System.out.print("Bestand > ");
					int bestand = Integer.parseInt(liesEingabe());
					boolean ok = false;
					if (groesse.isEmpty()) 
						shop.fuegeArtikelEin(m, nummer, bezeichnung, preis, bestand);	
					else
						shop.fuegeMassengutartikelEin(m, nummer, bezeichnung, preis, Integer.parseInt(groesse), bestand);
					ok = true;
					if (ok)
						System.out.println("Einfügen ok");
					else
						System.out.println("Fehler beim Einfügen");
				} catch (MitarbeiterExistiertNichtException e) {
					System.err.println("Mitarbeiter existiert nicht!");
				} catch (ArtikelBestandIstKeineVielfacheDerPackungsgroesseException e) {
					System.err.println("Bestand ist keine Vielfache der Packungsgroesse!");
				} catch (ArtikelExistiertBereitsException e) {
					System.err.println("Artikel existiert bereits!");
				} catch(NumberFormatException e) { 
					System.err.println("Sie haben eine ungültige Zahl eingegeben!");
				}
			}
			else if (line.equals("a")) {
				Collection<Artikel> liste = shop.gibAlleArtikelSortiertNachArtikelnummer();
				gibArtikellisteAus(liste);
			}
			else if (line.equals("b")) {
				Collection<Artikel> liste = shop.gibAlleArtikelSortiertNachBezeichnung();
				gibArtikellisteAus(liste);
			}
			else if (line.equals("c")) {
				try {
//					System.out.print("Mitarbeiter ID > ");
					Mitarbeiter m = shop.sucheMitarbeiter(p.getId());
					System.out.print("Artikelnummer > ");
					int nummer = Integer.parseInt(liesEingabe());
					System.out.print("Artikelanzahl > ");
					int anzahl = Integer.parseInt(liesEingabe());
					boolean ok = false;
					shop.artikelBestandVeraendern(m, nummer, anzahl);
					ok = true;
					if (ok)
						System.out.println("Bestand erhöhen ok");
					else
						System.out.println("Fehler beim Bestand erhöhen");
				} catch (MitarbeiterExistiertNichtException e) {
					System.err.println("Mitarbeiter existiert nicht!");
				} catch (ArtikelBestandIstKeineVielfacheDerPackungsgroesseException e) {
					System.err.println("Bestand ist keine Vielfache der Packungsgroesse!");
				} catch (ArtikelExistiertNichtException e) {
					System.err.println("Artikel existiert nicht!");
				} catch(NumberFormatException e) { 
					System.err.println("Sie haben eine ungültige Zahl eingegeben!");
				}
			}
			else if (line.equals("f")) {
				System.out.print("Artikelnummer > ");
				int nummer = Integer.parseInt(liesEingabe());
				List<Artikel> liste = shop.sucheArtikel(nummer);
				gibArtikellisteAus(liste);
			}
			else if (line.equals("g")) {
				System.out.print("Artikelbezeichnung > ");
				String bezeichnung = liesEingabe();
				List<Artikel> liste = shop.sucheArtikel(bezeichnung);
				gibArtikellisteAus(liste);
			}
			else if (line.equals("k")) {
				try {
//					System.out.print("Mitarbeiter ID > ");
					Mitarbeiter m = shop.sucheMitarbeiter(p.getId());
					System.out.print("Artikelnummer > ");
					int nummer = Integer.parseInt(liesEingabe());
					boolean ok = false;

					shop.entferneArtikel(m, nummer);
					ok = true;
					if (ok)
						System.out.println("Löschen ok");
					else
						System.out.println("Fehler beim Löschen");
				} catch (ArtikelExistiertNichtException e) {
					System.err.println("Artikel existiert nicht!");
				} catch (NumberFormatException e) {
					System.err.println("Die Mitarbeiter ID erwartet eine Zahl!");
				} catch (MitarbeiterExistiertNichtException e) {
					System.err.println(e.getMessage());
				}
			}
			else if(line.equals("bh")){
				
				try {
					System.out.print("Artikelnummer > ");
					int nummer = Integer.parseInt(liesEingabe());
					Artikel a = shop.gibArtikel(nummer);
					System.out.println(shop.gibBestandsHistorie(a));
				} catch (ArtikelExistiertNichtException e) {
					System.err.println(e.getMessage());
				} catch (NumberFormatException e){
					System.err.println("Sie müssen für die Artikelnummer eine Zahl eingeben.");
				}
			}
			else if (line.equals("me")) {
				System.out.print("Username >");
				String username = liesEingabe();
				System.out.print("Passwort >");
				String passwort = liesEingabe();
				System.out.print("Name >");
				String name = liesEingabe();
				System.out.print("Funktion: \tMitarbeiter / Admin >");
				String funktionStr = liesEingabe();
				
				try{
					MitarbeiterFunktion funktion = null;
					if(funktionStr.equals("Mitarbeiter")){
						funktion = MitarbeiterFunktion.Mitarbeiter;
					}else if(funktionStr.equals("Admin")){
						funktion = MitarbeiterFunktion.Admin;
					}
					
					if(funktion != null){
						shop.fuegeMitarbeiterHinzu(username, passwort, name, funktion, 0);
					}else{
						System.err.println("Die Funktion hat keinen gültigen Wert!");
					}
				} catch (MitarbeiterExistiertBereitsException e){
					System.err.println(e.getMessage());
				} catch (UsernameExistiertBereitsException e){
					System.err.println(e.getMessage());
				} catch (NumberFormatException e){
					System.err.println("Die id ist keine Zahl! Bitte versuchen Sie es erneut.");
				}
			}
			else if (line.equals("ma")) {
				Collection<Mitarbeiter> liste = shop.gibAlleMitarbeiter();
				gibMitarbeiterlisteAus(liste);
			}
			else if (line.equals("mf")) {
				System.out.print("Mitarbeiter ID >");
				int id = Integer.parseInt(liesEingabe());
				try{
					Mitarbeiter m = shop.sucheMitarbeiter(id);
					System.out.println(m.toString());
				}catch (MitarbeiterExistiertNichtException e1){
					System.out.println(e1.getMessage());
					e1.printStackTrace();
				}
			}
			else if (line.equals("ml")) {
				System.out.print("Mitarbeiter ID >");
				int id = Integer.parseInt(liesEingabe());
				try{
					shop.mitarbeiterLoeschen(shop.sucheMitarbeiter(id));
				}catch (MitarbeiterExistiertNichtException e1){
					System.out.println(e1.getMessage());
					e1.printStackTrace();
				}
			}
			else if (line.equals("ka")) {
				Collection<Kunde> liste = shop.gibAlleKunden();
				gibKundenlisteAus(liste);
			}
			else if (line.equals("kf")) {
				System.out.print("Kunden ID >");
				int id = Integer.parseInt(liesEingabe());
				
				try {
					Kunde k = shop.sucheKunde(id);
					System.out.println(k.toString());
				} catch (KundeExistiertNichtException e) {
					System.err.println("Der Kunde existiert nicht!");
				}
			}
			else if (line.equals("kl")) {
				System.out.print("Kunden ID >");
				try {
					shop.kundenLoeschen(shop.sucheKunde(Integer.parseInt(liesEingabe())));
				} catch (KundeExistiertNichtException e) {
					System.err.println("Der Kunde existiert nicht!");
				}
			}
		}
		
		private void verarbeiteKundenEingabe(String line) throws IOException {
			if (line.equals("al")) {
				String eingabe;
//				do {
				System.out.print("Sind Sie sicher? (y/n) >");
				eingabe = liesEingabe();
				if (eingabe.equals("y")) {
				try {
					shop.kundenLoeschen(shop.sucheKunde(p.getId()));
//					shop.kundenLoeschen(p); funktioniert?
				} catch (KundeExistiertNichtException e) {
					// ist die Exception ¸berhaupt noch notwendig?
					System.err.println("Der Kunde existiert nicht!");
				}
				System.out.println("Ihr Account wurde gelˆscht und der Client wird nun geschlossen!");
//				shop.schreibeArtikel();
//				shop.schreibeMitarbeiter();
//				shop.schreibeKunden();
//				shop.schreibeEreignisse();
//				System.exit(0);
				} else if (eingabe.equals("n"))
					System.out.println("Ihr Account wurde nicht gelˆscht.");
//				} while (!eingabe.equals("n"));
			} else if (line.equals("a")) {
				Collection<Artikel> liste = shop.gibAlleArtikelSortiertNachArtikelnummer();
				gibArtikellisteAus(liste);
			}
			else if (line.equals("b")) {
				Collection<Artikel> liste = shop.gibAlleArtikelSortiertNachBezeichnung();
				gibArtikellisteAus(liste);
			}

			// Artikel zum Warenkorb hinzufuegen
			
			else if (line.equals("wh")) {
				try {
					Kunde k = shop.sucheKunde(p.getId());
					System.out.print("Artikelnummer > ");
					Artikel a = shop.gibArtikel(Integer.parseInt(liesEingabe()));
					System.out.print("Stückzahl eingeben > ");
					int stZa = Integer.parseInt(liesEingabe());
					boolean ok = false;
					shop.inDenWarenkorbLegen(k, a, stZa);
					ok = true;
					if (ok)
						System.out.println("Hinzufügen ok");
					else
						System.out.println("Fehler beim Hinzufügen");
				} catch (KundeExistiertNichtException e){
					System.err.println("Kunde existiert nicht!");
				} catch (ArtikelExistiertNichtException e1) {
					System.err.println("Artikel existiert nicht!");
				} catch (ArtikelBestandIstKeineVielfacheDerPackungsgroesseException e) {
					System.err.println("Die Stückzahl ist keine Vielfache der Packungsgroesse!");
				} catch (ArtikelBestandIstZuKleinException e) {
					System.err.println("Der Bestand ist zu klein oder leer!");
				} catch(NumberFormatException e) { 
					System.err.println("Sie haben eine ungültige Zahl eingegeben!");
				} 
			}
			
			// Artikel aus dem Warenkorb entfernen
			
			else if (line.equals("we")) {
				try {
					Kunde k = shop.sucheKunde(p.getId());
					System.out.print("Artikelnummer > ");
					Artikel a = shop.gibArtikel(Integer.parseInt(liesEingabe()));
					boolean ok = false;
					shop.ausDemWarenkorbHerausnehmen(k, a);
					ok = true;
					if (ok)
						System.out.println("Entfernen ok");
					else
						System.out.println("Fehler beim Entfernen");
				} catch (KundeExistiertNichtException e){
					System.err.println("Kunde existiert nicht!");
				} catch (ArtikelExistiertNichtException e) {
					System.err.println("Artikel existiert nicht!");
				} catch (ArtikelBestandIstKeineVielfacheDerPackungsgroesseException e) {
					System.err.println("Der Bestand ist keine Vielfache der Packungsgroesse!");
				} catch(NumberFormatException e) { 
					System.err.println("Sie haben eine ungültige Zahl eingegeben!");
				}
			}
			
			// Inhalt des Warenkorbes anzeigen lassen
			
			else if (line.equals("wa")) {
				try {
					Kunde k = shop.sucheKunde(p.getId());
					Collection<WarenkorbArtikel> liste = k.getWarenkorbVerwaltung().getWarenkorb();
					gibWarenkorblisteAus(liste);
				} catch (KundeExistiertNichtException e) {
					System.err.println("Kunde existiert nicht!");
				} catch(NumberFormatException e) { 
					System.err.println("Sie haben eine ungültige Zahl eingegeben!");
				}
			}
			
			// Warenkorb leeren
			
			else if (line.equals("wl")) {
				try {
					Kunde k = shop.sucheKunde(p.getId());
					boolean ok = false;
					shop.leeren(k);
					ok = true;
					if (ok)
						System.out.println("Leeren ok");
					else
						System.out.println("Fehler beim Leeren");
				} catch (KundeExistiertNichtException e) {
					System.err.println("Kunde existiert nicht!");
				} catch (ArtikelBestandIstKeineVielfacheDerPackungsgroesseException e) {
					System.err.println("Der Bestand ist keine Vielfache der Packungsgroesse!");
				} catch(NumberFormatException e) { 
					System.err.println("Sie haben eine ungültige Zahl eingegeben!");
				}
			}
			else if (line.equals("wk")) {
				try {
					Kunde k = shop.sucheKunde(p.getId());
					System.out.println(shop.kaufen(k).toString());
				} catch (KundeExistiertNichtException e) {
					System.err.println("Kunde existiert nicht!");
				} catch (WarenkorbIstLeerException e) {
					System.err.println("Der Warenkorb ist leer!");
				} catch(NumberFormatException e) { 
					System.err.println("Sie haben eine ungültige Zahl eingegeben!");
				}
			}
		}
		
		private void gibArtikellisteAus(Collection<Artikel> artikel) {
			if (artikel.isEmpty()) {
				System.out.println("Liste ist leer.");
			} else {
				Iterator<Artikel> it = artikel.iterator();
				while (it.hasNext()) {
					Artikel a = it.next();
					System.out.println(a.toString());
				}
			}
		}
		
		private void gibMitarbeiterlisteAus(Collection<Mitarbeiter> mitarbeiter) {
			if (mitarbeiter.isEmpty()) {
				System.out.println("Liste ist leer.");
			} else {
				Iterator<Mitarbeiter> it = mitarbeiter.iterator();
				while (it.hasNext()) {
					Mitarbeiter m = it.next();
					System.out.println(m.toString());
				}
			}
		}
		
		private void gibKundenlisteAus(Collection<Kunde> kunden) {
			if (kunden.isEmpty()) {
				System.out.println("Liste ist leer.");
			} else {
				Iterator<Kunde> it = kunden.iterator();
				while (it.hasNext()) {
					Kunde k = it.next();
					System.out.println(k.toString());
				}
			}
		}
		
		private void gibWarenkorblisteAus(Collection<WarenkorbArtikel> warenkorb) {
			if (warenkorb.isEmpty()) {
				System.out.println("Warenkorb ist leer.");
			} else {
				Iterator<WarenkorbArtikel> it = warenkorb.iterator();
				while (it.hasNext()) {
					WarenkorbArtikel wa = it.next();
					System.out.println(wa.toString());
				}
			}
		}

		public void run() throws IOException {
			String input = "";
			do {
				anmeldungMenue();
				input = liesEingabe();
				verarbeiteAnmeldungEingabe(input);
				if (input.equals("q"))
					System.exit(0);
			} while (!input.equals("a"));
			do {
				logIn();
			} while (p == null);
			do {
				if (p.getPersonTyp().equals(PersonTyp.Mitarbeiter)) {
					mitarbeiterMenue();
			} else
				kundenMenue();
				try {
					input = liesEingabe();
					if (p.getPersonTyp().equals(PersonTyp.Mitarbeiter)) {
						verarbeiteMitarbeiterEingabe(input);
					} else {
						verarbeiteKundenEingabe(input);
					 }
				} catch (IOException e) {
					e.printStackTrace();
				}
			} while (!input.equals("q"));
				shop.schreibeArtikel();
				shop.schreibeMitarbeiter();
				shop.schreibeKunden();
				shop.schreibeEreignisse();
		}
		
		public static void main(String[] args) {
			ShopClientCUI cui;
			try {
				cui = new ShopClientCUI();
				cui.run();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
		
	}

	
	
	
	
	
	
	
	
	
	
	
	/*
	private ShopManager shopMgmt;
	
	private BufferedReader in;
	
	
	
	public ClientCUI() throws Exception{
		
		in = new BufferedReader(new InputStreamReader(System.in));
		
		shopMgmt = new ShopManager();
	}
	
	public String liesEingabe() throws IOException {
		return in.readLine();
	}	

	
	
	public void initialisiereShop() throws Exception {

		String input = ""; 
	
			try {
				input = bereichWaehlen();
				
				if(input.equals("1")){
					shopMgmt.starteMitarbeiterbereich();
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
		System.out.print("<----Das Hauptmen¸--->");
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
	
	
	public void kundenWahl() throws Exception{
		
		String kundenInput;
		
		do{
			System.out.println("Neuen Kunden Regestrieren - (1)");
			System.out.println("Als Kunde Einloggen - (2)");
			System.out.println("Program Beenden - (q)");
			System.out.print("> ");
			kundenInput = liesEingabe();
			
			if (kundenInput.equals("1")){
				
				System.out.print("Geben Sie den Benutzername ein\n > ");
				String benutzername = liesEingabe();
				System.out.print("Geben Sie Passwort ein\n > ");
				String passwort = liesEingabe();
				System.out.print("Geben Sie Ihre E-Mailadresse ein\n > ");
				String mail = liesEingabe();
				System.out.print("Geben Sie Ihren Vorname ein\n > ");
				String vorname = liesEingabe();
				System.out.print("Geben Sie Ihren Nachname ein\n > ");
				String nachname = liesEingabe();
				System.out.print("Geben Sie Ihre Strasse und Hausnummer ein\n > ");
				String strasseNummer = liesEingabe();
				System.out.print("Geben Sie Ihren Ort ein\n > ");
				String wohnort = liesEingabe();
				System.out.print("Geben Sie Ihre Postleizahl ein\n > ");
				int plz = Integer.parseInt(liesEingabe());
				float umsatz = 0;
				
				shopMgmt.fuegeKundeHinzu(benutzername, vorname, nachname, mail, passwort, strasseNummer, plz, wohnort, umsatz);
				
				shopMgmt.schreibeKunde();
				System.out.print("Registrieren erfolgreich!\n");
				
			}else if (kundenInput.equals("2")) {
				shopMgmt.starteKundenbereich();
			}
			
		}while((kundenInput.equals("1") || kundenInput.equals("2")));
			
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
	
	*/
}


	
	


