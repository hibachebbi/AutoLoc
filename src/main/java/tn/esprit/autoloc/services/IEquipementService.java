package tn.esprit.autoloc.services;

import tn.esprit.autoloc.entities.Equipement;
import java.util.List;
import java.util.Optional;

public interface IEquipementService {
    Equipement ajouter(Equipement entity);
    List<Equipement> afficherTous();
    Optional<Equipement> afficherParId(Long id);
    Equipement modifier(Equipement entity);
    void supprimer(Long id);
}