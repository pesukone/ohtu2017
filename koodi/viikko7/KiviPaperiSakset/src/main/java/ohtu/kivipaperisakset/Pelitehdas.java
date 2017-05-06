package ohtu.kivipaperisakset;

public class Pelitehdas {
	
	public static Peli alustaPeli(String tyyppi) {
		if (tyyppi.endsWith("a")) {
			return new KPSPelaajaVsPelaaja();
		} else if (tyyppi.endsWith("b")) {
			return new KPSTekoaly();
		} else if (tyyppi.endsWith("c")) {
			return new KPSParempiTekoaly();
		} else {
			return null;
		}
	}
}
