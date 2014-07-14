package shop.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;





import java.util.Vector;

import shop.exceptions.KundeExistiertBereitsException;
import shop.exceptions.KundeNichtGefundenException;
import shop.valueobjects.Artikel;
import shop.valueobjects.Person;
import shop.persitence.FilePersistenceManager;
import shop.persitence.PersistenceManager;
import shop.persitence.log.FileLogPersistenceManager;
import shop.persitence.log.LogPersistenceManager;
import shop.valueobjects.Kunde;


public class KundenManager {
	
	private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	
	private LogPersistenceManager pm = new FileLogPersistenceManager();
	
	private List<Kunde> kundenListe = new ArrayList<Kunde>();

	private Person kundeBesitzer;
	

	/**
	 * Methode zum Einlesen von Kundendaten auf einer Datei
	 * @param datei
	 * @throws IOException
	 */
	public void liesDaten(String datei) throws IOException{
		
		pm.openForReading(datei);
		
		Kunde einKunde;
		do{
			einKunde = pm.ladeKunde();
			if(einKunde!=null){
				try{
				einfuegenKunde(einKunde);
				}catch(KundeExistiertBereitsException e){
					
				}
			}
		}while(einKunde!=null);
		pm.close();
		
	}
	
	
	/**
	 * Methode zum Schreiben von Kundendaten in eine Datei
	 * @param datei
	 * @throws IOException
	 */
	public void schreibeDaten(String datei) throws IOException {
		
		pm.openForWriting(datei);

		if (!kundenListe.isEmpty()) {
			Iterator<Kunde> iter = kundenListe.iterator();
			while (iter.hasNext()) {
				Kunde k = (Kunde) iter.next();
				pm.speichereKunde(k);				
			}
		}				

		pm.close();
		
	}
	
	
	/**
	 * Diese Methode fuegt einen Kunden in die bestehende Kundenliste hinzu
	 * @param einKunde
	 * @throws KundeExistiertBereitsException
	 */
	public void einfuegenKunde(Kunde einKunde) throws  KundeExistiertBereitsException {		
		
		if (!kundenListe.contains(einKunde)){
			kundenListe.add(einKunde);
		} else {
			throw new KundeExistiertBereitsException(einKunde, " - in 'einfuegenKunde()'");
		}	
	}
	
	/**
	 * Diese Methode loescht einen Kunden aus der bestehenden Kundenliste
	 * @param einKunde
	 * @throws KundeNichtGefundenException
	 */
	public void loescheKunde(Kunde einKunde) throws KundeNichtGefundenException{ 
		
		if (kundenListe.contains(einKunde)) {
			kundenListe.remove(einKunde);
		} else {
			throw new KundeNichtGefundenException(einKunde, " - in 'loescheKunde()'");
		}
	}

	public Vector<Kunde> sucheKunde(final String benutzerName) {
		final Vector<Kunde> ergebnis = new Vector<Kunde>();
		for (final Kunde kunde : kundenListe) {
			if (kunde.getBenutzername().equals(benutzerName)) {				
			    ergebnis.add(kunde);
			}
		}
		return ergebnis;
	}
	
	
	
	public void setzeKunde(Person p) {

		kundeBesitzer = p;
		
	}
	
	
	public Person gibBesitzer() {
		
		return  kundeBesitzer;
		
	}
	
	
	public List<Kunde> getKundenliste() {
		return kundenListe;
	}
	
		
	
	public static String now(String dateFormat) {
		
	    Calendar cal = Calendar.getInstance();
	    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
	    return sdf.format(cal.getTime());
	    
	}
	
	
	protected String liesEingabe() throws IOException {
		
		return in.readLine();
		
	}


	
}