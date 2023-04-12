package br.com.infnet.administratorservice.repository;


import br.com.infnet.administratorservice.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AdminProfessorRepository extends JpaRepository<Professor, Long> {

    Professor getProfessorById(Long id) throws NullPointerException;
    void deleteProfessorById(Long id);



}
