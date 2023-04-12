package br.com.infnet.alunoService.service;

import br.com.infnet.alunoService.model.Aluno;
import br.com.infnet.alunoService.model.Atividade;
import br.com.infnet.alunoService.model.AtividadeRecebido;

import java.util.List;
import java.util.Optional;

public interface AlunoService {

    void submeteAtividade(AtividadeRecebido atividadeRecebido, Atividade atividade);
    List<Atividade> getAtividadeByIdAluno(Long id);
    Aluno getAlunoById(Long id);
}
