package ohtu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.util.Map;
import java.util.HashMap;
 
public class Tapahtumankuuntelija implements ActionListener {
    private JButton plus;
    private JButton miinus;
    private JButton nollaa;
    private JButton undo;
    private JTextField tuloskentta;
    private JTextField syotekentta;
    private Sovelluslogiikka sovellus;
	private Map<JButton, Komento> komennot;
	private Komento edellinen;
 
    public Tapahtumankuuntelija(JButton plus, JButton miinus, JButton nollaa, JButton undo, JTextField tuloskentta, JTextField syotekentta) {
        this.nollaa = nollaa;
        this.undo = undo;
        this.sovellus = new Sovelluslogiikka();
		this.sovellus.nollaa();
		this.komennot = new HashMap<>();
		this.komennot.put(plus, new Summa(this.sovellus, tuloskentta, syotekentta));
		this.komennot.put(miinus, new Erotus(this.sovellus, tuloskentta, syotekentta));
		this.komennot.put(nollaa, new Nollaa(this.sovellus, tuloskentta));
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {		
		Komento komento = this.komennot.get(ae.getSource());
		if (komento != null) {
			komento.suorita();
			this.edellinen = komento;
		} else {
			this.edellinen.peru();
			this.edellinen = null;
		}
		
		this.nollaa.setEnabled(sovellus.tulos() != 0);
		this.undo.setEnabled(edellinen != null);
    }
 
}