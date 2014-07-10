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

//TODO !!!
//TODO !!! Methoden aus Jonathans CSV-Datei implementieren und umformen.
//TODO !!!
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
		
		String artikelName = liesZeile();
		
		String artikelPreisString = liesZeile();
		float artikelPreis = Float.parseFloat(artikelPreisString);
		
		String artikelMengeString = liesZeile();
		int artikelMenge = Integer.parseInt(artikelMengeString);
		
		String verfuegbarTaster = liesZeile();
		
		boolean verfuegbar;
		
		if(verfuegbarTaster.equals("+")) {
			verfuegbar = true;
		} else if(verfuegbarTaster.equals("-")) {
			verfuegbar = false;
		}
		
		return Artikel(artikelId, artikelName, artikelPreis, artikelMenge, verfuegbar);
	}
	
	public boolean speichereKunde(Kunde k) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}
	public Kunde ladeKunde() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}
	public boolean speichereMitarbeiter(Mitarbeiter m) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}
	
	public Mitarbeiter ladeMitarbeiter() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ereignis ladeEreignis(ShopEreignis e) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
