package shop.ui.gui;

import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import shop.valueobjects.Artikel;

public class ArtikelTableModel extends DefaultTableModel{

	 public ArtikelTableModel(final List<Artikel> buecher, final Vector<String> columnNames) {
	        // Ober-Objekt der Klasse DefaultTableModel initialisieren
	        super();

	        // Spaltennamen in geerbtem Attribut merken
	        this.columnIdentifiers = columnNames;

	        // B�cher-Liste aufbereiten
	        this.setDataVector(buecher);
	    }

	    public void setDataVector(final List<Artikel> buecher) {
	        /*
	         * B�cher aus Liste aufbereiten: DefaultTableModel erwartet Repr�sentation der
	         * Tabellendaten als Vector von Vectoren
	         */
	        final Vector<Vector<String>> rows = new Vector<Vector<String>>();
	        for (final Artikel buch : buecher) {
	            final Vector<String> einArtikelAlsVector = new Vector<String>();
	            einArtikelAlsVector.add(Artikel.getTitel());
	            einArtikelAlsVector.add(Artikel.getTitel());
	            einArtikelAlsVector.add(Artikel.isVerfuegbar() ? "verf�gbar" : "ausgeliehen");
	            rows.add(einArtikelAlsVector);
	        }
	        // Vector von Vectoren mit B�cher-Strings eintragen (geerbte Methode)
	        this.setDataVector(rows, columnIdentifiers);
	    }
	
}
