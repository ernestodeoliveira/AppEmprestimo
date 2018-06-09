package com.example.hewlettpackard.appemprestimo.dao.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Array;

public class BancoDadosOpenHelper extends SQLiteOpenHelper {
    private static String nomeBD = "emprestimo.db";
    private static String createTablePessoa = "CREATE TABLE pessoa" +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "cpf VARCHAR(11)," +
            "nome VARCHAR(30)," +
            "endereco VARCHAR(30)," +
            "complemento VARCHAR(30)," +
            "bairro VARCHAR(30)," +
            "cidade VARCHAR(30)," +
            "estado VARCHAR(30)," +
            "cep VARCHAR(8)," +
            "telefone VARCHAR(20))";

    private static String createTableSimulacao = "CREATE TABLE simulacao" +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "pessoa INTEGER," +
            "parcela INTEGER," +
            "valorEmprestimo NUMERIC," +
            "valorParcela NUMERIC)";

    private static String createTableJuro = "CREATE TABLE juro" +
            "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "parcelas INTEGER," +
            "taxa NUMERIC)";

    private static String[] popularTableJuro = {"insert into juro (parcelas, taxa) values (	2	,	1.01	);	" ,
            "insert into juro (parcelas, taxa) values (	3	,	1.02	);	" ,
            "insert into juro (parcelas, taxa) values (	4	,	1.03	);	" ,
            "insert into juro (parcelas, taxa) values (	5	,	1.04	);	" ,
            "insert into juro (parcelas, taxa) values (	6	,	1.05	);	" ,
            "insert into juro (parcelas, taxa) values (	7	,	1.06	);	" ,
            "insert into juro (parcelas, taxa) values (	8	,	1.07	);	" ,
            "insert into juro (parcelas, taxa) values (	9	,	1.08	);	" ,
            "insert into juro (parcelas, taxa) values (	10	,	1.09	);	" ,
            "insert into juro (parcelas, taxa) values (	11	,	1.10	);	" ,
            "insert into juro (parcelas, taxa) values (	12	,	1.11	);	" ,
            "insert into juro (parcelas, taxa) values (	13	,	1.12	);	" ,
            "insert into juro (parcelas, taxa) values (	14	,	1.13	);	" ,
            "insert into juro (parcelas, taxa) values (	15	,	1.14	);	" ,
            "insert into juro (parcelas, taxa) values (	16	,	1.15	);	" ,
            "insert into juro (parcelas, taxa) values (	17	,	1.16	);	" ,
            "insert into juro (parcelas, taxa) values (	18	,	1.17	);	" ,
            "insert into juro (parcelas, taxa) values (	19	,	1.18	);	" ,
            "insert into juro (parcelas, taxa) values (	20	,	1.19	);	" ,
            "insert into juro (parcelas, taxa) values (	21	,	1.20	);	" ,
            "insert into juro (parcelas, taxa) values (	22	,	1.21	);	" ,
            "insert into juro (parcelas, taxa) values (	23	,	1.22	);	" ,
            "insert into juro (parcelas, taxa) values (	24	,	1.23	);	" ,
            "insert into juro (parcelas, taxa) values (	25	,	1.24	);	" ,
            "insert into juro (parcelas, taxa) values (	26	,	1.25	);	" ,
            "insert into juro (parcelas, taxa) values (	27	,	1.26	);	" ,
            "insert into juro (parcelas, taxa) values (	28	,	1.27	);	" ,
            "insert into juro (parcelas, taxa) values (	29	,	1.28	);	" ,
            "insert into juro (parcelas, taxa) values (	30	,	1.29	);	" ,
            "insert into juro (parcelas, taxa) values (	31	,	1.30	);	" ,
            "insert into juro (parcelas, taxa) values (	32	,	1.31	);	" ,
            "insert into juro (parcelas, taxa) values (	33	,	1.32	);	" ,
            "insert into juro (parcelas, taxa) values (	34	,	1.33	);	" ,
            "insert into juro (parcelas, taxa) values (	35	,	1.34	);	" ,
            "insert into juro (parcelas, taxa) values (	36	,	1.35	);	" ,
            "insert into juro (parcelas, taxa) values (	37	,	1.36	);	" ,
            "insert into juro (parcelas, taxa) values (	38	,	1.37	);	" ,
            "insert into juro (parcelas, taxa) values (	39	,	1.38	);	" ,
            "insert into juro (parcelas, taxa) values (	40	,	1.39	);	" ,
            "insert into juro (parcelas, taxa) values (	41	,	1.40	);	" ,
            "insert into juro (parcelas, taxa) values (	42	,	1.41	);	" ,
            "insert into juro (parcelas, taxa) values (	43	,	1.42	);	" ,
            "insert into juro (parcelas, taxa) values (	44	,	1.43	);	" ,
            "insert into juro (parcelas, taxa) values (	45	,	1.44	);	" ,
            "insert into juro (parcelas, taxa) values (	46	,	1.45	);	" ,
            "insert into juro (parcelas, taxa) values (	47	,	1.46	);	" ,
            "insert into juro (parcelas, taxa) values (	48	,	1.47	);	" ,
            "insert into juro (parcelas, taxa) values (	49	,	1.48	);	" ,
            "insert into juro (parcelas, taxa) values (	50	,	1.49	);	" ,
            "insert into juro (parcelas, taxa) values (	51	,	1.50	);	" ,
            "insert into juro (parcelas, taxa) values (	52	,	1.51	);	" ,
            "insert into juro (parcelas, taxa) values (	53	,	1.52	);	" ,
            "insert into juro (parcelas, taxa) values (	54	,	1.53	);	" ,
            "insert into juro (parcelas, taxa) values (	55	,	1.54	);	" ,
            "insert into juro (parcelas, taxa) values (	56	,	1.55	);	" ,
            "insert into juro (parcelas, taxa) values (	57	,	1.56	);	" ,
            "insert into juro (parcelas, taxa) values (	58	,	1.57	);	" ,
            "insert into juro (parcelas, taxa) values (	59	,	1.58	);	" ,
            "insert into juro (parcelas, taxa) values (	60	,	1.59	);	"};


    public BancoDadosOpenHelper(Context context) {
        super(context, nomeBD, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTablePessoa);
        db.execSQL(createTableSimulacao);
        db.execSQL(createTableJuro);
        for (String s: popularTableJuro) {
                db.execSQL(s);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE pessoa");
        db.execSQL("DROP TABLE simulacao");
        db.execSQL("DROP TABLE juro");
        db.execSQL(createTablePessoa);
        db.execSQL(createTableSimulacao);
        db.execSQL(createTableJuro);
        for (String s: popularTableJuro) {
            db.execSQL(s);
        }
    }
}
