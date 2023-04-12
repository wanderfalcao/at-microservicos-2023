package br.com.infnet.administratorservice.service;

import br.com.infnet.administratorservice.model.Aluno;

import java.beans.Transient;
import java.util.List;

public interface AdminAlunoService {
    @Transient
    List<Aluno> getAllAluno() throws InterruptedException;

    Aluno getAlunoById(Long id);
    Aluno getAlunoByNome(String nome);
    void deleteAlunoById(Long id);
    void createAluno(Aluno Aluno);
    void updateAluno(Aluno Aluno, Long id);
}
