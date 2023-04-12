package br.com.infnet.alunoService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AtividadeRecebido {
    @NotNull
    private Long id;
    @NotNull
    private Long professor;
    @NotNull
    private Long aluno;

}
