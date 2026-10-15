package tn.esprit.autoloc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.autoloc.entities.Employe;

public interface EmployeRepository
        extends JpaRepository<Employe, Long> {
}