package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import dao.UsuarioDAO;
import domain.Usuario;

@ManagedBean
@SessionScoped
public class UsuarioBean {

	private Usuario usuario = new Usuario();
	
	private String confirmarSenha;
	
	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void salvar() {
		UsuarioDAO dao = new UsuarioDAO();
		if (dao.bucarPorLoginSenha(usuario)!=null) {
			Messages.addGlobalError("Esse Login já está em uso!");
		} else if(!(usuario.getSenha().equals(confirmarSenha))){
			Messages.addGlobalError("As senhas estão diferentes!");
		}else {
			dao.salvar(usuario);
			Messages.addGlobalInfo("Cadastrado com sucesso!");
			usuario.setLogin("");
			usuario.setSenha("");
			confirmarSenha = "";
			usuario.setNome("");
		}
//		FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_ERROR,texto,texto);
//		FacesContext contexto = FacesContext.getCurrentInstance();
//		contexto.addMessage(null, mensagem);

	}
	
	
	public String logar() {
		UsuarioDAO dao = new UsuarioDAO();
		if (dao.bucarPorLoginSenha(usuario)!=null) {
			usuario = dao.bucarPorLoginSenha(usuario);
			Messages.addGlobalInfo("Logado!");
			return "/pages/inicio.xhtml?faces-redirect=true";
		} else {
		//Retorno de erro caso erre a senha ou login.
			Messages.addGlobalError("Conta Inválida!");
		}
		return null;
	}
	
	public void aumentarPontos() {
		usuario.setPontos(usuario.getPontos()+25);
		UsuarioDAO dao = new UsuarioDAO();
		dao.editar(usuario);
	}
	public void aumentarNivel() {
		usuario.setNivel(usuario.getNivel()+1);
		UsuarioDAO dao = new UsuarioDAO();
		dao.editar(usuario);
	}
	
}
