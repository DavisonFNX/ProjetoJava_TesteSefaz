package br.com.desafiosefaz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.desafiosefaz.dao.DaoGeneric;
import br.com.desafiosefaz.entidades.Usuario;

@ViewScoped
@ManagedBean(name = "usuarioBean")
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{usuario}")
	private Usuario usuario = new Usuario();

	private DaoGeneric<Usuario> daoGenericUsuario = new DaoGeneric<Usuario>();

	private List<Usuario> listaUsuarios = new ArrayList<Usuario>();

	public UsuarioBean() {
		super();

	}

	@PostConstruct
	public void init() {
		try {

			FacesContext context = FacesContext.getCurrentInstance();
			Map<String, String> requestParams = context.getExternalContext().getRequestParameterMap();
			Integer Id = Integer.parseInt(requestParams.get("Id"));

			if (Id != null) {
				usuario = daoGenericUsuario.find(Id, usuario);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public String salvar() {
		daoGenericUsuario.save(usuario);
		//carregarUsuarios();
		return "listagem";
	}

	public String novo() {
		usuario = new Usuario();
		return "";

	}

	public String Logar() throws Exception {
		
		try {
			usuario = daoGenericUsuario.find(usuario.getNome(), usuario.getSenha(), usuario);
			
		} catch (Exception e) {
			throw new Exception("Usuário inválido");
		}

		return "";
	}

	public String remover() {
		daoGenericUsuario.deletePorId(usuario);
		usuario = new Usuario();
		//carregarUsuarios();
		return "listagem";
	}

	@PostConstruct
	public void carregarUsuarios() {
		listaUsuarios = daoGenericUsuario.getListEntity(Usuario.class);
	}

	public Usuario getUsuario() {
		if (usuario == null) {
			usuario = new Usuario();
		}
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public DaoGeneric<Usuario> getDaoGeneric() {
		return daoGenericUsuario;
	}

	public void setDaoGeneric(DaoGeneric<Usuario> daoGeneric) {
		this.daoGenericUsuario = daoGeneric;
	}

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

}
