package shop.valueobjects;

public class ShopEreignis {

	protected String date;
	protected String zustand;
	protected String personName;
	protected String artikelName;
	protected int artikelMenge;
	
	
	public ShopEreignis(String date,String zustand, String personName, String artikelName, int menge){
		
		this.date = date;
		this.zustand = zustand;
		this.personName = personName;
		this.artikelName = artikelName;
		this.artikelMenge = menge;
		
	}
	
	public String getDate() {
        return date;
    }
    public void setDate(String ndate) {
        this.date = ndate;
    }
    
    
    public String getZustand() {
        return zustand;
    }
    public void setZustand(String zustand) {
        this.zustand = zustand;
    }

        
    public String getPersonName() {
        return personName;
    }
    public void setPersonName(String npName) {
        this.personName = npName;
    }
    
    
    public String getArtikelName() {
        return artikelName;
    }
    public void getArtikelName(String nbName) {
        this.artikelName = nbName;
    }
    
    
    public int getMenge() {
      	return artikelMenge; 
     }    
    public void setMenge(int nmenge) {
       	this.artikelMenge = nmenge; 
    }
}
