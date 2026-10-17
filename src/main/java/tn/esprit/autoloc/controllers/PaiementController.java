
package tn.esprit.autoloc.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.entities.Paiement;
import tn.esprit.autoloc.services.IPaiementService;

import java.util.List;

@RestController
@RequestMapping("/api/paiements")
public class PaiementController {

    private final IPaiementService paiementService;

    public PaiementController(IPaiementService paiementService) {
        this.paiementService = paiementService;
    }

    @PostMapping
    public ResponseEntity<Paiement> ajouter(
            @RequestBody Paiement entity) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(paiementService.ajouter(entity));
    }

    @GetMapping
    public List<Paiement> afficherTous() {
        return paiementService.afficherTous();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paiement> afficherParId(
            @PathVariable Long id) {
        return paiementService.afficherParId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Paiement> modifier(
            @PathVariable Long id, @RequestBody Paiement entity) {
        if (entity.getIdPaiement() == null
                || !id.equals(entity.getIdPaiement())) {
            return ResponseEntity.badRequest().build();
        }
        try {
            return ResponseEntity.ok(paiementService.modifier(entity));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        try {
            paiementService.supprimer(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
