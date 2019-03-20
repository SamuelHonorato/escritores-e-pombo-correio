package modelos;

import interfaces.Cliente;

public class Escritor implements Runnable {

	private Cliente caixaPostal;
	private String identificador;
	private int tempoDeEscritaEmSegundos;
	
	public Escritor(String identificador, int tempoDeEscritaEmSegundos, Cliente caixaPostal) {
		this.identificador = identificador;
		this.caixaPostal = caixaPostal;
		this.tempoDeEscritaEmSegundos = tempoDeEscritaEmSegundos;
	}
	
	private void escrever(){
		
		System.out.println(identificador + " está escrevendo.");
		
		dormir(tempoDeEscritaEmSegundos);
		
		caixaPostal.adicionaMensagens(identificador);
	}

	@Override
	public void run() {
		
		while(true){
			escrever();
		}
	}
	
	private void dormir(int tempoEmSegundos) {
		try {
			Thread.sleep(tempoEmSegundos * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
