package br.com.infnet.professorservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(	name = "professor")
public class Professor {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "professor_id")
        private Long id;

        @NotNull
        @Size(min = 2,max = 30, message = "Nome precisa ter no minimo 2 caracteres e no maximo 30 caracteres")
        @Column(name = "professor_nome")
        private String nome;

        @NotNull
        @Size(min = 2,max = 30, message = "Nome precisa ter no minimo 2 caracteres e no maximo 30 caracteres")
        @Column(name = "professor_materia")
        private String materia;
}