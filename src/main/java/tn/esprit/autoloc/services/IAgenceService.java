package tn.esprit.autoloc.services;

import tn.esprit.autoloc.entities.Agence;
import java.util.List;
import java.util.Optional;

public interface IAgenceService {
    Agence ajouter(Agence entity);
    List<Agence> afficherTous();
    Optional<Agence> afficherParId(Long id);
    Agence modifier(Agence entity);
    void supprimer(Long id);
}