package br.com.infnet.administratorservice.repository;

import br.com.infnet.administratorservice.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminAlunoRepository extends JpaRepository<Aluno, Long> {

    Aluno getAlunoById(Long id);
    void deleteAlunoById(Long id);

    Aluno getAlunoByNome(String nome);
}
