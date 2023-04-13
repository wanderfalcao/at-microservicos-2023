package br.com.infnet.administratorservice.service;

import br.com.infnet.administratorservice.model.Aluno;

import java.beans.Transient;
import java.util.List;

public interface AdminAlunoService {
    @Transient
    List<Aluno> getAllAluno() ;

    Aluno getAlunoById(Long id);
    Aluno getAlunoByNome(String nome);
    void deleteAlunoById(Long id);
    void createAluno(Aluno aluno);
    void updateAluno(Aluno aluno, Long id);
}
