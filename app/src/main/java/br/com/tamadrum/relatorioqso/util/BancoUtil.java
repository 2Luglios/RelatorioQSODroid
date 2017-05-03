package br.com.tamadrum.relatorioqso.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.tamadrum.relatorioqso.dao.EstacaoDao;

/**
 * Created by ettoreluglio on 03/05/17.
 */

public class BancoUtil extends SQLiteOpenHelper {

    private static final String DATABASE = "RELATORIOQSO";
    private static final int VERSAO = 1;

    public BancoUtil(Context context) {
        super(context, DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(EstacaoDao.CREATEQUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(EstacaoDao.UPDATETABLE);
    }
}
