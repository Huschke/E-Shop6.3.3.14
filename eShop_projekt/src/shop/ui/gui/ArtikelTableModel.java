package shop.ui.gui;

import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import shop.valueobjects.Artikel;

public class ArtikelTableModel extends DefaultTableModel{

	 public ArtikelTableModel(final List<Artikel> art, final Vector<String> columnNames) {
	        // Ober-Objekt der Klasse DefaultTableModel initialisieren
	        super();

	        // Spaltennamen in geerbtem Attribut merken
	        this.columnIdentifiers = columnNames;

	        // Bücher-Liste aufbereiten
	        this.setDataVector(art);
	    }

	    public void setDataVector(final List<Artikel> art) {
	        /*
	         * Bücher aus Liste aufbereiten: DefaultTableModel erwartet Repräsentation der
	         * Tabellendaten als Vector von Vectoren
	         */
	        final Vector<Vector<String>> rows = new Vector<Vector<String>>();
	        for (final Artikel Artikel : art) {
	            final Vector<String> einArtikelAlsVector = new Vector<String>();
	            einArtikelAlsVector.add(Artikel.getArtikelID());
	            einArtikelAlsVector.add(Artikel.getArtName());
	            einArtikelAlsVector.add(Float.toString(Artikel.getPreis()) + " €");
	            einArtikelAlsVector.add(Integer.toString(Artikel.getArtikelMenge()));
	            einArtikelAlsVector.add(Artikel.isVerfugbar() ? "verfügbar" : "ausverkauft");
	            rows.add(einArtikelAlsVector);
	        }
	        // Vector von Vectoren mit Bücher-Strings eintragen (geerbte Methode)
	        this.setDataVector(rows, columnIdentifiers);
	    }
	
}
