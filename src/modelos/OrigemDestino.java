package modelos;
import interfaces.SentidoDoVoo;

public class OrigemDestino implements SentidoDoVoo {

	public void voar(int tempoDeVooEmSegundos) {
		
		System.out.println("*** Pombo voando de A para B");
		
		try {
			Thread.sleep(tempoDeVooEmSegundos);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
