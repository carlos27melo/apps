package test;

import org.junit.Assert;
import org.junit.Test;
import org.omnifaces.util.Messages;

import dao.UsuarioDAO;
import domain.Usuario;

public class UsuarioTeste {
	
	//@Test
	public void salvar() {
		Usuario usuario = new Usuario();
		usuario.setLogin("carl");
		usuario.setNome("Carlos");
		usuario.setSenha("123");
		UsuarioDAO dao = new UsuarioDAO();
		Assert.assertTrue(dao.salvar(usuario));
	}
	
	@Test
	public void logar() {
		Usuario usuario = new Usuario();
		usuario.setLogin("carl");
		usuario.setNome("Carlos2");
		usuario.setSenha("123");
		UsuarioDAO dao = new UsuarioDAO();
		Assert.assertNotNull(dao.bucarPorLoginSenha(usuario));

	}

}
