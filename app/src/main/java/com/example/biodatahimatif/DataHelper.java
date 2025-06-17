package com.example.biodatahimatif;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "mahasiswa.db";
    private static final int DATABASE_VERSION = 2; // Upgrade versi!

    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tabel biodata
        String sqlBiodata = "CREATE TABLE biodata(" +
                "npm INTEGER PRIMARY KEY," +
                "nama TEXT NULL," +
                "rombel INTEGER NULL," +
                "angkatan INTEGER NULL);";
        db.execSQL(sqlBiodata);
        Log.d("Data", "onCreate Biodata: " + sqlBiodata);

        // Tabel user baru
        String sqlUser = "CREATE TABLE user(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "username TEXT NOT NULL UNIQUE," + // UNIQUE agar tidak ada duplikat
                "password TEXT NOT NULL);";
        db.execSQL(sqlUser);
        Log.d("Data", "onCreate User: " + sqlUser);

        // Contoh data awal
        db.execSQL("INSERT INTO biodata (npm,nama,rombel,angkatan) VALUES ('2340506065','Faizal Deshta Nugraha','02','2023');");
        db.execSQL("INSERT INTO user (username, password) VALUES ('admin', 'password123');"); // Contoh user
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Hapus tabel lama jika ada
        db.execSQL("DROP TABLE IF EXISTS biodata");
        db.execSQL("DROP TABLE IF EXISTS user");
        // Buat tabel baru
        onCreate(db);
    }
}