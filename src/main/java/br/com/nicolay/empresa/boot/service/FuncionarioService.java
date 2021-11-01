package br.com.nicolay.empresa.boot.service;

import br.com.nicolay.empresa.boot.domain.Departamento;
import br.com.nicolay.empresa.boot.domain.Funcionario;

import java.util.List;

public interface FuncionarioService {

    void salvar (Funcionario funcionario);

    void editar (Funcionario funcionario);

    void excluir(Long id);

    Funcionario buscarPorId(Long id);

    List<Funcionario> buscarTodos();

}
