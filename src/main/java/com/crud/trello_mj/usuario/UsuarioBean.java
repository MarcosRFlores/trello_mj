package com.crud.trello_mj.usuario;

import com.crud.trello_mj.exception.UsuarioExistenteException;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class UsuarioBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Inject
    private UsuarioServicio usuarioServicio;

    private Usuario usuario = new Usuario();
    private String email;
    private String contrasena;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


    public String registrar() {
        try {
            usuarioServicio.registrarUsuario(usuario);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Usuario registrado exitosamente"));
            usuario = new Usuario(); // Limpiar el formulario
            return "login?faces-redirect=true"; // Redirigir al login
        } catch (UsuarioExistenteException e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: " + e.getMessage(), null));
            return null; // No redirigir, mostrar mensaje de error
        }
    }


    public String login() {
        Usuario usuarioAutenticado = usuarioServicio.autenticar(email, contrasena);
        if (usuarioAutenticado != null) {
            usuario = usuarioAutenticado; // Guardar el usuario en la sesión
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuario);
            return "/protegido/tablero.xhtml?faces-redirect=true"; // Redirigir al tablero
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Credenciales incorrectas :("));
            return null; // Mostrar mensaje de error
        }
    }


    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login?faces-redirect=true"; // Redirigir al login
    }

    public boolean isLoggedIn() {
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario") != null;
    }

}