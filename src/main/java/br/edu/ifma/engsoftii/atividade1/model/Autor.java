package br.edu.ifma.engsoftii.atividade1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(of = {"nome"})
@NoArgsConstructor
@AllArgsConstructor
public class Autor {
  private String nome;
}
