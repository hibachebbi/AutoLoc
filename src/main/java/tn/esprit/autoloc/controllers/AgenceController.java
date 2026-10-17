
package tn.esprit.autoloc.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.entities.Agence;
import tn.esprit.autoloc.services.IAgenceService;

import java.util.List;

@RestController
@RequestMapping("/api/agences")
public class AgenceController {

    private final IAgenceService agenceService;

    public AgenceController(IAgenceService agenceService) {
        this.agenceService = agenceService;
    }

    @PostMapping
    public ResponseEntity<Agence> ajouter(@RequestBody Agence agence) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(agenceService.ajouter(agence));
    }

    @GetMapping
    public List<Agence> afficherTous() {
        return agenceService.afficherTous();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agence> afficherParId(@PathVariable Long id) {
        return agenceService.afficherParId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agence> modifier(
            @PathVariable Long id,
            @RequestBody Agence agence) {

        if (agence.getIdAgence() == null
                || !id.equals(agence.getIdAgence())) {
            return ResponseEntity.badRequest().build();
        }

        try {
            return ResponseEntity.ok(agenceService.modifier(agence));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        try {
            agenceService.supprimer(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
