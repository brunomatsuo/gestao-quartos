package br.com.quartos.model;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class EnderecoTest {

    private final Endereco endereco = new Endereco();

    @Test
    void getId() {
        assertThat(endereco.getId() != null);
    }

    @Test
    void getEndereco() {
        endereco.setEndereco("Teste");
        assertThat(endereco.getEndereco().equals("Teste"));
    }

    @Test
    void getCidade() {
        endereco.setCidade("Teste");
        assertThat(endereco.getCidade().equals("Teste"));
    }

    @Test
    void getEstado() {
        endereco.setEstado("Teste");
        assertThat(endereco.getEstado().equals("Teste"));
    }

    @Test
    void getCep() {
        endereco.setCep("Teste");
        assertThat(endereco.getCep().equals("Teste"));
    }
}