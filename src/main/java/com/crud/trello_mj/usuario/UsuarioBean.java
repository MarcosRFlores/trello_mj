package com.crud.trello_mj.usuario;

import com.crud.trello_mj.exception.UsuarioExistenteException;
import com.crud.trello_mj.util.PasswordUtil;


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
    private String contrasenaActual;
    private String nuevaContrasena;
    private String confirmarContrasena;


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
        return "/auth/login?faces-redirect=true";
    }

    public boolean isLoggedIn() {
        return FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario") != null;
    }
    public String cambiarContrasena() {
        FacesContext context = FacesContext.getCurrentInstance();

        // Validar que la contraseña actual sea correcta
        if (!PasswordUtil.checkPassword(contrasenaActual, usuario.getContrasena())) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: La contraseña actual es incorrecta", null));
            return null; // No redirigir, mostrar mensaje de error
        }

        // Validar que la nueva contraseña y la confirmación coincidan
        if (!nuevaContrasena.equals(confirmarContrasena)) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: Las contraseñas no coinciden", null));
            return null; // No redirigir, mostrar mensaje de error
        }

        // Cambiar la contraseña
        String encryptedPassword = PasswordUtil.encryptPassword(nuevaContrasena);
        usuario.setContrasena(encryptedPassword);
        usuarioServicio.actualizarUsuario(usuario);

        // Limpiar los campos
        contrasenaActual = null;
        nuevaContrasena = null;
        confirmarContrasena = null;

        // Mostrar mensaje de éxito
        context.addMessage(null, new FacesMessage("Contraseña cambiada exitosamente"));

        // Redirigir al perfil después de cambiar la contraseña
        return "perfil?faces-redirect=true";
    }

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

    public String getContrasenaActual() {return contrasenaActual;}

    public void setContrasenaActual(String contrasenaActual) {this.contrasenaActual = contrasenaActual;}

    // Getter y Setter para nuevaContrasena
    public String getNuevaContrasena() {return nuevaContrasena;}

    public void setNuevaContrasena(String nuevaContrasena) {this.nuevaContrasena = nuevaContrasena;}

    // Getter y Setter para confirmarContrasena
    public String getConfirmarContrasena() { return confirmarContrasena;}

    public void setConfirmarContrasena(String confirmarContrasena) {this.confirmarContrasena = confirmarContrasena;}



}