package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import domain.Licao;
import jdbc.Banco;

public class LicaoDAO {

	
	Connection conexao = Banco.conectarBanco("funlingo");
	
	public void salvar(Licao licao) {
		Banco.executaComando(conexao, "INSERT INTO licao VALUES(0,'"+licao.getPergunta1()+"','"+licao.getResposta1()+"',"+licao.getNivel()+",1)");
		Banco.executaComando(conexao, "INSERT INTO licao VALUES(0,'"+licao.getPergunta2()+"','"+licao.getResposta2()+"',"+licao.getNivel()+",2)");
		Banco.executaComando(conexao, "INSERT INTO licao VALUES(0,'"+licao.getPergunta3()+"','"+licao.getResposta3()+"',"+licao.getNivel()+",3)");
		Banco.executaComando(conexao, "INSERT INTO licao VALUES(0,'"+licao.getPergunta4()+"','"+licao.getResposta4()+"',"+licao.getNivel()+",4)");
		Banco.executaComando(conexao, "INSERT INTO licao VALUES(0,'"+licao.getPergunta5()+"','"+licao.getResposta5()+"',"+licao.getNivel()+",5)");
		Banco.executaComando(conexao, "INSERT INTO licao VALUES(0,'"+licao.getPergunta6()+"','"+licao.getResposta6()+"',"+licao.getNivel()+",6)");
		Banco.executaComando(conexao, "INSERT INTO licao VALUES(0,'"+licao.getPergunta7()+"','"+licao.getResposta7()+"',"+licao.getNivel()+",7)");
		Banco.executaComando(conexao, "INSERT INTO licao VALUES(0,'"+licao.getPergunta8()+"','"+licao.getResposta8()+"',"+licao.getNivel()+",8)");
		Banco.executaComando(conexao, "INSERT INTO licao VALUES(0,'"+licao.getPergunta9()+"','"+licao.getResposta9()+"',"+licao.getNivel()+",9)");
		Banco.executaComando(conexao, "INSERT INTO licao VALUES(0,'"+licao.getPergunta10()+"','"+licao.getResposta10()+"',"+licao.getNivel()+",10)");
		
		Banco.executaComando(conexao, "INSERT INTO explicacao VALUES(0,'"+licao.getExplicacao()+"',"+licao.getNivel()+")");
		Banco.executaComando(conexao, "INSERT INTO traducao VALUES(0,'"+licao.getTraducoes()+"',"+licao.getNivel()+")");
	}
	
	public Licao buscarPeloNivel(int nivel) {
		Licao retorno = new Licao();
		ResultSet res = Banco.retornaPesquisa(conexao, "SELECT * FROM licao WHERE nivel="+nivel);
		ResultSet res2 = Banco.retornaPesquisa(conexao, "SELECT * FROM explicacao WHERE nivel="+nivel);
		ResultSet res3 = Banco.retornaPesquisa(conexao, "SELECT * FROM traducao WHERE nivel="+nivel);
		try {
			ArrayList<String> perguntas = new ArrayList<String>();
			ArrayList<String> respostas = new ArrayList<String>();
			while(res.next()) {
				perguntas.add(res.getString("pergunta"));
				respostas.add(res.getString("resposta"));
			} 
			
			retorno.setPergunta1(perguntas.get(0));
			retorno.setResposta1(respostas.get(0));
			
			retorno.setPergunta2(perguntas.get(1));
			retorno.setResposta2(respostas.get(1));
			
			retorno.setPergunta3(perguntas.get(2));
			retorno.setResposta3(respostas.get(2));
			
			retorno.setPergunta4(perguntas.get(3));
			retorno.setResposta4(respostas.get(3));
			
			retorno.setPergunta5(perguntas.get(4));
			retorno.setResposta5(respostas.get(4));
			
			retorno.setPergunta6(perguntas.get(5));
			retorno.setResposta6(respostas.get(5));
			
			retorno.setPergunta7(perguntas.get(6));
			retorno.setResposta7(respostas.get(6));
			
			retorno.setPergunta8(perguntas.get(7));
			retorno.setResposta8(respostas.get(7));
			
			retorno.setPergunta9(perguntas.get(8));
			retorno.setResposta9(respostas.get(8));
			
			retorno.setPergunta10(perguntas.get(9));
			retorno.setResposta10(respostas.get(9));
			
			if(res2.next()) {
				retorno.setExplicacao(res2.getString("texto"));
			}
			
			if(res3.next()) {
				retorno.setTraducoes(res3.getString("texto"));
			}
			
		} catch (Exception e) {	}
		return retorno;
	}
	
}
