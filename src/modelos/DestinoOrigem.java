package modelos;
import interfaces.SentidoDoVoo;

public class DestinoOrigem implements SentidoDoVoo {
	
	public void voar(int tempoDeVooEmSegundos) {
		
		System.out.println("*** Pombo voando de B para A");
		
		try {
			Thread.sleep(tempoDeVooEmSegundos);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
