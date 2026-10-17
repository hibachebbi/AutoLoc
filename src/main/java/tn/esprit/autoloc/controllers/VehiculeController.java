
package tn.esprit.autoloc.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.entities.Vehicule;
import tn.esprit.autoloc.services.IVehiculeService;

import java.util.List;

@RestController
@RequestMapping("/api/vehicules")
public class VehiculeController {

    private final IVehiculeService vehiculeService;

    public VehiculeController(IVehiculeService vehiculeService) {
        this.vehiculeService = vehiculeService;
    }

    @PostMapping
    public ResponseEntity<Vehicule> ajouter(
            @RequestBody Vehicule entity) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(vehiculeService.ajouter(entity));
    }

    @GetMapping
    public List<Vehicule> afficherTous() {
        return vehiculeService.afficherTous();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicule> afficherParId(
            @PathVariable Long id) {
        return vehiculeService.afficherParId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehicule> modifier(
            @PathVariable Long id, @RequestBody Vehicule entity) {
        if (entity.getIdVehicule() == null
                || !id.equals(entity.getIdVehicule())) {
            return ResponseEntity.badRequest().build();
        }
        try {
            return ResponseEntity.ok(vehiculeService.modifier(entity));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        try {
            vehiculeService.supprimer(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
