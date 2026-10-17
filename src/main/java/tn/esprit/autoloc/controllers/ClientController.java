
package tn.esprit.autoloc.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.entities.Client;
import tn.esprit.autoloc.services.IClientService;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final IClientService clientService;

    public ClientController(IClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<Client> ajouter(@RequestBody Client entity) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(clientService.ajouter(entity));
    }

    @GetMapping
    public List<Client> afficherTous() {
        return clientService.afficherTous();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> afficherParId(@PathVariable Long id) {
        return clientService.afficherParId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> modifier(
            @PathVariable Long id, @RequestBody Client entity) {
        if (entity.getIdClient() == null
                || !id.equals(entity.getIdClient())) {
            return ResponseEntity.badRequest().build();
        }
        try {
            return ResponseEntity.ok(clientService.modifier(entity));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        try {
            clientService.supprimer(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
