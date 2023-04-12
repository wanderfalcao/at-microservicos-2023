package br.com.infnet.alunoService.controller;

import br.com.infnet.alunoService.model.Aluno;
import br.com.infnet.alunoService.model.Atividade;
import br.com.infnet.alunoService.model.AtividadeRecebido;
import br.com.infnet.alunoService.service.AlunoService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/aluno")

public class AlunoController {
    @Autowired
    AlunoService alunoService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aluno Encontrado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Aluno.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Aluno não encontrado",
                    content = @Content) })
    @GetMapping("/atividades/{id}")
    public ResponseEntity getAtividadesByIdAluno(@PathVariable Long id){
        List<Atividade> byId = alunoService.getAtividadeByIdAluno(id);
        if(byId.equals(null)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("Message", "Not Found"));
        }

        return ResponseEntity.ok(byId);

    }

    @GetMapping("/{id}")
    public ResponseEntity getAlunoById(@PathVariable Long id){
        Aluno byId = alunoService.getAlunoById(id);
        if(byId.equals(null)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("Message", "Not Found"));
        }

        return ResponseEntity.ok(byId);

    }

    @PostMapping("/submeteAtividadeParaProfessor")
    public ResponseEntity postAtividade(@RequestBody AtividadeRecebido atividadeRecebido){
        List<Atividade> atividadesPorAluno = alunoService.getAtividadeByIdAluno(atividadeRecebido.getAluno());
        Atividade atividadeBuscado = atividadesPorAluno.stream()
                .filter(at -> at.getId() == atividadeRecebido.getId())
                .findAny()
                .orElse(null);
        if (atividadeBuscado == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("Atividade", "Not Found"));
        }

        alunoService.submeteAtividade(atividadeRecebido,atividadeBuscado);
//        if (retornoAtividade == null){
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Não foi possivel submeter a tarefa");
//        }
        return ResponseEntity.ok("Atividade submetida para o professor");
    }

}
