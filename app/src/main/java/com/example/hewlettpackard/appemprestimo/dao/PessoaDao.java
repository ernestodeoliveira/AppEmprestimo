package com.example.hewlettpackard.appemprestimo.dao;

import java.util.List;

import com.example.hewlettpackard.appemprestimo.model.Pessoa;

public interface PessoaDao {
    public void inserir(Pessoa pessoa);
    public void excluir(Pessoa pessoa);
    public void atualizar(Pessoa pessoa);
    public List<Pessoa> listar();
    public Pessoa procurarPorId(int id);
    public Pessoa procurarPorCpf(String cpf);

}
