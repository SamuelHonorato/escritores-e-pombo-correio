package modelos;
import interfaces.SentidoDoVoo;


public class DestinoOrigem implements SentidoDoVoo {
	
	public void voar(int tempoDeVooEmSegundos) {
		try {
			Thread.sleep(tempoDeVooEmSegundos);
			System.out.println("Voando de B para A");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
