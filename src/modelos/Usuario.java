package modelos;
import interfaces.CaixaPostalCliente;
import interfaces.EstadoDeThread;


public class Usuario extends Thread implements EstadoDeThread {
	private String identificador;
	private int tempoDeEscritaEmSegundos;
	private CaixaPostalCliente caixaPostal;
	
	public Usuario(String identificador, int tempoDeEscritaEmSegundos) {
		this.identificador = identificador;
		this.tempoDeEscritaEmSegundos = tempoDeEscritaEmSegundos;
		
	}
	
	public void setCaixaPostal(CaixaPostalCliente caixaPostal) {
		this.caixaPostal = caixaPostal;
	}
	
	public synchronized void escrever() {
		
		//Escreve mensagem usando o tempo de escrita
		System.out.println(identificador + " Comecando a escrever");
		try {
			System.out.println(identificador + " está escrevendo.");
			Thread.sleep(tempoDeEscritaEmSegundos * 1000);
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(identificador + " Parando de escrever");
		
		if(!caixaPostal.adicionaMensagem(identificador)){
			dormir();
		}
	}
	
	public void iniciar() {
		this.start();
	}
	
	public void acordar() {
		this.notify();
	}
	
	public synchronized void dormir() {
		
		try {
			System.out.println(identificador + ": Vou é dormir.");
			this.wait();
		} catch(Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Erro ao colocar a thread para dormir");
		}
	}

	@SuppressWarnings("deprecation")
	public void eliminar() {
		this.stop();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			escrever();
		}
	}
}
