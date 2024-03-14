package br.com.quartos.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Entity
@Data
public class Localidade {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String nome;
    @OneToOne(cascade = CascadeType.ALL)
    private Endereco endereco;
    private List<String> amenidades;
}
