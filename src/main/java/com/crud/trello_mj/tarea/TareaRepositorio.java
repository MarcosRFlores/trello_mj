package com.crud.trello_mj.tarea;

import com.crud.trello_mj.estado.Estado;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class TareaRepositorio {

    @PersistenceContext(unitName = "jsf-crud-unit")
    private EntityManager entityManager;

    public void crear(Tarea tarea) {
        entityManager.persist(tarea);
    }

    public void actualizar(Tarea tarea) {
        entityManager.merge(tarea);
    }

    public void eliminar(Long id) {
        Tarea tarea = entityManager.find(Tarea.class, id);
        if (tarea != null) {
            entityManager.remove(tarea);
        }
    }

    public Tarea buscarPorId(Long id) {
        return entityManager.find(Tarea.class, id);
    }

    public List<Tarea> listarTodos() {
        return entityManager.createQuery("SELECT t FROM Tarea t", Tarea.class).getResultList();
    }

    public List<Tarea> listarTodosPorId(Long idUsuario) {
        return entityManager.createQuery(
                        "SELECT t FROM Tarea t WHERE t.usuario.id = :idUsuario", Tarea.class)
                .setParameter("idUsuario", idUsuario)  // 🔹 Aquí pasamos el parámetro
                .getResultList();
    }

    public List<Tarea> listarPorUsuarioYEstado(Long usuarioId, Long estadoId) {
        return entityManager.createQuery(
                        "SELECT t FROM Tarea t WHERE t.usuario.id = :usuarioId AND t.estado.id = :estadoId", Tarea.class)
                .setParameter("usuarioId", usuarioId)
                .setParameter("estadoId", estadoId)
                .getResultList();
    }

}