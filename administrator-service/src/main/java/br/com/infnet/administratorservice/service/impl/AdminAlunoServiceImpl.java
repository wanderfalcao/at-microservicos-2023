package br.com.infnet.administratorservice.service.impl;

import br.com.infnet.administratorservice.model.Aluno;
import br.com.infnet.administratorservice.repository.AdminAlunoRepository;
import br.com.infnet.administratorservice.service.AdminAlunoService;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class AdminAlunoServiceImpl implements AdminAlunoService {
    @Autowired
    AdminAlunoRepository adminAlunoRepository;

    @Override
    @Transient
    @Timed(value = "administrator.getAllALuno.process.time", description = "Tempo de processamento de buscar todos os alunos")
    public List<Aluno>getAllAluno() {
        return adminAlunoRepository.findAll();
    }

    @Override
    @Timed(value = "administrator.getAlunoById.process.time", description = "Tempo de processamento de buscar todos os alunos")
    public Aluno getAlunoById(Long id) {
        return adminAlunoRepository.getAlunoById(id);
    }

    @Override
    @Timed(value = "administrator.getAlunoByNome.process.time", description = "Tempo de processamento de buscar todos os alunos")
    public Aluno getAlunoByNome(String nome) {
        return adminAlunoRepository.getAlunoByNome(nome);
    }

    @Override
    @Timed(value = "administrator.deleteAlunoById.process.time", description = "Tempo de processamento de buscar todos os alunos")
    public void deleteAlunoById(Long id) {
        adminAlunoRepository.deleteAlunoById(id);
    }

    @Override
    @Timed(value = "administrator.createAluno.process.time", description = "Tempo de processamento de buscar todos os alunos")
    public void createAluno(Aluno aluno) {

        adminAlunoRepository.save(aluno);
    }

    @Override
    @Timed(value = "administrator.updateAluno.process.time", description = "Tempo de processamento de buscar todos os alunos")
    public void updateAluno(Aluno aluno, Long id) {
        Aluno alunoTemp = adminAlunoRepository.getAlunoById(id);
        alunoTemp.setNome(aluno.getNome());
        alunoTemp.setTurma(aluno.getTurma());
        adminAlunoRepository.save(alunoTemp);
    }

}
