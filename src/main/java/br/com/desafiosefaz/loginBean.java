package br.com.desafiosefaz;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.com.desafiosefaz.entidades.Usuario;


@RequestScoped
@ManagedBean(name = "loginBean")
public class loginBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@ManagedProperty(value="#{usuarioBean}")
	private UsuarioBean usuarioBean; 
	
	private Usuario usuario= new Usuario();
	private String user;
	private String pass;
	
	public loginBean() {
		super();
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String logarSistema() throws Exception {
		
		FacesContext context = FacesContext.getCurrentInstance();
        
      

	
		try {
			
			if(usuario.getNome() == null || usuario.getNome().isEmpty() ) {
				  throw new Exception("Informe o usuário!");
				  
			}else if(usuario.getSenha() == null || usuario.getSenha().isEmpty()) {
				throw new Exception("Informe a senha!");
			}

			usuarioBean = new UsuarioBean();
			usuarioBean.setUsuario(usuario);
			
			usuarioBean.Logar();

			if (usuarioBean.getUsuario() != null) {
				return "listagem";
			}
			
		} catch (Exception e) {
			System.out.println(e);
			context.addMessage("stik", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()) );
		}
		return "";

	}

}
