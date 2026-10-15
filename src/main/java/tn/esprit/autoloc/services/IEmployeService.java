package tn.esprit.autoloc.services;

import tn.esprit.autoloc.entities.Employe;
import java.util.List;
import java.util.Optional;

public interface IEmployeService {
    Employe ajouter(Employe entity);
    List<Employe> afficherTous();
    Optional<Employe> afficherParId(Long id);
    Employe modifier(Employe entity);
    void supprimer(Long id);
}