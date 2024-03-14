package br.com.quartos.model;

import br.com.quartos.model.enums.TipoBanheiro;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Banheiro {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private TipoBanheiro tipoBanheiro;
}
