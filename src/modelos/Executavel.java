package modelos;

import interfaces.CaixaPostalFuncionario;

public class Executavel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Quantidade maxima de mensagens da caixa postal e quantidade de mensagens de inicio
		CaixaPostal caixaPostal = new CaixaPostal(20, 10);
		ListaUsuarios usuarios = new ListaUsuarios(caixaPostal);
		
		//quantidadeMinimaParaEntrega, quantidadeNaBolsa, tempoDeRetiradaEmSegundos, tempoDeVooEmSegundos, tempoDeDescarregarNoDestino, CaixaPostalFuncionario caixaPostal)
		Pombo pombo = new Pombo(3, 0, 3, 3, 3, caixaPostal);
		
		
		//Identificador e tempo de escrita em segundos
		usuarios.adicionaUsuario(new Usuario("Samuel", 3));
		usuarios.adicionaUsuario(new Usuario("Bruno", 3));
		
		
		usuarios.iniciaUsuarios();
		pombo.start();
	}

}
