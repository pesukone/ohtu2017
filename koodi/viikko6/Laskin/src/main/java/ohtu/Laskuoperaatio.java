package ohtu;

import javax.swing.JTextField;

public abstract class Laskuoperaatio implements Komento {
	protected Sovelluslogiikka sovellus;
	protected JTextField tuloskentta;
	protected JTextField syotekentta;
	protected String edellinenTulos;
	
	public Laskuoperaatio(Sovelluslogiikka logiikka, JTextField tuloskentta, JTextField syotekentta) {
		this.sovellus = sovellus;
		this.tuloskentta = tuloskentta;
		this.syotekentta = syotekentta;
		try {
			this.edellinenTulos = tuloskentta.getText();
		} catch (Exception e) {
			this.edellinenTulos = "";
		}
	}
	
	protected int lueArvo() {
		int arvo;
		
		try {
			arvo = Integer.parseInt(this.syotekentta.getText());
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
		
		return arvo;
	}
	
	protected void paivitaKentat() {
		this.syotekentta.setText("");
		this.tuloskentta.setText("" + this.sovellus.tulos());
	}
	
	@Override
	public void peru() {
		this.tuloskentta.setText(this.edellinenTulos);
	}
}
