package ohtu.intjoukkosovellus;

import java.util.HashSet;
import java.util.Set;
import java.util.StringJoiner;

public class IntJoukko {
    private Set<Integer> alkiot;

    public IntJoukko() {
        this.alkiot = new HashSet<>();
    }

    public boolean lisaa(int luku) {
		return this.alkiot.add(luku);
    }

    public boolean kuuluu(int luku) {
        return this.alkiot.contains(luku);
    }

    public boolean poista(int luku) {
        return this.alkiot.remove(luku);
    }

    public int mahtavuus() {
        return this.alkiot.size();
    }

    @Override
    public String toString() {
		StringJoiner luvut = new StringJoiner(",");
		
		for (int alkio : this.alkiot) {
			luvut.add(Integer.toString(alkio));
		}
		
		return "{" + luvut + "}";
    }

    public int[] toIntArray() {
		int[] lista = new int[this.alkiot.size()];
		
		int i = 0;
		for (int alkio : this.alkiot) {
			lista[i++] = alkio;
		}
		
		return lista;
    }
	
	public Set<Integer> getAlkiot() {
		return this.alkiot;
	}
   

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
		IntJoukko yhdiste = new IntJoukko();
		
		for (int alkio : a.getAlkiot()) {
			yhdiste.lisaa(alkio);
		}
		
		for (int alkio : b.getAlkiot()) {
			yhdiste.lisaa(alkio);
		}
		
		return yhdiste;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
		IntJoukko leikkaus = new IntJoukko();
		
		for (int alkio : a.getAlkiot()) {
			if (a.kuuluu(alkio) && b.kuuluu(alkio)) {
				leikkaus.lisaa(alkio);
			}
		}
		
		return leikkaus;
    }
    
    public static IntJoukko erotus (IntJoukko a, IntJoukko b) {
		IntJoukko erotus = new IntJoukko();
		
		for (int alkio : a.getAlkiot()) {
			if (!b.kuuluu(alkio)) {
				erotus.lisaa(alkio);
			}
		}
		
		return erotus;
    }
        
}