package tn.esprit.autoloc.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.entities.Contrat;
import tn.esprit.autoloc.repositories.ContratRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ContratServiceImp implements IContratService {

    private final ContratRepository contratRepository;

    public ContratServiceImp(ContratRepository contratRepository) {
        this.contratRepository = contratRepository;
    }

    @Override
    public Contrat ajouter(Contrat entity) {
        return contratRepository.save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Contrat> afficherTous() {
        return contratRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Contrat> afficherParId(Long id) {
        return contratRepository.findById(id);
    }

    @Override
    public Contrat modifier(Contrat entity) {
        if (entity == null || entity.getIdContrat() == null
                || !contratRepository.existsById(entity.getIdContrat())) {
            throw new IllegalArgumentException("Contrat introuvable");
        }
        return contratRepository.save(entity);
    }

    @Override
    public void supprimer(Long id) {
        if (!contratRepository.existsById(id)) {
            throw new IllegalArgumentException("Contrat introuvable");
        }
        contratRepository.deleteById(id);
    }
}