package tn.esprit.autoloc.services;

import tn.esprit.autoloc.entities.Vehicule;
import java.util.List;
import java.util.Optional;

public interface IVehiculeService {
    Vehicule ajouter(Vehicule entity);
    List<Vehicule> afficherTous();
    Optional<Vehicule> afficherParId(Long id);
    Vehicule modifier(Vehicule entity);
    void supprimer(Long id);
}