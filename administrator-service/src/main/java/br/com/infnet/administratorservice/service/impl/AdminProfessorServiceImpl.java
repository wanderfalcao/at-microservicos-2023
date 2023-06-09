package br.com.infnet.administratorservice.service.impl;


import br.com.infnet.administratorservice.model.Professor;
import br.com.infnet.administratorservice.repository.AdminProfessorRepository;
import br.com.infnet.administratorservice.service.AdminProfessorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminProfessorServiceImpl implements AdminProfessorService {

    @Autowired
    AdminProfessorRepository adminProfessorRepository;

    @Override
    public List<Professor> getAllProfessor() {
        return adminProfessorRepository.findAll();
    }

    @Override
    public Professor getProfessorById(Long id) {
        return adminProfessorRepository.getProfessorById(id);
    }

    @Override
    public void deleteProfessorById(Long id) {
        adminProfessorRepository.deleteProfessorById(id);
    }

    @Override
    public void createProfessor(Professor professor) {
        adminProfessorRepository.save(professor);
    }

    @Override
    public void updateProfessor(Professor professor, Long id) {
        adminProfessorRepository.findById(id);
        Professor professorRecuperdoDoBanco = adminProfessorRepository.getProfessorById(id);
        if(professorRecuperdoDoBanco != null){
            professorRecuperdoDoBanco.setNome(professor.getNome());
            professorRecuperdoDoBanco.setMateria(professor.getMateria());
            adminProfessorRepository.save(professorRecuperdoDoBanco);
        }

    }
}
