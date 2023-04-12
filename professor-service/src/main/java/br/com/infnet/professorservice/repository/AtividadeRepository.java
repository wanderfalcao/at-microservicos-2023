package br.com.infnet.professorservice.repository;

import br.com.infnet.professorservice.model.Atividade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface AtividadeRepository extends JpaRepository<Atividade,Long> {
    @Transactional
    @Modifying
    @Query("update Atividade a set a.nota = ?1 where a.nota is null and a.id = ?2")
    void updateNotaByNotaNullAndId(@NonNull Long nota, Long id);
    List<Atividade> findByProfessor_Id(Long id);

    Atividade getAtividadesByProfessor_Id(Long id);

}
