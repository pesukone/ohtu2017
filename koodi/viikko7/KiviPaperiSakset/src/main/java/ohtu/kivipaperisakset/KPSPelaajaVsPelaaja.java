package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSPelaajaVsPelaaja extends Pelaaja implements Peli {

    private static final Scanner scanner = new Scanner(System.in);

	@Override
    public void pelaa() {
		super.pelaa();
    }
	
	@Override
	public String lueTokaSiirto() {
		System.out.print("Toisen pelaajan siirto: ");
        String tokanSiirto = scanner.nextLine();
		
		return tokanSiirto;
	}
	
	@Override
	public void asetaSiirto(String siirto) {
	}
}