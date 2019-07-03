package com.example.project;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

import com.example.project.model.C_data_model;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


public class ShowWeek extends Activity{
	
	C_data_model c_data_model;
	ArrayList arraylist;
	String user_name;
	
	private int colors[] = {
			Color.rgb(0xee,0xff,0xff),
			Color.rgb(0xf0,0x96,0x09),
			Color.rgb(0x8c,0xbf,0x26),
			Color.rgb(0x00,0xab,0xa9),
			Color.rgb(0x99,0x6c,0x33),
			Color.rgb(0x3b,0x92,0xbc),
			Color.rgb(0xd5,0x4d,0x34),
			Color.rgb(0xcc,0xcc,0xcc)
		};
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_week);
		
		user_name=getIntent().getStringExtra("user_name");
		
		//�ֱ��ʾ��һ������
        LinearLayout ll1 = (LinearLayout)findViewById(R.id.ll1);
        LinearLayout ll2 = (LinearLayout)findViewById(R.id.ll2);
        LinearLayout ll3 = (LinearLayout)findViewById(R.id.ll3);
        LinearLayout ll4 = (LinearLayout)findViewById(R.id.ll4);
        LinearLayout ll5 = (LinearLayout)findViewById(R.id.ll5);
        LinearLayout ll6 = (LinearLayout)findViewById(R.id.ll6);
        LinearLayout ll7 = (LinearLayout)findViewById(R.id.ll7);

		c_data_model=new C_data_model(this, "Student", null, 1);
		arraylist=c_data_model.control_main_week_show(this);
		if(!arraylist.isEmpty())
		{
			 //ÿ��Ŀγ�����
	        /*setClass(ll1, "", "", "", "", 2, 0);
	        setClass(ll1, "windows���ʵ��", "����  4-503", "1-9�ܣ�ÿһ��", "9:50-11:25", 2, 1);
	        setNoClass(ll1, 3, 0);
	        setClass(ll1, "������������ͳ��", "����  4-304", "1-15�ܣ�ÿһ��", "14:55-17:25", 3, 2);
	        setNoClass(ll1, 1, 0);
	        setClass(ll1, "���Ļ�ѧ", "һ�� 3-404", "3-13�ܣ�ÿһ��", "19:00-20:30", 2, 4);
	        setNoClass(ll1, 1, 0);
	        
	        setClass(ll2, "��ѧӢ��", "���� 4-302", "1-18�ܣ�ÿһ��", "8:00-9:35", 2, 3);
	        setClass(ll2, "�������֯��ϵ��ṹ", "���� 4-204", "1-15��ÿһ��", "9:50-12:15", 3, 5);
	        setNoClass(ll2, 3, 0);
	        setClass(ll2, "�ŶӼ����͹�ͨ", "���� 4-204", "1-9�ܣ�ÿһ��", "15:45-17:25", 2, 6);
	        setNoClass(ll2, 1, 0);
	        setClass(ll2, "�й����ִ�ʷ��Ҫ", "3�� 1-327", "1-9�ܣ�ÿһ��", "19:00-21:25", 3, 1);
	        
	        setNoClass(ll3, 2, 0);
	        setClass(ll3, "�й����ִ�ʷ��Ҫ", "3�� 1-328", "1-9�ܣ�ÿһ��", "9:50-12:15", 3, 1);
	        setNoClass(ll3, 1, 0);
	        setClass(ll3, "����������", "��Ϣѧ�� �ٳ�", "6-18�ܣ�ÿһ��", "14:00-15:40", 2, 2);
	        setNoClass(ll3, 3, 0);
	        setClass(ll3, "���������뾭��", "3�� 1-501", "1-7�ܣ�ÿһ��", "19:00-21:25", 3, 3);
	        
	        setClass(ll4, "�������֯��ϵ��ṹ", "���� 4-204", "1-15��ÿһ��", "8:00-9:35", 2, 5);
	        setClass(ll4, "���ݽṹ���㷨", "���� 4-304", "1-18�ܣ�ÿһ��", "9:50-12:15", 3, 4);
	        setNoClass(ll4, 1, 0);
	        setClass(ll4, "������������ƣ�JAVA��", "���� 1-103", "1-18�ܣ�ÿһ��", "14:00-16:30", 3, 5);
	        setNoClass(ll4, 2, 0);
	        setNoClass(ll4, 3, 0);
	        
	        setClass(ll5, "c#�������", "���� 4-102", "1-9�ܣ�ÿһ��", "8:00-9:35", 2, 6);
	        setClass(ll5, "��ѧӢ��", "���� 4-302", "1-18�ܣ�ÿһ��", "9:50-11:25", 2, 3);
	        setNoClass(ll5, 2, 0);
	        setClass(ll5, "��������", "���� 4-304", "1-18�ܣ�ÿһ��", "14:00-16:30", 3, 1);
	        setNoClass(ll5, 2, 0);
	        setClass(ll5, "�ֻ�Ӧ�÷����봴��", "1�� 5-103", "1-7�ܣ�ÿһ��", "19:00-21:2", 3, 2);
	        
	        setNoClass(ll6, 14, 0);
	        
	        setNoClass(ll7, 14, 0);
	        */
			
			ArrayList w1=(ArrayList) arraylist.get(0);
			ArrayList w2=(ArrayList) arraylist.get(1);
			ArrayList w3=(ArrayList) arraylist.get(2);
			ArrayList w4=(ArrayList) arraylist.get(3);
			ArrayList w5=(ArrayList) arraylist.get(4);
			
			ArrayList w6=(ArrayList) arraylist.get(5);
			ArrayList w7=(ArrayList) arraylist.get(6);
			
			//Toast.makeText(getApplicationContext(), "<>00: "+w6.size()+" "+w7.size(), Toast.LENGTH_LONG).show();
			
			SimpleDateFormat myFmt1=new SimpleDateFormat("yy/MM/dd HH:mm");
			//SimpleDateFormat myFmt1=new SimpleDateFormat("dd");
			
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
		    
			//Toast.makeText(getApplicationContext(),start_week.subSequence(6, 8).toString(),Toast.LENGTH_LONG).show();
			
			int s1=Integer.parseInt(start_week.subSequence(6, 8).toString());
			int s2=Integer.parseInt(end_week.subSequence(6, 8).toString());
			int sm1=Integer.parseInt(start_week.subSequence(3, 5).toString());
			//Toast.makeText(getApplicationContext(), String.valueOf(sm1), Toast.LENGTH_LONG).show();
			int st1=0,st2=0;
			if(s2-s1<0)
			{
				st1=s1;
				st2=7-s2;
				
				//19/06/24
				
			}
			else
			{
				st1=s1;
				st2=s2;
			}
			
			
			for(int i=0;i<w1.size()/7;i++)
			{
				int d_=st1;
				int m_=sm1;
				
				setClass(ll1,"","","" ,"", "", "", "", "","", 2, 0);
		        setClass(ll1, w1.get(i*7+5).toString(),w1.get(i*7+6).toString(),w1.get(i*7+4).toString(),"�γ̣�"+w1.get(i*7).toString(), "ʱ�䣺"+w1.get(1+i*7).toString(), "�ص㣺"+w1.get(2+i*7).toString(), "��ʦ��"+w1.get(3+i*7).toString(), m_+"",d_+"", 2, 1);
		        
			}
			for(int i=0;i<w2.size()/7;i++)
			{
				int d_=0;
				int m_=0;
				if(s2-s1<0)
				{
					switch(st2)
					{
					case 1:
						d_=1;
						m_=sm1+1;
						break;
						/*
					case 2:
						d_=st1+1;
						m_=sm1;
						break;
					case 3:
						break;
					case 4:
						break;
					case 5:
						break;
					case 6:
						break;
						*/
					default:
						d_=st1+1;
						m_=sm1;
						break;
					}
				}
				else
				{
					d_=st1+1;
					m_=sm1;
				}
		        setClass(ll2, w2.get(i*7+5).toString(),w2.get(i*7+6).toString(),w2.get(i*7+4).toString(),"�γ̣�"+w2.get(i*7).toString(), "ʱ�䣺"+w2.get(1+i*7).toString(), "�ص㣺"+w2.get(2+i*7).toString(), "��ʦ��"+w2.get(3+i*7).toString(), m_+"",d_+"",2, 2);
		        setClass(ll2, "","","","", "", "", "", "","",2, 0);
			}
			for(int i=0;i<w3.size()/7;i++)
			{
				int d_=0;
				int m_=0;
				if(s2-s1<0)
				{
					switch(st2)
					{
					case 1:
						d_=2;
						m_=sm1+1;
						break;
					case 2:
						d_=1;
						m_=sm1+1;
						break;
						/*
					case 3:
						break;
					case 4:
						break;
					case 5:
						break;
					case 6:
						break;
						*/
					default:
						d_=st1+2;
						m_=sm1;
						break;
					}
				}
				else
				{
					m_=sm1;
					d_=st1+2;
				}
				setClass(ll3,"","", "","", "", "", "", "","",2, 0);
		        setClass(ll3, w3.get(i*7+5).toString(),w3.get(i*7+6).toString(),w3.get(i*7+4).toString(),"�γ̣�"+w3.get(i*7).toString(), "ʱ�䣺"+w3.get(1+i*7).toString(), "�ص㣺"+w3.get(2+i*7).toString(), "��ʦ��"+w3.get(3+i*7).toString(), m_+"",d_+"",2, 3);
			}
			for(int i=0;i<w4.size()/7;i++)
			{
				int d_=0;
				int m_=0;
				if(s2-s1<0)
				{
					switch(st2)
					{
					case 1:
						d_=3;
						m_=sm1+1;
						break;
					case 2:
						d_=2;
						m_=sm1+1;
						break;
					case 3:
						d_=1;
						m_=sm1+1;
						break;
						/*
					case 4:
						break;
					case 5:
						break;
					case 6:
						break;
						*/
					default:
						d_=st1+3;
						m_=sm1;
						break;
					}
				}
				else
				{
					m_=sm1;
					d_=st1+3;
				}
		        setClass(ll4, w4.get(i*7+5).toString(),w4.get(i*7+6).toString(),w4.get(i*7+4).toString(),"�γ̣�"+w4.get(i*7).toString(), "ʱ�䣺"+w4.get(1+i*7).toString(), "�ص㣺"+w4.get(2+i*7).toString(), "��ʦ��"+w4.get(3+i*7).toString(), m_+"",d_+"",2, 4);
		        setClass(ll4, "","","","", "", "", "", "","",2, 0);
			}
			for(int i=0;i<w5.size()/7;i++)
			{
				int d_=0;
				int m_=0;
				if(s2-s1<0)
				{
					switch(st2)
					{
					case 1:
						d_=4;
						m_=sm1+1;
						break;
					case 2:
						d_=3;
						m_=sm1+1;
						break;
					case 3:
						d_=2;
						m_=sm1+1;
						break;
					case 4:
						d_=1;
						m_=sm1+1;
						break;
						/*
					case 5:
						break;
					case 6:
						break;
						*/
					default:
						d_=st1+4;
						m_=sm1;
						break;
					}
				}
				else
				{
					m_=sm1;
					d_=st1+4;
				}
				setClass(ll5, "","","","", "", "", "", "", "",2, 0);
		        setClass(ll5, w5.get(i*7+5).toString(),w5.get(i*7+6).toString(),w5.get(i*7+4).toString(),"�γ̣�"+w5.get(i*7).toString(), "ʱ�䣺"+w5.get(1+i*7).toString(), "�ص㣺"+w5.get(2+i*7).toString(), "��ʦ��"+w5.get(3+i*7).toString(), m_+"",d_+"",2, 5);
			}
			for(int i=0;i<w6.size()/7;i++)
			{
				int d_=0;
				int m_=0;
				if(s2-s1<0)
				{
					switch(st2)
					{
					case 1:
						d_=5;
						m_=sm1+1;
						break;
					case 2:
						d_=4;
						m_=sm1+1;
						break;
					case 3:
						d_=3;
						m_=sm1+1;
						break;
					case 4:
						d_=2;
						m_=sm1+1;
						break;
						
					case 5:
						d_=1;
						m_=sm1+1;
						break;
						/*
					case 6:
						break;
						*/
					default:
						d_=st1+5;
						m_=sm1;
						break;
					}
				}
				else
				{
					m_=sm1;
					d_=st1+5;
				}
				//Toast.makeText(getApplicationContext(),"yfc_skjfds", Toast.LENGTH_SHORT).show();
				
		        setClass(ll6, w6.get(i*7+5).toString(),w6.get(i*7+6).toString(),w6.get(i*7+4).toString(),"�γ̣�"+w6.get(i*7).toString(), "ʱ�䣺"+w6.get(1+i*7).toString(), "�ص㣺"+w6.get(2+i*7).toString(), "��ʦ��"+w6.get(3+i*7).toString(), m_+"",d_+"",2, 5);
		        setClass(ll6, "","","","", "", "", "", "", "",2, 0);
			}
			for(int i=0;i<w7.size()/7;i++)
			{
				int d_=0;
				int m_=0;
				if(s2-s1<0)
				{
					switch(st2)
					{
					case 1:
						d_=6;
						m_=sm1+1;
						break;
					case 2:
						d_=5;
						m_=sm1+1;
						break;
					case 3:
						d_=4;
						m_=sm1+1;
						break;
					case 4:
						d_=3;
						m_=sm1+1;
						break;
						
					case 5:
						d_=2;
						m_=sm1+1;
						break;
						
					case 6:
						d_=1;
						m_=sm1+1;
						break;
						
					}
				}
				else
				{
					m_=sm1;
					d_=st1+6;
				}
				//Toast.makeText(getApplicationContext(),"yfc_skjfds", Toast.LENGTH_SHORT).show();
				setClass(ll7, "","","","", "", "", "", "", "",2, 0);
		        setClass(ll7, w7.get(i*7+5).toString(),w7.get(i*7+6).toString(),w7.get(i*7+4).toString(),"�γ̣�"+w7.get(i*7).toString(), "ʱ�䣺"+w7.get(1+i*7).toString(), "�ص㣺"+w7.get(2+i*7).toString(), "��ʦ��"+w7.get(3+i*7).toString(), m_+"",d_+"",2, 5);
			}
			//Toast.makeText(getApplicationContext(), w1.toString(), Toast.LENGTH_SHORT).show();
			
		}
		else
			Toast.makeText(getApplicationContext(), "û�пγ̣�", Toast.LENGTH_LONG).show();
	}
	
	  /**
     * ���ÿγ̵ķ���
     * @param ll
     * @param title �γ�����
     * @param place �ص�
     * @param last ʱ��
     * @param time �ܴ�
     * @param classes ����
     * @param color ����ɫ
     */
    void setClass(LinearLayout ll, String start_week,String end_week,String idtitle, String title, String place,
    		String last, String time, String m_,String d_,int classes, int color)
    {
    	View view = LayoutInflater.from(this).inflate(R.layout.activity_week_plane, null);
    	view.setMinimumHeight(dip2px(this,classes * 48));
    	view.setBackgroundColor(colors[color]);
    	((TextView)view.findViewById(R.id.idtitle)).setText(idtitle);
    	((TextView)view.findViewById(R.id.idtitle)).setVisibility(4);
    	((TextView)view.findViewById(R.id.idstart_week)).setText(start_week);
    	((TextView)view.findViewById(R.id.idstart_week)).setVisibility(4);
    	((TextView)view.findViewById(R.id.idend_week)).setText(end_week);
    	((TextView)view.findViewById(R.id.idend_week)).setVisibility(4);
    	((TextView)view.findViewById(R.id.m_)).setText(m_);
    	((TextView)view.findViewById(R.id.m_)).setVisibility(4);
    	((TextView)view.findViewById(R.id.d_)).setText(d_);
    	((TextView)view.findViewById(R.id.d_)).setVisibility(4);
    	//Toast.makeText(getApplicationContext(), m_+" "+d_, Toast.LENGTH_LONG).show();
    	
    	((TextView)view.findViewById(R.id.title)).setText(title);
        ((TextView)view.findViewById(R.id.place)).setText(place);
        ((TextView)view.findViewById(R.id.last)).setText(last);
        ((TextView)view.findViewById(R.id.time)).setText(time);
        //Ϊ�γ�View���õ���ļ�����
        view.setOnClickListener(new OnClickClassListener());
        TextView blank1 = new TextView(this);
        TextView blank2 = new TextView(this);
        blank1.setHeight(dip2px(this,classes));
        blank2.setHeight(dip2px(this,classes));
        ll.addView(blank1);
        ll.addView(view);
        ll.addView(blank2);
    }
    /**
     * �����޿Σ��հ٣�
     * @param ll
     * @param classes �޿εĽ��������ȣ�
     * @param color
     */
    void setNoClass(LinearLayout ll,int classes, int color)
    {
    	TextView blank = new TextView(this);
    	if(color == 0)
    		blank.setMinHeight(dip2px(this,classes * 50));
    	blank.setBackgroundColor(colors[color]);
    	ll.addView(blank);
    }
    //����γ̵ļ�����
    class OnClickClassListener implements OnClickListener{
    	
    	public void onClick(View v) {
    		// TODO Auto-generated method stub
    		
    		String id_delete_id = (String) ((TextView)v.findViewById(R.id.idtitle)).getText();
    		String id_start_week = (String) ((TextView)v.findViewById(R.id.idstart_week)).getText();
    		String id_end_week = (String) ((TextView)v.findViewById(R.id.idend_week)).getText();
    		String id_time_week=(String) ((TextView)v.findViewById(R.id.place)).getText();
    		
    		if(id_delete_id.isEmpty())
    			return;
    		
    		//c_data_model.image_get(ShowWeek.this, user_name, id_delete_id, id_start_week, id_end_week, id_time_week.subSequence(3, id_time_week.length()).toString());
    		//final ArrayList arraylist=c_data_model.image_get(ShowWeek.this,user_name,id_time_week.subSequence(3, id_time_week.length()).toString(), id_delete_id, id_start_week.subSequence(0, 4).toString(), id_start_week.subSequence(5, id_start_week.charAt(6)=='-'?6:7).toString(), id_start_week.subSequence(id_start_week.charAt(6)=='-'?7:8,id_start_week.length()).toString());
    		final ArrayList arraylist=c_data_model.image_get(ShowWeek.this,user_name,id_time_week.subSequence(3, id_time_week.length()).toString(), id_delete_id, id_start_week.subSequence(0, 4).toString(), ((TextView)v.findViewById(R.id.m_)).getText().toString(),((TextView)v.findViewById(R.id.d_)).getText().toString());
    	//	Toast.makeText(getApplicationContext(), arraylis, duration)
    		View view=View.inflate(getApplicationContext(), R.layout.showweek,null);
    		if(arraylist!=null&&arraylist.size()!=0)
    		{
        		((TextView)view.findViewById(R.id.class_name)).setText(arraylist.get(1).toString());
        		((TextView)view.findViewById(R.id.class_status)).setText(arraylist.get(2).toString());
        		((TextView)view.findViewById(R.id.user_time_get)).setText(arraylist.get(3)+" "+arraylist.get(4)+"/"+arraylist.get(5)+" "+arraylist.get(6)+":"+arraylist.get(7));
        		((ImageView)view.findViewById(R.id.user_image)).setImageBitmap((Bitmap) arraylist.get(0));
        		//view.findViewById(R.id.class_status);
        		AlertDialog.Builder builder=new AlertDialog.Builder(ShowWeek.this);
        		AlertDialog dialog=builder.setMessage("�������").setView(view).create();
        		WindowManager.LayoutParams wlp=dialog.getWindow().getAttributes();
        		wlp.gravity=Gravity.TOP|Gravity.LEFT;
        		wlp.x=10;
        		wlp.y=10;
        		dialog.show();
        		
        		dialog.getWindow().setLayout(800, 800);
        		
        		((ImageView)view.findViewById(R.id.user_image)).setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						//Toast.makeText(getApplicationContext(), "ͼƬ����", Toast.LENGTH_LONG).show();
						
						View view=View.inflate(getApplicationContext(), R.layout.in_out_imageview,null);
						((ImageView)view.findViewById(R.id.inoutimageview)).setImageBitmap((Bitmap) arraylist.get(0));
						AlertDialog.Builder builder=new AlertDialog.Builder(ShowWeek.this);
						AlertDialog dialog=builder.setView(view).create();
						
		        		WindowManager.LayoutParams wlp=dialog.getWindow().getAttributes();
		        		wlp.gravity=Gravity.TOP|Gravity.LEFT;
		        		wlp.x=10;
		        		wlp.y=10;
		        		dialog.show();
		        		WindowManager manager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
		        		dialog.getWindow().setLayout(manager.getDefaultDisplay().getWidth(), (int)(manager.getDefaultDisplay().getHeight()/7*5));
					}
				});
    		}
    		
    		/*
    		((TextView)view.findViewById(R.id.class_name)).setText("��ѧ");
    		((TextView)view.findViewById(R.id.class_status)).setText("����");
    		view.findViewById(R.id.class_status);
    		new AlertDialog.Builder(ShowWeek.this).setMessage("�������").setView(view).create().show();
    		*/
    	}
	}
    
    public static int dip2px(Context context, float dpValue) {        
    	final float scale = context.getResources().getDisplayMetrics().density;        
    	return (int) (dpValue * scale + 0.5f);} /** * �����ֻ��ķֱ��ʴ� px(����) �ĵ�λ ת��Ϊ dp */
    public static int px2dip(Context context, float pxValue) {        
    	final float scale = context.getResources().getDisplayMetrics().density;        
    	return (int) (pxValue / scale + 0.5f);}
	
}
