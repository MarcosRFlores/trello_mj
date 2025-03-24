package com.crud.trello_mj.tablero;

import com.crud.trello_mj.tarea.Tarea;
import com.crud.trello_mj.tarea.TareaBean;
import com.crud.trello_mj.tarea.TareaServicio;
import com.crud.trello_mj.usuario.Usuario;
import com.crud.trello_mj.usuario.UsuarioBean;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class TableroBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private UsuarioBean usuarioBean;

    @Inject
    private TareaBean tareaBean;

    // Los 3 tableros
    private List<Tarea> tareasPendientes;
    private List<Tarea> tareasEnCurso;
    private List<Tarea> tareasFinalizada;
}