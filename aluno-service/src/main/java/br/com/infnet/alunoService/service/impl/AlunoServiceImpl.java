package br.com.infnet.alunoService.service.impl;

import br.com.infnet.alunoService.model.*;
import br.com.infnet.alunoService.repository.AlunoRepository;
import br.com.infnet.alunoService.repository.AtividadeRepository;
import br.com.infnet.alunoService.service.AlunoService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoServiceImpl implements AlunoService {
    private final String MESSAGES_QUEUE = "atividade-submetida-aluno-queue-v1";
    private final String ROUTING_KEY = MESSAGES_QUEUE;
    private final String MESSAGES_EXCHANGE = "atividade-submetida-aluno-queue-v1.exc";

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    AlunoRepository alunoRepository;

    @Autowired
    AtividadeRepository atividadeRepository;

    @Autowired
    RabbitTemplate rabbitTemplate;


    @Override
    @Transactional
    public void submeteAtividade(AtividadeRecebido atividadeRecebido, Atividade atividade) throws RuntimeException{

        Aluno alunoBuscado = alunoRepository.findById(atividadeRecebido.getAluno()).orElseThrow(() ->
                new RuntimeException("Aluno não encontrado"));;
        String idProfessor = String.valueOf(atividade.getProfessor().getId());
        boolean atividadeJaEnviada = atividade.isEnviadaPorAluno();
        if(atividade.getProfessor().getId() == atividadeRecebido.getId() && atividadeJaEnviada==Boolean.FALSE){

            atividadeRepository.updateIsEnviadaPorAlunoById(Boolean.TRUE,atividadeRecebido.getId());
            String mensagemParaProfessor = "A atividade com titulo:"+atividade.getTitulo()+
                    " do aluno:"+atividade.getAlunos().getNome()+" já se encontra disponível para avaliação";
            rabbitTemplate.convertAndSend(MESSAGES_EXCHANGE,ROUTING_KEY,mensagemParaProfessor);
        }else {
            throw new RuntimeException("A atividade já foi enviada anteriormente");
        }



    }

    @Override
    public List<Atividade> getAtividadeByIdAluno(Long id) {
        return atividadeRepository.findByAlunos_Id(id).orElseThrow(() ->
                        new RuntimeException("Atividade não encontrada"));
    }

    @Override
    public Aluno getAlunoById(Long id) {
        return alunoRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Aluno não encontrado"));
    }
}
