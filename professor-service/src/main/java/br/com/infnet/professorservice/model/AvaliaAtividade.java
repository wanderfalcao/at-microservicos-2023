package br.com.infnet.professorservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AvaliaAtividade {
    @NotNull
    @NotBlank
    private Long id;
    @NotNull
    @NotBlank
    private Long nota;
}
