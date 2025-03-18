package com.crud.trello_mj.model;

import javax.persistence.*;

@Entity
@Table(name = "rol")
public class Rol {

    //El id del rol es autogenerado
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    //El nombre del rol no puede ser nulo y debe ser único
    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;

    //Getters y Setter
    public Long getId() {
        return id;
    }

    public void setId( Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
