package main;

import dao.UsuarioDAO;
import domain.Usuario;

public class Main {

	public static void main(String[] args) {
		Usuario carlos = new Usuario();
		carlos.setNome("Carlos Henrique de Melo");
		carlos.setLogin("carlinbh");
		carlos.setSenha("bullpup");
		
		UsuarioDAO dao = new UsuarioDAO();
		dao.salvar(carlos);
	}

}
