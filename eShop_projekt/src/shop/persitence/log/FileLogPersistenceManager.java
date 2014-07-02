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
	
	
	public boolean speichereEreignis(ShopEreignis e) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}
	public boolean speichereArtikel(Artikel a) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}
	public Artikel ladeArtikel() throws IOException {
		// TODO Auto-generated method stub
		return null;
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

}
