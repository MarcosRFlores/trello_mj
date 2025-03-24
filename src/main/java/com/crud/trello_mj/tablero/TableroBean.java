package com.crud.trello_mj.tablero;

import com.crud.trello_mj.estado.EstadoBean;
import com.crud.trello_mj.tarea.Tarea;
import com.crud.trello_mj.tarea.TareaBean;
import com.crud.trello_mj.tarea.TareaServicio;
import com.crud.trello_mj.usuario.Usuario;
import com.crud.trello_mj.usuario.UsuarioBean;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class TableroBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private TareaBean tareaBean;

    @Inject
    private EstadoBean estadoBean;

    // Usuario Logueado
    private Usuario usuario;

    // Los 3 tableros
    private List<Tarea> tareasPendientes;
    private List<Tarea> tareasEnCurso;
    private List<Tarea> tareasFinalizadas;

    public List<Tarea> getTareasPendientes() {
        return tareasPendientes;
    }

    public void setTareasPendientes(List<Tarea> tareasPendientes) {
        this.tareasPendientes = tareasPendientes;
    }

    public List<Tarea> getTareasEnCurso() {
        return tareasEnCurso;
    }

    public void setTareasEnCurso(List<Tarea> tareasEnCurso) {
        this.tareasEnCurso = tareasEnCurso;
    }

    public List<Tarea> getTareasFinalizadas() {
        return tareasFinalizadas;
    }

    public void setTareasFinalizadas(List<Tarea> tareasFinalizadas) {
        this.tareasFinalizadas = tareasFinalizadas;
    }

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
            cargarTareas();
        }
    }

    public void cargarTareas() {
        // Asumimos que los estados tienen IDs fijos: 1=Pendiente, 2=En curso, 3=Finalizada
        tareasPendientes = tareaBean.listarPorUsuarioYEstado(usuario.getId(), 1L);
        tareasEnCurso = tareaBean.listarPorUsuarioYEstado(usuario.getId(), 2L);
        tareasFinalizadas = tareaBean.listarPorUsuarioYEstado(usuario.getId(), 3L);
    }

    public void cambiarEstado(Long idTarea, Long idEstado) {
        estadoBean.cargarEstado(idEstado);
        tareaBean.cambiarEstado(idTarea, estadoBean.getEstado());
        cargarTareas();
    }
}