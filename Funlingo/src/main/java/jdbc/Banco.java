package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;


public class Banco {

	private static boolean mostrarSQL = true;
	
	public static void ativarMostrarSQL() {
		mostrarSQL = true;
	}
	
	public static void desativarMostrarSQL() {
		mostrarSQL = false;
	}
	
	public static Connection conectarBanco(String nomeBanco){
		Connection conexao = null;
		//String url = "jdbc:mysql://localhost/"+nomeBanco+"?verifyServerCertificate=false&useSSL=true";

		String url = "jdbc:mysql://localhost/"+nomeBanco;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conexao = DriverManager.getConnection(url,"root","vertrigo");
		}catch(Exception erro){
			JOptionPane.showMessageDialog(null, "Ocorreu erro de conex�o com o Banco de dados "+nomeBanco);
		} 
		if(mostrarSQL)System.out.println("Conectou no Banco "+nomeBanco);
		return conexao;
	}
	
	public static ResultSet retornaPesquisa(Connection conexao,String comandoSQL) {
		ResultSet resultado = null;
		try{
			PreparedStatement pesquisa = conexao.prepareStatement(comandoSQL);
			resultado = pesquisa.executeQuery();
		}catch(Exception erro){
			JOptionPane.showMessageDialog(null, "Ocorreu o erro: "+erro);
		}
		if(mostrarSQL)System.out.println(comandoSQL);
		return resultado;
	}
	
	public static boolean executaComando(Connection conexao,String comandoSQL) {
		try{
			PreparedStatement inserir = conexao.prepareStatement(comandoSQL);
			inserir.executeUpdate();
		}catch(Exception erro){
			//JOptionPane.showMessageDialog(null, "Ocorreu erro na execu��o do comando de cadastro: "+erro);
			erro.printStackTrace();
			return false;
		}
		if(mostrarSQL)System.out.println(comandoSQL);
		return true;
	}
	
}
