package br.com.infnet.administratorservice.controller;

import br.com.infnet.administratorservice.model.Professor;
import br.com.infnet.administratorservice.service.impl.AdminProfessorServiceImpl;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/professor")
public class AdminProfessorController {
    @Autowired
    AdminProfessorServiceImpl adminProfessorService;

    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Professor Cadastrado",
                content = { @Content(mediaType = "application/json",
                        schema = @Schema(implementation = Professor.class))}),
        @ApiResponse(responseCode = "500", description = "Algo de errado aconteceu",
                content = @Content)})
    @PostMapping("/create")
    public ResponseEntity<Map<String, String>> create(@RequestBody Professor professor){
        adminProfessorService.createProfessor(professor);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("Message", "Created"));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Professors retornados com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Professor.class))}),
            @ApiResponse(responseCode = "500", description = "Algo de errado aconteceu",
                    content = @Content)})
    @GetMapping("/buscaTodosProfessores")
    public ResponseEntity<List<Professor>> getAllProfessors(){
        List<Professor> allProfessor = adminProfessorService.getAllProfessor();
        return ResponseEntity.ok(allProfessor);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Professor retornado com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Professor.class))}),
            @ApiResponse(responseCode = "500", description = "Algo de errado aconteceu",
                    content = @Content)})
    @GetMapping("/buscaProfessor/{professorId}")
    public ResponseEntity<Professor> getProfessorById(@PathVariable Long professorId){
        return ResponseEntity.ok(adminProfessorService.getProfessorById(professorId));
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Professor atualizado com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Professor.class))}),
            @ApiResponse(responseCode = "500", description = "Algo de errado aconteceu",
                    content = @Content)})
    @PutMapping("/atualizarProfessor/{professorId}")
    public ResponseEntity updateProfessor(@RequestBody Professor professor, @PathVariable Long professorId){
        adminProfessorService.updateProfessor(professor, professorId);
        return ResponseEntity.ok().build();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Professor deletado com sucesso",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Professor.class))}),
            @ApiResponse(responseCode = "500", description = "Algo de errado aconteceu",
                    content = @Content)})
    @DeleteMapping("/deletaProfessor/{professorId}")
    @Transactional
    public ResponseEntity deleteProfessor(@PathVariable Long professorId){
        adminProfessorService.deleteProfessorById(professorId);
        return ResponseEntity.ok("Professor deletado");
    }
}
