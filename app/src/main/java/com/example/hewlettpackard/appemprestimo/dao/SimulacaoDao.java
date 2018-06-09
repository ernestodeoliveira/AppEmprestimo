package com.example.hewlettpackard.appemprestimo.dao;

import com.example.hewlettpackard.appemprestimo.model.Simulacao;

import java.util.List;

public interface SimulacaoDao {
    public void inserir(Simulacao simulacao);
    public void excluir(Simulacao simulacao);
    public void atualizar(Simulacao simulacao);
    public List<Simulacao> listar();
    public Simulacao procurarPorId(int id);
    public Simulacao procurarPorPessoa(int pessoa);

}
