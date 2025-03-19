package com.crud.trello_mj.usuario;

import com.crud.trello_mj.util.PasswordUtil;
import javax.inject.Inject;
import java.util.List;

public class UsuarioServicio {

    @Inject
    private UsuarioRepositorio usuarioRepositorio;

    // Registrar un nuevo usuario
    public void registrarUsuario(Usuario usuario) {
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

    // Buscar un usuario por su email
    public Usuario buscarPorEmail(String email) {
        return usuarioRepositorio.buscarPorEmail(email);
    }

    // Buscar un usuario por su ID
    public Usuario buscarPorId(long id) {
        return usuarioRepositorio.buscarPorId(id);
    }

    // Listar todos los usuarios
    public List<Usuario> listarTodos() {
        return usuarioRepositorio.listarTodos();
    }
}