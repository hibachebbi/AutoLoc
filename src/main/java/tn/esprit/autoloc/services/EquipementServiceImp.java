package tn.esprit.autoloc.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.entities.Equipement;
import tn.esprit.autoloc.repositories.EquipementRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EquipementServiceImp implements IEquipementService {

    private final EquipementRepository equipementRepository;

    public EquipementServiceImp(EquipementRepository equipementRepository) {
        this.equipementRepository = equipementRepository;
    }

    @Override
    public Equipement ajouter(Equipement entity) {
        return equipementRepository.save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Equipement> afficherTous() {
        return equipementRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Equipement> afficherParId(Long id) {
        return equipementRepository.findById(id);
    }

    @Override
    public Equipement modifier(Equipement entity) {
        if (entity == null || entity.getIdEquipement() == null
                || !equipementRepository.existsById(entity.getIdEquipement())) {
            throw new IllegalArgumentException("Équipement introuvable");
        }
        return equipementRepository.save(entity);
    }

    @Override
    public void supprimer(Long id) {
        if (!equipementRepository.existsById(id)) {
            throw new IllegalArgumentException("Équipement introuvable");
        }
        equipementRepository.deleteById(id);
    }
}