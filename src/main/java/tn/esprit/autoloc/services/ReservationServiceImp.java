package tn.esprit.autoloc.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.entities.Reservation;
import tn.esprit.autoloc.repositories.ReservationRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReservationServiceImp implements IReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationServiceImp(
            ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public Reservation ajouter(Reservation entity) {
        return reservationRepository.save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Reservation> afficherTous() {
        return reservationRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Reservation> afficherParId(Long id) {
        return reservationRepository.findById(id);
    }

    @Override
    public Reservation modifier(Reservation entity) {
        if (entity == null || entity.getIdReservation() == null
                || !reservationRepository.existsById(entity.getIdReservation())) {
            throw new IllegalArgumentException("Réservation introuvable");
        }
        return reservationRepository.save(entity);
    }

    @Override
    public void supprimer(Long id) {
        if (!reservationRepository.existsById(id)) {
            throw new IllegalArgumentException("Réservation introuvable");
        }
        reservationRepository.deleteById(id);
    }
}