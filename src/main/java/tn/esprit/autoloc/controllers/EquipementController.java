
package tn.esprit.autoloc.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.entities.Equipement;
import tn.esprit.autoloc.services.IEquipementService;

import java.util.List;

@RestController
@RequestMapping("/api/equipements")
public class EquipementController {

    private final IEquipementService equipementService;

    public EquipementController(IEquipementService equipementService) {
        this.equipementService = equipementService;
    }

    @PostMapping
    public ResponseEntity<Equipement> ajouter(
            @RequestBody Equipement entity) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(equipementService.ajouter(entity));
    }

    @GetMapping
    public List<Equipement> afficherTous() {
        return equipementService.afficherTous();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Equipement> afficherParId(
            @PathVariable Long id) {
        return equipementService.afficherParId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Equipement> modifier(
            @PathVariable Long id, @RequestBody Equipement entity) {
        if (entity.getIdEquipement() == null
                || !id.equals(entity.getIdEquipement())) {
            return ResponseEntity.badRequest().build();
        }
        try {
            return ResponseEntity.ok(equipementService.modifier(entity));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        try {
            equipementService.supprimer(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
