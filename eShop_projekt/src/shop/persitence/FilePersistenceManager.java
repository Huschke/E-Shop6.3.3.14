package shop.persitence;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

import shop.valueobjects.Artikel;
import shop.valueobjects.Kunde;
import shop.valueobjects.Mitarbeiter;
import shop.valueobjects.ShopEreignis;

public class FilePersistenceManager implements PersistenceManager{

	private ObjectInputStream objIn = null;
	private ObjectOutputStream objOut = null;
	
	
	
	
	@Override
	public void openForReading(String datenquelle) throws IOException {
		objIn = new ObjectInputStream(new BufferedInputStream(new FileInputStream(datenquelle)));
		
	}

	@Override
	public void openForWriting(String datenquelle) throws IOException {
		objOut = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(datenquelle)));
		
	}

	@Override
	public boolean close() {
		if (objOut != null)
			try {
				objOut.close();
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		if (objIn != null) {
			try {
				objIn.close();
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Methode zum Einlesen von Artikeldaten aus einer externen Datenquelle
	 * @return Artikel-Objekt
	 * @throws IOException
	 */
	public Artikel ladeArtikel() throws IOException{
		try {
			return (Artikel) objIn.readObject();
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
			return null;			
		}				
	}
	
	/**
	 * Methode zum Speichern von Artikeldaten in eine externe Datei
	 * 
	 */
	public void speichereArtikel(Artikel a) throws IOException {
		objOut.writeObject(a);
	}
	
	
	/**
	 * Methode zum lasen von Mitarbeiterdaten aus einer externen Datenquelle
	 * @return Mitarbeiter m
	 * @thows IOException
	 */
	public Mitarbeiter ladeMitarbeiter() throws IOException {
		Mitarbeiter m;
		try {
			m = (Mitarbeiter) objIn.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}		
		
		return m;
	}
		
	/**
	 * Methode zum Speichern von Mitarbeiterdaten in eine externe Datei
	 * 
	 */
	public void speichereMitarbeiter(Mitarbeiter m) throws IOException {
		objOut.writeObject(m);
	}
	
	/**
	 * Methode zum Laden von 
	 * @return Kunde k
	 * @throws IOException
	 */
	public Kunde ladeKunde() throws IOException {
		Kunde k;
		try {
			k = (Kunde) objIn.readObject();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
			return null;
		}
		
		return k;
	}

	
	public void speichereKunde(Kunde k) throws IOException {
		objOut.writeObject(k);
		
	}
	
}
