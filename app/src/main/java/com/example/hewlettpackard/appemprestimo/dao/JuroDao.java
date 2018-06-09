package com.example.hewlettpackard.appemprestimo.dao;

import com.example.hewlettpackard.appemprestimo.model.Juro;

import java.util.List;

public interface JuroDao {
    public void inserir(Juro juro);
    public void excluir(Juro juro);
    public void atualizar(Juro juro);
    public List<Juro> listar();
    public Juro procurarPorId(int id);
    public Juro procurarPorParcela(int parcela);

}
