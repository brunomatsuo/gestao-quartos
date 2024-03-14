package br.com.quartos.model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LocalidadeTest {

    private final Localidade localidade = new Localidade();

    @Test
    void getId() {
        assertThat(localidade.getId() != null);
    }

    @Test
    void getNome() {
        localidade.setNome("Teste");
        assertThat(localidade.getNome().equals("Teste"));
    }

    @Test
    void getEndereco() {
        localidade.setEndereco(new Endereco());
        assertThat(localidade.getEndereco() != null);
    }

    @Test
    void getAmenidades() {
        localidade.setAmenidades(List.of("Teste", "Teste2"));
        assertThat(localidade.getAmenidades().size() > 1);
    }
}