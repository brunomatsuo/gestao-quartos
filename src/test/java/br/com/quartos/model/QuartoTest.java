package br.com.quartos.model;

import br.com.quartos.model.enums.TipoBanheiro;
import br.com.quartos.model.enums.TipoCama;
import br.com.quartos.model.enums.TipoQuarto;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class QuartoTest {

    private final Quarto quarto = new Quarto(1, new Predio(), TipoQuarto.LUXO_SIMPLES, 2, 2, TipoCama.QUEEN, new ArrayList<Movel>(), new ArrayList<Banheiro>(), BigDecimal.TEN);

    @Test
    void getId() {
        assertThat(quarto.getId().equals(1));
    }

    @Test
    void getPredio() {
        assertThat(quarto.getPredio() != null);
    }

    @Test
    void getTipo() {
        assertThat(quarto.getTipo().equals(TipoQuarto.LUXO_SIMPLES));
    }

    @Test
    void getTotalPessoas() {
        assertThat(quarto.getTotalPessoas().equals(2));
    }

    @Test
    void getTotalCamas() {
        assertThat(quarto.getTotalCamas().equals(2));
    }

    @Test
    void getTipoCama() {
        assertThat(quarto.getTipoCama().equals(TipoCama.QUEEN));
    }

    @Test
    void getMoveis() {
        assertThat(quarto.getMoveis() != null);
    }

    @Test
    void getBanheiros() {
        assertThat(quarto.getBanheiros() != null);
    }

    @Test
    void getValorDiaria() {
        assertThat(quarto.getValorDiaria() == BigDecimal.TEN);
    }

    @Test
    void getTipoBanheiros() {
        Banheiro banheiro = new Banheiro();
        banheiro.setTipoBanheiro(TipoBanheiro.PREMIUM);

        quarto.setBanheiros(List.of(banheiro));

        assertThat(quarto.getBanheiros().get(0).getTipoBanheiro().equals(TipoBanheiro.PREMIUM));
    }
}