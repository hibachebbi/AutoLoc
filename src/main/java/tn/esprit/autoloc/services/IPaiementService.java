package tn.esprit.autoloc.services;

import tn.esprit.autoloc.entities.Paiement;
import java.util.List;
import java.util.Optional;

public interface IPaiementService {
    Paiement ajouter(Paiement entity);
    List<Paiement> afficherTous();
    Optional<Paiement> afficherParId(Long id);
    Paiement modifier(Paiement entity);
    void supprimer(Long id);
}