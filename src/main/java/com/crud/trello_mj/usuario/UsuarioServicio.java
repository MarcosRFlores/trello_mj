package com.crud.trello_mj.usuario;

import com.crud.trello_mj.util.PasswordUtil;
import com.crud.trello_mj.exception.UsuarioExistenteException;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class UsuarioServicio {

    @Inject
    private UsuarioRepositorio usuarioRepositorio;

    // Registrar un nuevo usuario
    public void registrarUsuario(Usuario usuario) throws UsuarioExistenteException {
        Usuario usuarioExistente = usuarioRepositorio.buscarPorEmail(usuario.getEmail());
        if (usuarioExistente != null) {
            throw new UsuarioExistenteException("El email ya está registrado");
        }
        String Contrasena = usuario.getContrasena();
        if (Contrasena.length() < 8) {
            throw new UsuarioExistenteException("La contraseña debe tener al menos 8 caracteres");
        }
        // Encriptar la contraseña antes de guardarla
        String encryptedPassword = PasswordUtil.encryptPassword(usuario.getContrasena());
        usuario.setContrasena(encryptedPassword);
        usuarioRepositorio.guardar(usuario);
    }

    // Autenticar un usuario
    public Usuario autenticar(String email, String contrasena) {
        Usuario usuario = usuarioRepositorio.buscarPorEmail(email);
        if (usuario != null && PasswordUtil.checkPassword(contrasena, usuario.getContrasena())) {
            return usuario; // Autenticación exitosa
        }
        return null; // Autenticación fallida
    }

    public void actualizarUsuario(Usuario usuario) {
        usuarioRepositorio.actualizarUsuario(usuario);
    }

    // Buscar un usuario por su email
    public Usuario buscarPorEmail(String email) {
        return usuarioRepositorio.buscarPorEmail(email);
    }

    // Buscar un usuario por su ID
    public Usuario buscarPorId(Long id) {
        return usuarioRepositorio.buscarPorId(id);
    }

    // Listar todos los usuarios
    public List<Usuario> listarTodos() {
        return usuarioRepositorio.listarTodos();
    }
}