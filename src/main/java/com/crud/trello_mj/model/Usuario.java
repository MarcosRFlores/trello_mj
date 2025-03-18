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

    //Definimos la relación que Usuario tiene con Rol, siendo que muchos usuarios pueden tener un mismo rol
    //pero un usuario solo puede tener un rol.
    @ManyToOne
    @JoinColumn(name = "rol_id", nullable = false)
    private Rol rol;

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

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
