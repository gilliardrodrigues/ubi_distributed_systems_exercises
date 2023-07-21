package cinema;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SalaCinema {

	private String nomeFilme;
	private List<Boolean> bilhetes;

	public SalaCinema(String nomeFilme, Integer numBilhetes) {
		super();
		this.nomeFilme = nomeFilme;
		this.bilhetes = Stream.generate(() -> false).limit(numBilhetes)
				.collect(Collectors.toCollection(ArrayList::new));
	}

	public synchronized Integer venderBilhete() {
		
		Integer numBilhete = null;
		for(int posicao = 0; posicao < bilhetes.size(); posicao++) {
			if(bilhetes.get(posicao).equals(false)) {
				numBilhete = posicao + 1;
				bilhetes.set(posicao, true);
				break;
			}
		}
		return numBilhete;
	}
	
	public synchronized Integer getNumBilhetesDisponiveis() {
		
		return Integer.valueOf((int) bilhetes.stream().filter(bilhete -> !bilhete).count());
	}

	public String getNomeFilme() {
		return nomeFilme;
	}

	public void setNomeFilme(String nomeFilme) {
		this.nomeFilme = nomeFilme;
	}

	public List<Boolean> getBilhetes() {
		return bilhetes;
	}

	public void setBilhetes(List<Boolean> bilhetes) {
		this.bilhetes = bilhetes;
	}
}
