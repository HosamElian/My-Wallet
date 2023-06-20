package com.example.android.mywallet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DbTeller extends SQLiteOpenHelper {
    private final static String DB_NAME="myPhone_Db";
    private final static int DB_VERSION=1;
    private final static String KEY_ID="id";
    private final static String KEY_NAME="nameOfTeller";
    private final static String KEY_AMOUNT="amount";
    private final static String KEY_CAUSE="cause";
    private final static String Table_teller="teller";

    public DbTeller(Context context) {
        super ( context, DB_NAME, null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String Create_Table ="Create table "+Table_teller+"("+KEY_ID+" integer primary key,"+KEY_NAME+" String,"+KEY_AMOUNT+" integer,"+KEY_CAUSE+" String)";
        sqLiteDatabase.execSQL ( Create_Table );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String delete_Query="DROP TABLE IF EXISTS '" + Table_teller + "'";
        sqLiteDatabase.execSQL ( delete_Query );
        onCreate ( sqLiteDatabase );
    }
    public void addteller(tellerPerson tellerPerson){
        ContentValues values=new ContentValues (  );
        values.put ( KEY_NAME,tellerPerson.getNameTeller () );
        values.put ( KEY_AMOUNT,tellerPerson.getAmuont () );
        values.put ( KEY_CAUSE,tellerPerson.getCause () );
        SQLiteDatabase db=this.getWritableDatabase ();
        db.insert ( Table_teller,null,values );
    }
    public int getPrice (){
        int prices=0;
        ArrayList<Integer>price=new ArrayList<> ();
        SQLiteDatabase db=this.getReadableDatabase ();
        String select_query="select * from  "+Table_teller;
        Cursor cursor=db.rawQuery ( select_query,null );
        if (cursor.moveToFirst ()){
            while (cursor.moveToNext ()){
                int Amount= cursor.getInt ( cursor.getColumnIndex (KEY_AMOUNT) );
                price.add ( Amount );
            }}
        for (int i=0;i<price.size ();i++){
            prices+=price.get ( i );
        }
        return prices;
    }
    public ArrayList<tellerPerson> getAllteller(){

        ArrayList<tellerPerson> tellerPeople=new ArrayList<> ();
        String select_query="select * from  "+Table_teller;
        SQLiteDatabase db=this.getReadableDatabase ();
        Cursor cursor=db.rawQuery ( select_query,null );
        if (cursor.moveToFirst ()){
           while (cursor.moveToNext ()){

               String nameteller=cursor.getString ( cursor.getColumnIndex ( KEY_NAME ) );
               int amount=cursor.getInt ( cursor.getColumnIndex (KEY_AMOUNT) );
               String cause=cursor.getString ( cursor.getColumnIndex ( KEY_CAUSE ) );
               int id=cursor.getInt ( cursor.getColumnIndex ( KEY_ID ) );
               tellerPerson tellerPeople1=new tellerPerson (id, nameteller,amount,cause );
               tellerPeople.add ( tellerPeople1 );
           }
        }
        return tellerPeople;
    }
    public tellerPerson getPersonById(int id){
      SQLiteDatabase db =this.getReadableDatabase ();
      String select_query="select * from "+Table_teller+" Where id="+id;
      Cursor cursor =db.rawQuery ( select_query,null );
        tellerPerson tellerPeople1=null;
      if (cursor.moveToFirst ()){

          String nameteller=cursor.getString ( cursor.getColumnIndex ( KEY_NAME ) );
          int amount=cursor.getInt ( cursor.getColumnIndex (KEY_AMOUNT) );
          String cause=cursor.getString ( cursor.getColumnIndex ( KEY_CAUSE ) );
          tellerPeople1=new tellerPerson (id ,nameteller,amount,cause );
      }

      return tellerPeople1;
    }
    public void Update(tellerPerson tellerPerson){
        SQLiteDatabase db=this.getWritableDatabase ();
        ContentValues values=new ContentValues (  );
        values.put ( KEY_NAME,tellerPerson.getNameTeller () );
        values.put ( KEY_AMOUNT,tellerPerson.getAmuont () );
        values.put ( KEY_CAUSE,tellerPerson.getCause () );

        db.update ( Table_teller,values,"id=?",new String[]{String.valueOf ( tellerPerson.getId () )} );

    }
    public void Delete(int id){
        SQLiteDatabase db=this.getWritableDatabase ();
        db.delete ( Table_teller,"id=?",new  String[]{String.valueOf ( id )} );
    }

}
