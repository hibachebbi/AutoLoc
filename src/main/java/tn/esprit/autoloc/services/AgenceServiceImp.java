package tn.esprit.autoloc.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.entities.Agence;
import tn.esprit.autoloc.repositories.AgenceRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AgenceServiceImp implements IAgenceService {

    private final AgenceRepository agenceRepository;

    public AgenceServiceImp(AgenceRepository agenceRepository) {
        this.agenceRepository = agenceRepository;
    }

    @Override
    public Agence ajouter(Agence entity) {
        return agenceRepository.save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Agence> afficherTous() {
        return agenceRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Agence> afficherParId(Long id) {
        return agenceRepository.findById(id);
    }

    @Override
    public Agence modifier(Agence entity) {
        if (entity == null || entity.getIdAgence() == null
                || !agenceRepository.existsById(entity.getIdAgence())) {
            throw new IllegalArgumentException("Agence introuvable");
        }
        return agenceRepository.save(entity);
    }

    @Override
    public void supprimer(Long id) {
        if (!agenceRepository.existsById(id)) {
            throw new IllegalArgumentException("Agence introuvable");
        }
        agenceRepository.deleteById(id);
    }
}