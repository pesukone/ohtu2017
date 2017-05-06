package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSTekoaly extends Pelaaja implements Peli {

    private static final Scanner scanner = new Scanner(System.in);
	private Tekoaly tekoaly;

	@Override
    public void pelaa() {
		this.tekoaly = new Tekoaly();
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

    private static boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }
}