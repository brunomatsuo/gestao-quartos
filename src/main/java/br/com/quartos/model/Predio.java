package br.com.quartos.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Predio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne(cascade = CascadeType.ALL)
    private Localidade localidade;
    private String nome;
}
