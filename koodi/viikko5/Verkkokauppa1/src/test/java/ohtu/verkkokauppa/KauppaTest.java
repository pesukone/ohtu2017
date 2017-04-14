package ohtu.verkkokauppa;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {
	Pankki pankki;
	Viitegeneraattori viite;
	Varasto varasto;
	
	public KauppaTest() {
	}
	
	@BeforeClass
	public static void setUpClass() {
	}
	
	@AfterClass
	public static void tearDownClass() {
	}
	
	@Before
	public void setUp() {
		pankki = mock(Pankki.class);
		
		viite = mock(Viitegeneraattori.class);
		when(viite.uusi()).thenReturn(42);
		
		varasto = mock(Varasto.class);
		when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
		when(varasto.haeTuote(3)).thenReturn(new Tuote(3, "sokeri", 3));
		when(varasto.haeTuote(4)).thenReturn(new Tuote(4, "olut", 7));
		when(varasto.saldo(1)).thenReturn(10);
		when(varasto.saldo(3)).thenReturn(5);
		when(varasto.saldo(4)).thenReturn(0);
	}
	
	@After
	public void tearDown() {
	}

	@Test
	public void ostoksenPaatyttyaPankinMetodiaTilisiirtoKutsutaan() {
		// sitten testattava kauppa
		Kauppa k = new Kauppa(varasto, pankki, viite);
		
		// tehdään ostokset
		k.aloitaAsiointi();
		k.lisaaKoriin(1);		// ostetaan tuotetta numero 1 eli maitoa
		k.tilimaksu("pekka", "12345");
		
		// sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
		verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(), anyInt());
		// toistaiseksi ei välitetty kutsussa käytetyistä parametreista
	}
	
	@Test
	public void tilisiirtoTehdaanOikeanAsiakkaanTililta() {
		Kauppa k = new Kauppa(varasto, pankki, viite);
		
		k.aloitaAsiointi();
		k.lisaaKoriin(1);
		k.tilimaksu("pekka", "12345");
		
		verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(), eq(5));
	}
	
	@Test
	public void kaksiEriTuotettaLaskutetaanOikein() {
		Kauppa k = new Kauppa(varasto, pankki, viite);
		
		k.aloitaAsiointi();
		k.lisaaKoriin(3);
		k.lisaaKoriin(1);
		k.tilimaksu("pekka", "12345");
		
		verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(), eq(8));
	}
	
	@Test
	public void kaksiSamaaTuotettaLaskutetaanOikein() {
		Kauppa k = new Kauppa(varasto, pankki, viite);
		
		k.aloitaAsiointi();
		k.lisaaKoriin(1);
		k.lisaaKoriin(1);
		k.tilimaksu("pekka", "12345");
		
		verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(), eq(10));
	}
	
	@Test
	public void tuotettaJotaEiOleVarastossaEiLisataKoriin() {
		Kauppa k = new Kauppa(varasto, pankki, viite);
		
		k.aloitaAsiointi();
		k.lisaaKoriin(1);
		k.lisaaKoriin(4);
		k.tilimaksu("pekka", "12345");
		
		verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(), eq(5));
	}
	
	@Test
	public void edellisenOstoksenTiedotNollataanUudellaAsioinnilla() {
		Kauppa k = new Kauppa(varasto, pankki, viite);
		
		k.aloitaAsiointi();
		k.lisaaKoriin(1);
		
		k.aloitaAsiointi();
		k.lisaaKoriin(3);
		k.tilimaksu("pekka", "12345");
		
		verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(), eq(3));
	}
	
	@Test
	public void jokaMaksullePyydetaanUusiViitenumero() {
		Kauppa k = new Kauppa(varasto, pankki, viite);
		
		k.aloitaAsiointi();
		k.lisaaKoriin(1);
		k.tilimaksu("pekka", "12345");
		
		k.aloitaAsiointi();
		k.lisaaKoriin(3);
		k.tilimaksu("pekka", "12345");
		
		verify(viite, times(2)).uusi();
	}
	
	@Test
	public void koristaPoistetustaTuotteestaEiLaskuteta() {
		Kauppa k = new Kauppa(varasto, pankki, viite);
		
		k.aloitaAsiointi();
		k.lisaaKoriin(1);
		k.poistaKorista(1);
		k.lisaaKoriin(3);
		k.tilimaksu("pekka", "12345");
		
		verify(pankki).tilisiirto(eq("pekka"), anyInt(), eq("12345"), anyString(), eq(3));
	}
}
