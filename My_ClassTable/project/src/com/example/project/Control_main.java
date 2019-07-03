package com.example.project;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import com.example.project.model.C_data_model;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;

public class Control_main extends Activity {
	
	
	C_data_model c_data_model;
	SimpleAdapter spad;
	ListView list;
	ArrayList arraylist;
//	Iterator it;
	List<Map<String, Object>> data;
//	String[]list_text1={"查看","数学"};
//	String[]list_text2={"本周有什么课程?","666"};
	//String[]list_text1={};
	//String[]list_text2={};
	//int[]ima={R.drawable.table_welcome,R.drawable.ic_launcher};
	String[] week_days = new String[]{"Monday","Thesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
	RadioOnClick radioOnClick = new RadioOnClick(1);
	ListView areaRadioListView;
	Button activity_add,activity_setting,activity_weekshow;
	Intent intent;
	
	String user_name;
	
	NotificationManager notifyManager;
	
	private List<Map<String, Object>> data() {
		// TODO Auto-generated method stub
		data=new ArrayList<Map<String,Object>>();
/*		
		for(int i=0;i<2;i++){
			Map<String, Object> map=new HashMap<String, Object>();
			
			map.put("image_", ima[i]);
			map.put("text_1", list_text1[i]);
			map.put("text_2", list_text2[i]);
			data.add(map);
		}
*/
		
		Calendar calendar = Calendar.getInstance();
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
	    int minute = calendar.get(Calendar.MINUTE);
		int h_=0,m_=0;
		int h_1=-1,m_1=-1;
		String te_e=null,t_e=null,p_e=null,c_e = null;
		notifyManager = (NotificationManager) getSystemService( Context.NOTIFICATION_SERVICE);
		Map<String, Object> map = null;
		boolean bool_=true,bool_1=true,bool_2=true;
		for(int n=0;n<arraylist.size()/7;n++)
		{
			map=new HashMap<String, Object>();
			
			map.put("text_1", "课程名: "+arraylist.get(0+n*7)+" 地点: "+arraylist.get(4+n*7)+" 教师: "+arraylist.get(5+n*7)+" ");
			map.put("text_2", "时间: "+arraylist.get(6+n*7));
			int h=Integer.parseInt(arraylist.get(6+n*7).toString().subSequence(0, arraylist.get(6+n*7).toString().charAt(1)==':'?1:2).toString());
			int m=Integer.parseInt(arraylist.get(6+n*7).toString().subSequence(arraylist.get(6+n*7).toString().charAt(1)==':'?2:3, arraylist.get(6+n*7).toString().charAt(1)==':'?(arraylist.get(6+n*7).toString().charAt(3)=='-'?3:4):(arraylist.get(6+n*7).toString().charAt(4)=='-'?4:5)).toString());
			int eh=0;//Integer.parseInt(arraylist.get(6+n*7).toString().subSequence((arraylist.get(6+n*7).toString().charAt(3)=='-'?4:5),(arraylist.get(6+n*7).toString().charAt(3)=='-'?(arraylist.get(6+n*7).toString().charAt(5)==':'?5:6):(arraylist.get(6+n*7).toString().charAt(6)==':'?6:7))).toString());

			if(arraylist.get(6+n*7).toString().charAt(3)=='-')
				if(arraylist.get(6+n*7).toString().charAt(5)==':')
					eh=Integer.parseInt(arraylist.get(6+n*7).toString().subSequence(4, 5).toString());
				else
					eh=Integer.parseInt(arraylist.get(6+n*7).toString().subSequence(4, 6).toString());
			if(arraylist.get(6+n*7).toString().charAt(4)=='-')
				if(arraylist.get(6+n*7).toString().charAt(6)==':')
					eh=Integer.parseInt(arraylist.get(6+n*7).toString().subSequence(5, 6).toString());
				else
					eh=Integer.parseInt(arraylist.get(6+n*7).toString().subSequence(5, 7).toString());
			if(arraylist.get(6+n*7).toString().charAt(5)=='-')
				if(arraylist.get(6+n*7).toString().charAt(7)==':')
					eh=Integer.parseInt(arraylist.get(6+n*7).toString().subSequence(6, 7).toString());
				else
					eh=Integer.parseInt(arraylist.get(6+n*7).toString().subSequence(6, 8).toString());

			int em=0;
			if(arraylist.get(6+n*7).toString().charAt(3)=='-')
				if(arraylist.get(6+n*7).toString().charAt(5)==':')
					em=Integer.parseInt(arraylist.get(6+n*7).toString().substring(6).toString());
				else
					em=Integer.parseInt(arraylist.get(6+n*7).toString().substring(7).toString());
			if(arraylist.get(6+n*7).toString().charAt(4)=='-')
				if(arraylist.get(6+n*7).toString().charAt(6)==':')
					em=Integer.parseInt(arraylist.get(6+n*7).toString().substring(7).toString());
				else
					em=Integer.parseInt(arraylist.get(6+n*7).toString().substring(8).toString());
			if(arraylist.get(6+n*7).toString().charAt(5)=='-')
				if(arraylist.get(6+n*7).toString().charAt(7)==':')
					em=Integer.parseInt(arraylist.get(6+n*7).toString().substring(8).toString());
				else
					em=Integer.parseInt(arraylist.get(6+n*7).toString().substring(9).toString());
	//		Toast.makeText(getApplicationContext(), eh+":"+em, Toast.LENGTH_LONG).show();
			h_=h-hour;
			m_=m-minute;
			//int h_1=-1,m_1=-1;
			//&&(hour<=eh)
			//Toast.makeText(getApplicationContext(), bool_1+" "+(((h_<=0)&&bool_1))+" "+h_+"<=0"+"&&"+bool_+"&&"+hour+"<="+eh, Toast.LENGTH_LONG).show();
			if(h_<=0&&bool_1&&hour<=eh)
			{
				bool_=false;
				//Toast.makeText(getApplicationContext(), hour+"=="+eh+"&&"+eh+"=="+h, Toast.LENGTH_SHORT).show();
				if(hour==eh&&eh==h)
				{
					if(h_1==-1&&m_1==-1)
					{
						//Toast.makeText(getApplicationContext(), "1", Toast.LENGTH_SHORT).show();
						bool_2=false;
						h_1=h_;
						m_1=m_;
						te_e="时间: "+arraylist.get(6+n*7);
						p_e=" 地点: "+arraylist.get(4+n*7);
						t_e=" 教师: "+arraylist.get(5+n*7);
						c_e="课程名: "+arraylist.get(0+n*7);
					}
					else
						if(m_1>m_)
						{
							//Toast.makeText(getApplicationContext(), "2", Toast.LENGTH_SHORT).show();
							bool_2=false;
							h_1=h_;
							m_1=m_;
							te_e="时间: "+arraylist.get(6+n*7);
							p_e=" 地点: "+arraylist.get(4+n*7);
							t_e=" 教师: "+arraylist.get(5+n*7);
							c_e="课程名: "+arraylist.get(0+n*7);
						}
					
				}
				else
					if(bool_2&&(eh==hour&&minute<=em))
					{
						
						bool_1=false;
					//	Toast.makeText(getApplicationContext(), "3", Toast.LENGTH_SHORT).show();
						te_e="时间: "+arraylist.get(6+n*7);
						p_e=" 地点: "+arraylist.get(4+n*7);
						t_e=" 教师: "+arraylist.get(5+n*7);
						c_e="课程名: "+arraylist.get(0+n*7);
					}
					else
						if(bool_2&&hour<eh)
						{
							bool_1=false;
							te_e="时间: "+arraylist.get(6+n*7);
							p_e=" 地点: "+arraylist.get(4+n*7);
							t_e=" 教师: "+arraylist.get(5+n*7);
							c_e="课程名: "+arraylist.get(0+n*7);
						}
				
			//	Toast.makeText(getApplicationContext(), bool_2+"&&"+hour+"<"+em, Toast.LENGTH_LONG).show();
				
			}
			else
				if(bool_&&h_>0)
				{
					if(h_1==-1&&m_1==-1)
					{
						h_1=h_;
						m_1=m_;
						te_e="时间: "+arraylist.get(6+n*7);
						p_e=" 地点: "+arraylist.get(4+n*7);
						t_e=" 教师: "+arraylist.get(5+n*7);
						c_e="课程名: "+arraylist.get(0+n*7);
					}
					else
					{
						if(h_1>h_)
						{
							h_1=h_;
							m_1=m_;
							te_e="时间: "+arraylist.get(6+n*7);
							p_e=" 地点: "+arraylist.get(4+n*7);
							t_e=" 教师: "+arraylist.get(5+n*7);
							c_e="课程名: "+arraylist.get(0+n*7);
						}
						else
							if(h_1==h_&&m_1>m_)
							{
								h_1=h_;
								m_1=m_;
								te_e="时间: "+arraylist.get(6+n*7);
								p_e=" 地点: "+arraylist.get(4+n*7);
								t_e=" 教师: "+arraylist.get(5+n*7);
								c_e="课程名: "+arraylist.get(0+n*7);
							}
						
						//Toast.makeText(getApplicationContext(), h_1+"=="+h_+"&&"+m_1+">"+m_, Toast.LENGTH_LONG).show();
					}
					
				}
			data.add(map);
		}
		
		
		if(c_e!=null||te_e!=null||p_e!=null||t_e!=null)
		{
			PendingIntent pendingIntent = PendingIntent.getActivity(Control_main.this, 0, new Intent(Control_main.this,Control_main.class),0);
		
			String title=c_e+" "+te_e;
			String tcontext=p_e+" "+t_e;
        Notification builder = new Notification.Builder(Control_main.this)
                //设置小图标 
                .setSmallIcon(R.drawable.message)
                //设置通知标题
                .setContentTitle(title)
                //.setContentTitle("sf")
                //设置通知内容
                //.setContentText(new String(p_e+" "+t_e))
                .setContentText(tcontext)
                //设置延时Intent
                .setContentIntent(pendingIntent)
                //设置可以自动取消
                .setAutoCancel( true ) 
                //设置通知时间，默认为系统发出通知的时间，通常不用设置
                //.setWhen(System.currentTimeMillis());
                .build();
        
        notifyManager.notify(0, builder);
		}
		
		return data;
	}

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_control);
		
		intent=getIntent();
		user_name=intent.getStringExtra("user_login");
		
		
		c_data_model=new C_data_model(this, "Student", null, 1);
		c_data_model.idtable_get(this);

		arraylist=c_data_model.control_main_show(this);
		if(arraylist==null||arraylist.toString().equals("[]"))
			Toast.makeText(getApplicationContext(), "empty!", Toast.LENGTH_SHORT).show();
		else
		{
		//	Toast.makeText(getApplicationContext(), arraylist.size()+"", Toast.LENGTH_SHORT).show();
	
//		it=arraylist.iterator();
			
			
			
		list=(ListView) findViewById(R.id.activity_timetable_list);
		spad=new SimpleAdapter(Control_main.this, data(),
				R.layout.list_classtable, new String[]{"text_1","text_2"}, new int[]{R.id.text1,R.id.text2});
				//R.layout.list_classtable, new String[]{"image_","text_1","text_2"}, new int[]{R.id.image,R.id.text1,R.id.text2});
		list.setAdapter(spad);
	/*	
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				String test=list_text1[position];
				
				
				if(test.equals(list_text1[0]))
				{
					AlertDialog adg =new AlertDialog.Builder(Control_main.this).setTitle("选择")
							.setSingleChoiceItems(week_days, radioOnClick.getIndex(), radioOnClick).create();
					areaRadioListView=adg.getListView();
					   adg.show();
				}
				else
					if(test.equals(list_text1[1]))
					{
						Toast.makeText(getApplicationContext(), test, Toast.LENGTH_SHORT).show();
						
					}
			}
		});
		*/
			}
		activity_setting=(Button) findViewById(R.id.activity_timetable_setting);
		activity_setting.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//intent=new Intent(Control_main.this,Settings.class);
				intent.setClassName("com.example.project", "com.example.project.Settings");
				intent.putExtra("user_name", user_name);
				if(arraylist==null||arraylist.toString().equals("[]"))
					intent.putExtra("array", new ArrayList());
				else
					intent.putExtra("array", arraylist);
				startActivity(intent);
			}
		});
		
		activity_weekshow=(Button) findViewById(R.id.activity_show_week);
		activity_weekshow.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				intent=new Intent(Control_main.this,ShowWeek.class);
				intent.putExtra("user_name", user_name);
				startActivity(intent);
			}
		});
		
		activity_add=(Button) findViewById(R.id.activity_timetable_add_bt);
		activity_add.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch(v.getId())
				{
				case R.id.activity_timetable_add_bt:
					intent=new Intent(Control_main.this,Add_Delete.class);
		
					startActivity(intent);
				}
			}
		});
	}
	
	
	class RadioOnClick implements DialogInterface.OnClickListener{
		private int index;
  		public RadioOnClick(int index){
  			this.index = index;
  		}
  		public void setIndex(int index){
	   
  			this.index=index;
  		}
  		public int getIndex(){
  			return index;
  		}
  		
  		public void onClick(DialogInterface dialog, int whichButton){
  			setIndex(whichButton);
  			Toast.makeText(Control_main.this, "您已经选择了： " + index + ":" + week_days[index], Toast.LENGTH_LONG).show();
  			dialog.dismiss();
  			}
  		}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		c_data_model.close();
	}
}
