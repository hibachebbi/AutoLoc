package tn.esprit.autoloc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.autoloc.entities.Reservation;

public interface ReservationRepository
        extends JpaRepository<Reservation, Long> {
}