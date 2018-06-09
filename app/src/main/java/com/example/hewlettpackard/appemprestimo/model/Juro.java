package com.example.hewlettpackard.appemprestimo.model;


import java.io.Serializable;

public class Juro implements Serializable{
    private int id, parcelas;

    private double taxa;

    public Juro(int id, int parcelas, double taxa) {
        this.id = id;
        this.parcelas = parcelas;
        this.taxa = taxa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParcelas() {
        return parcelas;
    }

    public void setParcelas(int parcelas) {
        this.parcelas = parcelas;
    }

    public double getTaxa() {
        return taxa;
    }

    public void setTaxa(double taxa) {
        this.taxa = taxa;
    }
}