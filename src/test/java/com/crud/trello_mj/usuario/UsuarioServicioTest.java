package com.crud.trello_mj.usuario;

import com.crud.trello_mj.exception.UsuarioExistenteException;
import com.crud.trello_mj.util.PasswordUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServicioTest {

    @Mock
    private UsuarioRepositorio usuarioRepositorio;

    @InjectMocks
    private UsuarioServicio usuarioServicio;

    @Test
    public void testRegistrarUsuarioExitoso() throws UsuarioExistenteException {
        Usuario usuario = new Usuario();
        usuario.setEmail("nuevo@example.com");
        usuario.setContrasena("password123");

        when(usuarioRepositorio.buscarPorEmail("nuevo@example.com")).thenReturn(null);

        usuarioServicio.registrarUsuario(usuario);

        verify(usuarioRepositorio).guardar(usuario);
        assertTrue(usuario.getContrasena().startsWith("$2a$")); // Verificar que se encriptó
    }

    @Test
    public void testRegistrarUsuarioExistente() {
        Usuario usuarioExistente = new Usuario();
        usuarioExistente.setEmail("existente@example.com");

        when(usuarioRepositorio.buscarPorEmail("existente@example.com")).thenReturn(usuarioExistente);

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setEmail("existente@example.com");

        assertThrows(UsuarioExistenteException.class, () -> {
            usuarioServicio.registrarUsuario(nuevoUsuario);
        });
    }
}