package com.crud.trello_mj.tarea;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
public class TareaBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private Tarea tarea;
    private List<Tarea> tareas;

    @Inject
    private TareaServicio tareaServicio;

    @PostConstruct
    public void init() {
        tarea = new Tarea();
        tareas = tareaServicio.listarTodos();
    }

    //Getters and Setters
    public Tarea getTarea() {
        return tarea;
    }

    public void setTarea(Tarea tarea) {
        this.tarea = tarea;
    }

    public List<Tarea> getTareas() {
        return tareas;
    }

    public void setTareas(List<Tarea> tareas) {
        this.tareas = tareas;
    }
    // Fin getters and setters

    public void guardar(){
        tareaServicio.guardar(tarea);
        tareas = tareaServicio.listarTodos();
        tarea = new Tarea();
    }

    public void eliminar(Long id){
        tareaServicio.eliminar(id);
        tareas = tareaServicio.listarTodos();
    }

    public void cargarTarea(Long id){
        Tarea tareaEncontrada = tareaServicio.buscarPorId(id);
        if(tareaEncontrada != null){
            this.tarea = tareaEncontrada;
        }
    }
}