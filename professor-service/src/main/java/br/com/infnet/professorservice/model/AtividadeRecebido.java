package br.com.infnet.professorservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

@SuppressWarnings("JpaDataSourceORMInspection")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AtividadeRecebido {
    @NotBlank
    @Size(min = 2,max = 30, message = "Nome precisa ter no minimo 2 caracteres e no maximo 30 caracteres")
    private String titulo;
    @NotBlank
    @Size(min = 2,max = 200, message = "Nome precisa ter no minimo 2 caracteres e no maximo 200 caracteres")
    private String descricao;
    @NotBlank
    @Valid
    private LocalDate dataFinal;
    @NotBlank
    @NotNull
    private Long professor;
    @NotBlank
    @NotNull
    private Long aluno;

}
