package ohtu;

import javax.swing.JTextField;

public abstract class Laskuoperaatio implements Komento {
	protected Sovelluslogiikka sovellus;
	protected JTextField tuloskentta;
	protected JTextField syotekentta;
	protected int edellinenArvo;
	
	public Laskuoperaatio(Sovelluslogiikka sovellus, JTextField tuloskentta, JTextField syotekentta) {
		this.sovellus = sovellus;
		this.tuloskentta = tuloskentta;
		this.syotekentta = syotekentta;
		this.edellinenArvo = 0;
	}
	
	protected int lueArvo() {
		int arvo;
		
		try {
			arvo = Integer.parseInt(this.syotekentta.getText());
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
		
		this.edellinenArvo = arvo;
		return arvo;
	}
	
	protected void paivitaKentat() {
		this.syotekentta.setText("");
		this.tuloskentta.setText("" + this.sovellus.tulos());
	}
}
