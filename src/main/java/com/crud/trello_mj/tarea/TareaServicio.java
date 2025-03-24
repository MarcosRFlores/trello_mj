package com.crud.trello_mj.tarea;

import com.crud.trello_mj.estado.Estado;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class TareaServicio {

    @Inject
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

    public List<Tarea> listarTodosPorId(Long idUsuario) {
        return tareaRepositorio.listarTodosPorId(idUsuario);
    }

    public List<Tarea> listarPorUsuarioYEstado(long idUsuario, long idEstado) {
        return tareaRepositorio.listarPorUsuarioYEstado(idUsuario, idEstado);
    }

    public void cambiarEstado(Long idTarea, Estado nuevoEstado){
        Tarea tareaEncontrada = buscarPorId(idTarea);
        if(tareaEncontrada != null){
            tareaEncontrada.setEstado(nuevoEstado);
            guardar(tareaEncontrada);
        }
    }
}
