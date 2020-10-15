package br.com.desafiosefaz;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

import br.com.desafiosefaz.entidades.Usuario;


@ViewScoped
@ManagedBean(name = "listagemBean")
public class listagemBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value="#{usuarioBean}")
	private UsuarioBean usuarioBean; 
	
	private List<Usuario> listaUsuarios = new ArrayList<Usuario>();
	
	public listagemBean() {
		super();
	}
	
	@PostConstruct
	public void init() {
		usuarioBean = new UsuarioBean();
		usuarioBean.carregarUsuarios();
		listaUsuarios = usuarioBean.getListaUsuarios();
	}
	
	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}
	
	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}




}
