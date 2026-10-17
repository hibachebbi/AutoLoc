
package tn.esprit.autoloc.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.entities.Maintenance;
import tn.esprit.autoloc.services.IMaintenanceService;

import java.util.List;

@RestController
@RequestMapping("/api/maintenances")
public class MaintenanceController {

    private final IMaintenanceService maintenanceService;

    public MaintenanceController(IMaintenanceService maintenanceService) {
        this.maintenanceService = maintenanceService;
    }

    @PostMapping
    public ResponseEntity<Maintenance> ajouter(
            @RequestBody Maintenance entity) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(maintenanceService.ajouter(entity));
    }

    @GetMapping
    public List<Maintenance> afficherTous() {
        return maintenanceService.afficherTous();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Maintenance> afficherParId(
            @PathVariable Long id) {
        return maintenanceService.afficherParId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Maintenance> modifier(
            @PathVariable Long id, @RequestBody Maintenance entity) {
        if (entity.getIdMaintenance() == null
                || !id.equals(entity.getIdMaintenance())) {
            return ResponseEntity.badRequest().build();
        }
        try {
            return ResponseEntity.ok(maintenanceService.modifier(entity));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        try {
            maintenanceService.supprimer(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
