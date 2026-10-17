
package tn.esprit.autoloc.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.entities.Contrat;
import tn.esprit.autoloc.services.IContratService;

import java.util.List;

@RestController
@RequestMapping("/api/contrats")
public class ContratController {

    private final IContratService contratService;

    public ContratController(IContratService contratService) {
        this.contratService = contratService;
    }

    @PostMapping
    public ResponseEntity<Contrat> ajouter(@RequestBody Contrat entity) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(contratService.ajouter(entity));
    }

    @GetMapping
    public List<Contrat> afficherTous() {
        return contratService.afficherTous();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contrat> afficherParId(@PathVariable Long id) {
        return contratService.afficherParId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contrat> modifier(
            @PathVariable Long id, @RequestBody Contrat entity) {
        if (entity.getIdContrat() == null
                || !id.equals(entity.getIdContrat())) {
            return ResponseEntity.badRequest().build();
        }
        try {
            return ResponseEntity.ok(contratService.modifier(entity));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        try {
            contratService.supprimer(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
