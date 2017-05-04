package br.com.tamadrum.relatorioqso;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import br.com.tamadrum.relatorioqso.dao.EstacaoDao;
import br.com.tamadrum.relatorioqso.modelo.Estacao;

public class Formulario extends AppCompatActivity {

    private String[] subtons = {"",
            "67.0", "69.3", "71.9", "74.4", "77.0", "79.7", "82.5", "85.4",
            "88.5", "91.5", "94.8", "97.4", "100.0", "103.5", "107.2", "110.9",
            "114.8", "118.8", "123.0", "127.3", "131.8", "136.5", "141.3", "146.2",
            "151.4", "156.7", "162.2", "167.9", "173.8", "179.9", "186.2", "192.8",
            "203.5", "206.5", "210.7", "218.1", "225.7", "229.1", "233.6", "241.8",
            "250.3", "254.1"
    };

    private String[] offsets = {"", "-500", "+600", "-600", "-1600", "-5000" };

    private double frequenciaDouble;
    private int subTonInt;
    private int offsetInt;

    private double passo = 5;

    private Estacao estacao;

    private TextView frequencia;
    private TextView subTon;
    private TextView offset;
    private EditText nomeEstacao;
    private EditText indicativo;
    private EditText pais;
    private EditText estado;
    private EditText cidade;
    private Button btnHf;
    private Button btnVhf;
    private Button btnUhf;
    private Button btnFrequenciaAnterior100;
    private Button btnFrequenciaProximo100;
    private Button btnFrequenciaAnterior5;
    private Button btnFrequenciaProximo5;
    private Button btnSubTonAnterior;
    private Button btnSubTonProximo;
    private Button btnOffsetAnterior;
    private Button btnOffsetProximo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario_layout);

        frequencia = (TextView) findViewById(R.id.frequencia);
        subTon = (TextView) findViewById(R.id.subTon);
        offset = (TextView) findViewById(R.id.offset);
        nomeEstacao = (EditText) findViewById(R.id.nomeEstacao);
        indicativo = (EditText) findViewById(R.id.indicativo);
        pais = (EditText) findViewById(R.id.pais);
        estado = (EditText) findViewById(R.id.estado);
        cidade = (EditText) findViewById(R.id.cidade);

        btnHf = (Button) findViewById(R.id.btnHF);
        btnHf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frequenciaDouble = 26965;
                frequencia.setText(NumberFormat.getNumberInstance().format(frequenciaDouble));
                passo = 5;
                btnFrequenciaProximo5.setText("+" + passo);
                btnFrequenciaAnterior5.setText("-" + passo);
            }
        });

        btnVhf = (Button) findViewById(R.id.btnVHF);
        btnVhf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frequenciaDouble = 144000;
                frequencia.setText(NumberFormat.getNumberInstance().format(frequenciaDouble));
                passo = 5;
                btnFrequenciaProximo5.setText("+" + passo);
                btnFrequenciaAnterior5.setText("-" + passo);
            }
        });

        btnUhf = (Button) findViewById(R.id.btnUHF);
        btnUhf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frequenciaDouble = 430000;
                frequencia.setText(NumberFormat.getNumberInstance().format(frequenciaDouble));
                passo = 12.5;
                btnFrequenciaProximo5.setText("+" + passo);
                btnFrequenciaAnterior5.setText("-" + passo);
            }
        });

        btnFrequenciaAnterior100 = (Button) findViewById(R.id.btnFrequenciaAnterior100);
        btnFrequenciaAnterior100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frequenciaDouble -= 100;
                frequencia.setText(NumberFormat.getNumberInstance().format(frequenciaDouble));
            }
        });

        btnFrequenciaProximo100 = (Button) findViewById(R.id.btnFrequenciaProximo100);
        btnFrequenciaProximo100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frequenciaDouble += 100;
                frequencia.setText(NumberFormat.getNumberInstance().format(frequenciaDouble));
            }
        });

        btnFrequenciaAnterior5 = (Button) findViewById(R.id.btnFrequenciaAnterior5);
        btnFrequenciaAnterior5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frequenciaDouble -= passo;
                frequencia.setText(NumberFormat.getNumberInstance().format(frequenciaDouble));
            }
        });

        btnFrequenciaProximo5 = (Button) findViewById(R.id.btnFrequenciaProximo5);
        btnFrequenciaProximo5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frequenciaDouble += passo;
                frequencia.setText(NumberFormat.getNumberInstance().format(frequenciaDouble));
            }
        });

        btnSubTonAnterior = (Button) findViewById(R.id.btnSubTonAnterior);
        btnSubTonAnterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subTonInt--;
                if ( subTonInt < 0 ) subTonInt = subtons.length-1;
                subTon.setText(subtons[subTonInt]);
            }
        });

        btnSubTonProximo = (Button) findViewById(R.id.btnSubTonProximo);
        btnSubTonProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subTonInt++;
                if ( subTonInt >= subtons.length ) subTonInt = 0;
                subTon.setText(subtons[subTonInt]);
            }
        });

        btnOffsetAnterior = (Button) findViewById(R.id.btnOffsetAnterior);
        btnOffsetAnterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                offsetInt--;
                if ( offsetInt < 0 ) offsetInt = offsets.length-1;
                offset.setText(offsets[offsetInt]);
            }
        });

        btnOffsetProximo = (Button) findViewById(R.id.btnOffsetProximo);
        btnOffsetProximo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                offsetInt++;
                if ( offsetInt >= offsets.length ) offsetInt = 0;
                offset.setText(offsets[offsetInt]);
            }
        });

        estacao = (Estacao) getIntent().getSerializableExtra("estacao");
        if ( estacao == null ) {
            estacao = new Estacao();
            frequenciaDouble = 144000;
            frequencia.setText(NumberFormat.getNumberInstance().format(frequenciaDouble));
            subTonInt = 0;
            subTon.setText(subtons[subTonInt]);
            offsetInt = 0;
            offset.setText(offsets[offsetInt]);
        } else {
            try {
                frequenciaDouble = NumberFormat.getNumberInstance().parse(estacao.getFrequencia()).intValue();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            subTonInt = ((List)Arrays.asList(subtons)).indexOf(estacao.getSubTon());
            offsetInt = ((List)Arrays.asList(offsets)).indexOf(estacao.getOffset());

            if ( frequenciaDouble > 300000 ) passo = 12.5;
            else passo = 5;

            btnFrequenciaProximo5.setText("+" + passo);
            btnFrequenciaAnterior5.setText("-" + passo);

            frequencia.setText(estacao.getFrequencia());
            nomeEstacao.setText(estacao.getEstacao());
            indicativo.setText(estacao.getIndicatico());
            subTon.setText(estacao.getSubTon());
            offset.setText(estacao.getOffset());
            pais.setText(estacao.getPais());
            estado.setText(estacao.getEstado());
            cidade.setText(estacao.getCidade());
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_formulario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        estacao.setEstacao(nomeEstacao.getEditableText().toString());
        estacao.setIndicatico(indicativo.getEditableText().toString());
        estacao.setFrequencia(frequencia.getText().toString());
        estacao.setSubTon(subTon.getText().toString());
        estacao.setOffset(offset.getText().toString());
        estacao.setInicioQSO(Calendar.getInstance());
        estacao.setTerminoQSO(Calendar.getInstance());
        estacao.setPais(pais.getEditableText().toString());
        estacao.setEstado(estado.getEditableText().toString());
        estacao.setCidade(cidade.getEditableText().toString());

        EstacaoDao dao = new EstacaoDao(this);
        dao.insertOrUpdate(estacao);
        dao.close();

        finish();

        return super.onOptionsItemSelected(item);
    }
}
