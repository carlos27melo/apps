import java.sql.Connection;

import domain.Usuario;
import jdbc.Banco;

public class teste {

	public static void main(String[] args) {
		Connection conexao = Banco.conectarBanco("funlingo");
		Banco.executaComando(conexao, "INSERT INTO usuario VALUES(0,'X2323','Xawd','X3434')");

	}

}
