package com.example.hewlettpackard.appemprestimo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hewlettpackard.appemprestimo.dao.JuroDao;
import com.example.hewlettpackard.appemprestimo.dao.SimulacaoDao;
import com.example.hewlettpackard.appemprestimo.dao.bd.JuroDaoBd;
import com.example.hewlettpackard.appemprestimo.dao.bd.SimulacaoDaoBd;
import com.example.hewlettpackard.appemprestimo.model.Juro;
import com.example.hewlettpackard.appemprestimo.model.Pessoa;
import com.example.hewlettpackard.appemprestimo.model.Simulacao;

import java.text.DecimalFormat;

public class SimulacaoActivity extends AppCompatActivity {

    Pessoa pessoaSimulacao;

    Simulacao simulacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simulacao);

        Intent intent = getIntent();

        if (intent.hasExtra("pessoa")) {

            pessoaSimulacao = (Pessoa) intent.getSerializableExtra("pessoa");

        }

        if (intent.hasExtra("simulacao")) {

            simulacao = (Simulacao) intent.getSerializableExtra("simulacao");

            EditText textoValorEmprestimo = (EditText) findViewById(R.id.textoValorEmprestimo);
            EditText textoParcela = (EditText) findViewById(R.id.textoParcela);

            textoValorEmprestimo.setText(String.valueOf(simulacao.getValorEmprestimo()));
            textoParcela.setText(String.valueOf(simulacao.getParcela()));
        }
    }

    public void cancelarSimulacao(View view){
        finish();
    }

    public void simularEmprestimo(View view){

        EditText textoParcela = (EditText) findViewById(R.id.textoParcela);
        String parcela = textoParcela.getText().toString();

        EditText textoValorEmprestimo = (EditText) findViewById(R.id.textoValorEmprestimo);
        String valorEmprestimo = textoValorEmprestimo.getText().toString();

        Button botaoConcluir = (Button) findViewById(R.id.botaoConcluir);

        if (valorEmprestimo.equals("") ){
            Toast.makeText(this,"Informe o valor!", Toast.LENGTH_SHORT).show();
            return;
        }

        if (parcela.equals("") ){
            Toast.makeText(this,"Informe as parcela!", Toast.LENGTH_SHORT).show();
            return;
        }

        JuroDao dao = new JuroDaoBd(this);
        Juro juroProcurar = dao.procurarPorParcela(Integer.parseInt(parcela));
        if (juroProcurar != null) {

            TextView labelResultadoSimulacao = (TextView) findViewById(R.id.labelResultadoSimulacao);

            DecimalFormat doisDigitos = new DecimalFormat("###,##0.00");

            labelResultadoSimulacao.setText("Valor da parcela = R$ " + doisDigitos.format((Double.parseDouble(valorEmprestimo) * juroProcurar.getTaxa()) / Double.parseDouble(parcela)));
            botaoConcluir.setVisibility(View.VISIBLE);

        } else
        {
            botaoConcluir.setVisibility(View.INVISIBLE);
            Toast.makeText(this,"Condição não encontrada!", Toast.LENGTH_SHORT).show();
        }
    }

    public void concluirEmprestimo(View view){

        EditText textoParcela = (EditText) findViewById(R.id.textoParcela);
        String parcela = textoParcela.getText().toString();

        EditText textoValorEmprestimo = (EditText) findViewById(R.id.textoValorEmprestimo);
        String valorEmprestimo = textoValorEmprestimo.getText().toString();

        JuroDao juroDaoX = new JuroDaoBd(this);
        Juro juroProcurar = juroDaoX.procurarPorParcela(Integer.parseInt(parcela));
        if (juroProcurar != null) {

            TextView labelResultadoSimulacao = (TextView) findViewById(R.id.labelResultadoSimulacao);

            DecimalFormat doisDigitos = new DecimalFormat("###,##0.00");
            Double valorParcela = (Double.parseDouble(valorEmprestimo) * juroProcurar.getTaxa()) / Double.parseDouble(parcela);

            Simulacao simulacao = new Simulacao(pessoaSimulacao.getId(), Integer.parseInt(parcela), Double.parseDouble(valorEmprestimo), valorParcela);

            SimulacaoDao simulacaoDaoX = new SimulacaoDaoBd(this);
            simulacaoDaoX.inserir(simulacao);
            Toast.makeText(this, "Cadastro da simulação realizado com sucesso!", Toast.LENGTH_SHORT).show();

        }

        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        Intent it = new Intent(SimulacaoActivity.this, PessoaActivity.class);
        it.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        it.putExtra("exit", true);
        startActivity(it);
        finish();
    }


}
