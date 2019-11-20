package br.edu.ifma.engsoftii.atividade1.service;

import br.edu.ifma.engsoftii.atividade1.model.Autor;
import br.edu.ifma.engsoftii.atividade1.model.Emprestimo;
import br.edu.ifma.engsoftii.atividade1.model.Livro;
import br.edu.ifma.engsoftii.atividade1.model.Usuario;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class EmprestimoServiceTest {

    private EmprestimoService emprestimoService = new EmprestimoService();
    private Livro livro1 = Livro.builder()
            .autor(new Autor("Vitor Hugo"))
            .isEmprestado(false)
            .isReservado(false)
            .titulo("Les Miserables")
            .build();
    private Livro livro2 = Livro.builder()
            .autor(new Autor("Ernest Hemmingway"))
            .isEmprestado(false)
            .isReservado(false)
            .titulo("O Velho e o Mar")
            .build();
    private Livro livro3 = Livro.builder()
            .autor(new Autor("Mario Puzzo"))
            .isEmprestado(false)
            .isReservado(false)
            .titulo("O Poderoso Chefão")
            .build();
    private Usuario usuario1 = Usuario.builder()
            .matricula("123").nome("Ely").build();
    private Usuario usuario2 = Usuario.builder()
            .matricula("456").nome("Santos").build();


    @Test
    void deveRealizarEmprestimoDeLivroNaoReservado() {

        boolean emprestado = emprestimoService.criarEmprestimo(livro1, usuario1);

        assertTrue(emprestado);
    }

    @Test
    void deveRejeitarEmprestimoDeLivroReservadoOutroUsuario() {
        //cenário livro reservado para usuario
        livro1.setReservado(true);
        livro1.setUsuarioReserva(usuario2);

        //ação
        boolean resultado = emprestimoService.criarEmprestimo(livro1, usuario1);

        //verificação
        assertTrue(!resultado);
    }

    @Test
    void deveAceitarEmprestimoDeLivroReservadoMesmoUsuario() {
        //cenário livro reservado para usuario
        livro1.setReservado(true);
        livro1.setUsuarioReserva(usuario1);

        //ação
        boolean resultado = emprestimoService.criarEmprestimo(livro1, usuario1);

        //verificação
        assertTrue(resultado);
    }


    @Test
    void deveTestarDataPrevistaCorreta() {
        //cenário
        LocalDate dataEsperada = LocalDate.now().plusDays(Emprestimo.diasDeEmprestimo);

        //ação
        emprestimoService.criarEmprestimo(livro2, usuario1);

        //verificação
        assertTrue(dataEsperada.equals(emprestimoService.ultimoEmprestimo().getDataPrevista()));
    }

    @Test
    void deveVerificarUsuarioSemEmprestimo() {
        //ação
        List<Emprestimo> emprestimosPorUsuario = emprestimoService.consultarEmprestimosPorUsuario(usuario2);

        //verificação
        assertTrue(emprestimosPorUsuario.isEmpty());
    }

    @Test
    void deveTestarUsuarioComUnicoEmprestimo() {
        //cenário
        emprestimoService.criarEmprestimo(livro1, usuario1);

        //ação
        List<Emprestimo> emprestimosPorUsuario = emprestimoService.consultarEmprestimosPorUsuario(usuario1);

        //verificação
        assertTrue(emprestimosPorUsuario.size()==1);
    }

    @Test
    void deveTestarUsuarioComDoisEmprestimos() {
        //cenário
        emprestimoService.criarEmprestimo(livro1, usuario1);
        emprestimoService.criarEmprestimo(livro2, usuario1);

        //ação
        List<Emprestimo> emprestimosPorUsuario = emprestimoService.consultarEmprestimosPorUsuario(usuario1);

        //verificação
        assertTrue(emprestimosPorUsuario.size()==2);

    }

    @Test
    void deveTentarTerceiroEmprestimoPorUsuario() {
        //cenário
        emprestimoService.criarEmprestimo(livro1, usuario1);
        emprestimoService.criarEmprestimo(livro2, usuario1);

        //ação
        boolean resultado = emprestimoService.criarEmprestimo(livro3, usuario1);

        //verificação
        assertTrue(!resultado);

    }


    /**
     * TESTES EM DEVOLUÇÃO
     */
    @Test
    void deveTestarDevolucaoAntesDaData() {

    }

    @Test
    void deveTestarDevolucaoNaDataPrevista() {

    }

    @Test
    void deveTestarDevolucaoUmDiaDeAtraso() {

    }

    @Test
    void deveTestarDevolucaoTrintaDiasDeAtraso() {

    }
}