package br.com.infnet.alunoService.repository;

import br.com.infnet.alunoService.model.Atividade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface AtividadeRepository extends JpaRepository<Atividade,Long> {
    @Transactional
    @Modifying
    @Query("update Atividade a set a.isEnviadaPorAluno = ?1 where a.id = ?2")
    void updateIsEnviadaPorAlunoById(boolean isEnviadaPorAluno, Long id);
    Optional<List<Atividade>> findByAlunos_Id(Long id);

}
