package com.example.hewlettpackard.appemprestimo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import com.example.hewlettpackard.appemprestimo.adapter.SimulacaoAdapter;
import com.example.hewlettpackard.appemprestimo.dao.PessoaDao;
import com.example.hewlettpackard.appemprestimo.dao.SimulacaoDao;
import com.example.hewlettpackard.appemprestimo.dao.bd.PessoaDaoBd;
import com.example.hewlettpackard.appemprestimo.dao.bd.SimulacaoDaoBd;
import com.example.hewlettpackard.appemprestimo.model.Pessoa;
import com.example.hewlettpackard.appemprestimo.model.Simulacao;

public class MainActivity extends AppCompatActivity implements SimulacaoAdapter.SimulacaoOnClickListener {

    List<Simulacao> listaSimulacao = new ArrayList<>();
    RecyclerView recyclerView;
    SimulacaoAdapter simulacaoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycleView);

        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        simulacaoAdapter = new SimulacaoAdapter(getBaseContext(), listaSimulacao, this);
        recyclerView.setAdapter(simulacaoAdapter);
        recyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

    }

    @Override
    public void onResume() {
        super.onResume();
        SimulacaoDao dao = new SimulacaoDaoBd(this);
        listaSimulacao = dao.listar();
        simulacaoAdapter.setListaSimulacao(listaSimulacao);
        simulacaoAdapter.notifyDataSetChanged();
    }


    @Override
    public void onClickSimulacao(View view, int pos) {
        SimulacaoDao simulacaoDaoX = new SimulacaoDaoBd(this);
        Simulacao simulacao = simulacaoDaoX.procurarPorId(listaSimulacao.get(pos).getId());

        Intent it = new Intent(this, SimulacaoActivity.class);
        PessoaDao pessoaDaoX = new PessoaDaoBd(this);
        Pessoa pessoa = pessoaDaoX.procurarPorId(listaSimulacao.get(pos).getPessoa());
        it.putExtra("pessoa", pessoa);
        it.putExtra("simulacao", simulacao);
        startActivity(it);

        /*Intent it = new Intent(this, PessoaActivity.class);
        PessoaDao dao = new PessoaDaoBd(this);
        Pessoa pessoa = dao.procurarPorId(listaSimulacao.get(pos).getPessoa());
        it.putExtra("pessoa", pessoa);
        startActivity(it);*/
    }

    public void abrirFormulario(View v) {
        Intent it = new Intent(MainActivity.this, PessoaActivity.class);
        startActivity(it);
    }
}