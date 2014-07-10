package shop.valueobjects;

	
public class Mitarbeiter extends Person {
	
	protected int mitarbeiterNummer;
	
	public Mitarbeiter (String benutzername, String vorname, String nachname, String passwort) {
		
		this.benutzername = benutzername;
		this.vorname = vorname;
		this.nachname = nachname;
		this.passwort = passwort;
	}
	
	
}

