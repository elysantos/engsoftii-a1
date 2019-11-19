package br.edu.ifma.engsoftii.atividade1.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import lombok.Data;

@Data
public class Emprestimo {
  private Usuario usuario;
  private Livro livroLocado;
  private LocalDate dataEmprestimo;
  private LocalDate dataPrevista;
  private LocalDate dataDevolucao;

  private double valorPago;

  public static double valorPadraoEmprestimo = 5;
  public static int limitePorUsuario = 2;
  public static int diasDeEmprestimo = 7;

  public double getValorAPagar(){
    double valor = valorPadraoEmprestimo;
    if(dataDevolucao.isAfter(dataPrevista)){
      Period periodo  = Period.between(dataDevolucao, dataPrevista);
      int diasAtraso = periodo.getDays();
      if(diasAtraso * 0.4 > (valorPadraoEmprestimo * 0.6)){
        valor = valor + (valorPadraoEmprestimo * 0.6);
      }else if(diasAtraso > 0){
        valor = valor + (diasAtraso * 0.4);
      }
    }
    return valor;
  }

  public void setDataEmprestimo(LocalDate dataEmprestimo) {
    this.dataEmprestimo = dataEmprestimo;
    this.dataPrevista = dataEmprestimo.plusDays(diasDeEmprestimo);
  }
}
