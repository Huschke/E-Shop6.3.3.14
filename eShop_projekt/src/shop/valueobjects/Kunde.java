package shop.valueobjects;


public class Kunde extends Person{
	
	public Kunde(String benutzername, String vorname, String nachname, String mail, String passwort, String strasseNummer, int plz, String wohnort, float umsatz){
		this.benutzername = benutzername;
		this.vorname = vorname;
		this.nachname = nachname;
		this.email = mail;
		this.passwort = passwort;
		this.strasseNummer = strasseNummer;
		this.plz = plz;
		this.wohnort = wohnort;
		this.umsatz = umsatz;
		
	}
	

}



	