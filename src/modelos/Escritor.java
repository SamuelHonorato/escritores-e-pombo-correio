package modelos;

public class Escritor implements Runnable {

	private Correios caixaPostal;
	private String identificador;
	private int tempoDeEscritaEmSegundos;
	
	public Escritor(String identificador, int tempoDeEscritaEmSegundos, Correios caixaPostal) {
		this.identificador = identificador;
		this.caixaPostal = caixaPostal;
		this.tempoDeEscritaEmSegundos = tempoDeEscritaEmSegundos;
	}
	
	private void escrever(){
		
		System.out.println(identificador + " está escrevendo.");
		
		try {
			Thread.sleep(tempoDeEscritaEmSegundos * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		caixaPostal.adicionaMensagens(identificador);
	}

	@Override
	public void run() {
		
		while(true){
			escrever();
		}
	}
}
