package br.edu.ifma.engsoftii.atividade1.model;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Builder
@EqualsAndHashCode(of = {"nome", "matricula"})
public class Usuario {
  private String nome;
  private String matricula;
}
