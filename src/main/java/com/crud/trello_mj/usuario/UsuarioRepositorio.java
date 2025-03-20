package com.crud.trello_mj.usuario;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

public class UsuarioRepositorio {

    @PersistenceContext
    private EntityManager em;

    // Guardar un usuario en la base de datos
    @Transactional
    public void guardar(Usuario usuario) {
        em.persist(usuario);
    }

    // Buscar un usuario por su email
    public Usuario buscarPorEmail(String email) {
        TypedQuery<Usuario> query = em.createQuery(
                "SELECT u FROM Usuario u WHERE u.email = :email", Usuario.class);
        query.setParameter("email", email);
        List<Usuario> resultados = query.getResultList();
        return resultados.isEmpty() ? null : resultados.get(0);
    }

    // Buscar un usuario por su ID
    public Usuario buscarPorId(Long id) {
        return em.find(Usuario.class, id);
    }

    // Listar todos los usuarios
    public List<Usuario> listarTodos() {
        TypedQuery<Usuario> query = em.createQuery("SELECT u FROM Usuario u", Usuario.class);
        return query.getResultList();
    }
}