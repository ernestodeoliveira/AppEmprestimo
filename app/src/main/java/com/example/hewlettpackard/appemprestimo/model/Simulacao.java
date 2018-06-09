package com.example.hewlettpackard.appemprestimo.model;


import java.io.Serializable;

public class Simulacao implements Serializable{
    private int id, pessoa, parcela;

    private double valorEmprestimo, valorParcela;


    public Simulacao(int id, int pessoa, int parcela, double valorEmprestimo, double valorParcela) {
        this.id = id;
        this.pessoa = pessoa;
        this.parcela = parcela;
        this.valorEmprestimo = valorEmprestimo;
        this.valorParcela = valorParcela;
    }

    public Simulacao(int pessoa, int parcela, double valorEmprestimo, double valorParcela) {
        this.pessoa = pessoa;
        this.parcela = parcela;
        this.valorEmprestimo = valorEmprestimo;
        this.valorParcela = valorParcela;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPessoa() {
        return pessoa;
    }

    public void setPessoa(int pessoa) {
        this.pessoa = pessoa;
    }

    public int getParcela() {
        return parcela;
    }

    public void setParcela(int parcela) {
        this.parcela = parcela;
    }

    public double getValorEmprestimo() {
        return valorEmprestimo;
    }

    public void setValorEmprestimo(double valorEmprestimo) {
        this.valorEmprestimo = valorEmprestimo;
    }

    public double getValorParcela() {
        return valorParcela;
    }

    public void setValorParcela(double valorParcela) {
        this.valorParcela = valorParcela;
    }
}