package com.example.hewlettpackard.appemprestimo.dao.bd;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.hewlettpackard.appemprestimo.dao.SimulacaoDao;
import com.example.hewlettpackard.appemprestimo.model.Simulacao;

import java.util.ArrayList;
import java.util.List;

public class SimulacaoDaoBd implements SimulacaoDao {
    private BancoDadosOpenHelper bdOpenHelper;

    public SimulacaoDaoBd(Context contexto){
        bdOpenHelper = new BancoDadosOpenHelper(contexto);
    }

    @Override
    public void inserir(Simulacao simulacao) {
        SQLiteDatabase banco = bdOpenHelper.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("pessoa",simulacao.getPessoa());
        valores.put("parcela",simulacao.getParcela());
        valores.put("valorEmprestimo",simulacao.getValorEmprestimo());
        valores.put("valorParcela",simulacao.getValorParcela());
        banco.insert("simulacao",null,valores);

        banco.close();

    }

    @Override
    public void excluir(Simulacao simulacao) {
        SQLiteDatabase banco = bdOpenHelper.getWritableDatabase();
        banco.delete("simulacao","id=?",
                new String[]{String.valueOf(simulacao.getId())});
        banco.close();
    }

    @Override
    public void atualizar(Simulacao simulacao) {
        SQLiteDatabase banco = bdOpenHelper.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("pessoa",simulacao.getPessoa());
        valores.put("parcela",simulacao.getParcela());
        valores.put("valorEmprestimo",simulacao.getValorEmprestimo());
        valores.put("valorParcela",simulacao.getValorParcela());

        banco.update("simulacao",valores,"id=?",
                new String[]{String.valueOf(simulacao.getId())});
        banco.close();
    }

    @Override
    public List<Simulacao> listar() {
        SQLiteDatabase banco = bdOpenHelper.getReadableDatabase();

        Cursor cursor = banco.query("simulacao",
                new String[]{"id","pessoa","parcela","valorEmprestimo","valorParcela"},
                null,null,null,null,"id");

        List<Simulacao> listaSimulacaos = new ArrayList<>();

        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            Integer pessoa = cursor.getInt(cursor.getColumnIndex("pessoa"));
            Integer parcela = cursor.getInt(cursor.getColumnIndex("parcela"));
            Float valorEmprestimo = cursor.getFloat(cursor.getColumnIndex("valorEmprestimo"));
            Float valorParcela = cursor.getFloat(cursor.getColumnIndex("valorParcela"));
            Simulacao simulacao = new Simulacao(id, pessoa, parcela, valorEmprestimo, valorParcela);
            listaSimulacaos.add(simulacao);
        }
        return listaSimulacaos;
    }

    @Override
    public Simulacao procurarPorId(int id) {
        SQLiteDatabase banco = bdOpenHelper.getReadableDatabase();

        Cursor cursor = banco.query("simulacao",
                new String[]{"id","pessoa","parcela","valorEmprestimo","valorParcela"},
                "id = ?",new String[]{String.valueOf(id)},
                null,null,null);

        if(cursor.moveToNext()){
            int idX = cursor.getInt(cursor.getColumnIndex("id"));
            Integer pessoa = cursor.getInt(cursor.getColumnIndex("pessoa"));
            Integer parcela = cursor.getInt(cursor.getColumnIndex("parcela"));
            Float valorEmprestimo = cursor.getFloat(cursor.getColumnIndex("valorEmprestimo"));
            Float valorParcela = cursor.getFloat(cursor.getColumnIndex("valorParcela"));
            Simulacao simulacao = new Simulacao(idX, pessoa, parcela, valorEmprestimo, valorParcela);
            return simulacao;
        }
        return null;
    }

    @Override
    public Simulacao procurarPorPessoa(int pessoa) {
        SQLiteDatabase banco = bdOpenHelper.getReadableDatabase();

        Cursor cursor = banco.query("simulacao",
                new String[]{"id","pessoa","parcela","valorEmprestimo","valorParcela"},
                "pessoa = ?",new String[]{String.valueOf(pessoa)},
                null,null,null);

        if(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            Integer pessoaX = cursor.getInt(cursor.getColumnIndex("pessoa"));
            Integer parcela = cursor.getInt(cursor.getColumnIndex("parcela"));
            Float valorEmprestimo = cursor.getFloat(cursor.getColumnIndex("valorEmprestimo"));
            Float valorParcela = cursor.getFloat(cursor.getColumnIndex("valorParcela"));
            Simulacao simulacao = new Simulacao(id, pessoaX, parcela, valorEmprestimo, valorParcela);
            return simulacao;
        }
        return null;
    }
}
