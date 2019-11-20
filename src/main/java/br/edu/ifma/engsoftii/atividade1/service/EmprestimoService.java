package br.edu.ifma.engsoftii.atividade1.service;

import br.edu.ifma.engsoftii.atividade1.model.Emprestimo;
import br.edu.ifma.engsoftii.atividade1.model.Livro;
import br.edu.ifma.engsoftii.atividade1.model.Usuario;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmprestimoService {
  private List<Emprestimo> emprestimos = new ArrayList<>();

  public List<Emprestimo> consultarEmprestimosPorUsuario(Usuario usuario){
    return emprestimos.stream().filter(
        emprestimo -> emprestimo.getUsuario() == usuario)
        .collect(Collectors.toList());
  }

  public boolean criarEmprestimo(Livro livro, Usuario usuario){
    List<Emprestimo> emprestimosDoUsuario  = consultarEmprestimosPorUsuario(usuario);
    if(emprestimosDoUsuario.size() == Emprestimo.limitePorUsuario){
      return false;
    }
    if(livro.isReservado() && livro.getUsuarioReserva() != usuario){
      return false;
    }
    livro.setEmprestado(true);

    Emprestimo emprestimo = new Emprestimo();
    emprestimo.setUsuario(usuario);
    emprestimo.setLivroLocado(livro);
    emprestimo.setDataEmprestimo(LocalDate.now());

    emprestimos.add(emprestimo);
    return true;
  }

  public Emprestimo ultimoEmprestimo(){
    return emprestimos.get(emprestimos.size() - 1);
  }

  public Emprestimo registrarDevolucao(Livro livro, Usuario usuario, LocalDate data){
    Optional<Emprestimo> optionalEmprestimo = emprestimos.stream()
            .filter(e-> (e.getUsuario().equals(usuario) && e.getLivroLocado().equals(livro)))
            .findAny();
    if(optionalEmprestimo.isPresent()){
      Emprestimo emprestimo = optionalEmprestimo.get();
      emprestimo.getLivroLocado().setEmprestado(false);

      emprestimo.setDataDevolucao(data);

      return emprestimo;
    }
    return null;
  }
}
