package com.example.jaaouanyassine.quizpro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Basedonneequiz extends SQLiteOpenHelper {
    private static final String DATABASE_NAME ="Quiz";
    private static final int DATABASE_VERSION = 1;
    private static final String Table_name = "Question";
    private static final String colonne_id="id";
    private static final String Colonne_Question ="ColuneQuestion";
    private static final String Colonne_Anser1="Anser1";
    private static final String Colonne_Anser2="Anser2";
    private static final String Colonne_Anser3="Anser3";
    private static final String Colonne_AnserCorrect="Corret";
    private SQLiteDatabase db;
    private static final String Create_Table="Create Table " + Table_name + " ( " +colonne_id + " INTEGER PRIMARY KEY , "
            +Colonne_Question + " Text , " +Colonne_Anser1+ " Text , " +Colonne_Anser2 + " Text , " +Colonne_Anser3+ " Text , "
            +Colonne_AnserCorrect + " Text ) ";
    private static final String Colonne_Score="score";
    private static  final  String Create_Table_HighScore = "Create Table HighScore ( "+Colonne_Score+" INTEGER PRIMARY KEY )";

    public Basedonneequiz( Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Create_Table);
        db.execSQL(Create_Table_HighScore);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE if exists " +Table_name + ";");
        onCreate(db);
    }
    public SQLiteDatabase open()
    {
        return this.getWritableDatabase();
    }
    public void addQuestion(ClassQuestion q)
    {
        db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(colonne_id,q.getId());
        values.put(Colonne_Question,q.getQuestion());
        values.put(Colonne_Anser1,q.getAnser1());
        values.put(Colonne_Anser2,q.getAnser2());
        values.put(Colonne_Anser3,q.getAnser3());
        values.put(Colonne_AnserCorrect,q.getCorrect());
        db.insert(Table_name,null,values);

    }
    public ClassQuestion getQuestionQuiz( int n)
    {
        db=this.getReadableDatabase();
        Cursor C= db.rawQuery("Select * from "+Table_name+ " where " + colonne_id + " = "+n,null);
        C.moveToNext();
        ClassQuestion Q=new ClassQuestion(Integer.parseInt(C.getString(0)),C.getString(1),C.getString(2),
                C.getString(3),C.getString(4),C.getString(5));
        return Q;
    }


    public boolean TableExist(){
        db=this.getReadableDatabase();
        Cursor C= db.rawQuery("Select * from "+Table_name,null);
        if (C.getCount()>0)
            return true;
        else
            return false;
    }
    public void initScore()
    {
        db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(Colonne_Score,0);
        db.insert("HighScore",null,values);
    }
    public void addScore(int score)
    {
        db=this.getWritableDatabase();
        db.execSQL("update HighScore set "+Colonne_Score+" = "+score);

    }
    public int getHighScore()
    {
        db=this.getReadableDatabase();
        Cursor C= db.rawQuery("Select * from HighScore",null);
        C.moveToNext();
        return Integer.parseInt(C.getString(0));
    }
}
