package ohtu;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import ohtu.verkkokauppa.Kauppa;
import ohtu.verkkokauppa.Kirjanpito;
import ohtu.verkkokauppa.Pankki;
import ohtu.verkkokauppa.Varasto;
import ohtu.verkkokauppa.Viitegeneraattori;

public class Main {

    public static void main(String[] args) {
		ApplicationContext ctx = new FileSystemXmlApplicationContext("src/main/resources/spring-context.xml");
		
		Kirjanpito kirjanpito = (Kirjanpito) ctx.getBean(Kirjanpito.class);
		Varasto varasto = (Varasto) ctx.getBean(Varasto.class);
		Pankki pankki = (Pankki) ctx.getBean(Pankki.class);
		Viitegeneraattori viitegen = (Viitegeneraattori) ctx.getBean(Viitegeneraattori.class);
		Kauppa kauppa = (Kauppa) ctx.getBean(Kauppa.class);

        // kauppa hoitaa yhden asiakkaan kerrallaan seuraavaan tapaan:
        kauppa.aloitaAsiointi();
        kauppa.lisaaKoriin(1);
        kauppa.lisaaKoriin(3);
        kauppa.lisaaKoriin(3);
        kauppa.poistaKorista(1);
        kauppa.tilimaksu("Pekka Mikkola", "1234-12345");

        // seuraava asiakas
        kauppa.aloitaAsiointi();
        for (int i = 0; i < 24; i++) {
            kauppa.lisaaKoriin(5);
        }

        kauppa.tilimaksu("Arto Vihavainen", "3425-1652");

        // kirjanpito
        for (String tapahtuma : kirjanpito.getTapahtumat()) {
            System.out.println(tapahtuma);
        }
    }
}
