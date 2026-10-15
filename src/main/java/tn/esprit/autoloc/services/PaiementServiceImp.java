package tn.esprit.autoloc.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.entities.Paiement;
import tn.esprit.autoloc.repositories.PaiementRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PaiementServiceImp implements IPaiementService {

    private final PaiementRepository paiementRepository;

    public PaiementServiceImp(PaiementRepository paiementRepository) {
        this.paiementRepository = paiementRepository;
    }

    @Override
    public Paiement ajouter(Paiement entity) {
        return paiementRepository.save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Paiement> afficherTous() {
        return paiementRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Paiement> afficherParId(Long id) {
        return paiementRepository.findById(id);
    }

    @Override
    public Paiement modifier(Paiement entity) {
        if (entity == null || entity.getIdPaiement() == null
                || !paiementRepository.existsById(entity.getIdPaiement())) {
            throw new IllegalArgumentException("Paiement introuvable");
        }
        return paiementRepository.save(entity);
    }

    @Override
    public void supprimer(Long id) {
        if (!paiementRepository.existsById(id)) {
            throw new IllegalArgumentException("Paiement introuvable");
        }
        paiementRepository.deleteById(id);
    }
}