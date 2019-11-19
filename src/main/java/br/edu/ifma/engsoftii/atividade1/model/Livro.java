package br.edu.ifma.engsoftii.atividade1.model;

import java.util.List;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(of = {"titulo"})
public class Livro {
  private String titulo;
  private Autor autor;
  private boolean isReservado;
  private boolean isEmprestado;

  private List<Emprestimo> historico;
}
