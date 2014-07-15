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
}


	
	


