package tn.esprit.autoloc.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.autoloc.entities.Employe;
import tn.esprit.autoloc.repositories.EmployeRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmployeServiceImp implements IEmployeService {

    private final EmployeRepository employeRepository;

    public EmployeServiceImp(EmployeRepository employeRepository) {
        this.employeRepository = employeRepository;
    }

    @Override
    public Employe ajouter(Employe entity) {
        return employeRepository.save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Employe> afficherTous() {
        return employeRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Employe> afficherParId(Long id) {
        return employeRepository.findById(id);
    }

    @Override
    public Employe modifier(Employe entity) {
        if (entity == null || entity.getIdEmploye() == null
                || !employeRepository.existsById(entity.getIdEmploye())) {
            throw new IllegalArgumentException("Employé introuvable");
        }
        return employeRepository.save(entity);
    }

    @Override
    public void supprimer(Long id) {
        if (!employeRepository.existsById(id)) {
            throw new IllegalArgumentException("Employé introuvable");
        }
        employeRepository.deleteById(id);
    }
}