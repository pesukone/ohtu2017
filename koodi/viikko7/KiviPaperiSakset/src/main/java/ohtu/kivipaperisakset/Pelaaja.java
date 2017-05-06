package ohtu.kivipaperisakset;

import java.util.Scanner;

public abstract class Pelaaja {
	private static final Scanner scanner = new Scanner(System.in);
	
	public void pelaa() {
		Tuomari tuomari = new Tuomari();
		
		System.out.print("Ensimmäisen pelaajan siirto: ");
        String ekanSiirto = scanner.nextLine();
		
		String tokanSiirto = this.lueTokaSiirto();
		
		while (onkoOkSiirto(ekanSiirto) && onkoOkSiirto(tokanSiirto)) {
            tuomari.kirjaaSiirto(ekanSiirto, tokanSiirto);
            System.out.println(tuomari);
            System.out.println();

            System.out.print("Ensimmäisen pelaajan siirto: ");
            ekanSiirto = scanner.nextLine();
            
            tokanSiirto = this.lueTokaSiirto();
			this.asetaSiirto(tokanSiirto);
        }
		
		System.out.println();
        System.out.println("Kiitos!");
        System.out.println(tuomari);
	}
	
	public abstract String lueTokaSiirto();
	
	public abstract void asetaSiirto(String siirto);
	
	private static boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }
}
