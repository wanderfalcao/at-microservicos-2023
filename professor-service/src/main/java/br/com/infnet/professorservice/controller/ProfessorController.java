package br.com.infnet.professorservice.controller;

import br.com.infnet.professorservice.model.Atividade;
import br.com.infnet.professorservice.model.AtividadeRecebido;
import br.com.infnet.professorservice.model.AvaliaAtividade;
import br.com.infnet.professorservice.model.Professor;
import br.com.infnet.professorservice.service.ProfessorSevice;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/professor")
public class ProfessorController {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    ProfessorSevice professorSevice;

    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Professor retornado",
                content = { @Content(mediaType = "application/json",
                        schema = @Schema(implementation = Professor.class))}),
        @ApiResponse(responseCode = "500", description = "Algo de errado aconteceu",
                content = @Content)}
//        @ApiResponse(responseCode = "404", description = "Professor não retornado",
//                content =  @Content)
        )
    @GetMapping("/{id}")
    public ResponseEntity buscaProfessorPorID(@PathVariable Long id){
        System.out.println("Professor Service - Busca professor por ID: " + id);
        Professor professor = professorSevice.buscaPorId(id);
        if(professor.equals(null)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("Message", "Not Found"));
        }

        return ResponseEntity.ok(professor);

    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Professor retornado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Professor.class))}),
            @ApiResponse(responseCode = "500", description = "Algo de errado aconteceu",
                    content = @Content),
        @ApiResponse(responseCode = "404", description = "Professor não retornado",
                content =  @Content)}
    )
    @PostMapping("/envia-tarefas")
    public ResponseEntity enviarTarefasParaOsAlunos(@RequestBody AtividadeRecebido atividadeRecebido){
        System.out.println("Professor Service - Enviando tarefas com titulo " + atividadeRecebido.getTitulo());
        Atividade retornoAtividade = professorSevice.enviaAtividades(atividadeRecebido);
        return ResponseEntity.ok("Professor retornado é " + retornoAtividade);

    }
    @GetMapping("/buscaAtividadePorProfessor/{id}")
    public ResponseEntity buscaAtividadePorProfessor(@PathVariable Long id){
        List<Atividade> retornoAtividade = professorSevice.buscaAtividadePorProfessor(id);
        return ResponseEntity.ok(retornoAtividade);
    }

    @PostMapping("/avaliaAtividade")
    public ResponseEntity avaliaAtividadeSubmetida(@RequestBody AvaliaAtividade avaliaAtividade){

        String mensagemRetorno = professorSevice.atribuiNota(avaliaAtividade);
        return ResponseEntity.ok(mensagemRetorno);
    }
}
