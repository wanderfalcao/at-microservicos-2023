package br.com.infnet.alunoService.repository;

import br.com.infnet.alunoService.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    void deleteAlunoById(Long id);

    Aluno getAlunoByNome(String nome);

    @Override
    Optional<Aluno> findById(Long aLong);

}
