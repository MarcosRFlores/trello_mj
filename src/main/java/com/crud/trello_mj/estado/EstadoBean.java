package com.crud.trello_mj.estado;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@SessionScoped
public class EstadoBean implements Serializable {

    private static final long serialVersionUID = 1L; // Requerido para serialización

    private Estado estado;
    private List<Estado> estados;

    @Inject
    private EstadoServicio estadoServicio;

    @PostConstruct
    public void init() {
        estado = new Estado();
        estados = estadoServicio.listarTodos();
    }

    // Getters y Setters
    public Estado getEstado() { return estado; }
    public void setEstado(Estado estado) { this.estado = estado; }
    public List<Estado> getEstados() { return estados; }

    public void guardar() {
        estadoServicio.guardar(estado);
        estados = estadoServicio.listarTodos();
        estado = new Estado();
    }

    public void eliminar(Long id) {
        estadoServicio.eliminar(id);
        estados = estadoServicio.listarTodos();
    }

    public void cargarEstado(Long id) {
        Estado estadoEncontrado = estadoServicio.buscarPorId(id);
        if (estadoEncontrado != null) {
            this.estado = estadoEncontrado;
        }
    }

    public EstadoServicio getEstadoServicio() {
        return estadoServicio;
    }
}
