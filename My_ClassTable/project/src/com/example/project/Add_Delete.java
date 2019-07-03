package com.example.project;

import java.util.Calendar;

import com.example.project.control.C_data_control;
import com.example.project.model.C_data_model;
import com.example.project.view.In_view_add_data;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


public class Add_Delete extends Activity implements OnClickListener,In_view_add_data{
	
	TabHost mTabHost;
	Button savebt,deletebt;
	TextView teacher,place,week,start_week,end_week,name,sbtime,ebtime;
	
	String teachers,places,weeks,start_weeks,end_weeks,names,times;
	C_data_control c_data_control;
	DatePickerDialog dateDialog;
	TimePickerDialog timePickerDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_delete);
		
		mTabHost=(TabHost) findViewById(R.id.tabhost);
		mTabHost.setup();
		
		
		TabHost.TabSpec ptabSpec=mTabHost.newTabSpec("one1");
		ptabSpec.setIndicator("+",getResources().getDrawable(R.drawable.add));
		ptabSpec.setContent(R.id.tab01);
		
		TabHost.TabSpec ctabSpec=mTabHost.newTabSpec("two");
		ctabSpec.setIndicator("-",getResources().getDrawable(R.drawable.reduction));
		ctabSpec.setContent(R.id.tab02);
		
		mTabHost.addTab(ptabSpec);
		mTabHost.addTab(ctabSpec);
		
		savebt=(Button) findViewById(R.id.add_save_button);
		deletebt=(Button) findViewById(R.id.delete_class);
		deletebt.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog.Builder dialog=new AlertDialog.Builder(Add_Delete.this);
				dialog.setMessage("免责声明："
						+ "本操做为不可逆转!"
						+ "请谨慎使用!").setIcon(R.drawable.ic_launcher)
				.setPositiveButton("NO", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						//Toast.makeText(getApplicationContext(), "no", Toast.LENGTH_SHORT).show();
					}
				}).setNegativeButton("YES", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						c_data_control.C_data_control_delete(Add_Delete.this);
						//Toast.makeText(getApplicationContext(), "yes", Toast.LENGTH_SHORT).show();
					}
				}).create();
				dialog.show();
				//c_data_control.C_data_control_delete(Add_Delete.this);
			}
		});

		
		teacher= (TextView) findViewById(R.id.teacher);
		place= (TextView) findViewById(R.id.place);
		week=(TextView) findViewById(R.id.week);
		start_week=(TextView) findViewById(R.id.start_week);
		end_week=(TextView) findViewById(R.id.end_week);
		name=(TextView) findViewById(R.id.name);
		sbtime=(TextView) findViewById(R.id.stime);
		ebtime=(TextView) findViewById(R.id.etime);
		
		savebt.setOnClickListener(this);
		ebtime.setOnClickListener(this);
		sbtime.setOnClickListener(this);
		start_week.setOnClickListener(this);
		end_week.setOnClickListener(this);
		week.setOnClickListener(this);
		
		c_data_control=new C_data_control(this);
		
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		int year,monthOfYear,dayOfMonth,hour,minute,seconds;
		Calendar calendar = Calendar.getInstance();
		
		
		year = calendar.get(calendar.YEAR);
		monthOfYear = calendar.get(calendar.MONTH);
		dayOfMonth = calendar.get(calendar.DAY_OF_MONTH);
        //hour = calendar.get(Calendar.HOUR);
		hour=calendar.get(Calendar.HOUR_OF_DAY);
        minute = calendar.get(Calendar.MINUTE);
        //Toast.makeText(getApplicationContext(), calendar.get(Calendar.HOUR_OF_DAY)+"", Toast.LENGTH_SHORT).show();
		switch(arg0.getId())
		{
		case R.id.add_save_button:
			//Toast.makeText(getApplicationContext(), teacher.getText(), Toast.LENGTH_SHORT).show();
			if(!teacher.getText().toString().equals(""))
				teachers=teacher.getText().toString();
			else
				teachers="None";
			if(!place.getText().toString().equals(""))
				places=place.getText().toString();
			else
				places="None";
			
			if(name.getText().toString().equals(""))
			{
				Toast.makeText(getApplicationContext(), "课程名未填写", Toast.LENGTH_SHORT).show();
				return;
			}
			else
				names=name.getText().toString();
			
		
			if(sbtime.getText().toString().equals(" 点击"))
			{
				Toast.makeText(getApplicationContext(), "时间未选择", Toast.LENGTH_SHORT).show();
				return;
			}
			else
				times=sbtime.getText().toString();
			
			if(ebtime.getText().toString().equals(" 点击"))
			{
				Toast.makeText(getApplicationContext(), "时间未选择", Toast.LENGTH_SHORT).show();
				return;
			}
			else
				times=times+"-"+ebtime.getText().toString();
			
			if(start_week.getText().toString().equals(" 点击"))
			{
				Toast.makeText(getApplicationContext(), "start_week未选择", Toast.LENGTH_SHORT).show();
				return;
			}
			else
				start_weeks=start_week.getText().toString();
			
			if(end_week.getText().toString().equals(" 点击"))
			{
				Toast.makeText(getApplicationContext(), "end_week未选择", Toast.LENGTH_SHORT).show();
				return;
			}
			else
				end_weeks=end_week.getText().toString();
			
			if(week.getText().toString().equals(" 点击"))
			{
				Toast.makeText(getApplicationContext(), "week未选择", Toast.LENGTH_SHORT).show();
				return;
			}
			else
				weeks=week.getText().toString();
			
			if(Integer.parseInt(start_weeks.subSequence(0, 4).toString())<year)
			{
				Toast.makeText(getApplicationContext(), "设置年份小于系统年份!", Toast.LENGTH_SHORT).show();
				return;
			}
			//
			/*if(Integer.parseInt(start_weeks.subSequence(0, 4).toString())==year)
			{
				int stime = 0;
				if(times.charAt(1)==':')
				{
					stime=Integer.parseInt(String.valueOf(times.charAt(0)));

				}
				else
				{
					
				}
				
				if(hour>stime)
					return;
			}*/
			
			if(Integer.parseInt(start_weeks.subSequence(0, 4).toString())==Integer.parseInt(end_weeks.subSequence(0, 4).toString()))
			{
				int sweek,eweek,shour;
				if(start_weeks.charAt(6)=='-')
					sweek=Integer.parseInt(String.valueOf(start_weeks.charAt(5)));
				else
					sweek=Integer.parseInt(start_weeks.substring(5, 7).toString());
				
				if(end_weeks.charAt(6)=='-')
					eweek=Integer.parseInt(String.valueOf(end_weeks.charAt(5)));
				else
					eweek=Integer.parseInt(end_weeks.substring(5, 7).toString());
				
				if(monthOfYear>sweek)
				{
					Toast.makeText(getApplicationContext(), "设定月份小于系统月份", Toast.LENGTH_SHORT).show();
					return;
				}
				
				if(eweek<sweek)
				{
					Toast.makeText(getApplicationContext(), "月份设置有误!", Toast.LENGTH_SHORT).show();
					return;
				}
			
				if(eweek==sweek)
				{
					if(start_weeks.charAt(start_weeks.length()-2)=='-')
						sweek=Integer.parseInt(String.valueOf(start_weeks.charAt(start_weeks.length()-1)));
					else
						sweek=Integer.parseInt(start_weeks.substring(start_weeks.length()-2, start_weeks.length()).toString());
					
					if(end_weeks.charAt(end_weeks.length()-2)=='-')
						eweek=Integer.parseInt(String.valueOf(end_weeks.charAt(end_weeks.length()-1)));
					else
						eweek=Integer.parseInt(end_weeks.substring(end_weeks.length()-2, end_weeks.length()).toString());
					
					if(sweek<dayOfMonth)
					{
						Toast.makeText(getApplicationContext(), "设定的日期小于系统日期!", Toast.LENGTH_SHORT).show();
						return;
					}
					
					if(sweek==dayOfMonth)
					{
						int ehtime = 0,estime = 0,shtime=0,sstime = 0,posistion=0;
						if(times.charAt(3)=='-')
						{
							posistion=3;
							//if(times.subSequence(4, times.length()).toString())
							//Toast.makeText(getApplicationContext(), times.subSequence(4, times.length()).toString()+"-3", Toast.LENGTH_SHORT).show();
							if(times.subSequence(4, times.length()).toString().charAt(1)==':')
							{
								ehtime=Integer.parseInt(String.valueOf(times.subSequence(4, times.length()).toString().charAt(0)));
								estime=Integer.parseInt(times.subSequence(4, times.length()).toString().subSequence(2, times.subSequence(4, times.length()).toString().length()).toString());
								
								//Toast.makeText(getApplicationContext(), "1", Toast.LENGTH_SHORT).show();
							}
							else
							{
								//Toast.makeText(getApplicationContext(), "2", Toast.LENGTH_SHORT).show();
								ehtime=Integer.parseInt(times.subSequence(4, times.length()).toString().subSequence(0, 2).toString());
								estime=Integer.parseInt(times.subSequence(4, times.length()).toString().subSequence(3, times.subSequence(4, times.length()).toString().length()).toString());
							}
						}

						if(times.charAt(4)=='-')
						{ 
							posistion=4;
							if(times.subSequence(5, times.length()).toString().charAt(1)==':')
							{
								ehtime=Integer.parseInt(String.valueOf(times.subSequence(5, times.length()).toString().charAt(0)));
								estime=Integer.parseInt(times.subSequence(5, times.length()).toString().subSequence(2, times.subSequence(5, times.length()).length()).toString());
								//Toast.makeText(getApplicationContext(), "->"+ehtime+":"+estime, Toast.LENGTH_SHORT).show();
								//Toast.makeText(getApplicationContext(), ehtime, Toast.LENGTH_SHORT).show();
								//return;
							}
							else
							{
								//Toast.makeText(getApplicationContext(), "2", Toast.LENGTH_SHORT).show();
								ehtime=Integer.parseInt(times.subSequence(5, times.length()).subSequence(0, 2).toString());
								estime=Integer.parseInt(times.subSequence(5, times.length()).subSequence(3, times.subSequence(5, times.length()).length()).toString());
								//Toast.makeText(getApplicationContext(), "->"+ehtime+":"+estime, Toast.LENGTH_SHORT).show();
								//return;
							}
							//Toast.makeText(getApplicationContext(), times.subSequence(5, times.length()).toString()+"-4", Toast.LENGTH_SHORT).show();
						}

						if(times.charAt(5)=='-')
						{
							posistion=5;
							if(times.subSequence(6, times.length()).charAt(1)==':')
							{
								ehtime=Integer.parseInt(String.valueOf(times.subSequence(6, times.length()).toString().charAt(0)));
								estime=Integer.parseInt(times.subSequence(6, times.length()).toString().subSequence(2, times.subSequence(6, times.length()).length()).toString());
								//Toast.makeText(getApplicationContext(), "->"+ehtime+":"+estime, Toast.LENGTH_SHORT).show();
								//Toast.makeText(getApplicationContext(), ehtime, Toast.LENGTH_SHORT).show();
								//return;
							}
							else
							{
								//Toast.makeText(getApplicationContext(), "2", Toast.LENGTH_SHORT).show();
								ehtime=Integer.parseInt(times.subSequence(6, times.length()).subSequence(0, 2).toString());
								estime=Integer.parseInt(times.subSequence(6, times.length()).subSequence(3, times.subSequence(6, times.length()).length()).toString());
								//Toast.makeText(getApplicationContext(), "->"+ehtime+":"+estime, Toast.LENGTH_SHORT).show();
								//return;
							}
						}
						/*
						if(times.charAt(1)==':')
						{
							shour=Integer.parseInt(String.valueOf(times.charAt(0)));

						}
						else
						{
							shour=Integer.parseInt(times.substring(0, 2).toString());
						}*/
						/*if(ehtime<hour)
						{
							Toast.makeText(getApplicationContext(), "结束的小时数不能小于系统小时数!", Toast.LENGTH_SHORT).show();
							return;
						}
						if(ehtime==hour)
						{
							if((estime-minute)<30)
							{
								Toast.makeText(getApplicationContext(), "设定的结束时间的分钟数需要大于30", Toast.LENGTH_SHORT).show();
								return;
							}
						}*/
						if(times.charAt(1)==':')
							shtime=Integer.parseInt(String.valueOf(times.charAt(0)));
						else
							shtime=Integer.parseInt(times.subSequence(0, 2).toString());
						if(times.charAt(1)==':')
							sstime=Integer.parseInt(times.subSequence(2, posistion).toString());
						else
							sstime=Integer.parseInt(times.subSequence(3, posistion).toString());
						if(ehtime<shtime)
						{
							
							Toast.makeText(getApplicationContext(), "设定的开始小时大于结束小时", Toast.LENGTH_SHORT).show();
							return;
						}
						
						if(estime<sstime&&shtime==ehtime)
						{
							Toast.makeText(getApplicationContext(), "设定的开始分钟大于结束分钟", Toast.LENGTH_SHORT).show();
							return;
						}
						
					}
					
					if(sweek>eweek)
					{
						Toast.makeText(getApplicationContext(), "日期设置有误!", Toast.LENGTH_SHORT).show();
						return;
					}
					
					
				}
				
				
			}
			
			if(Integer.parseInt(start_weeks.subSequence(0, 4).toString())>Integer.parseInt(end_weeks.subSequence(0, 4).toString()))
			{
				Toast.makeText(getApplicationContext(), "年份设置有误!", Toast.LENGTH_SHORT).show();
				return;
			}
			
			//	Toast.makeText(getApplicationContext(), times, Toast.LENGTH_SHORT).show();
			
			
			String data[]={names,weeks,start_weeks,end_weeks,places,teachers,times,year+"-"+monthOfYear+"-"+dayOfMonth+" "+hour+":"+minute};
	//		String data[]={"math","5_1","1","5","Beijing","yfc","3:00"};

			c_data_control.In_data_c(data,"insert");
			
			break;

			
		case R.id.end_week:
			/*year = calendar.get(calendar.YEAR);
			monthOfYear = calendar.get(calendar.MONTH);
			dayOfMonth = calendar.get(calendar.DAY_OF_MONTH);*/
	        //seconds = calendar.get(Calendar.SECOND);//获取秒钟
	        
			dateDialog = new DatePickerDialog(this,AlertDialog.THEME_DEVICE_DEFAULT_LIGHT, new OnDateSetListener() {
				 
				@Override
				public void onDateSet(DatePicker arg0, int year, int monthOfYear,
						int dayOfMonth) {
					// 把获取的日期显示在文本框内，月份从0开始计数，所以要加1
					String text = year + "-" + (monthOfYear+1)+ "-" + dayOfMonth;
					//editText.setText(text);
					end_week.setText(text);
				}
			}, year, monthOfYear, dayOfMonth); 
	
			dateDialog.show();
			break;
			
		case R.id.week:
			final String text[]=new String[] { "单", "双","全" };
			week.setText(text[0]);
			AlertDialog.Builder abdialog=new AlertDialog.Builder(this);
			abdialog.setTitle("自定义布局").setSingleChoiceItems(text,  0,
			   	new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface arg0, int arg1) {
						// TODO Auto-generated method stub
						//Toast.makeText(getApplicationContext(), arg1  +"", Toast.LENGTH_SHORT).show();
						
						week.setText(text[arg1]);
					}
				})
			     .setNegativeButton("确定", null).show();
			   
			   break;
			
		case R.id.start_week:
			/*year = calendar.get(calendar.YEAR);
			monthOfYear = calendar.get(calendar.MONTH);
			dayOfMonth = calendar.get(calendar.DAY_OF_MONTH);*/
	        //seconds = calendar.get(Calendar.SECOND);//获取秒钟
	        
			dateDialog = new DatePickerDialog(this,AlertDialog.THEME_DEVICE_DEFAULT_LIGHT, new OnDateSetListener() {
				 
				@Override
				public void onDateSet(DatePicker arg0, int year, int monthOfYear,
						int dayOfMonth) {
					// 把获取的日期显示在文本框内，月份从0开始计数，所以要加1
					String text = year + "-" + (monthOfYear+1) + "-" + dayOfMonth;
					//editText.setText(text);
					start_week.setText(text);
				}
			}, year, monthOfYear, dayOfMonth); 
	
			dateDialog.show();
			break;
			
		case R.id.stime:
	        /*hour = calendar.get(Calendar.HOUR);
	        minute = calendar.get(Calendar.MINUTE);*/
	        timePickerDialog = new TimePickerDialog(this,android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar,new TimePickerDialog.OnTimeSetListener() {
	            @Override
	            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
	            	String text=hourOfDay+":"+minute;
	            	sbtime.setText(text);
	            }
	        },hour,minute,true);
	        timePickerDialog.setTitle("开始时间");
	        timePickerDialog.show();
			break;
			
		case R.id.etime:
	        timePickerDialog = new TimePickerDialog(this,android.R.style.Theme_DeviceDefault_Light_Dialog_NoActionBar,new TimePickerDialog.OnTimeSetListener() {
	            @Override
	            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
	            	String text=hourOfDay+":"+minute;
	            	ebtime.setText(text);
	            }
	        },hour,minute,true);
	        timePickerDialog.setTitle("结束时间");
	        timePickerDialog.show();
			break;
			

		}
		
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		c_data_control.c_data_model.close();
	}

}