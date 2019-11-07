package com.example.practica03gonzalojuanluis;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    String tabla = ("CREATE TABLE PERSONAS()");

    private static final String DATABASE_NAME = "prueba2.db";

    //DATOS DE TABLA 1
    private static final String TABLE_NAME = "registros";
    private static final int VERSION = 1;
    public static final String NUMERO = "numero";
    public static final String MENSAJE = "mensaje";


    //Constructor de tabla 1
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, numero TEXT, mensaje TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        android.util.Log.v("prueba2", "actualizando la base de datos, se destruiran los datos existentes");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }


}