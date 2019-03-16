package modelos;
import interfaces.CaixaPostalFuncionario;
import interfaces.EstadoDeThread;
import interfaces.SentidoDoVoo;


public class Pombo extends Thread implements EstadoDeThread {
	private int quantidadeMinimaParaEntrega;
	private int quantidadeNaBolsa;
	private int tempoDeRetiradaEmSegundos;
	private int tempoDeVooEmSegundos;
	private int tempoDeDescarregarNoDestino;
	private SentidoDoVoo sentidoDoVoo;
	private CaixaPostalFuncionario caixaPostal;
	
	public Pombo(int quantidadeMinimaParaEntrega, int quantidadeNaBolsa, int tempoDeRetiradaEmSegundos, int tempoDeVooEmSegundos, int tempoDeDescarregarNoDestino, CaixaPostalFuncionario caixaPostal) {
		this.quantidadeMinimaParaEntrega = quantidadeMinimaParaEntrega;
		this.tempoDeRetiradaEmSegundos = tempoDeRetiradaEmSegundos;
		this.tempoDeVooEmSegundos = tempoDeVooEmSegundos;
		this.tempoDeDescarregarNoDestino = tempoDeDescarregarNoDestino;
		this.sentidoDoVoo = new OrigemDestino();
		this.quantidadeNaBolsa = quantidadeNaBolsa;
		this.caixaPostal = caixaPostal;
	}
	
	public synchronized void setSentidoDoVoo(SentidoDoVoo sentidoDoVoo){
		this.sentidoDoVoo = sentidoDoVoo;
	}
	
	public synchronized int quantidadeDeMensagensASolicitar() {
		return quantidadeMinimaParaEntrega - quantidadeNaBolsa;
	}
	
	public synchronized void retiraMensagens(){
		//tempo de retirar mensagem da caixa postal
		//Quantidade solicitada igual ao que precisa para preencher a bolsa do pombo
		int mensagensRecebidas = caixaPostal.retiraMensagens(quantidadeDeMensagensASolicitar());
		System.out.println("Pombo: solicitando " + mensagensRecebidas + " mensagens");
		try {
			Thread.sleep(tempoDeRetiradaEmSegundos * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		quantidadeNaBolsa += mensagensRecebidas;
		System.out.println("Pombo: entregar " + mensagensRecebidas + "mensagens");
		if(quantidadeNaBolsa < quantidadeMinimaParaEntrega) {
			dormir();
		}
		else {
			sentidoDoVoo = new OrigemDestino();
			voar();
		}
	}
	
	public void descarregaMensagensNoDestino(){
		//tempo de descarregar mensagem no destino
	}
	
	public synchronized void voar(){
		//Verificar tipo de voo do pombo: A->B ou B->A
		//tempo de voar do pombo
		sentidoDoVoo.voar(tempoDeVooEmSegundos);
	}
	
	public synchronized void acordar() {
		this.notify();
	}
	
	public synchronized void dormir() {
		
		try {
			System.out.println("Pombo: Vou é dormir.");
			this.wait();
		} catch(Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Erro ao colocar a thread para dormir");
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		retiraMensagens();
	}
}
