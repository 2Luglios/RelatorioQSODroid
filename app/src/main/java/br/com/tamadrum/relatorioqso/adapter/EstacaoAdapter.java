package br.com.tamadrum.relatorioqso.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.tamadrum.relatorioqso.R;
import br.com.tamadrum.relatorioqso.modelo.Estacao;

/**
 * Created by ettoreluglio on 03/05/17.
 */

public class EstacaoAdapter extends BaseAdapter {

    private Context ctx;
    private List<Estacao> estacoes;

    public EstacaoAdapter(Context ctx, List<Estacao> estacoes) {
        this.ctx = ctx;
        this.estacoes = estacoes;
    }

    @Override
    public int getCount() {
        return estacoes.size();
    }

    @Override
    public Object getItem(int position) {
        return estacoes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return estacoes.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = LayoutInflater.from(ctx).inflate(R.layout.item_estacao, null);

        Estacao estacao = estacoes.get(position);

        ((TextView) v.findViewById(R.id.frequencia)).setText(estacao.getFrequencia());
        ((TextView) v.findViewById(R.id.subTon)).setText(estacao.getSubTon());
        ((TextView) v.findViewById(R.id.offset)).setText(estacao.getOffset());
        ((TextView) v.findViewById(R.id.nomeEstacao)).setText(estacao.getEstacao());

        return v;
    }
}
