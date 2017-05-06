package ohtu.kivipaperisakset;

import java.util.Scanner;

import java.util.Scanner;

// Kivi-Paperi-Sakset, jossa voidaan valita pelataanko vastustajaa
// vastaan vai ei
public class KPSParempiTekoaly extends Pelaaja implements Peli {

    private static final Scanner scanner = new Scanner(System.in);
	private TekoalyParannettu tekoaly;

	@Override
    public void pelaa() {
        this.tekoaly = new TekoalyParannettu(20);
		super.pelaa();
    }
	
	@Override
	public String lueTokaSiirto() {
		String tokanSiirto = this.tekoaly.annaSiirto();
		System.out.println("Tietokone valitsi: " + tokanSiirto);
		
		return tokanSiirto;
	}
	
	@Override
	public void asetaSiirto(String siirto) {
		this.tekoaly.asetaSiirto(siirto);
	}
}
