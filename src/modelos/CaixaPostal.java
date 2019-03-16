package modelos;
import interfaces.CaixaPostalCliente;
import interfaces.CaixaPostalFuncionario;


public class CaixaPostal implements CaixaPostalCliente, CaixaPostalFuncionario {
	private int quantidadeMaximaMensagens;
	private int quantidadeDeMensagens;
	
	public CaixaPostal(int quantidadeMaximaMensagens, int quantidadeDeMensagens) {
		this.quantidadeMaximaMensagens = quantidadeMaximaMensagens;
		this.quantidadeDeMensagens = quantidadeDeMensagens;
	}
	
	public synchronized boolean adicionaMensagem(String remetente){
		
		if(quantidadeDeMensagens < quantidadeMaximaMensagens){
			quantidadeDeMensagens++;
			System.out.println("Caixa Postal por " + remetente + ": " + quantidadeDeMensagens + "/" + quantidadeMaximaMensagens);
			return true;
		}
		else {
			return false;
		}
	}
	
	public synchronized int retiraMensagens(int quantidadeSolicitada) {
		int entrega = 0;
		int total = quantidadeDeMensagens - quantidadeSolicitada;
		
		if(total >= 0){
			entrega = quantidadeSolicitada;
			quantidadeDeMensagens = total;
			//Pombo ir� executar
		}
		else {
			entrega = quantidadeDeMensagens;
			quantidadeDeMensagens = 0;
			//Pombo ir� dormir
		}
		
		System.out.println("Caixa Postal pelo pombo: " + quantidadeDeMensagens + "/" + quantidadeMaximaMensagens);
		
		return entrega;
	}
}
