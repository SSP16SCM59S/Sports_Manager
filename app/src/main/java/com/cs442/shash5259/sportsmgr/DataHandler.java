package com.cs442.shash5259.sportsmgr;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by shash on 24-02-2016.
 */
public class DataHandler {




    //Player profile attributes
    public static final String name = "name";
    public static final String email = "email";
    public static final String password = "password";
    public static final String dob = "dob";
    public static final String phone = "phone";
    public static final String gender = "gender";
    public static final String role = "role";
    public static final String notify = "notify";
    //public static final String wins = "wins";
   // public static final String lost = "lost";
    //public static final String ties = "ties";

    public static final String TABLE_NAME_PROFILE = "player_profile";
    public static final String TABLE_NAME_SPORT = "sport_profile";

    //Team profile attributes
    /* Fill details for team here*/

    //Sport profile attributes
    /* Fill details for sport here*/
    public static final String s_name="s_name";
    public static final String s_max_teams="s_max_teams";

    public static final String DATA_BASE_NAME = "sports_finale";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_CREATE_PROFILE = "create table player_profile(name text not null,email text primary key,password text not null,phone text not null,dob text not null,gender text not null,role text not null,notify text not null);";
    public static final String TABLE_CREATE_SPORT = "create table sport_profile(s_name text primary key,s_max_teams text primary key DEFAULT '16');";
    DatabaseHelper dbhelper;
    Context ctx;
    SQLiteDatabase db;
    public DataHandler(Context ctx)
    {
        this.ctx = ctx;
        dbhelper = new DatabaseHelper(ctx);

    }
    public static class DatabaseHelper extends SQLiteOpenHelper
    {

        public DatabaseHelper(Context ctx)
        {
            super(ctx, DATA_BASE_NAME, null, DATABASE_VERSION);


        }
        @Override
        public void onCreate(SQLiteDatabase db)
        {
            try
            {
                db.execSQL(TABLE_CREATE_PROFILE);
                db.execSQL(TABLE_CREATE_SPORT);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        }



        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS player_profile");

        }
    }
    public DataHandler open()
    {
        db = dbhelper.getWritableDatabase();

        return this;
    }

    public void close()
    {
        dbhelper.close();
    }

   // public long insertData(String name,String email)
    public long insertPlayerData(String pl_name,String pl_email,String pl_password,String pl_dob,String pl_phone,String pl_gender,String pl_role,String pl_notify)
    {

        ContentValues content = new ContentValues();
        content.put(name,pl_name);
        content.put(email, pl_email);
        content.put(password, pl_password);
        content.put(dob,pl_dob);
        content.put(phone,pl_phone);
        content.put(gender,pl_gender);
        content.put(role,pl_role);
        content.put(notify,pl_notify);
        //content.put(wins,pl_wins);
        //content.put(lost,pl_lost);
       // content.put(ties,pl_ties);

        return db.insertOrThrow(TABLE_NAME_PROFILE, null, content);
    }

    public long insertSportData()
    {   ContentValues content = new ContentValues();
        content.put(s_name,"Baseball");
        content.put(s_name,"Cricket");
        content.put(s_name,"Soccer");
        content.put(s_name,"Football");
        content.put(s_name,"Tabletennis");
        content.put(s_name,"Bowling");
        content.put(s_name,"Billiards");
        content.put(s_name,"Basketball");
        content.put(s_name,"Chess");
        content.put(s_name,"Badminton");
        content.put(s_name,"Racquetball");
        content.put(s_name,"Yoga");
        content.put(s_name,"Pilates");
        content.put(s_name,"Swimming");
        content.put(s_name,"Zumba");
        content.put(s_name,"Kickboxing");
        content.put(s_name,"Cardio");
        return db.insert(TABLE_NAME_SPORT,null,content);
    }


    public long updatePlayerData(String pl_password,String pl_phone,String pl_email)
    {
        ContentValues content = new ContentValues();
        content.put(phone,pl_phone);
        content.put(password,pl_password);

        return db.update(TABLE_NAME_PROFILE,content,"email='"+pl_email+"'",null);

    }

   public Cursor returnPlayerData()
   {
        //return db.query(TABLE_NAME,new String[]{name,veg}, null, null, null, null, null);
       Cursor cs;
       String query = "SELECT * FROM "+TABLE_NAME_PROFILE;
        //cs = db.query(TABLE_NAME,new String[] {name,veg},null,null,null,null,null);
       cs = db.rawQuery(query,null);

   return cs;
   }

    public Cursor returnPlayerData1(String s)
    {
        //return db.query(TABLE_NAME,new String[]{name,veg}, null, null, null, null, null);
        Cursor cs;
        String query = "SELECT * FROM "+TABLE_NAME_PROFILE+" WHERE email ='"+s+"'";
        //cs = db.query(TABLE_NAME,new String[] {name,veg},null,null,null,null,null);
        cs = db.rawQuery(query,null);

        return cs;
    }

    public Cursor returnPlayerName(String s)
    {   Cursor cs;
        String query = "SELECT name FROM "+TABLE_NAME_PROFILE+" WHERE email ='"+s+"'";
        cs = db.rawQuery(query,null);
        return cs;
    }

    public Cursor returnPlayerGender(String s)
    {   Cursor cs;
        String query = "SELECT gender FROM "+TABLE_NAME_PROFILE+" WHERE email ='"+s+"'";
        cs = db.rawQuery(query,null);
        return cs;
    }

    public Cursor DeletePlayerData()
    {
        //return db.query(TABLE_NAME,new String[]{name,veg}, null, null, null, null, null);
        Cursor cs;
        String query = "DELETE FROM "+TABLE_NAME_PROFILE;
        //cs = db.query(TABLE_NAME,new String[] {name,veg},null,null,null,null,null);
        cs = db.rawQuery(query,null);
        return cs;
    }

}
