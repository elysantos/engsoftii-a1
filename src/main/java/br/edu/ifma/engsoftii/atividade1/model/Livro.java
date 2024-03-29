package br.edu.ifma.engsoftii.atividade1.model;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(of = {"titulo"})
@Builder
@EqualsAndHashCode(of = {"titulo", "autor"})
public class Livro {
  private String titulo;
  private Autor autor;
  private boolean isReservado;
  private boolean isEmprestado;

  private Usuario usuarioReserva;

  private List<Emprestimo> historico;

  public void setEmprestado(boolean emprestado) {
    isEmprestado = emprestado;
    if(emprestado){
      isReservado = false;
      usuarioReserva = null;
    }
  }
}
