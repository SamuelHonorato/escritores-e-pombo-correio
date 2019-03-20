package modelos;

import interfaces.SentidoDoVoo;

public class PomboCorreio implements Runnable {

	private int quantidadeMinimaParaEntrega;
	private int quantidadeNaBolsa;
	private int tempoDeRetiradaEmSegundos;
	private int tempoDeVooEmSegundos;
	private int tempoDeDescarregarNoDestinoEmSegundo;
	private Correios caixaPostal;
	private boolean sentidoDoVooOrigemDestino;
	private SentidoDoVoo sentidoDoVoo;
	
	
	private final String avisoDeDescarregar = "*** Pombo está descarregando mensagens no destino";
	private final String avisoSolicitaMensagens = "*** Pombo vai solicirar mensagens";
	private final String avisoRetiraMensagens = "*** Pombo está retirando mensagens da caixa postal";
	
	public PomboCorreio(int quantidadeMinimaParaEntrega, int quantidadeNaBolsa,
			int tempoDeRetiradaEmSegundos, int tempoDeVooEmSegundos,
			int tempoDeDescarregarNoDestinoEmSegundo, Correios caixaPostal) {
		
		this.quantidadeMinimaParaEntrega = quantidadeMinimaParaEntrega;
		this.quantidadeNaBolsa = quantidadeNaBolsa;
		this.tempoDeRetiradaEmSegundos = tempoDeRetiradaEmSegundos;
		this.tempoDeVooEmSegundos = tempoDeVooEmSegundos;
		this.tempoDeDescarregarNoDestinoEmSegundo = tempoDeDescarregarNoDestinoEmSegundo;
		this.caixaPostal = caixaPostal;
		this.sentidoDoVooOrigemDestino = true;
	}

	private int quantidadeDeMensagensASolicitar() {
		
		int quantidadeSolicitada = quantidadeMinimaParaEntrega - quantidadeNaBolsa;
		
		System.out.println(avisoSolicitaMensagens + quantidadeSolicitada);
		
		return quantidadeSolicitada;
	}
	
	private void retiraMensagens() {
		
		int quantidadeDeMensagensASolicitar = quantidadeDeMensagensASolicitar();
		
		// SOLICITA UMA QUANTIDADE DE MENSAGENS PARA PREENCHER A BOLSA COM O VALOR MINIMO PARA ENTREGA
		caixaPostal.retiraMensagens(quantidadeDeMensagensASolicitar);
		
		// TEMPO PARA RETIRAR MENSAGENS DOS CORREIOS
		
		System.out.println(avisoRetiraMensagens);
		
		try {
			Thread.sleep(tempoDeRetiradaEmSegundos * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
	}
	
	private void descarregaMensagens() {
		
		// DESCARREGA MENSAGENS NO DESTINO
		quantidadeNaBolsa = 0;
		
		// TEMPO PARA DESCARREGAR MENSAGENS
		
		System.out.println(avisoDeDescarregar);
		
		try {
			Thread.sleep(tempoDeDescarregarNoDestinoEmSegundo * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		
		while(true) {
			
			// SE O POMBO ESTIVER NA ORIGEM
			if(sentidoDoVooOrigemDestino){
				retiraMensagens();
				sentidoDoVoo = new OrigemDestino();
			}
			
			// SE O POMBO ESTIVER NO DESTINO
			else {
				descarregaMensagens();
				sentidoDoVoo = new DestinoOrigem();
			}
			
			sentidoDoVoo.voar(tempoDeVooEmSegundos);
			sentidoDoVooOrigemDestino = !sentidoDoVooOrigemDestino;
		}
	}
}
