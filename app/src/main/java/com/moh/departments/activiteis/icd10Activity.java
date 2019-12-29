package com.moh.departments.activiteis;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.moh.departments.R;
import com.moh.departments.constants.Controller;
import com.moh.departments.constants.DBContract;
import com.moh.departments.constants.DbHelper;
import com.moh.departments.dialog.DialogLoding;
import com.moh.departments.dialog.DialogMsg;

public class icd10Activity extends AppCompatActivity {

    SearchView searchicd10;
    SQLiteDatabase db;
    String[] coulmns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Controller.LOADER_DIALOG = new DialogLoding(this);
        Controller.Msg_DIALOG = new DialogMsg(this);
        setContentView(R.layout.activity_icd10);
        final ListView lvicd10 = findViewById(R.id.lvicd10);
        DbHelper mDbHelper = new DbHelper(this);
        db = mDbHelper.getReadableDatabase();

        String[] coulmns = {
                DBContract.Icd._ID,
                DBContract.Icd.COLUMN_NAME_EN,
                DBContract.Icd.COLUMN_CD,
                DBContract.Icd.COLUMN_CLASS,
                DBContract.Icd.COLUMN_INCUBATION_CD,
                DBContract.Icd.COLUMN_TYPE
        };

        String selection = DBContract.Icd.COLUMN_TYPE + " = ?";
        String[] selectionArgs = {"2"};
        String sortOrder = DBContract.Icd.COLUMN_NAME_EN + "ASC";
        final Cursor cursor = db.query(DBContract.Icd.TABLE_NAME, coulmns, selection, selectionArgs, null, null, null);
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(
                icd10Activity.this,
                R.layout.icd_list_row,
                cursor,
                new String[]{DBContract.Icd.COLUMN_NAME_EN, DBContract.Icd.COLUMN_CD},
                new int[]{R.id.txt_icd_name, R.id.txt_icd_cd},
                0);
        lvicd10.setAdapter(simpleCursorAdapter);


//       while (cursor.moveToNext()){
//               String icd_name= cursor.getString(cursor.getColumnIndexOrThrow(DBContract.Icd.COLUMN_NAME_EN));
//               String icd_cd= cursor.getString(cursor.getColumnIndexOrThrow(DBContract.Icd.COLUMN_CD));
//       }


        searchicd10 = findViewById(R.id.searchViewicd10);
        searchicd10.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                String[] coulmns = {
                        DBContract.Icd._ID,
                        DBContract.Icd.COLUMN_NAME_EN,
                        DBContract.Icd.COLUMN_CD,
                        DBContract.Icd.COLUMN_CLASS,
                        DBContract.Icd.COLUMN_INCUBATION_CD,
                        DBContract.Icd.COLUMN_TYPE
                };
                if (s.length() >= 3) {

                    String selection = DBContract.Icd.COLUMN_TYPE + " = ?" + " and " + DBContract.Icd.COLUMN_NAME_EN + " like '" + s + "%'" + " or " + DBContract.Icd.COLUMN_CD + " like '" + s + "%'";
                    Log.e("selection", selection);
                    String[] selectionArgs = {"2"};
                    final Cursor cursor = db.query(DBContract.Icd.TABLE_NAME, coulmns, selection, selectionArgs, null, null, null);
                    SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(
                            icd10Activity.this,
                            R.layout.icd_list_row,
                            cursor,
                            new String[]{DBContract.Icd.COLUMN_NAME_EN, DBContract.Icd.COLUMN_CD},
                            new int[]{R.id.txt_icd_name, R.id.txt_icd_cd},
                            0);
                    lvicd10.setAdapter(simpleCursorAdapter);
                } else {
                    String selection = DBContract.Icd.COLUMN_TYPE + " = ?";
                    Log.e("selection", selection);
                    String[] selectionArgs = {"2"};
                    final Cursor cursor = db.query(DBContract.Icd.TABLE_NAME, coulmns, selection, selectionArgs, null, null, null);
                    SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(
                            icd10Activity.this,
                            R.layout.icd_list_row,
                            cursor,
                            new String[]{DBContract.Icd.COLUMN_NAME_EN, DBContract.Icd.COLUMN_CD},
                            new int[]{R.id.txt_icd_name, R.id.txt_icd_cd},
                            0);
                    lvicd10.setAdapter(simpleCursorAdapter);

                }

                return true;
            }
        });

    }


}
