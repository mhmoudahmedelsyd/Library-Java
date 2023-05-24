package com.example.project.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class BookDatabase extends SQLiteOpenHelper {
    private Context context;
    public static final String DATABASE_NAME="BookLibrary.db";
    public static final int DATABASE_VERSION=1;
    public static final String TABLE_NAME="Book_sc";
    public static final String COLUMN_ID="_id";
    public static final String COLUMN_TITLE="book_title";
    public static final String COLUMN_AUTHOR="book_author";
    public static final String COLUMN_PAGES="book_pages";


    public BookDatabase(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query= "CREATE TABLE "+ TABLE_NAME +
                        " ("+ COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_TITLE + " TEXT, "+
                        COLUMN_AUTHOR + " TEXT, "+
                        COLUMN_PAGES + " INTEGER); ";
        db.execSQL(query);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);

    }
    public void insertBook(String title,String author,int pages){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_TITLE,title);
        cv.put(COLUMN_AUTHOR,author);
        cv.put(COLUMN_PAGES,pages);
       long result= db.insert(TABLE_NAME,null,cv);
        if(result==-1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Add Successfully!", Toast.LENGTH_SHORT).show();
        }
    }
    public Cursor SelectAllBooks(){
        String query="SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=null;
        if(db!=null){
            cursor =db.rawQuery(query,null);
        }
        return cursor;

    }


     public void updateData(String row_id, String title, String author, String pages){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COLUMN_TITLE,title);
        cv.put(COLUMN_AUTHOR,author);
        cv.put(COLUMN_PAGES,pages);
        long result= db.update(TABLE_NAME,cv,"_id=?",new String[]{row_id});
        if(result==-1){
            Toast.makeText(context, "Failed To UpDate", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "UPDATE Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteOneRow(String row_id){
        SQLiteDatabase db=this.getWritableDatabase();
     long result = db.delete(TABLE_NAME,"_id=?",new String[]{row_id});
        if(result==-1){
            Toast.makeText(context, "Failed To DELETE", Toast.LENGTH_SHORT).show();

        } else{
        Toast.makeText(context, "DELETE Successfully!", Toast.LENGTH_SHORT).show();
    }

    }
}
