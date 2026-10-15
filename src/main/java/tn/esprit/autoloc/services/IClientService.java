package tn.esprit.autoloc.services;

import tn.esprit.autoloc.entities.Client;
import java.util.List;
import java.util.Optional;

public interface IClientService {
    Client ajouter(Client entity);
    List<Client> afficherTous();
    Optional<Client> afficherParId(Long id);
    Client modifier(Client entity);
    void supprimer(Long id);
}