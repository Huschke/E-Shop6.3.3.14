package shop.valueobjects;


public class Artikel {
	
	
	protected String artikelID;
	protected String artikelName;
	protected float preis;
	protected int artikelMenge;
	protected boolean verfuegbar;
	
	public Artikel (String artNo, String name, float price, int artMenge, boolean verfuegbar) {
		
		
		this.artikelID = artNo;
		this.artikelName = name; 
		this.preis = price;
		this.artikelMenge = artMenge;
		this.verfuegbar = verfuegbar;
	}
	
	//getter, Setter
	
	
	public String getArtikelID(){
		return artikelID;
	}
	
	public void setArtNum(String aNum){
		this.artikelID = aNum;
	}
	
	public String getArtName(){
		return this.artikelName;
	}
	
	public void setArtName(String aNa){
		this.artikelName = aNa;
	}
	
	public float getPreis(){
		return this.preis;
	}
	
	public void setPreis(float pr){
		this.preis = pr;
	}
	
	public int getArtikelMenge() {
		return artikelMenge;
	}
	
	public void setArtikelMenge(int menge) {
		this.artikelMenge = menge;		
	}
	
	public boolean isVerfugbar() {
		return verfuegbar;
	}
	
	public String toString() {
		return ("ID: " + artikelID + " / Name: " + artikelName + " / Preis: " + preis + " / Menge: " + artikelMenge);
	}
	
	public boolean equals(Object andererArtikel) {
		if (andererArtikel instanceof Artikel) {
			return (this.artikelID == ((Artikel) andererArtikel).artikelID);
		} else {
			return false;
		}
	}

	public static String getNummer() {
		// TODO Auto-generated method stub
		return null;
	}

	public static boolean isVerfuegbar() {
		// TODO Auto-generated method stub
		return false;
	}

	public static String getTitel() {
		// TODO Auto-generated method stub
		return null;
	}
	
}

