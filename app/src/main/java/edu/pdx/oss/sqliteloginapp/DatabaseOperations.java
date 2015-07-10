package edu.pdx.oss.sqliteloginapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseOperations extends SQLiteOpenHelper {
    public static final int database_version = 1;
    public String CREATE_QUERY = "CREATE TABLE " + TableData.TableInfo.TABLE_NAME + "(" + TableData.TableInfo.USER_NAME + " TEXT, " + TableData.TableInfo.USER_PASS + " TEXT);";

    public DatabaseOperations(Context context) {
        super(context, TableData.TableInfo.DATABASE_NAME, null, database_version);
        Log.d("Database operations", "Database Created");
    }

    @Override
    public void onCreate(SQLiteDatabase sdb) {
        sdb.execSQL(CREATE_QUERY);
        Log.d("Database operations", "Table Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void putInformation(DatabaseOperations dop, String name, String pass){
        SQLiteDatabase SQ = dop.getWritableDatabase();
        ContentValues  cv = new ContentValues();
        cv.put(TableData.TableInfo.USER_NAME, name);
        cv.put(TableData.TableInfo.USER_PASS, pass);
        long k = SQ.insert(TableData.TableInfo.TABLE_NAME, null, cv);
        Log.d("Database operations", "One row inserted");
    }

    public Cursor getInformation(DatabaseOperations dop){
        SQLiteDatabase SQ = dop.getReadableDatabase();
        String[] columns = {TableData.TableInfo.USER_NAME, TableData.TableInfo.USER_PASS};
        Cursor CR = SQ.query(TableData.TableInfo.TABLE_NAME, columns, null, null, null, null, null);
        return CR;
    }

    public Cursor getUserPassword(DatabaseOperations dop, String user_name){
        SQLiteDatabase SQ = dop.getReadableDatabase();
        String columns[] = {TableData.TableInfo.USER_NAME};
        String selection = TableData.TableInfo.USER_NAME + " LIKE ?";
        String args[] = {user_name};
        Cursor CR = SQ.query(TableData.TableInfo.TABLE_NAME, columns, selection, args, null, null, null);
        return CR;
    }

    public void deleteUser(DatabaseOperations dop, String user_name, String user_pass){
        SQLiteDatabase SQ = dop.getWritableDatabase();
        String selection = TableData.TableInfo.USER_NAME + " LIKE ? AND " + TableData.TableInfo.USER_PASS + " LIKE ?";
        String args[] = {user_name, user_pass};
        SQ.delete(TableData.TableInfo.TABLE_NAME, selection, args);
    }

    public void updateUser(DatabaseOperations dop, String user_name, String user_pass, String new_user_name){
        SQLiteDatabase SQ = dop.getWritableDatabase();
        String selection = TableData.TableInfo.USER_NAME + " LIKE ? AND " + TableData.TableInfo.USER_PASS + " LIKE ?";
        String args[] = {user_name, user_pass};
        ContentValues values = new ContentValues();
        values.put(TableData.TableInfo.USER_NAME, new_user_name);
        SQ.update(TableData.TableInfo.TABLE_NAME, values, selection, args);
    }
}
