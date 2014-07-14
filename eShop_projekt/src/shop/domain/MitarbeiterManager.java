package shop.domain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Vector;

import shop.exceptions.MitarbeiterExistiertBereitsException;
import shop.exceptions.MitarbeiterNichtGefundenException;
import shop.valueobjects.Person;
import shop.persitence.FilePersistenceManager;
import shop.persitence.PersistenceManager;
import shop.persitence.log.FileLogPersistenceManager;
import shop.persitence.log.LogPersistenceManager;
import shop.valueobjects.Mitarbeiter;

public class MitarbeiterManager {

	private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	
	Vector<Mitarbeiter> mitarbeiterListe = new Vector<Mitarbeiter>(); 
	
	private PersistenceManager pm = new FilePersistenceManager();
	
	private static Person mitarbeiterBesitzer = new Person();
	
	/**
	 * Methode zum auslesen aus einer externen Datenquelle
	 * @param datei
	 * @throws IOException
	 */
	public void liesDaten(String datei) throws IOException{
			
			pm.openForReading(datei);
			
			Mitarbeiter einMitarbeiter;
			do{
				einMitarbeiter = pm.ladeMitarbeiter();
				if(einMitarbeiter!=null){
					try{
					einfuegenMitarbeiter(einMitarbeiter);
					}catch(MitarbeiterExistiertBereitsException e){
						
					}
				}
			}while(einMitarbeiter!=null);
			pm.close();
			
		}
	
	
	/**
	 * Methode zum schreiben in eine Datenquelle
	 * @param datei
	 * @throws IOException
	 */
	public void schreibeDaten(String datei) throws IOException {
		
		pm.openForWriting(datei);
	
		if (!mitarbeiterListe.isEmpty()) {
			Iterator<Mitarbeiter> iter = mitarbeiterListe.iterator();
			while (iter.hasNext()) {
				Mitarbeiter m = (Mitarbeiter) iter.next();
				pm.speichereMitarbeiter(m);				
			}
		}				
	
		pm.close();
		
	}
	
	
	/**
	 * Eine Methode die einen Mitarbeiter in die bestehende Mitarbeiterliste hinzufügt
	 * @param einMitarbeiter
	 * @throws MitarbeiterExistiertBereitsException
	 */
	public void einfuegenMitarbeiter(Mitarbeiter einMitarbeiter)  throws  MitarbeiterExistiertBereitsException {
		
		if (!mitarbeiterListe.contains(einMitarbeiter)){
			mitarbeiterListe.add(einMitarbeiter);
		} else {
			throw new MitarbeiterExistiertBereitsException(einMitarbeiter, " - in 'einfuegenMitarbeiter()' ");
		}	
	}

	
	/**
	 * Eine Methode, die einen Mitarbeiter aus der bestehenden Mitarbeiterliste loescht
	 * @param einMitarbeiter
	 * @throws MitarbeiterNichtGefundenException
	 */
	public void loescheMitarbeiter(Mitarbeiter einMitarbeiter) throws MitarbeiterNichtGefundenException {
		
		if (mitarbeiterListe.contains(einMitarbeiter)) {
			mitarbeiterListe.remove(einMitarbeiter);
		} else {
			throw new MitarbeiterNichtGefundenException(einMitarbeiter, " - in 'loescheMitarbeiter()'");
		}
	}
	
	
	/**
	 * Diese Methode gibt die Mitarbieterliste wieder
	 * @return
	 */
	public Vector<Mitarbeiter> getMitarbeiterliste() {
		
		return mitarbeiterListe;
		
	}

	public void setzeBesitzer(Person p) {
		
		mitarbeiterBesitzer = p;
		
	}
	
	
	public Person gibBesitzer() {
		
		return  mitarbeiterBesitzer;
		
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
