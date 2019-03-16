package modelos;
import interfaces.SentidoDoVoo;


public class OrigemDestino implements SentidoDoVoo {

	public void voar(int tempoDeVooEmSegundos) {
		try {
			Thread.sleep(tempoDeVooEmSegundos);
			System.out.println("Voando de A para B");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
