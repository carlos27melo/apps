package dao;



import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import domain.Usuario;
import jdbc.Banco;

public class UsuarioDAO extends GenericDAO<Usuario>{
	
	Connection conexao = Banco.conectarBanco("funlingo");
	
	public boolean salvar(Usuario usuario) {
		return Banco.executaComando(conexao, "INSERT INTO usuario(login,nome,senha,nivel,pontos,previlegio) VALUES('"+usuario.getLogin()+"','"+usuario.getNome()+"','"+usuario.getSenha()+"',0,0,0)");
	}
	
	public void excluir(Usuario usuario) {
		Banco.executaComando(conexao, "DELETE FROM usuario WHERE codigo="+usuario.getCodigo());
	}
	
	public void editar(Usuario usuario) {
		Banco.executaComando(conexao, "UPDATE usuario SET nome='"+usuario.getNome()+"', login='"+usuario.getLogin()+"', senha='"+usuario.getSenha()+"', nivel="+usuario.getNivel()+" , pontos="+usuario.getPontos()+" , previlegio="+usuario.getPrevilegio()+" WHERE codigo="+usuario.getCodigo());
		System.out.println("ATUALIZOU");
	}
	
	
	public Usuario bucarPorCodigo(Usuario usuario) {
		Usuario retorno = null;
		ResultSet res = Banco.retornaPesquisa(conexao, "SELECT * FROM usuario WHERE id="+usuario.getCodigo());
		try {
			if(res.next()) {
				retorno = new Usuario();
				retorno.setLogin(res.getString("login"));
				retorno.setCodigo(res.getLong("codigo"));
				retorno.setNome(res.getString("nome"));
				retorno.setSenha(res.getString("senha"));
				retorno.setPontos(res.getInt("pontos"));
				retorno.setNivel(res.getInt("nivel"));
				retorno.setPrevilegio(res.getInt("previlegio"));
			} 
		} catch (Exception e) {	}
		return retorno;
	}
	
	public Usuario bucarPorLoginSenha(Usuario usuario) {
		Usuario retorno = null;
		ResultSet res = Banco.retornaPesquisa(conexao, "SELECT * FROM usuario WHERE login='"+usuario.getLogin()+"' AND senha='"+usuario.getSenha()+"'");
		try {
			if(res.next()) {
				retorno = new Usuario();
				retorno.setLogin(res.getString("login"));
				retorno.setCodigo(res.getLong("codigo"));
				retorno.setNome(res.getString("nome"));
				retorno.setSenha(res.getString("senha"));
				retorno.setPontos(res.getInt("pontos"));
				retorno.setNivel(res.getInt("nivel"));
				retorno.setPrevilegio(res.getInt("previlegio"));
			} 
		} catch (Exception e) {	}
		return retorno;
	}
	
	public ArrayList<Usuario> listar(){
		ArrayList<Usuario> listaDeUsuarios = new ArrayList<Usuario>();
		
		ResultSet res = Banco.retornaPesquisa(conexao, "SELECT * FROM usuario");
		try {
			while(res.next()) {
				Usuario item = new Usuario();
				item.setLogin(res.getString("login"));
				item.setCodigo(res.getLong("codigo"));
				item.setNome(res.getString("nome"));
				item.setSenha(res.getString("senha"));
				item.setNivel(res.getInt("nivel"));
				item.setPontos(res.getInt("pontos"));
				item.setPrevilegio(res.getInt("previlegio"));
				listaDeUsuarios.add(item);
			} 
		} catch (Exception e) {	}		
		return listaDeUsuarios;
	}
	
	public ArrayList<Usuario> listarPorPosicao(){
		ArrayList<Usuario> listaDeUsuarios = new ArrayList<Usuario>();
		
		ResultSet res = Banco.retornaPesquisa(conexao, "SELECT * FROM usuario ORDER BY pontos DESC");
		try {
			int posicao = 1;
			while(res.next()) {
				Usuario item = new Usuario();
				item.setLogin(res.getString("login"));
				item.setCodigo(res.getLong("codigo"));
				item.setNome(res.getString("nome"));
				item.setSenha(res.getString("senha"));
				item.setNivel(res.getInt("nivel"));
				item.setPontos(res.getInt("pontos"));
				item.setPrevilegio(res.getInt("previlegio"));
				item.setPosicao(posicao);
				listaDeUsuarios.add(item);
				posicao++;
			} 
		} catch (Exception e) {	}		
		return listaDeUsuarios;
	}
}
