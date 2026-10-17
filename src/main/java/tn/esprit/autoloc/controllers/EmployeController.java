
package tn.esprit.autoloc.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.entities.Employe;
import tn.esprit.autoloc.services.IEmployeService;

import java.util.List;

@RestController
@RequestMapping("/api/employes")
public class EmployeController {

    private final IEmployeService employeService;

    public EmployeController(IEmployeService employeService) {
        this.employeService = employeService;
    }

    @PostMapping
    public ResponseEntity<Employe> ajouter(@RequestBody Employe entity) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(employeService.ajouter(entity));
    }

    @GetMapping
    public List<Employe> afficherTous() {
        return employeService.afficherTous();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employe> afficherParId(@PathVariable Long id) {
        return employeService.afficherParId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employe> modifier(
            @PathVariable Long id, @RequestBody Employe entity) {
        if (entity.getIdEmploye() == null
                || !id.equals(entity.getIdEmploye())) {
            return ResponseEntity.badRequest().build();
        }
        try {
            return ResponseEntity.ok(employeService.modifier(entity));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        try {
            employeService.supprimer(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
