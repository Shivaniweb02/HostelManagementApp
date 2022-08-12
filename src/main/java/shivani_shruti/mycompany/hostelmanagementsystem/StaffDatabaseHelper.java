package shivani_shruti.mycompany.hostelmanagementsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class StaffDatabaseHelper extends SQLiteOpenHelper {

    public StaffDatabaseHelper (Context context) {
        super(context, "StaffDataBase.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE StaffData(email TEXT  PRIMARY KEY, name TEXT, gender TEXT, address TEXT, phone_no TEXT, role TEXT, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS StaffData");
    }

    public boolean StaffDataInsert (String email, String name, String gender, String address, String phone_no, String role, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("name", name);
        contentValues.put("gender", gender);
        contentValues.put("address", address);
        contentValues.put("phone_no", phone_no);
        contentValues.put("role", role);
        contentValues.put("password", password);
        long result = db.insert("StaffData",null,contentValues);
        if(result==-1)
            return false;
        return true;
    }

    public boolean StaffDataUpdate (String email, String name, String gender, String address, String phone_no, String role, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("gender", gender);
        contentValues.put("address", address);
        contentValues.put("phone_no", phone_no);
        contentValues.put("role", role);
        contentValues.put("password", password);
        Cursor cursor = db.rawQuery("SELECT * FROM StaffData WHERE email = ?" ,new  String[]{email});
        if(cursor.getCount()>0){

            long result = db.update("StaffData",contentValues,"email=?", new String[] {email});
            if(result==-1)
                return false;
            else
                return true;}
        else
            return false;
    }


    public boolean StaffDataDelete (String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM StaffData WHERE email = ?" ,new  String[]{email});
        if(cursor.getCount()>0){

            long result = db.delete("StaffData","email=?", new String[] {email});
            if(result==-1)
                return false;
            else
                return true;}
        else
            return false;
    }

    public Cursor Show () {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM StaffData",null);
        return cursor;
    }

    public boolean checkIfUserExists(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM StaffData WHERE email=? and password=?",new String[]{email,password});
        if(cursor.getCount()>0)
            return true;
        return false;
    }

    public Cursor ShowMyInfo(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM StaffData WHERE email=? ",new String[]{email});
        return cursor;

    }
}
