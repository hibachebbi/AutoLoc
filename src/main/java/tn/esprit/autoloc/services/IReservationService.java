package tn.esprit.autoloc.services;

import tn.esprit.autoloc.entities.Reservation;
import java.util.List;
import java.util.Optional;

public interface IReservationService {
    Reservation ajouter(Reservation entity);
    List<Reservation> afficherTous();
    Optional<Reservation> afficherParId(Long id);
    Reservation modifier(Reservation entity);
    void supprimer(Long id);
}