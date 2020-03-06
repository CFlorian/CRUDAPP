package com.cksolutions.crudapp.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class dbData {

    private static final int DBVER = 1  ;
    private static final String DBNAME = "CRUDDB";
    private DBHelper conn;
    @SuppressWarnings("unused")
    private Context context;
    private String strResultado;

    public dbData(Context ctx) {
        this.context = ctx;
        conn = new DBHelper(ctx);
    }

    public String inserta_contacto(String nombre, String fechaNac, int telefono, int sexo) {

        SQLiteDatabase db;
        db = conn.getWritableDatabase();
        String strSql = "";

        strSql = "";
        strSql += "INSERT INTO contacto (Nombre,Fecha_nacimiento,Telefono,Sexo) ";
        strSql += "VALUES(";
        strSql += "'" + nombre + "',";
        strSql += "'" + fechaNac + "',";
        strSql += "'" + telefono + "',";
        strSql += "'" + sexo + "')";
        db.execSQL(strSql);
        db.close();
        return "Contacto Grabado!!";
    }

    public ArrayList<String> getContactos() {
        ArrayList<String> my_array = new ArrayList<String>();
        my_array.clear();
        SQLiteDatabase db;
        db = conn.getWritableDatabase();
        Cursor allrows = db.rawQuery("SELECT Nombre, Telefono FROM contacto ", null);
        if (allrows.moveToFirst()) {
            do {
                String nombre = allrows.getString(0);
                String tel = allrows.getString(1);
                my_array.add(nombre);
                my_array.add(tel);
            } while (allrows.moveToNext());
        }
        allrows.close();
        db.close();
        return my_array;
    }

    class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context) {
            super(context, DBNAME, null, DBVER);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String strSql = "CREATE TABLE contacto ("
                    + " Id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + " Nombre TEXT,"
                    + " Fecha_nacimiento TEXT,"
                    + " Telefono TEXT,"
                    + " Sexo INTEGER )";
            db.execSQL(strSql);


        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            if (newVersion > oldVersion) {

                String strSql = "DROP TABLE IF EXISTS contacto";
                db.execSQL(strSql);

                strSql = "CREATE TABLE contacto ("
                        + " Id INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + " Nombre TEXT,"
                        + " Fecha_nacimiento TEXT,"
                        + " Telefono TEXT,"
                        + " Sexo INTEGER )";
                db.execSQL(strSql);
            }

        }



    }

}
