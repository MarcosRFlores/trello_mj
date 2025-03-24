package com.crud.trello_mj.tarea;

import com.crud.trello_mj.estado.Estado;
import com.crud.trello_mj.usuario.Usuario;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@ManagedBean
@SessionScoped
public class TareaBean implements Serializable {
    private static final long serialVersionUID = 1L;

    private Tarea tarea;
    private List<Tarea> tareas;
    private Usuario usuario;

    @Inject
    private TareaServicio tareaServicio;

    @PostConstruct
    public void init() {
        FacesContext context = FacesContext.getCurrentInstance();

        usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuario");

        if (usuario == null) {
            try {
                context.getExternalContext().redirect("login.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            tarea = new Tarea();
            cargarTareasPorId();
        }
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
        if (usuario != null) {
            tarea.setUsuario(usuario); // Asigna el usuario logueado a la tarea
            tareaServicio.guardar(tarea);
            cargarTareasPorId();
            tarea = new Tarea();
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Usuario no autenticado"));
        }
    }

    public void eliminar(Long id){
        tareaServicio.eliminar(id);
        tareas = tareaServicio.listarTodosPorId(usuario.getId());
    }

    public void cargarTarea(Long id){
        Tarea tareaEncontrada = tareaServicio.buscarPorId(id);
        if(tareaEncontrada != null){
            this.tarea = tareaEncontrada;
        }
    }

    public void cargarTareasPorId(){
        tareas = tareaServicio.listarTodosPorId(usuario.getId());
    }

    public List<Tarea> listarPorUsuarioYEstado(long idUsuario, long idEstado) {
        return tareaServicio.listarPorUsuarioYEstado(idUsuario, idEstado);
    }

    public void cambiarEstado(Long idTarea, Estado nuevoEstado){
        tareaServicio.cambiarEstado(idTarea, nuevoEstado);
    }
}