package br.com.infnet.administratorservice.controller;

import br.com.infnet.administratorservice.model.Aluno;
import br.com.infnet.administratorservice.model.Professor;
import br.com.infnet.administratorservice.service.impl.AdminAlunoServiceImpl;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/aluno")
public class AdminAlunoController {
    @Autowired
    AdminAlunoServiceImpl adminAlunoService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Aluno Cadastrado",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Professor.class))}),
            @ApiResponse(responseCode = "500", description = "Algo de errado aconteceu",
                    content = @Content)})
    @PostMapping("/criarNovoAluno")
    public ResponseEntity<Map<String, String>> create(@Valid @RequestBody Aluno aluno){
        adminAlunoService.createAluno(aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(aluno.getNome()," Criado com sucesso"));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alunos retornados com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Aluno.class))}),
            @ApiResponse(responseCode = "500", description = "Algo de errado aconteceu",
                    content = @Content)})
    @GetMapping("/buscaTodosAlunos")
    public ResponseEntity<List<Aluno>> getAllAlunos() throws InterruptedException {
        List<Aluno> allAluno = adminAlunoService.getAllAluno();
        return ResponseEntity.ok(allAluno);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aluno retornado com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Aluno.class))}),
            @ApiResponse(responseCode = "500", description = "Algo de errado aconteceu",
                    content = @Content)})
    @GetMapping("/buscaAluno/{alunoId}")
    public ResponseEntity getAlunoById(@PathVariable Long alunoId){
        Aluno aluno = adminAlunoService.getAlunoById(alunoId);
        return ResponseEntity.ok(aluno);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aluno atualizado com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Aluno.class))}),
            @ApiResponse(responseCode = "500", description = "Algo de errado aconteceu",
                    content = @Content)})
    @PutMapping("/atualizarAluno/{alunoId}")
    public ResponseEntity updateAluno(@RequestBody Aluno aluno, @PathVariable Long alunoId){
        adminAlunoService.updateAluno(aluno, alunoId);
        return ResponseEntity.ok().build();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aluno deletado com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Aluno.class))}),
            @ApiResponse(responseCode = "500", description = "Algo de errado aconteceu",
                    content = @Content)})
    @DeleteMapping("/deletaAluno/{alunoId}")
    @Transactional
    public ResponseEntity deleteAluno(@PathVariable Long alunoId){
        adminAlunoService.deleteAlunoById(alunoId);
        return ResponseEntity.ok("aluno deletado");
    }
}
