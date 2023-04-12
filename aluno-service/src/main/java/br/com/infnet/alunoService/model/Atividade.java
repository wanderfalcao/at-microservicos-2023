package br.com.infnet.alunoService.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@SuppressWarnings("JpaDataSourceORMInspection")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(	name = "atividade")
public class Atividade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "atividade_id")
    private Long id;
    @NotNull
    @Size(min = 2,max = 30, message = "Nome precisa ter no minimo 2 caracteres e no maximo 30 caracteres")
    @Column(name = "atividade_titulo")
    private String titulo;
    @NotNull
    @Size(min = 2,max = 200, message = "Nome precisa ter no minimo 2 caracteres e no maximo 200 caracteres")
    @Column(name = "atividade_descricao")
    private String descricao;
    @NotNull
    @Column(name = "atividade_data_final")
    private LocalDate dataFinal;
    @Column(name = "atividade_nota")
    private Long nota;

    @Column(name = "atividade_isEnviadaPorAluno")
    private boolean isEnviadaPorAluno = Boolean.FALSE;
    @ManyToOne()
    private Professor professor;
    @ManyToOne()
    private Aluno alunos ;
}
