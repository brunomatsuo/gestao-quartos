package br.com.quartos.service;

import br.com.quartos.model.Banheiro;
import br.com.quartos.model.Movel;
import br.com.quartos.model.Predio;
import br.com.quartos.model.Quarto;
import br.com.quartos.model.enums.TipoCama;
import br.com.quartos.model.enums.TipoQuarto;
import br.com.quartos.repository.QuartoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class QuartoServiceImplTest {

    @Mock
    QuartoRepository quartoRepository;

    private final Quarto quarto1 = new Quarto(1, new Predio(), TipoQuarto.LUXO_SIMPLES, 2, 2, TipoCama.QUEEN, new ArrayList<Movel>(), new ArrayList<Banheiro>(), BigDecimal.TEN);
    private final Quarto quarto2 = new Quarto(1, new Predio(), TipoQuarto.PREMIUM_DUPLO, 4, 2, TipoCama.KING, new ArrayList<Movel>(), new ArrayList<Banheiro>(), BigDecimal.TEN);


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAll() {
        createQuartos();
        List<Quarto> quartos = Arrays.asList(quarto1, quarto2);

        Mockito.when(quartoRepository.findAll()).thenReturn(List.of(quarto1, quarto2));

        List<Quarto> listaQuartos = quartoRepository.findAll();

        assertEquals(quartos, listaQuartos);
    }

    private void createQuartos() {
        quartoRepository.save(quarto1);
        quartoRepository.save(quarto2);
    }

    @Test
    void getById() {
        createQuartos();

        Mockito.when(quartoRepository.findById(quarto1.getId())).thenReturn(Optional.of(quarto1));

        Quarto quarto = quartoRepository.findById(quarto1.getId()).orElse(null);

        assertEquals(quarto, quarto1);
    }

    @Test
    void newQuarto() {
        Mockito.when(quartoRepository.save(quarto1)).thenReturn(quarto1);

        Quarto quarto = quartoRepository.save(quarto1);

        assertEquals(quarto, quarto1);
    }

    @Test
    void editQuarto() {
        createQuartos();

        quarto1.setTotalPessoas(1);

        Mockito.when(quartoRepository.save(quarto1)).thenReturn(quarto1);

        Quarto quarto = quartoRepository.save(quarto1);
        assertEquals(quarto, quarto1);
    }

    @Test
    void removeQuarto() {
        createQuartos();

        Mockito.when(quartoRepository.findById(quarto1.getId())).thenReturn(null);

        quartoRepository.deleteById(quarto1.getId());
        Optional<Quarto> quarto = quartoRepository.findById(quarto1.getId());

        assertEquals(quarto, null);
    }
}