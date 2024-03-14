package br.com.quartos.service;

import br.com.quartos.model.Predio;
import br.com.quartos.model.Quarto;

import java.util.List;

public interface QuartoService {
    List<Quarto> getAll();
    Quarto getById(String id);
    List<Quarto> getByLocalidade(String localidade);
    Quarto newQuarto(Quarto quarto);
    Quarto editQuarto(Quarto quarto, String id);
    Boolean removeQuarto(String id);
}
