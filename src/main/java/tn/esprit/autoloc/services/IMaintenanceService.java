package tn.esprit.autoloc.services;

import tn.esprit.autoloc.entities.Maintenance;
import java.util.List;
import java.util.Optional;

public interface IMaintenanceService {
    Maintenance ajouter(Maintenance entity);
    List<Maintenance> afficherTous();
    Optional<Maintenance> afficherParId(Long id);
    Maintenance modifier(Maintenance entity);
    void supprimer(Long id);
}