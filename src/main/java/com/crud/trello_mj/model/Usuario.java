package com.crud.trello_mj.model;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;

    @Column(name = "contrasena", nullable = false, length = 100)
    private String contrasena;

    //Definimos la relación que Usuario tiene con estado, siendo que muchos usuarios pueden tener un mismo estado
    //pero un usuario solo puede tener un estado.
    @ManyToOne
    @JoinColumn(name = "esatdo_id", nullable = false)
    private Estado estado;

    //Getters y Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
}
