package tn.esprit.autoloc.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.entities.Maintenance;
import tn.esprit.autoloc.repositories.MaintenanceRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class MaintenanceServiceImp implements IMaintenanceService {

    private final MaintenanceRepository maintenanceRepository;

    public MaintenanceServiceImp(
            MaintenanceRepository maintenanceRepository) {
        this.maintenanceRepository = maintenanceRepository;
    }

    @Override
    public Maintenance ajouter(Maintenance entity) {
        return maintenanceRepository.save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Maintenance> afficherTous() {
        return maintenanceRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Maintenance> afficherParId(Long id) {
        return maintenanceRepository.findById(id);
    }

    @Override
    public Maintenance modifier(Maintenance entity) {
        if (entity == null || entity.getIdMaintenance() == null
                || !maintenanceRepository.existsById(entity.getIdMaintenance())) {
            throw new IllegalArgumentException("Maintenance introuvable");
        }
        return maintenanceRepository.save(entity);
    }

    @Override
    public void supprimer(Long id) {
        if (!maintenanceRepository.existsById(id)) {
            throw new IllegalArgumentException("Maintenance introuvable");
        }
        maintenanceRepository.deleteById(id);
    }
}