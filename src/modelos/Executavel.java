package modelos;

public class Executavel {
	
	public static void main(String[] args) {

		// QUANTIDADE MAXIMA DE MENSAGENS, QUANTIDADE DE MENSAGENS 
		Correios caixaPostal = new Correios(10, 0);
		
		// QUANTIDADE MINIMA PARA ENTREGA, QUANTIDADE NA BOLSA, TEMPO DE RETIRADA, TEMPO DE VOO, TEMPO DE DESCARREGAR, CORREIOS
		Thread pomboCorreio = new Thread(new PomboCorreio(3, 0, 3, 1, 3, caixaPostal));
		
		// IDENTIFICADOR, TEMPO DE ESCRITA, CORREIOS
		Thread escritor1 = new Thread(new Escritor("Samuel", 3, caixaPostal));
		Thread escritor2 = new Thread(new Escritor("Joao", 3, caixaPostal));
		Thread escritor3 = new Thread(new Escritor("Pedro", 3, caixaPostal));
		
		pomboCorreio.start();
		escritor1.start();
		escritor2.start();
		escritor3.start();
	}
}
