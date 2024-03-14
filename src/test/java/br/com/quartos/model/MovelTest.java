package br.com.quartos.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MovelTest {

    private final Movel movel = new Movel("Cadeira");

    @Test
    void getId() {
        assertThat(movel.getId() != null);
    }

    @Test
    void getNome() {
        assertThat(movel.getNome().equals("Cadeira"));
    }

    @Test
    void getQuantidade() {
        movel.setQuantidade(2);
        assertThat(movel.getQuantidade().equals(2));
    }
}