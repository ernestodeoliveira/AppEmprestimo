package com.example.hewlettpackard.appemprestimo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hewlettpackard.appemprestimo.R;
import com.example.hewlettpackard.appemprestimo.model.Simulacao;

import java.text.DecimalFormat;
import java.util.List;

public class SimulacaoAdapter extends RecyclerView.Adapter<SimulacaoAdapter.ViewHolder>
{

    private List<Simulacao> listaSimulacao;
    private Context contexto;
    private SimulacaoOnClickListener contatoOnClickListener;

    public SimulacaoAdapter(Context contexto, List<Simulacao> listaSimulacao, SimulacaoOnClickListener contatoOnClickListener) {
        this.contexto = contexto;
        this.listaSimulacao = listaSimulacao;
        this.contatoOnClickListener = contatoOnClickListener;
    }

    public void setListaSimulacao(List<Simulacao> listaSimulacao) {
        this.listaSimulacao = listaSimulacao;
    }

    @Override
    public SimulacaoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_item_simulacao,parent,false );
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final SimulacaoAdapter.ViewHolder holder, final int position) {
        holder.textoParcela.setText(String.valueOf(listaSimulacao.get(position).getParcela()));
        DecimalFormat doisDigitos = new DecimalFormat("###,##0.00");
        holder.textoValorEmprestimo.setText(doisDigitos.format(listaSimulacao.get(position).getValorEmprestimo()));
        holder.textoValorParcela.setText(doisDigitos.format(listaSimulacao.get(position).getValorParcela()));

        holder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                contatoOnClickListener.onClickSimulacao(holder.itemView, position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listaSimulacao.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView textoParcela;
        public TextView textoValorEmprestimo;
        public TextView textoValorParcela;
        public ViewHolder(View view) {
            super(view);
            this.textoParcela = view.findViewById(R.id.textoParcela);
            this.textoValorEmprestimo = view.findViewById(R.id.textoValorEmprestimo);
            this.textoValorParcela = view.findViewById(R.id.textoValorParcela);

        }
    }

    public interface SimulacaoOnClickListener{
        public void onClickSimulacao(View view, int pos);
    }

}
