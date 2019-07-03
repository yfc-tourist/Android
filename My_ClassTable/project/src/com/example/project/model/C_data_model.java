package com.example.project.model;



import java.io.ByteArrayInputStream;
import java.util.ArrayList;

import com.example.project.Add_Delete;
import com.example.project.Control_main;
import com.example.project.Settings;
import com.example.project.ShowWeek;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

public class C_data_model extends SQLiteOpenHelper //implements In_data_model
{
	
	private SQLiteDatabase db;
	Cursor cursor;
	
	private C_model_SqlOperation sql_operation;
	Context ctext;
	int delete_id=0;

	public C_data_model(Context context, String name, CursorFactory factory, int version) {
		super(context, name, factory, version);
		// TODO Auto-generated constructor stub
		db=this.getWritableDatabase();
		sql_operation=new C_model_SqlOperation(db);
		ctext=context;
		
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
		db.execSQL("CREATE TABLE User(user VARCHAR(20) not null PRIMARY KEY,password VARCHAR(20) not null)");
		//db.execSQL("CREATE TABLE User_image(id INTEGER DEFAULT('1') NOT NULL AUTOINCREMENT,user VARCHAR(20) not null PRIMARY KEY,photo BLOB not null,FOREIGN KEY (user) REFERENCES User(user) ON DELETE CASCADE)");
		db.execSQL("CREATE TABLE User_image(delete_id INTEGER not null,status varchar(10),user VARCHAR(20) not null,photo BLOB not null,year INTEGER,month INTEGER,day INTEGER,hour INTEGER,minute INTEGER,FOREIGN KEY (user) REFERENCES User(user) ON DELETE CASCADE)");
		db.execSQL("CREATE TABLE Tdelete(delete_id INTEGER PRIMARY KEY not null)");
		db.execSQL("CREATE TABLE Classes(delete_id INTEGER PRIMARY KEY not null,name VARCHAR(20) not null,week VARCHAR(20) not null,start_week VARCHAR(20) not null,end_week VARCHAR(20) not null,place VARCHAR(20),teacher VARCHAR(20),FOREIGN KEY (delete_id) REFERENCES Tdelete(delete_id) ON DELETE CASCADE)");
		db.execSQL("CREATE TABLE Time(delete_id INTEGER PRIMARY KEY not null,time VARCHAR(20) not null,FOREIGN KEY (delete_id) REFERENCES Tdelete(delete_id) ON DELETE CASCADE)");
		//db.execSQL("CREATE TABLE Main(delete_id INTEGER PRIMARY KEY not null,today VARCHAR(30) not null,bool boolean default('true'),FOREIGN KEY (delete_id) REFERENCES Tdelete(delete_id) ON DELETE CASCADE)");
		db.execSQL("CREATE TABLE Main(delete_id INTEGER PRIMARY KEY not null,bool boolean default('true'),FOREIGN KEY (delete_id) REFERENCES Tdelete(delete_id) ON DELETE CASCADE)");
		db.execSQL("insert into User values('r','1')");

	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("ALTER TABLE person ADD phone VARCHAR(12) NULL");
	}
	
	public void data_delete_m(Add_Delete m)
	{
		//Toast.makeText(m, "yes", Toast.LENGTH_SHORT).show();
		cursor = db.rawQuery("select * from Main where bool='false'", null);
		while(cursor.moveToNext())
		{
			//Toast.makeText(m, "yes", Toast.LENGTH_SHORT).show();
			delete_id=cursor.getInt(cursor.getColumnIndex("delete_id"));
			
			db.execSQL("DELETE FROM Tdelete WHERE delete_id = ?",
	                new String[]{delete_id+""});
			db.execSQL("DELETE FROM Classes WHERE delete_id = ?",
	                new String[]{delete_id+""});
			db.execSQL("DELETE FROM Time WHERE delete_id = ?",
	                new String[]{delete_id+""});
			db.execSQL("DELETE FROM Main WHERE delete_id = ?",
	                new String[]{delete_id+""});
			db.execSQL("DELETE FROM User_image WHERE delete_id = ?",
					new String[]{delete_id+""});
		}
		//Toast.makeText(m, "no", Toast.LENGTH_SHORT).show();
	}
	
	public boolean data_set_m(String[] data,String operation,Add_Delete m) {
		// TODO Auto-generated method stub
	
		if(operation=="insert")
		{
			
			return sql_operation.databases_insert(data,m,delete_id);
			
		}
		/*
		if(operation=="query")
		{
			//return sql_operation.databases_query(data);
		}
		
		if(operation=="update")
		{
			//return sql_operation.databases_update(data);
		}
		
		if(operation=="delete")
		{
			//return sql_operation.databases_delete(data);
		}
		*/
		return false;
	}
	

	public boolean idtable_get(Object m)
	{
	
		cursor =  db.rawQuery("SELECT * FROM Tdelete", null);
		
		int id=0;
		while(cursor.moveToNext())
	    {
	        id = cursor.getInt(cursor.getColumnIndex("delete_id"));
	        
	        if(delete_id<id)
	        	delete_id=id;
	/*        if(m.getClass().getSimpleName().equals("Control_main"))
	        	Toast.makeText((Control_main)m, delete_id+" ", Toast.LENGTH_SHORT).show();
			else
				Toast.makeText((Add_Delete)m, delete_id+" ", Toast.LENGTH_SHORT).show();*/
	     
	    }
		
	/*	if(m.getClass().getSimpleName().equals("Control_main"))
		{
			Toast.makeText((Control_main)m, delete_id+" ", Toast.LENGTH_SHORT).show();
		}
		
		if(m.getClass().getSimpleName().equals("Add_Delete"))
		{
			Toast.makeText((Add_Delete)m, delete_id+" ", Toast.LENGTH_SHORT).show();
		}
	*/	
		return false;
	
	}
	
	public ArrayList control_main_week_show(ShowWeek m)
	{
		 return sql_operation.database_week_query(m);
	}
	
	public ArrayList control_main_show(Control_main m){
		
		if(delete_id!=0)
		{
			
			return sql_operation.databases_query(m); 
			
		}
		return null;
		
	}

	public int delete_id_image_get(String start_week,String end_week,String time)
	{
		Cursor cursor=db.rawQuery("select * from Classes where start_week=? and end_week=?", new String[]{start_week,end_week});
		while(cursor.moveToNext())
		{
			int delete_id=cursor.getInt(cursor.getColumnIndex("delete_id"));
			Cursor cursor1=db.rawQuery("select * from Time where delete_id=? and time=?", new String[]{delete_id+"",time});
			if(cursor1.moveToFirst())
			{
				return delete_id;
			}
		}
		return -1;
	}
	
	public ArrayList image_get(ShowWeek m,String user_name,String te_e,String id_delete_id,String year,String month,String day)
	{
	//	String user_name1[]={user_name};
		Bitmap bitmapget=null;
		ArrayList arraylist=new ArrayList();
		
		int eh=0;
		if(te_e.charAt(3)=='-')
			if(te_e.charAt(5)==':')
				eh=Integer.parseInt(te_e.subSequence(4, 5).toString());
			else
				eh=Integer.parseInt(te_e.subSequence(4, 6).toString());
		if(te_e.charAt(4)=='-')
			if(te_e.charAt(6)==':')
				eh=Integer.parseInt(te_e.subSequence(5, 6).toString());
			else
				eh=Integer.parseInt(te_e.subSequence(5, 7).toString());
		if(te_e.charAt(5)=='-')
			if(te_e.charAt(7)==':')
				eh=Integer.parseInt(te_e.subSequence(6, 7).toString());
			else
				eh=Integer.parseInt(te_e.subSequence(6, 8).toString());

		int em=0;
		if(te_e.charAt(3)=='-')
			if(te_e.charAt(5)==':')
				em=Integer.parseInt(te_e.substring(6).toString());
			else
				em=Integer.parseInt(te_e.substring(7).toString());
		if(te_e.charAt(4)=='-')
			if(te_e.charAt(6)==':')
				em=Integer.parseInt(te_e.substring(7).toString());
			else
				em=Integer.parseInt(te_e.substring(8).toString());
		if(te_e.charAt(5)=='-')
			if(te_e.charAt(7)==':')
				em=Integer.parseInt(te_e.substring(8).toString());
			else
				em=Integer.parseInt(te_e.substring(9).toString());
		//Toast.makeText(getApplicationContext(), te_e+" "+" "+te_sweek+"|"+te_eweek, Toast.LENGTH_LONG).show();
		
		int sh=Integer.parseInt(te_e.subSequence(0, te_e.charAt(1)==':'?1:2).toString());
		int sm=Integer.parseInt(te_e.subSequence(te_e.charAt(1)==':'?2:3, te_e.charAt(1)==':'?(te_e.charAt(3)=='-'?3:4):(te_e.charAt(4)=='-'?4:5)).toString());
		
		
		//Toast.makeText(m, id_delete_id+" "+user_name+" "+sh+":"+sm+"-"+eh+":"+em+" "+year+" "+month+" "+day, Toast.LENGTH_LONG).show();
		// delete_id ,status ,user ,photo ,year month ,day ,hour ,minute
		//Cursor cursor = db.rawQuery("select * from User_image where delete_id=? and user=? and year=? and month=? day=?", new String[]{id_delete_id,user_name,year,month,day});
		Cursor cursor=db.rawQuery("select * from User_image where delete_id=? and user=? and year=? and month=? and day=?", new String[]{id_delete_id,user_name,year,month,day});
		
		while(cursor.moveToNext())
		{
			int qh=cursor.getInt(cursor.getColumnIndex("hour"));
			int qm=cursor.getInt(cursor.getColumnIndex("minute"));
			if(sm-5<0)
			{
				if(qh==sh-1&&qm>=sm+55)
				{
					byte[] in = cursor.getBlob(cursor.getColumnIndexOrThrow("photo"));
					bitmapget = BitmapFactory.decodeByteArray(in, 0, in.length, null);
					arraylist.add(bitmapget);
					arraylist.add(cursor.getString(cursor.getColumnIndex("user")));
					arraylist.add(cursor.getString(cursor.getColumnIndex("status")));
					arraylist.add(cursor.getString(cursor.getColumnIndex("year")));
					arraylist.add(cursor.getString(cursor.getColumnIndex("month")));
					arraylist.add(cursor.getString(cursor.getColumnIndex("day")));
					arraylist.add(cursor.getString(cursor.getColumnIndex("hour")));
					arraylist.add(cursor.getString(cursor.getColumnIndex("minute")));
					
				}
				else
					if(qh==sh&&qm<=sm+15)
					{
						byte[] in = cursor.getBlob(cursor.getColumnIndexOrThrow("photo"));
						bitmapget = BitmapFactory.decodeByteArray(in, 0, in.length, null);
						//return bitmapget;
						arraylist.add(bitmapget);
						arraylist.add(cursor.getString(cursor.getColumnIndex("user")));
						arraylist.add(cursor.getString(cursor.getColumnIndex("status")));
						arraylist.add(cursor.getString(cursor.getColumnIndex("year")));
						arraylist.add(cursor.getString(cursor.getColumnIndex("month")));
						arraylist.add(cursor.getString(cursor.getColumnIndex("day")));
						arraylist.add(cursor.getString(cursor.getColumnIndex("hour")));
						arraylist.add(cursor.getString(cursor.getColumnIndex("minute")));
					}
				
			}
			else
				if(sm-5>=0&&sm+15<=60)
				{
					if(qh==sh&&qm>=sm-5&&qm<=sm+15)
					{
						byte[] in = cursor.getBlob(cursor.getColumnIndexOrThrow("photo"));
						bitmapget = BitmapFactory.decodeByteArray(in, 0, in.length, null);
						arraylist.add(bitmapget);
						arraylist.add(cursor.getString(cursor.getColumnIndex("user")));
						arraylist.add(cursor.getString(cursor.getColumnIndex("status")));
						arraylist.add(cursor.getString(cursor.getColumnIndex("year")));
						arraylist.add(cursor.getString(cursor.getColumnIndex("month")));
						arraylist.add(cursor.getString(cursor.getColumnIndex("day")));
						arraylist.add(cursor.getString(cursor.getColumnIndex("hour")));
						arraylist.add(cursor.getString(cursor.getColumnIndex("minute")));
					}
				}
				else
					if(sm+15>60)
					{
						if(qh==sh&&qm>=sm-5)
						{
							byte[] in = cursor.getBlob(cursor.getColumnIndexOrThrow("photo"));
							bitmapget = BitmapFactory.decodeByteArray(in, 0, in.length, null);
							arraylist.add(bitmapget);
							arraylist.add(cursor.getString(cursor.getColumnIndex("user")));
							arraylist.add(cursor.getString(cursor.getColumnIndex("status")));
							arraylist.add(cursor.getString(cursor.getColumnIndex("year")));
							arraylist.add(cursor.getString(cursor.getColumnIndex("month")));
							arraylist.add(cursor.getString(cursor.getColumnIndex("day")));
							arraylist.add(cursor.getString(cursor.getColumnIndex("hour")));
							arraylist.add(cursor.getString(cursor.getColumnIndex("minute")));
						}
						else
							if(qh==sh+1&&qm<=sm-45)
							{
								byte[] in = cursor.getBlob(cursor.getColumnIndexOrThrow("photo"));
								bitmapget = BitmapFactory.decodeByteArray(in, 0, in.length, null);
								arraylist.add(bitmapget);
								arraylist.add(cursor.getString(cursor.getColumnIndex("user")));
								arraylist.add(cursor.getString(cursor.getColumnIndex("status")));
								arraylist.add(cursor.getString(cursor.getColumnIndex("year")));
								arraylist.add(cursor.getString(cursor.getColumnIndex("month")));
								arraylist.add(cursor.getString(cursor.getColumnIndex("day")));
								arraylist.add(cursor.getString(cursor.getColumnIndex("hour")));
								arraylist.add(cursor.getString(cursor.getColumnIndex("minute")));
							}
					}
			//Cursor cursor1=db.rawQuery("select * from Classes where delete_id=?", selectionArgs)
		}
	/*	
		Cursor cursor =db.rawQuery("select * from User_image where user=?", user_name1);
		Toast.makeText(m, user_name+" "+id_delete_id+" "+id_start_week+" "+id_end_week+" "+id_time_week, Toast.LENGTH_LONG).show();
		if(cursor.moveToFirst())
		{
			byte[] in = cursor.getBlob(cu rsor.getColumnIndexOrThrow("photo"));
			
			bitmapget = BitmapFactory.decodeByteArray(in, 0, in.length, null);
			Toast.makeText(m, cursor.getInt(cursor.getColumnIndex("year"))+" "+cursor.getInt(cursor.getColumnIndex("month"))+"/"+cursor.getInt(cursor.getColumnIndex("day"))+" "+cursor.getInt(cursor.getColumnIndex("hour"))+":"+cursor.getInt(cursor.getColumnIndex("minute")), Toast.LENGTH_SHORT).show();
			return bitmapget;
		}*/
		if(arraylist.size()==0)
			return null;
		else
			return arraylist;
		//Toast.makeText(m, arraylist.size()+"", Toast.LENGTH_LONG).show();
	}
	
	public boolean image_save(ContentValues initValues,String status,int delete_id,int year,int monthOfYear,int dayOfMonth,String user_name,int hour,int h,int m,int minute)
	{
		//db.insert("User_image", null, initValues);
		
		initValues.put("status", status);
		initValues.put("delete_id",delete_id+"");
		if(status.equals("¿õ¿Î"))
			return false;
		cursor=db.rawQuery("select * from User_image where delete_id=? and year=? and month=? and day=? and user=?", new String[]{delete_id+"",year+"",monthOfYear+"",dayOfMonth+"",user_name});
		while(cursor.moveToNext())
		{
			if(cursor.getInt(cursor.getColumnIndex("hour"))==hour)
			{
				
				if(cursor.getInt(cursor.getColumnIndex("minute"))>=(m-5<0?0:(m-5))&&cursor.getInt(cursor.getColumnIndex("minute"))<=((m+15)>60?60:(m+15)))
				{
					return false;
				}
					
			}
			else
				if(cursor.getInt(cursor.getColumnIndex("hour"))==hour-1)
				{
					if(h==hour)
					{
						if(cursor.getInt(cursor.getColumnIndex("minute"))>=(m-5<0?(55+m):61))
						{
							return false;
						}
					}
					else
						if(h==hour-1)
						{
							if(cursor.getInt(cursor.getColumnIndex("minute"))>=m-5&&(m+15>60?m-45:-1)>=minute)
								return false;
							//if(cursor.getInt(cursor.getColumnIndex("minute"))>=(m-5<0?(55+m):))
						}
					
				}
		}
		
		if(cursor.moveToFirst())
		{
			return false;
		}
		db.insert("User_image", null, initValues);
		return true;
	}
	
	public boolean userlogin(Object m,String[]data)
	{
		cursor = db.rawQuery("select * from User where user=? and password=?", new String[]{data[0],data[1]});
		if(cursor.moveToFirst())
		{
			return true;
		}
		return false;
		
	}
	
	
}
