package br.com.tamadrum.relatorioqso.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.tamadrum.relatorioqso.modelo.Estacao;
import br.com.tamadrum.relatorioqso.util.BancoUtil;

/**
 * Created by ettoreluglio on 03/05/17.
 */

public class EstacaoDao extends BancoUtil {

    private static final String TABELA = "ESTACAO";

    public static final String CREATEQUERY = "CREATE TABLE " + TABELA +
            " (id INTEGER PRIMARY KEY, " +
            "estacao TEXT, " +
            "indicativo TEXT, " +
            "frequencia TEXT, " +
            "subTon TEXT, " +
            "offset TEXT, " +
            "inicioQSO TEXT, " +
            "terminoQSO TEXT, " +
            "cidade TEXT, " +
            "estado TEXT, " +
            "pais TEXT, " +
            "latitude REAL, " +
            "longitude REAL, " +
            "altitude REAL);";

    public static final String CREATETABLE = "";

    public static final String UPDATETABLE = "";

    private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyy hh:mm:ss");

    public EstacaoDao(Context context) {
        super(context);
    }

    public void insertOrUpdate(Estacao estacao) {
        if ( estacao.getId() == null ) {
            Long id = getWritableDatabase().insert(TABELA, null, getContentValues(estacao));
            estacao.setId(id);
        } else {
            getWritableDatabase().update(TABELA, getContentValues(estacao), "id=?", new String[]{estacao.getId().toString()});
        }
    }

    public void delete (Estacao estacao) {
        getWritableDatabase().delete(TABELA, "id=?", new String[]{estacao.getId().toString()});
    }

    public List<Estacao> getLista () {
        List<Estacao> lista = new ArrayList<>();

        Cursor c = getWritableDatabase().query(TABELA, null, null, null, null, null, null);
        while(c.moveToNext()) {
            Estacao estacao = new Estacao();
            estacao.setId(c.getLong(c.getColumnIndex("id")));
            estacao.setEstacao(c.getString(c.getColumnIndex("estacao")));
            estacao.setIndicatico(c.getString(c.getColumnIndex("indicativo")));
            estacao.setFrequencia(c.getString(c.getColumnIndex("frequencia")));
            estacao.setSubTon(c.getString(c.getColumnIndex("subTon")));
            estacao.setOffset(c.getString(c.getColumnIndex("offset")));
            Calendar i = Calendar.getInstance();
            Calendar t = Calendar.getInstance();
            try {
                i.setTime(sdf.parse(c.getString(c.getColumnIndex("inicioQSO"))));
                t.setTime(sdf.parse(c.getString(c.getColumnIndex("terminoQSO"))));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            estacao.setInicioQSO(i);
            estacao.setTerminoQSO(t);
            estacao.setCidade(c.getString(c.getColumnIndex("cidade")));
            estacao.setEstado(c.getString(c.getColumnIndex("estado")));
            estacao.setPais(c.getString(c.getColumnIndex("pais")));
            estacao.setLatitude(c.getDouble(c.getColumnIndex("latitude")));
            estacao.setLongitude(c.getDouble(c.getColumnIndex("longitude")));
            estacao.setAltitude(c.getDouble(c.getColumnIndex("altitude")));

            lista.add(estacao);
        }
        return lista;
    }

    public ContentValues getContentValues(Estacao estacao) {
        ContentValues values = new ContentValues();
        values.put("id", estacao.getId());
        values.put("estacao", estacao.getEstacao());
        values.put("indicativo", estacao.getIndicatico());
        values.put("frequencia", estacao.getFrequencia());
        values.put("subTon", estacao.getSubTon());
        values.put("offset", estacao.getOffset());
        values.put("inicioQSO", sdf.format(estacao.getInicioQSO().getTime()));
        values.put("terminoQSO", sdf.format(estacao.getTerminoQSO().getTime()));
        values.put("cidade", estacao.getCidade());
        values.put("estado", estacao.getEstado());
        values.put("pais", estacao.getPais());
        values.put("latitude", estacao.getLatitude());
        values.put("longitude", estacao.getLongitude());
        values.put("altitude", estacao.getAltitude());

        return values;
    }
}
