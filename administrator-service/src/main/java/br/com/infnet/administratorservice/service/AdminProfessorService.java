package br.com.infnet.administratorservice.service;

import java.util.List;
import java.util.Optional;

import br.com.infnet.administratorservice.model.Professor;

public interface AdminProfessorService {
    List<Professor> getAllProfessor();
    Professor getProfessorById(Long id);
    void deleteProfessorById(Long id);
    void createProfessor(Professor Professor);

    void updateProfessor(Professor Professor, Long id);
}
