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
public class UsuarioBean implements Serializable { // Implementar Serializable
    private static final long serialVersionUID = 1L; // Agregar serialVersionUID

    @Inject
    private UsuarioServicio usuarioServicio;

    private Usuario usuario = new Usuario(); // Usuario actual
    private String email; // Para el login
    private String contrasena; // Para el login

    // Getters y Setters
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

    // Registrar un nuevo usuario
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

    // Autenticar un usuario
    public String login() {
        Usuario usuarioAutenticado = usuarioServicio.autenticar(email, contrasena);
        if (usuarioAutenticado != null) {
            usuario = usuarioAutenticado; // Guardar el usuario en la sesión
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", usuario);
            return "dashboard?faces-redirect=true"; // Redirigir al dashboard
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Credenciales incorrectas"));
            return null; // Mostrar mensaje de error
        }
    }

    // Cerrar sesión
    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login?faces-redirect=true"; // Redirigir al login
    }
}