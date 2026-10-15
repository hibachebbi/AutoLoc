package tn.esprit.autoloc.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.entities.Client;
import tn.esprit.autoloc.repositories.ClientRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClientServiceImp implements IClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImp(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client ajouter(Client entity) {
        return clientRepository.save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Client> afficherTous() {
        return clientRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Client> afficherParId(Long id) {
        return clientRepository.findById(id);
    }

    @Override
    public Client modifier(Client entity) {
        if (entity == null || entity.getIdClient() == null
                || !clientRepository.existsById(entity.getIdClient())) {
            throw new IllegalArgumentException("Client introuvable");
        }
        return clientRepository.save(entity);
    }

    @Override
    public void supprimer(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new IllegalArgumentException("Client introuvable");
        }
        clientRepository.deleteById(id);
    }
}