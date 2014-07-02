package shop.valueobjects;

public class Person {
	
	protected String benutzername;
	protected String vorname;
	protected String nachname;
	protected String email;
	protected String passwort;
	protected String strasseNummer;
	protected int plz;
	protected String wohnort;
	protected float umsatz = 0;
	
	//
	//getter
	//
	
	public String getBenutzername() {
		return benutzername;
	}
	
	public String getVorname() {
		return vorname;
	}
	
	public String getNachname() {
		return nachname;
	}

	public String getEmail() {
		return email;
	}
	
	public String getPasswort() {
		return passwort;
	}
	
	public String getStrasseNummer() {
		return strasseNummer;
	}
	
	public int getPlz() {
		return plz;
	}
	
	public String getWohnort() {
		return wohnort;
	}
	
	public float getUmsatz() {
		return umsatz;
	}
	
	//
	//setter
	//
	
	public void setBenutzername(String benutzer) {
		this.benutzername = benutzer;
	}
	
	public void setVorname(String vName) {
		this.vorname = vName;
	}
	
	public void setNachname(String nName) {
		this.nachname = nName;
	}
	
	public void setEmail(String mail) {
		this.email = mail;
	}
	
	public void setPasswort(String pass) {
		this.passwort = pass;
	}
	
	public void setStrasseNummer(String strasseNummer) {
		this.strasseNummer = strasseNummer;
	}
	
	public void setPlz(int plz) {
		this.plz = plz;
	}
	
	public void setWohnort(String wOrt) {
		this.wohnort = wOrt;
	}
	
	public void setUmstatz(float umsatz) {
		this.umsatz += umsatz;
	}
}
