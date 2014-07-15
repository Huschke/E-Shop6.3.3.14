package shop.persitence.log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import shop.valueobjects.Artikel;
import shop.valueobjects.Ereignis;
import shop.valueobjects.Kunde;
import shop.valueobjects.Mitarbeiter;
import shop.valueobjects.ShopEreignis;


public class FileLogPersistenceManager implements LogPersistenceManager{
	
	private BufferedReader in = null;
	private PrintWriter out = null;
	
	public void openForReading(String datei) throws IOException {
		
		in = new BufferedReader(new FileReader(datei));
		
	}
	
	public void openForWriting(String datei) throws IOException {
		
		out = new PrintWriter(new BufferedWriter(new FileWriter(datei)));
		
	}
	
	
	public boolean close() {
			
		if (out != null)
			out.close();
		
		if (in != null) {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();					
				return false;
			}
		}
			return true;		
	}
	
	/*
	 * Hilfsmethoden zum lesen bzw. schreiben einer Zeile
	 */
	private String liesZeile() throws IOException {
		if (in != null)
			return in.readLine();
		else
			return "";
	}

	public void schreibeZeile(String daten) {
		if (out	 != null)
			out.println(daten);
	}

	
	
	public boolean speichereEreignis(ShopEreignis e) throws IOException {
		schreibeZeile(e.getDate());
		schreibeZeile(e.getZustand());
		schreibeZeile(e.getPersonName());
		schreibeZeile(e.getArtikelName());
		schreibeZeile(new Integer(e.getMenge()).toString());
		
        return true;
	}
	
	public boolean speichereArtikel(Artikel a) throws IOException {
		schreibeZeile(a.getArtikelID());
		schreibeZeile(a.getArtName());
		schreibeZeile(new Float(a.getPreis()).toString());
		schreibeZeile(new Integer(a.getArtikelMenge()).toString());
		
		if(a.isVerfugbar()) {
			schreibeZeile("+");
		} else {
			schreibeZeile("-");
		}
		return true;
	}
	
	
	public Artikel ladeArtikel() throws IOException {
		String artikelId = liesZeile();
		if (artikelId == null) {
			return null;
		}
		
		String artikelName = liesZeile();
		
		String artikelPreisString = liesZeile();
		float artikelPreis = Float.parseFloat(artikelPreisString);
		
		
		String artikelMengeString = liesZeile();
		int artikelMenge = Integer.parseInt(artikelMengeString);
		
		String verfuegbarTaster = liesZeile();
		
		boolean verfuegbar = false;
		
		if(verfuegbarTaster.equals("+")) {
			verfuegbar = true;
		} else if(verfuegbarTaster.equals("-")) {
			verfuegbar = false;
		}
		
		return new Artikel(artikelId, artikelName, artikelPreis, artikelMenge, verfuegbar);
	}
	
	public boolean speichereKunde(Kunde k) throws IOException {
		schreibeZeile(k.getBenutzername());
		schreibeZeile(k.getVorname());
		schreibeZeile(k.getNachname());
		schreibeZeile(k.getEmail());
		schreibeZeile(k.getPasswort());
		schreibeZeile(k.getStrasseNummer());
		schreibeZeile(new Integer (k.getPlz()).toString());
		schreibeZeile(k.getWohnort());
		schreibeZeile(new Float (k.getUmsatz()).toString());
		return true;
	}
	public Kunde ladeKunde() throws IOException {
		
		String benutzername = liesZeile();
		
		if(benutzername == null){
			return null;
		}
		
		String vorname = liesZeile();
		
		String nachname = liesZeile();
		
		String eMail = liesZeile();
		
		String passwort = liesZeile();
		
		String strasseNummer = liesZeile();
		
		String plzString = liesZeile();
		
		int plz = Integer.parseInt(plzString);
		
		
		String wohnort = liesZeile();
		
		String umsatzString = liesZeile();
		float umsatz = Float.parseFloat(umsatzString);
		
		return new Kunde(benutzername, vorname, nachname, eMail, passwort, strasseNummer, plz, wohnort, umsatz);
	}
	
	public boolean speichereMitarbeiter(Mitarbeiter m) throws IOException {
		schreibeZeile(m.getBenutzername());
		schreibeZeile(m.getVorname());
		schreibeZeile(m.getNachname());
		schreibeZeile(m.getPasswort());
		
		return true;
	}
	
	public Mitarbeiter ladeMitarbeiter() throws IOException {
		String benutzername = liesZeile();
		
		if(benutzername == null){
			return null;
		}
		
		String vorname = liesZeile();
		
		String nachname = liesZeile();
		
		String passwort = liesZeile();
		return new Mitarbeiter(benutzername, vorname, nachname, passwort);
	}

	@Override
	public Ereignis ladeEreignis(ShopEreignis e) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
