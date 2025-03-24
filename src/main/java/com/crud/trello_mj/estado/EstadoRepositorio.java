package com.crud.trello_mj.estado;

import com.crud.trello_mj.estado.Estado;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class EstadoRepositorio {

    @PersistenceContext(unitName = "jsf-crud-unit")
    private EntityManager entityManager;

    @PostConstruct
    public void init() {
        if (listarTodos().isEmpty()) {
            entityManager.persist(new Estado(null, "pendiente"));
            entityManager.persist(new Estado(null, "en curso"));
            entityManager.persist(new Estado(null, "finalizado"));
        }
    }

    public void crear(Estado estado) {
        entityManager.persist(estado);
    }

    public void actualizar(Estado estado) {
        entityManager.merge(estado);
    }

    public void eliminar(Long id) {
        Estado estado = entityManager.find(Estado.class, id);
        if (estado != null) {
            entityManager.remove(estado);
        }
    }

    public Estado buscarPorId(Long id) {
        return entityManager.find(Estado.class, id);
    }

    public List<Estado> listarTodos() {
        return entityManager.createQuery("SELECT e FROM Estado e", Estado.class).getResultList();
    }
}
