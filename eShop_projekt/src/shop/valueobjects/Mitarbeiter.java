package shop.valueobjects;

	
public class Mitarbeiter extends Person {
	
	protected int mitarbeiterNummer;
	
	public Mitarbeiter (int maNummer, String benutzername, String vorname, String nachname) {
		this.mitarbeiterNummer = maNummer;
		this.benutzername = benutzername;
		this.vorname = vorname;
		this.nachname = nachname;		
	}
	
	
}

