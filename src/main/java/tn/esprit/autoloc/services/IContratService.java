package tn.esprit.autoloc.services;

import tn.esprit.autoloc.entities.Contrat;
import java.util.List;
import java.util.Optional;

public interface IContratService {
    Contrat ajouter(Contrat entity);
    List<Contrat> afficherTous();
    Optional<Contrat> afficherParId(Long id);
    Contrat modifier(Contrat entity);
    void supprimer(Long id);
}