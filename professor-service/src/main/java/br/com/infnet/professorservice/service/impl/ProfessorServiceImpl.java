package br.com.infnet.professorservice.service.impl;

import br.com.infnet.professorservice.model.*;
import br.com.infnet.professorservice.repository.AtividadeRepository;
import br.com.infnet.professorservice.repository.ProfessorRepository;
import br.com.infnet.professorservice.service.ProfessorSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;
import org.webjars.NotFoundException;

import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.List;

@Service
public class ProfessorServiceImpl implements ProfessorSevice {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    ProfessorRepository professorRepository;

    @Autowired
    AtividadeRepository atividadeRepository;

    @Override
    @Transactional
    public Atividade enviaAtividades(AtividadeRecebido atividade) {
        Professor professor = professorRepository.getProfessorById(atividade.getProfessor());
        System.out.println(restTemplate.getForObject("http://Aluno/api/aluno/"+atividade.getAluno().toString(), Aluno.class));
        Aluno alunoResponse = restTemplate.getForObject("http://Aluno/api/aluno/"+atividade.getAluno().toString(), Aluno.class);
        Atividade atividadeParaEnvio = new Atividade();
        atividadeParaEnvio.setTitulo(atividade.getTitulo());
        atividadeParaEnvio.setDescricao(atividade.getDescricao());
        atividadeParaEnvio.setDataFinal(atividade.getDataFinal());
        atividadeParaEnvio.setProfessor(professor);
        atividadeParaEnvio.setAlunos(alunoResponse);
        Atividade atividade1 = atividadeRepository.save(atividadeParaEnvio);

        return atividade1;
    }

    @Override
    @Transactional
    public String atribuiNota(AvaliaAtividade avaliaAtividade) {
        Atividade atividade = this.buscaAtividadePorId(avaliaAtividade.getId());
        if (atividade.getNota() == null && atividade.isEnviadaPorAluno() == Boolean.TRUE){
            atividadeRepository.updateNotaByNotaNullAndId(avaliaAtividade.getNota(),atividade.getId());
            return "Atividade com titulo "+ atividade.getTitulo()+" do aluno "
                    +atividade.getAlunos().getNome()+ " avaliada com sucesso";
        }else{
            return "Atividade indisponivel para avaliação";
        }
    }

    @Override
    public Professor buscaPorId(Long id) throws NotFoundException {
        Professor professor = professorRepository.getProfessorById(id);
        return professor;
    }

    @Override
    @GetMapping("/buscaAtividadePorProfessor/{id}")
    public List<Atividade> buscaAtividadePorProfessor(@PathVariable Long id) {
        List<Atividade> atividade = atividadeRepository.findByProfessor_Id(id);
        return atividade;
    }

    @Override
    public Atividade buscaAtividadePorId(Long id) {
        return atividadeRepository.findById(id).orElseThrow(()->
                new RuntimeException("Atividade não encontrada"));
    }

//    public Specification<Atividade> buscaAtividadePorIDDoProfessor(Long id) {
//
//        CriteriaQuery<Atividade> cq = cb.createQuery(Atividade.class);
//        Root<Atividade> atividadeRoot = cq.from(Atividade.class);
//        atividadeRoot.fetch(Atividade.find)
//        return (root, query, criteriaBuilder) -> {
//            List<Predicate> list = new ArrayList<Predicate>();
//            Join<Professor, Atividade> professorAtividadeJoin = root.join("professor");
//            return criteriaBuilder.equal(professorAtividadeJoin.get("professor_id"), id);
//        };
//    }


}
