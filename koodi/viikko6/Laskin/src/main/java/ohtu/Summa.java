package ohtu;

import javax.swing.JTextField;

public class Summa extends Laskuoperaatio {
	
	public Summa(Sovelluslogiikka sovellus, JTextField tuloskentta, JTextField syotekentta) {
		super(sovellus, tuloskentta, syotekentta);
	}
	
	@Override
	public void suorita() {
		super.sovellus.plus(super.lueArvo());
		super.paivitaKentat();
	}
}
