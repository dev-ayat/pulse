package com.moh.departments.constants;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "ICD10_DB.db";
    private static final int DB_VERSION = 1;
    private static String DB_BATH;
    private static Context mcontext;


    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        mcontext = context;
        File databaseFile = context.getDatabasePath(DbHelper.DB_NAME);
        if (!databaseFile.exists()) {

            copyDatabaseFile(context, databaseFile);
        } else {
            SQLiteDatabase db = SQLiteDatabase.openDatabase(databaseFile.getAbsolutePath(), null, 0);

            if (db.needUpgrade(DbHelper.DB_VERSION)) {
                if (databaseFile.delete()) {
                    copyDatabaseFile(context, databaseFile);
                }

            }
        }
    }

    private void copyDatabaseFile(Context context, File databaseFile) {
        try {
            InputStream input = context.getAssets().open(DbHelper.DB_NAME);
            OutputStream output = new FileOutputStream(databaseFile);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = input.read(buffer)) > 0) {
                output.write(buffer, 0, length);
            }
            output.flush();
            output.close();
            input.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException mIOException) {
            throw new Error("Error Copying DataBase");
        }
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
