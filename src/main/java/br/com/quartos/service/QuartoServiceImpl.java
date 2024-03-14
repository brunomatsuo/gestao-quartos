package br.com.quartos.service;

import br.com.quartos.model.Banheiro;
import br.com.quartos.model.Movel;
import br.com.quartos.model.Predio;
import br.com.quartos.model.Quarto;
import br.com.quartos.model.enums.TipoBanheiro;
import br.com.quartos.model.enums.TipoCama;
import br.com.quartos.model.enums.TipoQuarto;
import br.com.quartos.repository.QuartoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class QuartoServiceImpl implements QuartoService {

    @Autowired
    QuartoRepository quartoRepository;

    @Override
    public List<Quarto> getAll() {
        return quartoRepository.findAll();
    }

    @Override
    public Quarto getById(String id) {
        return quartoRepository.findById(Integer.parseInt(id)).orElse(null);
    }

    @Override
    public List<Quarto> getByLocalidade(String localidade) {
        return quartoRepository.findAll().stream().filter(quarto -> quarto.getPredio().getLocalidade().getNome().equals(localidade)).collect(Collectors.toList());
    }

    @Override
    public Quarto newQuarto(Quarto quarto) {
        TipoQuarto tipoQuarto = quarto.getTipo();
        quarto.setTipoCama(getTipoCama(tipoQuarto));
        quarto.setTotalCamas(getQuantidadeCamas(tipoQuarto));
        quarto.setTotalPessoas(getQuantidadePessoas(tipoQuarto));
        quarto.setBanheiros(getBanheiros(tipoQuarto));
        quarto.setMoveis(getMoveis(tipoQuarto));
        quarto.setValorDiaria(getValorDiaria(tipoQuarto));
        return quartoRepository.save(quarto);
    }

    @Override
    public Quarto editQuarto(Quarto quarto, String id) {
        Quarto quartoAtual = quartoRepository.findById(Integer.parseInt(id)).orElse(null);
        if(quartoAtual != null) {
            quarto.setId(quartoAtual.getId());
            TipoQuarto tipoQuarto = quarto.getTipo();
            quarto.setTipoCama(getTipoCama(tipoQuarto));
            quarto.setTotalCamas(getQuantidadeCamas(tipoQuarto));
            quarto.setTotalPessoas(getQuantidadePessoas(tipoQuarto));
            quarto.setBanheiros(getBanheiros(tipoQuarto));
            quarto.setMoveis(getMoveis(tipoQuarto));
            quarto.setValorDiaria(getValorDiaria(tipoQuarto));
            return quartoRepository.save(quarto);
        }
        return null;
    }

    @Override
    public Boolean removeQuarto(String id) {
        Quarto quarto = quartoRepository.findById(Integer.parseInt(id)).orElse(null);
        if(quarto != null) {
            quartoRepository.deleteById(Integer.parseInt(id));
            return true;
        }
        return false;
    }

    private TipoCama getTipoCama(TipoQuarto tipoQuarto) {
        switch (tipoQuarto) {
            case STANDARD_SIMPLES:
                return TipoCama.SOLTEIRO;
            case STANDARD_DUPLO:
                return TipoCama.CASAL;
            case LUXO_SIMPLES:
            case LUXO_DUPLO:
                return TipoCama.QUEEN;
            case PREMIUM_SIMPLES:
            case PREMIUM_DUPLO:
                return TipoCama.KING;
        }
        return TipoCama.SOLTEIRO;
    }

    private Integer getQuantidadeCamas(TipoQuarto tipoQuarto) {
        switch (tipoQuarto) {
            case STANDARD_SIMPLES:
            case LUXO_SIMPLES:
            case PREMIUM_SIMPLES:
                return 1;
            case STANDARD_DUPLO:
            case LUXO_DUPLO:
            case PREMIUM_DUPLO:
                return 2;
        }
        return 1;
    }

    private Integer getQuantidadePessoas(TipoQuarto tipoQuarto) {
        switch (tipoQuarto) {
            case STANDARD_SIMPLES:
                return 1;
            case STANDARD_DUPLO:
            case LUXO_SIMPLES:
            case PREMIUM_SIMPLES:
                return 2;
            case LUXO_DUPLO:
            case PREMIUM_DUPLO:
                return 4;
        }
        return 1;
    }

    private Banheiro getBanheiroPorTipo(TipoQuarto tipoQuarto) {
        Banheiro banheiro = new Banheiro();
        switch (tipoQuarto) {
            case STANDARD_SIMPLES:
            case STANDARD_DUPLO:
            {
                banheiro.setTipoBanheiro(TipoBanheiro.SIMPLES);
            }
            case LUXO_SIMPLES:
            {
                banheiro.setTipoBanheiro(TipoBanheiro.LUXO);
            }
            case LUXO_DUPLO:
            {
                banheiro.setTipoBanheiro(TipoBanheiro.LUXO);
            }
            case PREMIUM_SIMPLES:
            case PREMIUM_DUPLO:
            {
                banheiro.setTipoBanheiro(TipoBanheiro.PREMIUM);
            }
        }
        return banheiro;
    }

    private List<Banheiro> getBanheiros(TipoQuarto tipoQuarto) {
        List<Banheiro> listaBanheiros = new ArrayList<>();
        int quantidade = 0;
        switch (tipoQuarto) {
            case STANDARD_SIMPLES:
            case LUXO_SIMPLES:
            case PREMIUM_SIMPLES:
                quantidade = 1;
            case STANDARD_DUPLO:
            case LUXO_DUPLO:
            case PREMIUM_DUPLO:
                quantidade = 2;
        }
        for(int i = 0; i < quantidade; i++){
            Banheiro banheiro = getBanheiroPorTipo(tipoQuarto);
            listaBanheiros.add(banheiro);
        }
        return listaBanheiros;
    }

    private List<Movel> getMoveis(TipoQuarto tipoQuarto) {
        List<Movel> moveis = new ArrayList<>();
        Movel sofa = new Movel("Sofá");
        Movel frigobar = new Movel("Frigobar");
        Movel tv = new Movel("TV LED");
        Movel cofre = new Movel("Cofre");
        Movel escrivaninha = new Movel("Escrivaninha");
        Movel cadeira = new Movel("Cadeira de escritório");
        switch (tipoQuarto) {
            case STANDARD_SIMPLES:
            case STANDARD_DUPLO:
            case LUXO_SIMPLES: {
                tv.setQuantidade(1);
                frigobar.setQuantidade(1);
                cofre.setQuantidade(1);
                escrivaninha.setQuantidade(1);
                cadeira.setQuantidade(1);
                moveis.add(tv);
                moveis.add(frigobar);
                moveis.add(cofre);
                moveis.add(cadeira);
                moveis.add(escrivaninha);
            }
            case LUXO_DUPLO:
            case PREMIUM_DUPLO:
            case PREMIUM_SIMPLES: {
                sofa.setQuantidade(1);
                tv.setQuantidade(2);
                frigobar.setQuantidade(2);
                cofre.setQuantidade(2);
                escrivaninha.setQuantidade(2);
                cadeira.setQuantidade(2);
                moveis.add(sofa);
                moveis.add(tv);
                moveis.add(frigobar);
                moveis.add(cofre);
                moveis.add(escrivaninha);
                moveis.add(cadeira);
            }
        }
        return moveis;
    }

    private BigDecimal getValorDiaria(TipoQuarto tipoQuarto) {
        switch (tipoQuarto) {
            case STANDARD_SIMPLES:
                return BigDecimal.valueOf(159.90);
            case STANDARD_DUPLO:
                return BigDecimal.valueOf(179.90);
            case LUXO_SIMPLES:
                return BigDecimal.valueOf(199.90);
            case LUXO_DUPLO:
                return BigDecimal.valueOf(259.90);
            case PREMIUM_SIMPLES:
                return BigDecimal.valueOf(299.90);
            case PREMIUM_DUPLO:
                return BigDecimal.valueOf(399.90);
        }
        return BigDecimal.ZERO;
    }
}
