package com.crud.trello_mj.estado;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@Stateless
public class EstadoServicio {

    @EJB
    private EstadoRepositorio estadoRepositorio;

    public void guardar(Estado estado) {
        if (estado.getId() == null) {
            estadoRepositorio.crear(estado);
        } else {
            estadoRepositorio.actualizar(estado);
        }
    }

    public void eliminar(Long id) {
        estadoRepositorio.eliminar(id);
    }

    public Estado buscarPorId(Long id) {
        return estadoRepositorio.buscarPorId(id);
    }

    public List<Estado> listarTodos() {
        return estadoRepositorio.listarTodos();
    }
}
