package br.edu.ifma.engsoftii.atividade1.service;

import br.edu.ifma.engsoftii.atividade1.model.Emprestimo;
import br.edu.ifma.engsoftii.atividade1.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmprestimoService {
  private List<Emprestimo> emprestimos = new ArrayList<>();

  public List<Emprestimo> consultarEmprestimosPorUsuario(Usuario usuario){
    return null;
  }
}
