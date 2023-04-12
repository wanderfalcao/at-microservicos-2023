package br.com.infnet.professorservice.repository;

import br.com.infnet.professorservice.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    Professor getProfessorById(Long id);

}
