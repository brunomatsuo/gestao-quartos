package br.com.quartos.model;

import br.com.quartos.model.enums.TipoCama;
import br.com.quartos.model.enums.TipoQuarto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quarto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @ManyToOne(cascade = CascadeType.ALL)
    private Predio predio;
    private TipoQuarto tipo;
    private Integer totalPessoas;
    private Integer totalCamas;
    private TipoCama tipoCama;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Movel> moveis;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Banheiro> banheiros;
    private BigDecimal valorDiaria;
}
