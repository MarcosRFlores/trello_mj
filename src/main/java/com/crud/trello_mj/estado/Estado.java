package com.crud.trello_mj.estado;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Table(name = "estado")
@Data // Genera automáticamente los getters, setters, toString, equals, y hashCode
@NoArgsConstructor // Constructor sin parámetros
@AllArgsConstructor // Constructor con todos los parámetros
public class Estado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false, length = 15)
    private String nombre;
}