package com.example.hewlettpackard.appemprestimo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hewlettpackard.appemprestimo.dao.PessoaDao;
import com.example.hewlettpackard.appemprestimo.dao.SimulacaoDao;
import com.example.hewlettpackard.appemprestimo.dao.bd.PessoaDaoBd;
import com.example.hewlettpackard.appemprestimo.dao.bd.SimulacaoDaoBd;
import com.example.hewlettpackard.appemprestimo.model.Pessoa;

public class PessoaActivity extends AppCompatActivity {
    Pessoa pessoaEditar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pessoa);

        Intent intent = getIntent();

        if (intent.hasExtra("pessoa")) {

            pessoaEditar = (Pessoa) intent.getSerializableExtra("pessoa");

            EditText textoCPF = (EditText) findViewById(R.id.textoCPF);
            EditText textoNome = (EditText) findViewById(R.id.textoParcelas);
            EditText textoEndereco = (EditText) findViewById(R.id.textoEndereco);
            EditText textoComplemento = (EditText) findViewById(R.id.textoComplemento);
            EditText textoBairro = (EditText) findViewById(R.id.textoComplemento);
            EditText textoCidade = (EditText) findViewById(R.id.textoComplemento);
            EditText textoEstado = (EditText) findViewById(R.id.textoComplemento);
            EditText textoCEP = (EditText) findViewById(R.id.textoCEP);
            EditText textoTelefone = (EditText) findViewById(R.id.textoTelefone);

            textoCPF.setText(pessoaEditar.getCpf());
            textoNome.setText(pessoaEditar.getNome());
            textoEndereco.setText(pessoaEditar.getEndereco());
            textoComplemento.setText(pessoaEditar.getComplemento());
            textoBairro.setText(pessoaEditar.getBairro());
            textoCidade.setText(pessoaEditar.getCidade());
            textoEstado.setText(pessoaEditar.getEstado());
            textoCEP.setText(pessoaEditar.getCep());
            textoTelefone.setText(pessoaEditar.getTelefone());
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        if (getIntent().getBooleanExtra("exit", false)) {
            finish();
        }
    }

    public void cadastrarPessoa(View view){

        EditText textoCPF = (EditText) findViewById(R.id.textoCPF);
        EditText textoNome = (EditText) findViewById(R.id.textoParcelas);
        EditText textoEndereco = (EditText) findViewById(R.id.textoEndereco);
        EditText textoComplemento = (EditText) findViewById(R.id.textoComplemento);
        EditText textoBairro = (EditText) findViewById(R.id.textoComplemento);
        EditText textoCidade = (EditText) findViewById(R.id.textoComplemento);
        EditText textoEstado = (EditText) findViewById(R.id.textoComplemento);
        EditText textoCEP = (EditText) findViewById(R.id.textoCEP);
        EditText textoTelefone = (EditText) findViewById(R.id.textoTelefone);

        String cpf = textoCPF.getText().toString();

        if (cpf.equals("") ){
            Toast.makeText(this,"Informe o CPF!", Toast.LENGTH_SHORT).show();
            return;
        }

        String nomeX = textoNome.getText().toString();
        if (nomeX.equals("") ){
            Toast.makeText(this,"Informe o nome!", Toast.LENGTH_SHORT).show();
            return;
        }

        PessoaDao dao = new PessoaDaoBd(this);
        Pessoa pessoaProcurar = dao.procurarPorCpf(cpf);
        if (pessoaProcurar != null){
            pessoaEditar.setNome(textoNome.getText().toString());
            pessoaEditar.setEndereco(textoEndereco.getText().toString());
            pessoaEditar.setComplemento(textoComplemento.getText().toString());
            pessoaEditar.setBairro(textoBairro.getText().toString());
            pessoaEditar.setCidade(textoCidade.getText().toString());
            pessoaEditar.setEstado(textoEstado.getText().toString());
            pessoaEditar.setCep(textoCEP.getText().toString());
            pessoaEditar.setTelefone(textoTelefone.getText().toString());

            dao.atualizar(pessoaEditar);
            Toast.makeText(this,"Edição realizada com sucesso!", Toast.LENGTH_SHORT).show();
        }else {
            String nome = textoNome.getText().toString();
            String endereco = textoEndereco.getText().toString();
            String complemento = textoComplemento.getText().toString();
            String bairro = textoBairro.getText().toString();
            String cidade = textoCidade.getText().toString();
            String estado = textoEstado.getText().toString();
            String cep = textoCEP.getText().toString();
            String telefone = textoTelefone.getText().toString();

            Pessoa pessoa = new Pessoa(cpf, nome, endereco, complemento, bairro, cidade, estado, cep, telefone);

            dao.inserir(pessoa);
            Toast.makeText(this, "Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();
        }
    }

    public void cancelarCadastro(View view){

        finish();

    }

    public void simularEmprestimo(View view){

        cadastrarPessoa(view);

        EditText textoCPF = (EditText) findViewById(R.id.textoCPF);
        String cpf = textoCPF.getText().toString();
        PessoaDao dao = new PessoaDaoBd(this);
        Pessoa pessoaProcurar = dao.procurarPorCpf(cpf);
        if (pessoaProcurar != null) {

            Intent it = new Intent(this, SimulacaoActivity.class);
            it.putExtra("pessoa", pessoaProcurar);
            startActivity(it);

        }
        else {

            Toast.makeText(this, "Cadastre os dados da pessoa!", Toast.LENGTH_SHORT).show();

        }
    }
}
