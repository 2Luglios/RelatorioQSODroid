package br.com.tamadrum.relatorioqso.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;

import br.com.tamadrum.relatorioqso.R;
import br.com.tamadrum.relatorioqso.modelo.Estacao;

/**
 * Created by ettoreluglio on 03/05/17.
 */

public class EstacaoAdapter extends BaseAdapter {

    private Context ctx;
    private List<Estacao> estacoes;
    private SimpleDateFormat sdfHora = new SimpleDateFormat("hh:mm:ss");

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
        if ( estacao.getInicioQSO().getTimeInMillis() == estacao.getTerminoQSO().getTimeInMillis() ) {
            ((TextView) v.findViewById(R.id.tempo)).setText(sdfHora.format(estacao.getInicioQSO().getTime()));
        } else {
            long diferenca = estacao.getTerminoQSO().getTimeInMillis() - estacao.getInicioQSO().getTimeInMillis();
            diferenca = diferenca/1000;
            long hora = diferenca/60/60;
            long minuto = diferenca/60%60;
            long segundo = diferenca-hora*60*60-minuto*60;

            String tempo =  (hora<10?"0"+hora:""+hora) + ":" +
                    (minuto<10?"0"+minuto:""+minuto) + ":" +
                    (segundo<10?"0"+segundo:""+segundo);

            ((TextView) v.findViewById(R.id.tempo)).setText(tempo);
        }

        return v;
    }
}
