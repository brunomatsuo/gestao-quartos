package br.com.quartos.repository;

import br.com.quartos.model.Predio;
import br.com.quartos.model.Quarto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuartoRepository extends JpaRepository<Quarto, Integer> {
    List<Quarto> findByPredio(Predio predio);
}
