package br.com.quartos.controller;

import br.com.quartos.model.Predio;
import br.com.quartos.model.Quarto;
import br.com.quartos.service.QuartoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/quartos")
public class QuartoController {

    @Autowired
    QuartoService quartoService;

    @GetMapping
    public ResponseEntity getAll() {
        List<Quarto> quartos = quartoService.getAll();
        return quartos.size() > 0 ? ResponseEntity.ok(quartos) : ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable String id) {
        Quarto quarto = quartoService.getById(id);
        return quarto != null ? ResponseEntity.ok(quarto) : ResponseEntity.notFound().build();
    }

    @PostMapping("/localidade")
    public ResponseEntity getByLocalidade(@RequestParam String localidade) {
        List<Quarto> quartos = quartoService.getByLocalidade(localidade);
        return quartos.size() > 0 ? ResponseEntity.ok(quartos) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity createQuarto(@RequestBody Quarto quarto) {
        Quarto created = quartoService.newQuarto(quarto);
        return created != null ? ResponseEntity.created(URI.create(created.getId().toString())).body(created) : ResponseEntity.internalServerError().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity updateQuarto(@RequestBody Quarto quarto, @PathVariable String id) {
        Quarto updated = quartoService.editQuarto(quarto, id);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteQuarto(@PathVariable String id) {
        Boolean removed = quartoService.removeQuarto(id);
        return removed ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
