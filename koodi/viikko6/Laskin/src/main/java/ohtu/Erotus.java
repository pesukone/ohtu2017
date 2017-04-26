package ohtu;

import javax.swing.JTextField;

public class Erotus extends Laskuoperaatio {
	
	public Erotus(Sovelluslogiikka sovellus, JTextField tuloskentta, JTextField syotekentta) {
		super(sovellus, tuloskentta, syotekentta);
	}
	
	@Override
	public void suorita() {		
		super.sovellus.miinus(super.lueArvo());
		super.paivitaKentat();
	}
}
