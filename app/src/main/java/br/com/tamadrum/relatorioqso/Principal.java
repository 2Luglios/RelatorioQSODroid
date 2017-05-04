package br.com.tamadrum.relatorioqso;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.Calendar;
import java.util.List;

import br.com.tamadrum.relatorioqso.adapter.EstacaoAdapter;
import br.com.tamadrum.relatorioqso.dao.EstacaoDao;
import br.com.tamadrum.relatorioqso.modelo.Estacao;

public class Principal extends AppCompatActivity {

    private Estacao estacaoSelecionada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal_layout);

        final ListView lista = (ListView) findViewById(R.id.listaQSO);
        lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                estacaoSelecionada = (Estacao) parent.getItemAtPosition(position);
                registerForContextMenu(lista);
                return false;
            }
        });

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent editar = new Intent(Principal.this, Formulario.class);
                editar.putExtra("estacao", (Estacao) parent.getItemAtPosition(position));
                startActivity(editar);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }

    public void carregaLista() {
        ListView lista = (ListView) findViewById(R.id.listaQSO);

        EstacaoDao dao = new EstacaoDao(this);
        List<Estacao> estacoes = dao.getLista();
        dao.close();

        lista.setAdapter(new EstacaoAdapter(this, estacoes));
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_contexto_principal, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        EstacaoDao dao = new EstacaoDao(this);

        switch (item.getItemId()) {
            case R.id.menu_contexto_terminar_qso:
                estacaoSelecionada.setTerminoQSO(Calendar.getInstance());
                dao.insertOrUpdate(estacaoSelecionada);
                break;
            case R.id.menu_contexto_deletar:
                dao.delete(estacaoSelecionada);
                break;
        }

        dao.close();

        carregaLista();

        return super.onContextItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if ( item.getItemId() == R.id.menu_add_qso) {
            startActivity(new Intent(Principal.this, Formulario.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
