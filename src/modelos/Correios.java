package modelos;

import interfaces.Cliente;
import interfaces.Servidor;

public class Correios implements Cliente, Servidor {
	private int quantidadeMaximaMensagens;
	private int quantidadeDeMensagens;
	private int quantidadeParaAcordarOPombo;
	
	
	public Correios(int quantidadeMaximaMensagens, int quantidadeDeMensagens) {
		this.quantidadeMaximaMensagens = quantidadeMaximaMensagens;
		this.quantidadeDeMensagens = quantidadeDeMensagens;
		quantidadeParaAcordarOPombo = 0;
	}
	
	public synchronized void adicionaMensagens(String remetente) {
		
		// COLOCA O ESCRITOR PARA DORMIR CASO A CAIXA POSTAL NAO TENHA MAIS ESPACO
		while(quantidadeDeMensagens >= quantidadeMaximaMensagens) {
			
			System.out.println(remetente + ": muitas mensagens. *** Vou dormir.");
			
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		// ADICIONA MENSAGEM NA CAIXA POSTAL CASO TENHA ESPACO
		quantidadeDeMensagens++;
		
		System.out.println(remetente + ": mensagens adicionadas na caixa postal: " + quantidadeDeMensagens + "/" + quantidadeMaximaMensagens );
		
		// ACORDA OS ESCRITORES
		
		if(quantidadeParaAcordarOPombo < 0){
			quantidadeParaAcordarOPombo++;
		}
		else {
			notifyAll();
		}
	}
	
	public synchronized void retiraMensagens(int quantidadeSolicitada) {
		
		quantidadeParaAcordarOPombo = quantidadeDeMensagens - quantidadeSolicitada;
		
		// SE A QUANTIDADE SOLICITADA FOR MAIOR QUE A QUANTIDADE EXISTENTE NA CAIXA POSTAL O POMBO DORME
		while(quantidadeParaAcordarOPombo < 0) {
			
			System.out.println("*** Pombo: poucas mensagens. *** Vou dormir.");
			
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		// SE A QUANTIDADE SOLICITADA FOR MENOR QUE A QUANTIDADE EXISTENTE NA CAIXA POSTAL O POMBO EXECUTA
		quantidadeDeMensagens -= quantidadeSolicitada;
		
		System.out.println("*** Pombo: mensagens retiradas da caixa postal: " + quantidadeDeMensagens + "/" + quantidadeMaximaMensagens );
		
		if(quantidadeDeMensagens <= quantidadeMaximaMensagens)
			notify();
	}
}
