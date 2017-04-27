package ohtu;

import javax.swing.JTextField;

public class Nollaa implements Komento {
	private Sovelluslogiikka sovellus;
	private JTextField tuloskentta;
	private int edellinenTulos;
	
	public Nollaa(Sovelluslogiikka sovellus, JTextField tuloskentta) {
		this.sovellus = sovellus;
		this.tuloskentta = tuloskentta;
		this.edellinenTulos = 0;
	}
	
	@Override
	public void suorita() {
		this.edellinenTulos = this.sovellus.tulos();
		this.sovellus.nollaa();
		this.tuloskentta.setText("0");
	}
	
	@Override
	public void peru() {
		this.sovellus.plus(this.edellinenTulos);
		this.tuloskentta.setText("" + this.sovellus.tulos());
	}
}
