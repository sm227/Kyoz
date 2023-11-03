package com.example.obm.menu;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;


@Getter
@Setter
@Entity
public class Menu {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column (columnDefinition = "TEXT")
    private String content;
}
