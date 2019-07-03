package com.example.project.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.example.project.Add_Delete;
import com.example.project.Control_main;
import com.example.project.ShowWeek;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class C_model_SqlOperation {
	private Cursor cursor,cursor1;
	SQLiteDatabase db;
	Add_Delete add_delete_main;
	int delete_id;
	ArrayList arrayList;
	String name,week_signal,start_week,end_week,place,teacher,time_get;
	
	public C_model_SqlOperation(SQLiteDatabase db)
	{
		//main=(Add_Delete)m;
		this.db=db;
		arrayList= new ArrayList();
	
	}
	
	protected boolean databases_insert(String data[],Add_Delete m,int delete_id)
	{
		try {
			/*db.execSQL("INSERT INTO Time values(?,?)",
	                new String[]{data[0],data[1]});
			db.execSQL("INSERT Into classes values(?,?,?,?,?,?,?)",new String[]{data[2],data[3],data[4],data[5],data[6],data[7],data[8]});
			db.execSQL("INSERT INTO main values(?,?,?)",
					new String[]{data[9],data[10],data[11]});*/
			if(delete_id==0)
			{
				delete_id=1;

			}
			else
			{
				
				delete_id+=1;
				
			}
			
			//Toast.makeText(m, data[0]+" "+data[1]+" "+data[2]+" "+data[3]+" "+data[4]+" "+data[5]+" "+data[6]+" "+data[7], Toast.LENGTH_SHORT).show();
			db.execSQL("INSERT INTO Tdelete values(?)",new String[]{delete_id+""});
			db.execSQL("INSERT INTO Time values(?,?)",new String[]{delete_id+"",data[6]});
			//db.execSQL("INSERT INTO Main(delete_id,today) values(?,?)",new String[]{delete_id+"",data[7]});
			db.execSQL("INSERT INTO Main(delete_id) values(?)",new String[]{delete_id+""});
			db.execSQL("INSERT INTO Classes values(?,?,?,?,?,?,?)",new String[]{delete_id+"",data[0],data[1],data[2],data[3],data[4],data[5]});
			Toast.makeText(m, "保存成功!", Toast.LENGTH_SHORT).show();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

	}
	
	public ArrayList databases_query(Control_main m)
	{
		cursor =db.rawQuery("select * FROM Main where bool='true'", null);
	
		String[] delete_id={""};
		String time="";
		int htime = 0,mtime = 0,year=0,dayOfMonth=0,monthOfYear=0,hour=0,minute=0,emonth=0,eday=0,eyear=0;
		Calendar calendar = Calendar.getInstance();
		hour = calendar.get(Calendar.HOUR_OF_DAY);
	    minute = calendar.get(Calendar.MINUTE);
	    year = calendar.get(calendar.YEAR);
		monthOfYear = calendar.get(calendar.MONTH)+1;
		dayOfMonth = calendar.get(calendar.DAY_OF_MONTH);
	        
		//遍历Main表，得到delete_id的值
		while(cursor.moveToNext())
		{
			delete_id[0]=cursor.getString(cursor.getColumnIndex("delete_id"));
			//Toast.makeText(m, delete_id[0], Toast.LENGTH_SHORT).show();
			cursor1=db.rawQuery("select * from Classes where delete_id=?", delete_id);
			if(cursor1.moveToFirst())
			{
				eyear=Integer.parseInt(cursor1.getString(cursor1.getColumnIndex("end_week")).subSequence(0, 4).toString());
				name=cursor1.getString(cursor1.getColumnIndex("name"));  
		        week_signal=cursor1.getString(cursor1.getColumnIndex("week"));
		        start_week=cursor1.getString(cursor1.getColumnIndex("start_week"));
		        end_week=cursor1.getString(cursor1.getColumnIndex("end_week"));
		        place=cursor1.getString(cursor1.getColumnIndex("place"));
		        teacher=cursor1.getString(cursor1.getColumnIndex("teacher"));
				
				if(Integer.parseInt(cursor1.getString(cursor1.getColumnIndex("end_week")).subSequence(0, 4).toString())<year)
				{
					db.execSQL("update Main set bool='false' where delete_id=?",delete_id);
					continue;
				}
				else
					if(year==Integer.parseInt(cursor1.getString(cursor1.getColumnIndex("end_week")).subSequence(0, 4).toString()))
					{
				
						if(cursor1.getString(cursor1.getColumnIndex("end_week")).charAt(6)=='-')
						{
							emonth=Integer.parseInt(String.valueOf(cursor1.getString(cursor1.getColumnIndex("end_week")).charAt(5)));
							eday=Integer.parseInt(cursor1.getString(cursor1.getColumnIndex("end_week")).subSequence(7, cursor1.getString(cursor1.getColumnIndex("end_week")).length()).toString());
						}
						else
						{
							emonth=Integer.parseInt(cursor1.getString(cursor1.getColumnIndex("end_week")).subSequence(5, 7).toString());
							eday=Integer.parseInt(cursor1.getString(cursor1.getColumnIndex("end_week")).subSequence(8, cursor1.getString(cursor1.getColumnIndex("end_week")).length()).toString());
						}
						
						if(emonth<monthOfYear)
						{
							db.execSQL("update Main set bool='false' where delete_id=?",delete_id);
							continue;
						}
						else
							if(emonth==monthOfYear)
							{
									
								if(eday<dayOfMonth)
								{
									db.execSQL("update Main set bool='false' where delete_id=?",delete_id);
									continue;
								}
								else
									if(eday==dayOfMonth)
									{
										//遍历Time表，根据delete_id得到time值
										cursor1=db.rawQuery("select * from Time where delete_id=?", delete_id);
										
										if(cursor1.moveToFirst())
										{
											
											if(cursor1.getString(cursor1.getColumnIndex("time")).charAt(3)=='-')
											{
												time=cursor1.getString(cursor1.getColumnIndex("time")).subSequence(4, cursor1.getString(cursor1.getColumnIndex("time")).length()).toString();
											}
											
											if(cursor1.getString(cursor1.getColumnIndex("time")).charAt(4)=='-')
											{
												time=cursor1.getString(cursor1.getColumnIndex("time")).subSequence(5, cursor1.getString(cursor1.getColumnIndex("time")).length()).toString();
											}
											
											if(cursor1.getString(cursor1.getColumnIndex("time")).charAt(5)=='-')
											{
												
												time=cursor1.getString(cursor1.getColumnIndex("time")).subSequence(6, cursor1.getString(cursor1.getColumnIndex("time")).length()).toString();
											}
											//Toast.makeText(m, time,Toast.LENGTH_SHORT).show();
											if(time.charAt(1)==':')
											{
	
												//Toast.makeText(m, time,Toast.LENGTH_SHORT).show();
												htime=Integer.parseInt(String.valueOf(time.charAt(0)));
												mtime=Integer.parseInt(time.subSequence(2, time.length()).toString());
												
											}
											else
											{
												
												htime=Integer.parseInt(time.subSequence(0, 2).toString());
												mtime=Integer.parseInt(time.subSequence(3, time.length()).toString());
											}
											
											if(htime<hour)
											{
												
												db.execSQL("update Main set bool='false' where delete_id=?",delete_id);
												continue;
											}
											else
												if(htime==hour&&minute>=mtime)
												{
													
													db.execSQL("update Main set bool='false' where delete_id=?",delete_id);
													continue;
												}
								//			Toast.makeText(m, (minute<=mtime)+"", Toast.LENGTH_SHORT).show();
										}
									}
							}
		
						
						//Toast.makeText(m, htime+":"+mtime, Toast.LENGTH_SHORT).show();
					}
			}
			

			cursor1 =  db.rawQuery("SELECT * FROM Time WHERE delete_id =?",delete_id);
			if(cursor1.moveToFirst())
		    {
		        time_get=cursor1.getString(cursor1.getColumnIndex("time"));    
		    }
/*			cursor1=db.rawQuery("SELECT * FROM Classes WHERE delete_id =?", delete_id);
			if(cursor1.moveToFirst())
		    {
		        name=cursor1.getString(cursor1.getColumnIndex("name"));  
		        week_signal=cursor1.getString(cursor1.getColumnIndex("week"));
		        start_week=cursor1.getString(cursor1.getColumnIndex("start_week"));
		        end_week=cursor1.getString(cursor1.getColumnIndex("end_week"));
		        place=cursor1.getString(cursor1.getColumnIndex("place"));
		        teacher=cursor1.getString(cursor1.getColumnIndex("teacher"));
		    }
			
*/			//Toast.makeText(m, name+" "+week_signal+" "+start_week+" "+end_week+" "+place+" "+teacher, Toast.LENGTH_SHORT).show();
			
			if(Integer.parseInt(start_week.subSequence(0, 4).toString())<=year&&year<=eyear)
			{
				
				
				if(Integer.parseInt(start_week.subSequence(5, start_week.charAt(6)=='-'?6:7).toString())<=monthOfYear&&monthOfYear<emonth)
				{
					
					if(week_signal.equals("单"))
					{
						if(dayOfMonth%2!=0)
						{
							arrayList.add(name);
							arrayList.add(week_signal);
							arrayList.add(start_week);
							arrayList.add(end_week);
							arrayList.add(place);
							arrayList.add(teacher);
							arrayList.add(time_get);
							continue;
						}
					}
					else
						if(week_signal.equals("双"))
							if(dayOfMonth%2==0)
							{
								arrayList.add(name);
								arrayList.add(week_signal);
								arrayList.add(start_week);
								arrayList.add(end_week);
								arrayList.add(place);
								arrayList.add(teacher);
								arrayList.add(time_get);
								continue;
							}
							if(week_signal.equals("全"))
							{
								arrayList.add(name);
								arrayList.add(week_signal);
								arrayList.add(start_week);
								arrayList.add(end_week);
								arrayList.add(place);
								arrayList.add(teacher);
								arrayList.add(time_get);
								continue;
							}
				
		//			if(Integer.parseInt(start_week.subSequence(start_week.charAt(6)=='-'?7:8,start_week.length()).toString())<=dayOfMonth)
				}
				
				//if(Integer.parseInt(start_week.subSequence(5, start_week.charAt(6)=='-'?6:7).toString())==monthOfYear&&monthOfYear==emonth)
				if(monthOfYear==emonth)
				{
					
		//			Toast.makeText(m, dayOfMonth%2+"", Toast.LENGTH_SHORT).show();
					if(Integer.parseInt(start_week.subSequence(start_week.charAt(6)=='-'?7:8,start_week.length()).toString())<=dayOfMonth&&dayOfMonth<=eday)
					{
						
						if(week_signal.equals("单"))
						{
							if(dayOfMonth%2!=0)
							{
								arrayList.add(name);
								arrayList.add(week_signal);
								arrayList.add(start_week);
								arrayList.add(end_week);
								arrayList.add(place);
								arrayList.add(teacher);
								arrayList.add(time_get);
								continue;
							}
						}
						else
							if(week_signal.equals("双"))
								if(dayOfMonth%2==0)
								{
									arrayList.add(name);
									arrayList.add(week_signal);
									arrayList.add(start_week);
									arrayList.add(end_week);
									arrayList.add(place);
									arrayList.add(teacher);
									arrayList.add(time_get);
									continue;
								}
				
				//					Toast.makeText(m, "("+week_signal.equals("全"), Toast.LENGTH_SHORT).show();	
									
									if(week_signal.equals("全"))
									{
										
										arrayList.add(name);
										arrayList.add(week_signal);
										arrayList.add(start_week);
										arrayList.add(end_week);
										arrayList.add(place);
										arrayList.add(teacher);
										arrayList.add(time_get);
										continue;
									}
					}
				}
			}
					
	/*		arrayList.add(name);
			arrayList.add(week_signal);
			arrayList.add(start_week);
			arrayList.add(end_week);
			arrayList.add(place);
			arrayList.add(teacher);
			arrayList.add(time_get);*/
			//return new String[]{name,week_signal,start_week,end_week,place,teacher,time_get};
			
		}
//		Toast.makeText(m, arrayList.toString(), Toast.LENGTH_SHORT).show();
		return arrayList;
		
	}
	
	
	private String[] time_week_get(ShowWeek m)
	{
		SimpleDateFormat myFmt1=new SimpleDateFormat("yy/MM/dd HH:mm");
	
		Calendar c = Calendar.getInstance();
		int day_of_week_start = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (day_of_week_start == 0)
			day_of_week_start = 7;
		c.add(Calendar.DATE, -day_of_week_start + 1);
		String start_week=myFmt1.format(c.getTime());
		  
		int day_of_week_end = c.get(Calendar.DAY_OF_WEEK) - 1;
	    if (day_of_week_end == 0)
	    	day_of_week_end = 7;
	    c.add(Calendar.DATE, -day_of_week_end + 7);
	    String end_week=myFmt1.format(c.getTime());
		
	  //  Toast.makeText(m,start_week+" "+end_week, Toast.LENGTH_LONG).show();
	    return new String[]{start_week.subSequence(3, 8).toString(),end_week.subSequence(3, 8).toString()};
	    
	}
	
	public void arraylist_add(ShowWeek m,ArrayList w1,ArrayList w2,ArrayList w3,ArrayList w4,ArrayList w5,ArrayList w6,ArrayList w7,String[]y_n,String week_signal,int n)
	{
		
		if(week_signal.equals("单"))
		{
			if(n%2!=0)
			{
				
				switch((1+n-Integer.parseInt(y_n[0].subSequence(3,5).toString())))
				{
				case 1:	     
					w1.add(name);
					w1.add(time_get);
					w1.add(place);
					w1.add(teacher);
					w1.add(delete_id);
					w1.add(start_week);
					w1.add(end_week);
					
					break;
				case 2:
					w2.add(name);
					w2.add(time_get);
					w2.add(place);
					w2.add(teacher);
					w2.add(delete_id);
					w2.add(start_week);
					w2.add(end_week);
					
					
					break;
				case 3:
					w3.add(name);
					w3.add(time_get);
					w3.add(place);
					w3.add(teacher);
					w3.add(delete_id);
					w3.add(start_week);
					w3.add(end_week);
					
					break;
				case 4:
					w4.add(name);
					w4.add(time_get);
					w4.add(place);
					w4.add(teacher);
					w4.add(delete_id);
					w4.add(start_week);
					w4.add(end_week);
					
					break;
				case 5:
					w5.add(name);
					w5.add(time_get);
					w5.add(place);
					w5.add(teacher);
					w5.add(delete_id);
					w5.add(start_week);
					w5.add(end_week);
					
					break;
				case 6:
					w6.add(name);
					w6.add(time_get);
					w6.add(place);
					w6.add(teacher);
					w6.add(delete_id);
					w6.add(start_week);
					w6.add(end_week);
					
					break;
				case 7:
					w7.add(name);
					w7.add(time_get);
					w7.add(place);
					w7.add(teacher);
					w7.add(delete_id);
					w7.add(start_week);
					w7.add(end_week);
					
					break;
				}
			}	
		}
		
		if(week_signal.equals("双"))
		{
			if(n%2==0)
			{
				
				switch((1+n-Integer.parseInt(y_n[0].subSequence(3,5).toString())))
				{
				case 1:	     
					w1.add(name);
					w1.add(time_get);
					w1.add(place);
					w1.add(teacher);
					w1.add(delete_id);
					w1.add(start_week);
					w1.add(end_week);
					
					
					break;
				case 2:
					w2.add(name);
					w2.add(time_get);
					w2.add(place);
					w2.add(teacher);
					w2.add(delete_id);
					w2.add(start_week);
					w2.add(end_week);
					break;
				case 3:
					w3.add(name);
					w3.add(time_get);
					w3.add(place);
					w3.add(teacher);
					
					w3.add(delete_id);
					w3.add(start_week);
					w3.add(end_week);
					
					break;
				case 4:
					w4.add(name);
					w4.add(time_get);
					w4.add(place);
					w4.add(teacher);
					
					w4.add(delete_id);
					w4.add(start_week);
					w4.add(end_week);
					
					break;
				case 5:
					w5.add(name);
					w5.add(time_get);
					w5.add(place);
					w5.add(teacher);
					
					w5.add(delete_id);
					w5.add(start_week);
					w5.add(end_week);
					
					break;
				case 6:
					w6.add(name);
					w6.add(time_get);
					w6.add(place);
					w6.add(teacher);
					w6.add(delete_id);
					w6.add(start_week);
					w6.add(end_week);
					
					break;
				case 7:
					w7.add(name);
					w7.add(time_get);
					w7.add(place);
					w7.add(teacher);
					w7.add(delete_id);
					w7.add(start_week);
					w7.add(end_week);
					
					break;
				}
			}			
		}
		
		if(week_signal.equals("全"))
		{
			//Toast.makeText(m, n+" ---=", Toast.LENGTH_LONG).show();
			switch((1+n-Integer.parseInt(y_n[0].subSequence(3,5).toString())))
			{
			case 1:	     
				w1.add(name);
				w1.add(time_get);
				w1.add(place);
				w1.add(teacher);
				
				w1.add(delete_id);
				w1.add(start_week);
				w1.add(end_week);
				break;
			case 2:
				w2.add(name);
				w2.add(time_get);
				w2.add(place);
				w2.add(teacher);
				
				w2.add(delete_id);
				w2.add(start_week);
				w2.add(end_week);
				break;
			case 3:
				w3.add(name);
				w3.add(time_get);
				w3.add(place);
				w3.add(teacher);
				
				w3.add(delete_id);
				w3.add(start_week);
				w3.add(end_week);
				break;
			case 4:
				w4.add(name);
				w4.add(time_get);
				w4.add(place);
				w4.add(teacher);
				
				w4.add(delete_id);
				w4.add(start_week);
				w4.add(end_week);
				break;
			case 5:
				w5.add(name);
				w5.add(time_get);
				w5.add(place);
				w5.add(teacher);
				w5.add(delete_id);
				w5.add(start_week);
				w5.add(end_week);
				
				break;
			case 6:
				w6.add(name);
				w6.add(time_get);
				w6.add(place);
				w6.add(teacher);
				w6.add(delete_id);
				w6.add(start_week);
				w6.add(end_week);
				
				break;
			case 7:
				w7.add(name);
				w7.add(time_get);
				w7.add(place);
				w7.add(teacher);
				w7.add(delete_id);
				w7.add(start_week);
				w7.add(end_week);
				
				break;
			}
		}
	}
	
	public ArrayList database_week_query(ShowWeek m)
	{
		//String name,week_signal,start_week,end_week,place,teacher,time_get;
		ArrayList w1,w2,w3,w4,w5,w6,w7;
		w1=new ArrayList();
		w2=new ArrayList();
		w3=new ArrayList();
		w4=new ArrayList();
		w5=new ArrayList();
		w6=new ArrayList();
		w7=new ArrayList();
		int htime = 0,mtime = 0,year=0,dayOfMonth=0,monthOfYear=0,hour=0,minute=0,emonth=0,eday=0,eyear=0;
		Calendar calendar = Calendar.getInstance();
		hour = calendar.get(Calendar.HOUR_OF_DAY);
	    minute = calendar.get(Calendar.MINUTE);
	    year = calendar.get(calendar.YEAR);
		monthOfYear = calendar.get(calendar.MONTH)+1;
		dayOfMonth = calendar.get(calendar.DAY_OF_MONTH);
		
		String[]y_n=time_week_get(m);
		
		cursor=db.rawQuery("select * from Classes", null);
		while(cursor.moveToNext())
		{
			delete_id=cursor.getInt(cursor.getColumnIndex("delete_id"));
			name=cursor.getString(cursor.getColumnIndex("name"));
	        week_signal=cursor.getString(cursor.getColumnIndex("week"));
	        start_week=cursor.getString(cursor.getColumnIndex("start_week"));
	        end_week=cursor.getString(cursor.getColumnIndex("end_week"));
	        place=cursor.getString(cursor.getColumnIndex("place"));
	        teacher=cursor.getString(cursor.getColumnIndex("teacher"));
	        cursor1=db.rawQuery("select * from Time where delete_id=?", new String[]{delete_id+""});
	        if(cursor1.moveToFirst())
	        {
	        	time_get=cursor1.getString(cursor1.getColumnIndex("time"));
	        }
			//delete_id INTEGER PRIMARY KEY not null,name VARCHAR(20) not null,week VARCHAR(20) not null,start_week VARCHAR(20) not null,end_week VARCHAR(20) not null,place VARCHAR(20),teacher VARCHAR(20)
	        if(Integer.parseInt(start_week.subSequence(0, 4).toString())<=year&&year<=Integer.parseInt(end_week.subSequence(0, 4).toString()))
	        	if(Integer.parseInt(start_week.subSequence(5,start_week.charAt(6)=='-'?6:7).toString())<=monthOfYear&&monthOfYear<=Integer.parseInt(end_week.subSequence(5,end_week.charAt(6)=='-'?6:7).toString()))
	        	{
	        		//if(Integer.parseInt(start_week.subSequence(start_week.charAt(6)=='-'?7:8, start_week.length()).toString()));
	        		if(Integer.parseInt(y_n[0].subSequence(0, 2).toString())==Integer.parseInt(y_n[1].subSequence(0, 2).toString()))
	        		{
	        			int n=Integer.parseInt(y_n[0].subSequence(3, 5).toString());
	        			for(;n<=Integer.parseInt(y_n[1].subSequence(3, 5).toString());n++)
	        			{ 
	        				//Toast.makeText(m, n+"", Toast.LENGTH_LONG).show();
	        				//if(Integer.parseInt(start_week.subSequence(5, start_week.charAt(6)=='-'?6:7).toString())<monthOfYear&&monthOfYear<Integer.parseInt(end_week.subSequence(5, end_week.charAt(6)=='-'?6:7).toString()))
	        				if(Integer.parseInt(start_week.subSequence(5, start_week.charAt(6)=='-'?6:7).toString())<=monthOfYear&&monthOfYear<Integer.parseInt(end_week.subSequence(5, end_week.charAt(6)=='-'?6:7).toString()))
	        				{
	        					if(n<Integer.parseInt(start_week.subSequence(start_week.charAt(6)=='-'?7:8, start_week.length()).toString())&&Integer.parseInt(start_week.subSequence(5, start_week.charAt(6)=='-'?6:7).toString())==Integer.parseInt(y_n[0].subSequence(0, 2).toString()))
	        						continue;
	        					arraylist_add(m,w1,w2,w3, w4, w5,w6,w7,y_n, week_signal, n);
	        					//Toast.makeText(m, "-> "+w1.toString()+" "+w2.toString()+" "+w3.toString()+" "+w4.toString()+" "+w5.toString(), Toast.LENGTH_SHORT).show();
	        				}
	        				
	        			/*	if(Integer.parseInt(start_week.subSequence(5, start_week.charAt(6)=='-'?6:7).toString())==monthOfYear&&Integer.parseInt(end_week.subSequence(5, end_week.charAt(6)=='-'?6:7).toString())!=monthOfYear)
	        				{
	        				
	        					arraylist_add(w1,w2,w3, w4, w5,y_n, week_signal, n);
	        				}*/
	        			/*	
	        				if(monthOfYear==Integer.parseInt(end_week.subSequence(5, end_week.charAt(6)=='-'?6:7).toString())&&Integer.parseInt(start_week.subSequence(5, start_week.charAt(6)=='-'?6:7).toString())!=monthOfYear)
	        				{
	        					arraylist_add(w1,w2,w3, w4, w5,y_n, week_signal, n);
	        				}
	        				
	        				if(monthOfYear==Integer.parseInt(end_week.subSequence(5, end_week.charAt(6)=='-'?6:7).toString())&&Integer.parseInt(start_week.subSequence(5, start_week.charAt(6)=='-'?6:7).toString())==monthOfYear)*/
	        				if(monthOfYear==Integer.parseInt(end_week.subSequence(5, end_week.charAt(6)=='-'?6:7).toString()))
	        				{
	        					if(n>Integer.parseInt(end_week.subSequence(end_week.charAt(6)=='-'?7:8, end_week.length()).toString()))
	        						break;
	        					if(n<Integer.parseInt(start_week.subSequence(start_week.charAt(6)=='-'?7:8, start_week.length()).toString())&&Integer.parseInt(start_week.subSequence(5, start_week.charAt(6)=='-'?6:7).toString())==Integer.parseInt(end_week.subSequence(5, end_week.charAt(6)=='-'?6:7).toString()))
	        						continue;
	        					arraylist_add(m,w1,w2,w3, w4, w5,w6,w7,y_n, week_signal, n);
	        				//	Toast.makeText(m, "^-^", Toast.LENGTH_SHORT).show();
	        				}
	        				//if(Integer.parseInt(start_week.subSequence(start_week.charAt(6)=='-'?7:8, start_week.length())))
	        				
	        			}
	        			//Toast.makeText(m, Integer.parseInt(y_n[0].subSequence(0, 2).toString())+"=="+Integer.parseInt(y_n[1].subSequence(0, 2).toString()), Toast.LENGTH_SHORT).show();
	        		}
	        		else
	        		{
	        			int n=Integer.parseInt(y_n[0].subSequence(3, 5).toString());
	        			
	        		    calendar.set(Calendar.YEAR, year);
	        	        calendar.set(Calendar.MONTH, Integer.parseInt(y_n[0].subSequence(0, 2).toString())-1);
	        	        int lastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	        	        
	        	        
	        			for(;n<=lastDay;n++)
	        			{
	        				if(Integer.parseInt(start_week.subSequence(5, start_week.charAt(6)=='-'?6:7).toString())<=Integer.parseInt(y_n[0].subSequence(0, 2).toString())&&Integer.parseInt(y_n[0].subSequence(0, 2).toString())<Integer.parseInt(end_week.subSequence(5, end_week.charAt(6)=='-'?6:7).toString()))
	        				{
	        					if(n<Integer.parseInt(start_week.subSequence(start_week.charAt(6)=='-'?7:8, start_week.length()).toString())&&Integer.parseInt(start_week.subSequence(5, start_week.charAt(6)=='-'?6:7).toString())==Integer.parseInt(y_n[0].subSequence(0, 2).toString()))
	        						continue;
	        					arraylist_add(m,w1,w2,w3, w4, w5,w6,w7,y_n, week_signal, n);
	        				}
	        				if(Integer.parseInt(end_week.subSequence(5, end_week.charAt(6)=='-'?6:7).toString())==Integer.parseInt(y_n[0].subSequence(0, 2).toString()))
	        				{
	        					if(n>Integer.parseInt(end_week.subSequence(end_week.charAt(6)=='-'?7:8, end_week.length()).toString()))
	        						break;
	        					if(n<Integer.parseInt(start_week.subSequence(start_week.charAt(6)=='-'?7:8, start_week.length()).toString())&&Integer.parseInt(start_week.subSequence(5, start_week.charAt(6)=='-'?6:7).toString())==Integer.parseInt(end_week.subSequence(5, end_week.charAt(6)=='-'?6:7).toString()))
	        						continue;
	        					arraylist_add(m,w1, w2, w3, w4, w5,w6,w7, y_n, week_signal, n);
	        				}
	        			}
	        			int x=Integer.parseInt(y_n[1].subSequence(3, 5).toString());
	        			for(n=1;n<=x;n++)
	        			{
	        				if(Integer.parseInt(start_week.subSequence(5, start_week.charAt(6)=='-'?6:7).toString())<=Integer.parseInt(y_n[1].subSequence(0, 2).toString())&&Integer.parseInt(y_n[1].subSequence(0, 2).toString())<Integer.parseInt(end_week.subSequence(5, end_week.charAt(6)=='-'?6:7).toString()))
	        				{
	        					if(n<Integer.parseInt(start_week.subSequence(start_week.charAt(6)=='-'?7:8, start_week.length()).toString())&&Integer.parseInt(start_week.subSequence(5, start_week.charAt(6)=='-'?6:7).toString())==Integer.parseInt(y_n[0].subSequence(0, 2).toString()))
	        						continue;
	        					arraylist_add(m,w1, w2, w3, w4, w5, w6,w7,y_n, week_signal, n);
	        				}
	        				//if(Integer.parseInt(start_week.subSequence(5, start_week.charAt(6)=='-'?6:7).toString())==Integer.parseInt(end_week.subSequence(5, end_week.charAt(6)=='-'?6:7).toString())&&Integer.parseInt(end_week.subSequence(5, end_week.charAt(6)=='-'?6:7).toString())==Integer.parseInt(y_n[1].subSequence(0, 2).toString()))
	        				if(Integer.parseInt(end_week.subSequence(5, end_week.charAt(6)=='-'?6:7).toString())==Integer.parseInt(y_n[1].subSequence(0, 2).toString()))
	        				{
	        					if(n>Integer.parseInt(end_week.subSequence(end_week.charAt(6)=='-'?7:8, end_week.length()).toString()))
	        						break;
	        					if(n<Integer.parseInt(start_week.subSequence(start_week.charAt(6)=='-'?7:8, start_week.length()).toString())&&Integer.parseInt(start_week.subSequence(5, start_week.charAt(6)=='-'?6:7).toString())==Integer.parseInt(end_week.subSequence(5, end_week.charAt(6)=='-'?6:7).toString()))
	        						continue;
	        					arraylist_add(m,w1, w2, w3, w4, w5,w6,w7, y_n, week_signal, n);
	        				}
	        			}
	        		}
	        	}
	        //	Toast.makeText(m,y_n[0]+" "+y_n[1], Toast.LENGTH_SHORT).show();
			
		}
		//Toast.makeText(m, w1.toString()+" "+w1.isEmpty(), Toast.LENGTH_LONG).show();
		ArrayList s_list=new ArrayList();
		if(!w1.isEmpty()||!w2.isEmpty()||!w3.isEmpty()||!w4.isEmpty()||!w5.isEmpty()||!w6.isEmpty()||!w7.isEmpty())
		{
			s_list.add(w1);
			s_list.add(w2);
			s_list.add(w3);
			s_list.add(w4);
			s_list.add(w5);
			s_list.add(w6);
			s_list.add(w7);
		}
		return s_list;
	}
	
	/*	
	protected boolean databases_query(String data[])
	{
		try {
			cursor =  db.rawQuery("SELECT * FROM Time WHERE time_id =?",
		            new String[]{data[0]});
			if(cursor.moveToFirst())
		    {
		        int time_id = cursor.getInt(cursor.getColumnIndex("time_id"));
		        String time = cursor.getString(cursor.getColumnIndex("time"));
		        Toast.makeText(add_delete_main, time_id+" "+time, Toast.LENGTH_SHORT).show();
		     
		    }
			cursor= db.rawQuery("SELECT * FROM classes WHERE class_id=?",new String[]{"110"});
			//存在数据才返回true
		    if(cursor.moveToFirst())
		    {
		        int class_id=cursor.getInt(cursor.getColumnIndex("class_id"));
		        String name=cursor.getString(cursor.getColumnIndex("name"));
		        String place=cursor.getString(cursor.getColumnIndex("place"));
		        String teacher=cursor.getString(cursor.getColumnIndex("teacher"));
		        int week=cursor.getInt(cursor.getColumnIndex("week"));
		        int start_week=cursor.getInt(cursor.getColumnIndex("start_week"));
		        int end_week=cursor.getInt(cursor.getColumnIndex("end_week"));
		       Toast.makeText(add_delete_main, class_id+" "+name+" "+place+" "+teacher+" "+week+" "+start_week+" "+end_week,Toast.LENGTH_SHORT).show();
		        
		    }
			cursor= db.rawQuery("SELECT * FROM main WHERE time_id=?",new String[]{"0"});
			//存在数据才返回true
		    if(cursor.moveToFirst())
		    {
		    	String day=cursor.getString(cursor.getColumnIndex("day"));    
		    	Toast.makeText(add_delete_main, day ,Toast.LENGTH_SHORT).show();
		    }
		    
		    cursor.close();
		    return true;
		    } catch (Exception e) {
		    	// TODO: handle exception
		    	return false;
			}
		}

	protected boolean databases_update(String data[])
	{
		try {
			
			db.execSQL("UPDATE Time SET time = ? WHERE time_id = ?",
			        new String[]{data[0],data[1]});
			db.execSQL("UPDATE classes SET name=?,place=?,teacher=?,week=?,start_week=?,end_week=? WHERE class_id=?",new String[]
					{data[2],data[3],data[4],data[5],data[6],data[7],data[8]});
            db.execSQL("UPDATE main SET day=? WHERE time_id=?",new String[]{data[9],data[1]});
			
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

	}
	
	protected boolean databases_delete(String data[])
	{
		try {
			 db.execSQL("DELETE FROM main WHERE time_id = ?",
		                new String[]{data[0]});
			 db.execSQL("DELETE FROM Time WHERE time_id = ?",
		                new String[]{data[0]});
			 db.execSQL("DELETE FROM classes WHERE class_id = ?",
		                new String[]{data[1]});
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}

	}
	
*/
	
	
}
