package bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.ListDataModel;

import dao.UsuarioDAO;
import domain.Usuario;

@ManagedBean(name="rankBean")
@ViewScoped
public class RankBean implements Serializable {
     
    private ListDataModel<Usuario> usuarios ;

	public ListDataModel<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(ListDataModel<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
	@PostConstruct
	public void preencherTabela() {
		UsuarioDAO dao = new UsuarioDAO();
		usuarios = new ListDataModel<Usuario>(dao.listarPorPosicao());
	}
     

}