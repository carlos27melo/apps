package bean;

import java.text.Normalizer;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omg.CORBA.RepositoryIdHelper;
import org.omnifaces.util.Messages;

import dao.LicaoDAO;
import dao.UsuarioDAO;
import domain.Licao;
import domain.Usuario;

@ManagedBean
@ViewScoped
public class LicaoBean {
	private Usuario usuario = new Usuario();
	private Licao licao = new Licao();
	
	private boolean iniciada = false;

	int ordem = 1;
	int acertos = 0;
	int erros = 0;
	private String perguntaAtual;
	private String respostaAtual;
	private String respostaEnviada;
	
	public void iniciarLicao() {
		System.out.println("IniciarLicao clicada");
	}
	
	public boolean isIniciada() {
		return iniciada;
	}
	
	public void setIniciada(boolean iniciada) {
		this.iniciada = iniciada;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getRespostaEnviada() {
		return respostaEnviada;
	}

	public void setRespostaEnviada(String respostaEnviada) {
		this.respostaEnviada = respostaEnviada;
	}

	public String getPerguntaAtual() {
		return perguntaAtual;
	}

	public void setPerguntaAtual(String perguntaAtual) {
		this.perguntaAtual = perguntaAtual;
	}

	public String getRespostaAtual() {
		return respostaAtual;
	}

	public void setRespostaAtual(String respostaAtual) {
		this.respostaAtual = respostaAtual;
	}

	public Licao getLicao() {
		return licao;
	}

	public void setLicao(Licao licao) {
		this.licao = licao;
	}
	
	public void verificarTentativa() {
		if(compararFrase(respostaEnviada,respostaAtual)) {
			Messages.addGlobalInfo("Acertou! Reposta: "+respostaAtual);
			usuario.setPontos(usuario.getPontos()+25);
			acertos++;
		} else {
			Messages.addGlobalError("Errou! Resposta: "+respostaAtual);
			usuario.setPontos(usuario.getPontos()-10);
			erros++;
		}
		ordem++;
		licao.setOrdem(licao.getOrdem()+1);
		respostaEnviada = "";
		if(ordem<11) {
			carregarPergunta();
		} else {
			perguntaAtual = "";
			if(acertos>=6) {
				usuario.setNivel(usuario.getNivel()+1);
				Messages.addGlobalInfo("Você concluiu essa lição com "+acertos+" acertos e "+erros+" erros, seu nível agora é "+usuario.getNivel());
			} else {
				Messages.addGlobalError("Você foi reprovado nessa lição com "+acertos+" acertos e "+erros+" erros, seu nível continua "+usuario.getNivel());
			}
			iniciada=false;
			ordem=1;
			acertos=0;
			erros=0;
		}
		UsuarioDAO dao = new UsuarioDAO();
		dao.editar(usuario);
	}

	public void carregarPergunta() {
		switch(ordem) {
		case 1: perguntaAtual = licao.getPergunta1(); 
		respostaAtual = licao.getResposta1(); break;
		
		case 2: perguntaAtual = licao.getPergunta2(); 
		respostaAtual = licao.getResposta2(); break;
		
		case 3: perguntaAtual = licao.getPergunta3(); 
		respostaAtual = licao.getResposta3(); break;
		
		case 4: perguntaAtual = licao.getPergunta4(); 
		respostaAtual = licao.getResposta4(); break;
		
		case 5: perguntaAtual = licao.getPergunta5(); 
		respostaAtual = licao.getResposta5(); break;
		
		case 6: perguntaAtual = licao.getPergunta6(); 
		respostaAtual = licao.getResposta6(); break;
		
		case 7: perguntaAtual = licao.getPergunta7(); 
		respostaAtual = licao.getResposta7(); break;
		
		case 8: perguntaAtual = licao.getPergunta8(); 
		respostaAtual = licao.getResposta8(); break;
		
		case 9: perguntaAtual = licao.getPergunta9(); 
		respostaAtual = licao.getResposta9(); break;
		
		case 10: perguntaAtual = licao.getPergunta10(); 
		respostaAtual = licao.getResposta10(); break;
		
		}
	}
	

	public void carregarLicao(Usuario usuario) {
		this.usuario = usuario;
		iniciada = true;
		LicaoDAO dao = new LicaoDAO();
		licao = dao.buscarPeloNivel(usuario.getNivel());
		carregarPergunta();
	}
	
	public void salvar() {
		LicaoDAO dao = new LicaoDAO();
		dao.salvar(licao);
	}
	
	public static boolean compararFrase(String frase1, String frase2) {
		String respostasCertas[] = frase2.split(" / ");
		
	    String f1 = Normalizer.normalize(frase1, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").replace("!", "").replace("?", "").replace(",", "").replace(".", "").replace(" ", "").replace("-", "").trim();
	    for(int i=0; i<respostasCertas.length; i++) {
	    	
	    String f2 = Normalizer.normalize(respostasCertas[i], Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "").replace("!", "").replace("?", "").replace(",", "").replace(".", "").replace(" ", "").replace("-", "").trim();
		    if(f1.equalsIgnoreCase(f2)) {
		    	return true;
		    }
		}
	    return false;
	}
	
	
}
