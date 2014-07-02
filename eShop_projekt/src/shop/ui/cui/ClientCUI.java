package shop.ui.cui;


// Artikel/ Kunden nach z.b Namen sortieren
import java.io.BufferedReader; 
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import shop.domain.ArtikelListe;
import shop.domain.KundenManager;
import shop.domain.ShopManager;
import shop.valueobjects.Artikel;
import shop.valueobjects.Kunde;
import shop.valueobjects.Warenkorb;

public class ClientCUI {
	
	private ShopManager shopMgmt;
	
	private BufferedReader in;
	
	
	
	public ClientCUI(String datei) throws Exception{//Shopkonstruktor
		
		in = new BufferedReader(new InputStreamReader(System.in));
		
		shopMgmt = new ShopManager(datei);
	}
	
	public String einlesenEingabe() throws IOException {
		return in.readLine();
	}	
	
	
	
	public void initialisiereShop() throws Exception {

		String input = ""; 
	
			try {
				input = bereichWaehlen();
				
				if(input.equals("1")){
					shopMgmt.mitarbeiterZone();
				}
				else if(input.equals("2")){
					kundenWahl();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
			
	}
	
	
	public String bereichWaehlen() throws IOException{
		
		String wahl;
		do{
			System.out.println("Anmelden als Mitarbeiter(1)");
			System.out.println("Anmelden als Kunden(2)");
			System.out.print("> ");
			
			wahl = einlesenEingabe();
			
		}while(!((wahl.equals("1") || wahl.equals("2"))));
		return wahl;
		
	}
	
	
	
	public void kundenWahl() throws Exception{
		
		String kundenInput;
		
		do{
			System.out.println("Neue Kunde Registrieren(1)");
			System.out.println("Kunden Einloggen(2)");
			System.out.println("Beenden (q)");
			System.out.print(": ");
			kundenInput = einlesenEingabe();
			
			if (kundenInput.equals("1")){
				
				System.out.print("Geben Sie den Benutzername ein > ");
				String benutzername = einlesenEingabe();
				System.out.print("Geben Sie Passwort ein > ");
				String password = einlesenEingabe();
				System.out.print("Geben Sie Ihre E-Mailadresse ein > ");
				String email = einlesenEingabe();
				System.out.print("Geben Sie Ihren Vorname ein > ");
				String vorname = einlesenEingabe();
				System.out.print("Geben Sie Ihren Nachname ein > ");
				String nachname = einlesenEingabe();
				System.out.print("Geben Sie Ihre Strasse und Hausnummer ein > ");
				String strUndHausnummer = einlesenEingabe();
				System.out.print("Geben Sie Ihren Ort ein > ");
				String ort = einlesenEingabe();
				System.out.print("Geben Sie Ihre Postleizahl ein > ");
				int plz = Integer.parseInt(einlesenEingabe());
				
				shopMgmt.fuegeKundeEin(benutzername, password, email, vorname, nachname, strUndHausnummer, ort, plz);
				
				shopMgmt.schreibeKunde();
				System.out.print("Registrieren erfolgreich!\n");
				
			}else if (kundenInput.equals("2")) {
				shopMgmt.kundenZone();
			}
			
		}while((kundenInput.equals("1") || kundenInput.equals("2")));
			
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
		
		
		ClientCUI meinShop = new ClientCUI("Shop");
					
			try {

			meinShop.initialisiereShop();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
}


	
	


