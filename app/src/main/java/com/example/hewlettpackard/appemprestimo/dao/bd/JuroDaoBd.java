package com.example.hewlettpackard.appemprestimo.dao.bd;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.hewlettpackard.appemprestimo.dao.JuroDao;
import com.example.hewlettpackard.appemprestimo.model.Juro;

import java.util.ArrayList;
import java.util.List;

public class JuroDaoBd implements JuroDao {
    private BancoDadosOpenHelper bdOpenHelper;

    public JuroDaoBd(Context contexto){
        bdOpenHelper = new BancoDadosOpenHelper(contexto);
    }

    @Override
    public void inserir(Juro juro) {
        SQLiteDatabase banco = bdOpenHelper.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("parcelas",juro.getParcelas());
        valores.put("taxa",juro.getTaxa());
        banco.insert("juro",null,valores);

        banco.close();

    }

    @Override
    public void excluir(Juro juro) {
        SQLiteDatabase banco = bdOpenHelper.getWritableDatabase();
        banco.delete("juro","id=?",
                new String[]{String.valueOf(juro.getId())});
        banco.close();
    }

    @Override
    public void atualizar(Juro juro) {
        SQLiteDatabase banco = bdOpenHelper.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("parcelas",juro.getParcelas());
        valores.put("taxa",juro.getTaxa());

        banco.update("juro",valores,"id=?",
                new String[]{String.valueOf(juro.getId())});
        banco.close();
    }

    @Override
    public List<Juro> listar() {
        SQLiteDatabase banco = bdOpenHelper.getReadableDatabase();

        Cursor cursor = banco.query("juro",
                new String[]{"id","parcelas","taxa"},
                null,null,null,null,"id");

        List<Juro> listaJuros = new ArrayList<>();

        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            Integer parcelas = cursor.getInt(cursor.getColumnIndex("parcelas"));
            Float taxa = cursor.getFloat(cursor.getColumnIndex("taxa"));
            Juro juro = new Juro(id, parcelas, taxa);
            listaJuros.add(juro);
        }
        return listaJuros;
    }

    @Override
    public Juro procurarPorId(int id) {
        SQLiteDatabase banco = bdOpenHelper.getReadableDatabase();

        Cursor cursor = banco.query("juro",
                new String[]{"id","parcelas","taxa"},
                "id = ?",new String[]{String.valueOf(id)},
                null,null,null);

        if(cursor.moveToNext()){
            int idX = cursor.getInt(cursor.getColumnIndex("id"));
            Integer parcelas = cursor.getInt(cursor.getColumnIndex("parcelas"));
            Float taxa = cursor.getFloat(cursor.getColumnIndex("taxa"));
            Juro juro = new Juro(idX, parcelas, taxa);
            return juro;
        }
        return null;
    }

    @Override
    public Juro procurarPorParcela(int parcela) {
        SQLiteDatabase banco = bdOpenHelper.getReadableDatabase();

        Cursor cursor = banco.query("juro",
                new String[]{"id","parcelas","taxa"},
                "parcelas = ?",new String[]{String.valueOf(parcela)},
                null,null,null);

        if(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            Integer parcelas = cursor.getInt(cursor.getColumnIndex("parcelas"));
            Float taxa = cursor.getFloat(cursor.getColumnIndex("taxa"));
            Juro juro = new Juro(id, parcelas, taxa);
            return juro;
        }
        return null;
    }
}
