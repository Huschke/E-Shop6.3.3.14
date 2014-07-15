package shop.ui.cui;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import shop.domain.ArtikelListe;
import shop.domain.ShopManager;
import shop.valueobjects.Artikel;
import shop.valueobjects.Person;



public class KundenCUI {
	
	private ShopManager shopMgmt;
	private ArtikelListe aListe;
	
    private Person p;
    
	//private Vector<KaufBier> warenkorb = new Vector<KaufBier>();
	private float summe;
	

	public KundenCUI() throws IOException, Exception{

		
		shopMgmt = new  ShopManager();
		aListe = new ArtikelListe();
		
	}
	
	
	private void gibMenueAus() {
		
		System.out.println("Bierliste ausgeben (1)");
		System.out.println("Bier suchen (2)");
		System.out.println("In Warenkorb legen (3)");
		System.out.println("Warenkorb (4)");
		System.out.println("Zur Kasse (5)");
		System.out.println("Beenden ('q')");
		System.out.println(": ");
		System.out.flush();
		
	}
	
	
	private void verarbeiteEingabe(String line, String personName) throws IOException, Exception {
	
		if (line.equals("1")) {
			
			List<Artikel> artikelBestand = aListe.getArtikelBestand();
			gibBestandAus(artikelBestand);
			
		}
		
		else if (line.equals("2")) {
			
			System.out.println("Biername  > ");
			String biername = liesEingabe();
			
			Vector<Bier> liste;
			try {
				liste = bierShopVt.sucheBierNachName(biername);
				gibBierlisteAus(liste);
			} catch (BierNichtGefundenException e) {
				System.out.println(e.getMessage());
			}	
			
		}
		
		else if (line.equals("3")) {
			
			String biername;
			int menge;
			
			System.out.println("Biername > ");
			biername = liesEingabe();
			Bier b = bierShopVt.sucheBier(biername);
			
			if(b != null){
				System.out.println("Menge ("+b.getbierMenge()+" mal verfuegbar)"+"> ");
				menge = Integer.parseInt(liesEingabe());
				
				if(b.getbierMenge() < menge){
					System.out.println("Bier in gewuenschter Menge ist nicht verfügbar.");
				}
				else {
					warenkorb.add(new KaufBier(b.getID(),b.getbierName(),b.getbierPreis(),b.getbierInhalt(),b.getType(),b.getbierMenge(),menge));
					System.out.println("Bier ist im Warenkorb!!\n");
				}
			}
			else{
				System.out.println("Bier nicht gefunden!!");
				}
			
		}
		
		else if (line.equals("4")) {
						
			String w = "";
			
			if (warenkorb != null){
				
				do{
					gibGewuenschtesBierListeAus(warenkorb);
					
					System.out.println("---Warenkorb---");
					System.out.println("Menge aendern (1)");
					System.out.println("Bier loeschen (2)");
					System.out.println("Warenkorb leeren (3)");
					System.out.println("Zurueck (q)");
					
					w = liesEingabe();
					
					if(w.equals("1")){
						
						System.out.println("Biername > ");
						String biername = liesEingabe();
						Iterator<KaufBier> iter = warenkorb.iterator();
						
						while (iter.hasNext()){
							KaufBier bBearbeiten = iter.next();
							
							if (biername.equals(bBearbeiten.getbierName())){
								
								System.out.println("Neue Menge > ");
								int neueMenge = Integer.parseInt(liesEingabe());
								
								if(neueMenge>bBearbeiten.getbierMenge()){
								System.out.println("Nicht genug vorhanden!");
									
								}else{
									bBearbeiten.setKaufAnzahl(neueMenge);
									System.out.println("Gewuenschte Menge wurde geaendert.");
								}
							}
						}
						
					}
					
					else if(w.equals("2")){
						
						System.out.println("Biername > ");
						String biername = liesEingabe();
						
						boolean ok = gekauftesBierLoeschen(biername);
						if(ok){
							System.out.println("Das Bier wurde geloescht");
						}
						else{
							System.out.println("Bier nicht gefunden!!");
						}
						
					}
					
					else if(w.equals("3")){
						
						System.out.println("Ihr Warenkorb wird geleert, wollen Sie fortfahren? (y/n)");
						String b = liesEingabe();
						
						if(b.equals("y")){
							warenkorb.clear();
						}
						
					}
					
				}while (!w.equals("q"));
			}
			
		}
		
		else if (line.equals("5")) {
			
			gibGewuenschtesBierListeAus(warenkorb);
			summe = bierVt.summeBerechnen(warenkorb);
			
			if(summe != 0){
				
				System.out.println("Gesamte Summe = "+summe+ " €");
				System.out.println("Bezahlen (1)");
				System.out.println("Abbrechen (2)");
				String b = liesEingabe();
				
				if(b.equals("1")){
					printRechnung(warenkorb,summe);
					
					bierShopVt.setMengeNachEinkauf(warenkorb, personName);

					bierShopVt.schreibeBier();												
					bierShopVt.schreibeEreignis();

					warenkorb.clear();
					}
			}else{
			        System.out.println("Fehler beim Bezahlen!"); 		
			}
		}
		
	}


	private boolean gekauftesBierLoeschen(String biername) {
		
		Iterator<KaufBier> iter = warenkorb.iterator();
		while (iter.hasNext()){
			KaufBier bLoeschen = iter.next();
			if(biername.equals(bLoeschen.getbierName())){
				warenkorb.removeElement(bLoeschen);
				return true;
			}
			
		}
		return false;
		
	}


	private void gibGewuenschtesBierListeAus(Vector<KaufBier> bierliste) {
		
		if(bierliste.isEmpty()){
			System.out.println("Bierliste ist leer.");
		}else{
			Iterator<KaufBier> iter = bierliste.iterator();
			while(iter.hasNext()){
				KaufBier b = iter.next();
				System.out.println(b.toString());
			}
		}
	}
	
	
	public void gibBestandAus(List<Artikel> liste ) {
		
		if (liste.isEmpty()) {
			System.out.println("Die Liste ist leer.");
		} else {
			Iterator<Artikel> iter = liste.iterator();
			while (iter.hasNext()) {
				Artikel artikel = iter.next();
				System.out.println(artikel.toString());
			}
		}
		
	}
	
	
	private void printRechnung(Vector<KaufBier> warenkorb, float summe) {
		
		System.out.println("---Rechnung---");
		
		Iterator<KaufBier> iter = warenkorb.iterator();
		int i = 1;
		
		while(iter.hasNext()){
			KaufBier gewBier = (KaufBier)iter.next();
			System.out.println(i+". ");
			System.out.println(gewBier.toString());
			i++;
		}
		
		System.out.println("======");
		System.out.println("Summe = "+summe);
		System.out.println("======");
		System.out.println("Vielen Dank fuer Einkaufen!!");
		
	}
	
	
	public void kClRun() throws Exception {
		
		String input = "";
		
		p = bierShopVt.gibPerson("k");
		String pName = p.getName();
		
		do{
			gibMenueAus();
			try{
				input = liesEingabe();
				verarbeiteEingabe(input, pName);
				
			}catch (IOException e) {
				e.printStackTrace();
			}
		}while (!input.equals("q"));
	}

}
