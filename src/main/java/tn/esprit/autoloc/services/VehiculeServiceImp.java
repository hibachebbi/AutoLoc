package tn.esprit.autoloc.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.entities.Vehicule;
import tn.esprit.autoloc.repositories.VehiculeRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VehiculeServiceImp implements IVehiculeService {

    private final VehiculeRepository vehiculeRepository;

    public VehiculeServiceImp(VehiculeRepository vehiculeRepository) {
        this.vehiculeRepository = vehiculeRepository;
    }

    @Override
    public Vehicule ajouter(Vehicule entity) {
        return vehiculeRepository.save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vehicule> afficherTous() {
        return vehiculeRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Vehicule> afficherParId(Long id) {
        return vehiculeRepository.findById(id);
    }

    @Override
    public Vehicule modifier(Vehicule entity) {
        if (entity == null || entity.getIdVehicule() == null
                || !vehiculeRepository.existsById(entity.getIdVehicule())) {
            throw new IllegalArgumentException("Véhicule introuvable");
        }
        return vehiculeRepository.save(entity);
    }

    @Override
    public void supprimer(Long id) {
        if (!vehiculeRepository.existsById(id)) {
            throw new IllegalArgumentException("Véhicule introuvable");
        }
        vehiculeRepository.deleteById(id);
    }
}