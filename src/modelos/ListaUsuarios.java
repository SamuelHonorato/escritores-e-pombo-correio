package modelos;
import interfaces.CaixaPostalCliente;

import java.util.List;
import java.util.Vector;

public class ListaUsuarios {
	
	List<Usuario> usuarios;
	private CaixaPostalCliente caixaPostal;
	
	public ListaUsuarios(CaixaPostalCliente caixaPostal) {
		this.usuarios = new Vector<Usuario>();
		this.caixaPostal = caixaPostal;
	}
	
	public synchronized void adicionaUsuario(Usuario novoUsuario){
		novoUsuario.setCaixaPostal(caixaPostal);
		usuarios.add(novoUsuario);
	}
	
	public void eliminaUsuario(int index){
		Usuario usuario = usuarios.get(index);
		usuario.eliminar();
		usuarios.remove(index);
	}
	
	public synchronized void acordaUsuarios() {
		
	}
	
	public synchronized void iniciaUsuarios() {
		for(int i=0; i<usuarios.size(); i++) {
			
			Usuario usuario = usuarios.get(i);
			usuario.iniciar();
		}
	}
}
