package shivani_shruti.mycompany.hostelmanagementsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class StudentDatabaseHelper extends SQLiteOpenHelper {

    public StudentDatabaseHelper (Context context) {
        super(context, "StudentDataBase.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE StudentData(email TEXT  PRIMARY KEY, name TEXT, gender TEXT, address TEXT, phone_no TEXT, roll_no TEXT, enrolment_no TEXT , branch TEXT, semester TEXT)");
        db.execSQL("CREATE TABLE HOSTEL (Hostel_name TEXT, Room_type TEXT, email TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS StudentData");
        db.execSQL("DROP TABLE IF EXISTS HOSTEL");
    }

    public boolean HostelDataInsert(String email, String Hostel_name, String Room_type)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("Hostel_name", Hostel_name);
        contentValues.put("Room_type", Room_type);
        long result = db.insert("HOSTEL",null,contentValues);
        if(result==-1)
            return false;
        return true;
    }



    public boolean StudentDataInsert (String email, String name, String gender, String address, String phone_no, String roll_no, String enrolment_no, String branch, String semester) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("name", name);
        contentValues.put("gender", gender);
        contentValues.put("address", address);
        contentValues.put("phone_no", phone_no);
        contentValues.put("roll_no", roll_no);
        contentValues.put("enrolment_no", enrolment_no);
        contentValues.put("branch", branch);
        contentValues.put("semester", semester);

        long result = db.insert("StudentData",null,contentValues);
        if(result==-1)
            return false;
        return true;
    }

    public boolean StudentDataUpdate (String email, String name, String gender, String address, String phone_no, String roll_no, String enrolment_no, String branch, String semester) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("name", name);
        contentValues.put("gender", gender);
        contentValues.put("address", address);
        contentValues.put("phone_no", phone_no);
        contentValues.put("roll_no", roll_no);
        contentValues.put("enrolment_no", enrolment_no);
        contentValues.put("branch", branch);
        contentValues.put("semester", semester);
        Cursor cursor = db.rawQuery("SELECT * FROM StudentData WHERE email = ?" ,new  String[]{email});
        if(cursor.getCount()>0){

            long result = db.update("StudentData",contentValues,"email=?", new String[] {email});
            if(result==-1)
                return false;
            else
                return true;}
        else
            return false;
    }

    public boolean StudentDataDelete (String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM StudentData WHERE email = ?" ,new  String[]{email});
        if(cursor.getCount()>0){

            long result = db.delete("StudentData","email=?", new String[] {email});
            if(result==-1)
                return false;
            else
                return true;}
        else
            return false;
    }

    public Cursor DataShow () {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM StudentData",null);
        return cursor;
    }

    public boolean checkIfUserExistsStudent(String email, String enrolment_no) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM StudentData WHERE email=? and enrolment_no=?",new String[]{email,enrolment_no});
        if(cursor.getCount()>0)
            return true;
        return false;
    }

    public Cursor ShowMyInfoStudent(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM StudentData WHERE email=? ",new String[]{email});
        return cursor;

    }
}
