package br.com.infnet.professorservice.service;

import br.com.infnet.professorservice.model.Atividade;
import br.com.infnet.professorservice.model.AtividadeRecebido;
import br.com.infnet.professorservice.model.AvaliaAtividade;
import br.com.infnet.professorservice.model.Professor;

import java.util.List;

public interface ProfessorSevice {
    Atividade enviaAtividades(AtividadeRecebido atividade);
    String atribuiNota(AvaliaAtividade avaliaAtividade);

    Professor buscaPorId(Long id);

    List<Atividade> buscaAtividadePorProfessor(Long id);

    Atividade buscaAtividadePorId(Long id);
}
