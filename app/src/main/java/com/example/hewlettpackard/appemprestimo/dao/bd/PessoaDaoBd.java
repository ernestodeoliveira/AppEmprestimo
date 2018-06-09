package com.example.hewlettpackard.appemprestimo.dao.bd;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.hewlettpackard.appemprestimo.dao.PessoaDao;

import java.util.ArrayList;
import java.util.List;

import com.example.hewlettpackard.appemprestimo.dao.bd.BancoDadosOpenHelper;
import  com.example.hewlettpackard.appemprestimo.model.Pessoa;

public class PessoaDaoBd implements PessoaDao {
    private BancoDadosOpenHelper bdOpenHelper;

    public PessoaDaoBd(Context contexto){
        bdOpenHelper = new BancoDadosOpenHelper(contexto);
    }

    @Override
    public void inserir(Pessoa pessoa) {
        SQLiteDatabase banco = bdOpenHelper.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("cpf",pessoa.getCpf());
        valores.put("nome",pessoa.getNome());
        valores.put("endereco",pessoa.getEndereco());
        valores.put("complemento",pessoa.getComplemento());
        valores.put("bairro",pessoa.getBairro());
        valores.put("cidade",pessoa.getCidade());
        valores.put("estado",pessoa.getEstado());
        valores.put("cep",pessoa.getCep());
        valores.put("telefone",pessoa.getTelefone());
        banco.insert("pessoa",null,valores);

        banco.close();

    }

    @Override
    public void excluir(Pessoa pessoa) {
        SQLiteDatabase banco = bdOpenHelper.getWritableDatabase();
        banco.delete("pessoa","id=?",
                new String[]{String.valueOf(pessoa.getId())});
        banco.close();
    }

    @Override
    public void atualizar(Pessoa pessoa) {
        SQLiteDatabase banco = bdOpenHelper.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put("cpf",pessoa.getCpf());
        valores.put("nome",pessoa.getNome());
        valores.put("endereco",pessoa.getEndereco());
        valores.put("complemento",pessoa.getComplemento());
        valores.put("bairro",pessoa.getBairro());
        valores.put("cidade",pessoa.getCidade());
        valores.put("estado",pessoa.getEstado());
        valores.put("cep",pessoa.getCep());
        valores.put("telefone",pessoa.getTelefone());

        banco.update("pessoa",valores,"id=?",
                new String[]{String.valueOf(pessoa.getId())});
        banco.close();
    }

    @Override
    public List<Pessoa> listar() {
        SQLiteDatabase banco = bdOpenHelper.getReadableDatabase();

        Cursor cursor = banco.query("pessoa",
                new String[]{"id","cpf","nome","endereco","complemento","bairro","cidade","estado","cep","telefone"},
                null,null,null,null,"nome");

        List<Pessoa> listaPessoas = new ArrayList<>();

        while(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String cpf = cursor.getString(cursor.getColumnIndex("cpf"));
            String nome = cursor.getString(cursor.getColumnIndex("nome"));
            String endereco = cursor.getString(cursor.getColumnIndex("endereco"));
            String complemento = cursor.getString(cursor.getColumnIndex("complemento"));
            String bairro = cursor.getString(cursor.getColumnIndex("bairro"));
            String cidade = cursor.getString(cursor.getColumnIndex("cidade"));
            String estado = cursor.getString(cursor.getColumnIndex("estado"));
            String cep = cursor.getString(cursor.getColumnIndex("cep"));
            String telefone = cursor.getString(cursor.getColumnIndex("telefone"));
            Pessoa pessoa = new Pessoa(id, cpf, nome, endereco, complemento, bairro, cidade, estado, cep, telefone);
            listaPessoas.add(pessoa);
        }
        return listaPessoas;
    }

    @Override
    public Pessoa procurarPorId(int id) {
        SQLiteDatabase banco = bdOpenHelper.getReadableDatabase();

        Cursor cursor = banco.query("pessoa",
                new String[]{"id","cpf","nome","endereco","complemento","bairro","cidade","estado","cep","telefone"},
                "id = ?",new String[]{String.valueOf(id)},
                null,null,null);

        if(cursor.moveToNext()){
            int idX = cursor.getInt(cursor.getColumnIndex("id"));
            String cpf = cursor.getString(cursor.getColumnIndex("cpf"));
            String nome = cursor.getString(cursor.getColumnIndex("nome"));
            String endereco = cursor.getString(cursor.getColumnIndex("endereco"));
            String complemento = cursor.getString(cursor.getColumnIndex("complemento"));
            String bairro = cursor.getString(cursor.getColumnIndex("bairro"));
            String cidade = cursor.getString(cursor.getColumnIndex("cidade"));
            String estado = cursor.getString(cursor.getColumnIndex("estado"));
            String cep = cursor.getString(cursor.getColumnIndex("cep"));
            String telefone = cursor.getString(cursor.getColumnIndex("telefone"));
            Pessoa pessoa = new Pessoa(idX, cpf, nome, endereco, complemento, bairro, cidade, estado, cep, telefone);
            return pessoa;
        }
        return null;
    }

    @Override
    public Pessoa procurarPorCpf(String cpf) {
        SQLiteDatabase banco = bdOpenHelper.getReadableDatabase();

        Cursor cursor = banco.query("pessoa",
                new String[]{"id","cpf","nome","endereco","complemento","bairro","cidade","estado","cep","telefone"},
                "cpf = ?",new String[]{String.valueOf(cpf)},
                null,null,null);

        if(cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String cpfX = cursor.getString(cursor.getColumnIndex("cpf"));
            String nome = cursor.getString(cursor.getColumnIndex("nome"));
            String endereco = cursor.getString(cursor.getColumnIndex("endereco"));
            String complemento = cursor.getString(cursor.getColumnIndex("complemento"));
            String bairro = cursor.getString(cursor.getColumnIndex("bairro"));
            String cidade = cursor.getString(cursor.getColumnIndex("cidade"));
            String estado = cursor.getString(cursor.getColumnIndex("estado"));
            String cep = cursor.getString(cursor.getColumnIndex("cep"));
            String telefone = cursor.getString(cursor.getColumnIndex("telefone"));
            Pessoa pessoa = new Pessoa(id, cpfX, nome, endereco, complemento, bairro, cidade, estado, cep, telefone);
            return pessoa;
        }
        return null;
    }
}
