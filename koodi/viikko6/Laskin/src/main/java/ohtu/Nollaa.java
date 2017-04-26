package ohtu;

import javax.swing.JTextField;

public class Nollaa implements Komento {
	private Sovelluslogiikka sovellus;
	private JTextField tuloskentta;
	private String edellinenTulos;
	
	public Nollaa(Sovelluslogiikka sovellus, JTextField tuloskentta) {
		this.tuloskentta = tuloskentta;
		this.edellinenTulos = tuloskentta.getText();
	}
	
	@Override
	public void suorita() {
		this.sovellus.nollaa();
		this.tuloskentta.setText("0");
	}
	
	@Override
	public void peru() {
		this.tuloskentta.setText(this.edellinenTulos);
	}
}
