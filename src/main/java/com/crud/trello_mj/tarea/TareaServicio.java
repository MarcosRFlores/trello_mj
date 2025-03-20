package com.crud.trello_mj.tarea;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class TareaServicio {
    @EJB
    private TareaRepositorio tareaRepositorio;

    public void guardar(Tarea tarea) {
        if (tarea.getId() == null) {
            tareaRepositorio.crear(tarea);
        } else {
            tareaRepositorio.actualizar(tarea);
        }
    }

    public void eliminar(Long id) {
        tareaRepositorio.eliminar(id);
    }

    public Tarea buscarPorId(Long id) {
        return tareaRepositorio.buscarPorId(id);
    }

    public List<Tarea> listarTodos() {
        return tareaRepositorio.listarTodos();
    }
}
