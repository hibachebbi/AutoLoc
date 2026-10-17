
package tn.esprit.autoloc.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.autoloc.entities.Reservation;
import tn.esprit.autoloc.services.IReservationService;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final IReservationService reservationService;

    public ReservationController(IReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<Reservation> ajouter(
            @RequestBody Reservation entity) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(reservationService.ajouter(entity));
    }

    @GetMapping
    public List<Reservation> afficherTous() {
        return reservationService.afficherTous();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> afficherParId(
            @PathVariable Long id) {
        return reservationService.afficherParId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reservation> modifier(
            @PathVariable Long id, @RequestBody Reservation entity) {
        if (entity.getIdReservation() == null
                || !id.equals(entity.getIdReservation())) {
            return ResponseEntity.badRequest().build();
        }
        try {
            return ResponseEntity.ok(reservationService.modifier(entity));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable Long id) {
        try {
            reservationService.supprimer(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
