package com.example.project;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Calendar;

import com.example.project.model.C_data_model;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Settings extends Activity{
	Intent intent;
	String user_name;
	TextView play_card;
	ImageView imageIV;
	
	C_data_model c_data_model;
	ArrayList arraylist;
	private final int CAMERA_REQUEST = 8888;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		c_data_model=new C_data_model(this, "Student", null, 1);
		intent=getIntent();
		user_name=intent.getStringExtra("user_name");
		arraylist=intent.getIntegerArrayListExtra("array");
		
		
		play_card=(TextView) findViewById(R.id.play_card);
		imageIV=(ImageView) findViewById(R.id.imageiv);
		
		//Toast.makeText(getApplicationContext(), arraylist.size()+" size", Toast.LENGTH_SHORT).show();
		
		play_card.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//  TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "签到", Toast.LENGTH_SHORT).show();
				intent=null;
		        intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
				 
		        startActivityForResult(intent, CAMERA_REQUEST);
		        
			}
		});
	}
	
	private byte[] getPicture(Bitmap bitmap) {
//      if(drawable == null) {
//          return null;
//      }
          if(bitmap == null) {
          return null;
      }
//      BitmapDrawable bd = (BitmapDrawable) drawable;传入int资源时用
//      Bitmap bitmap = bd.getBitmap();
      ByteArrayOutputStream os = new ByteArrayOutputStream();
      bitmap.compress(Bitmap.CompressFormat.PNG, 100, os);
      return os.toByteArray();
  }
	

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		//super.onActivityResult(requestCode, resultCode, data);
		//Toast.makeText(getApplicationContext(), "图片：", Toast.LENGTH_SHORT).show();

		
		boolean bool_=true,bool_1=true,bool_2=true;
		String te_e = null;
		String te_sweek=null;
		String te_eweek=null;
		int h_1=-1,m_1=-1;
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            
            ContentValues initValues = new ContentValues();
          

		/**
		* Bitmap.CompressFormat.JPEG 和 Bitmap.CompressFormat.PNG
		* JPEG 与 PNG 的是区别在于 JPEG是有损数据图像，PNG使用从LZ77派生的无损数据压缩算法。
		* 这里建议使用PNG格式保存
		* 100 表示的是质量为100%。当然，也可以改变成你所需要的百分比质量。
		* os 是定义的字节输出流
		* 
		* .compress() 方法是将Bitmap压缩成指定格式和质量的输出流
		*/
            
            Calendar calendar = Calendar.getInstance();
    		int hour = calendar.get(Calendar.HOUR_OF_DAY);
    	    int minute = calendar.get(Calendar.MINUTE);
    	    int year = calendar.get(calendar.YEAR);
    		int monthOfYear = calendar.get(calendar.MONTH)+1;
    		int dayOfMonth = calendar.get(calendar.DAY_OF_MONTH);
    		
            initValues.put("user", user_name);
            initValues.put("photo", getPicture(photo));
            initValues.put("year", year+"");
            initValues.put("month", monthOfYear+"");
            initValues.put("day", dayOfMonth+"");
            initValues.put("hour", hour+"");
            initValues.put("minute",minute+"");
            
            if(arraylist.size()==0)
            {
            	Toast.makeText(getApplicationContext(), "没有课程！", Toast.LENGTH_SHORT).show();
            	return;
            }
            
    		for(int n=0;n<arraylist.size()/7;n++)
    		 {
    			int h=Integer.parseInt(arraylist.get(6+n*7).toString().subSequence(0, arraylist.get(6+n*7).toString().charAt(1)==':'?1:2).toString());
    			int m=Integer.parseInt(arraylist.get(6+n*7).toString().subSequence(arraylist.get(6+n*7).toString().charAt(1)==':'?2:3, arraylist.get(6+n*7).toString().charAt(1)==':'?(arraylist.get(6+n*7).toString().charAt(3)=='-'?3:4):(arraylist.get(6+n*7).toString().charAt(4)=='-'?4:5)).toString());
    			int eh=0;
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
    			
    			//Toast.makeText(getApplicationContext(), arraylist.get(2+n*7).toString()+" "+arraylist.get(3+n*7).toString()+" "+arraylist.get(6+n*7).toString(),Toast.LENGTH_SHORT).show();
    			
    			
    			int h_=h-hour;
    			int m_=m-minute;
    			//int h_1=-1,m_1=-1;
    			
    			if(h_<=0&&bool_1&&hour<=eh)
    			{
    				bool_=false;
    				
    				if(hour==eh&&eh==h)
    				{
    					if(h_1==-1&&m_1==-1)
    					{
    						bool_2=false;
    						h_1=h_;
    						m_1=m_;
    						te_e=arraylist.get(6+n*7).toString();
    						te_sweek=arraylist.get(2+n*7).toString();
    						te_eweek=arraylist.get(3+n*7).toString();
    					}
    					else
    						if(m_1>m_)
    						{
    							bool_2=false;
    							h_1=h_;
    							m_1=m_;
    							te_e=arraylist.get(6+n*7).toString();
        						te_sweek=arraylist.get(2+n*7).toString();
        						te_eweek=arraylist.get(3+n*7).toString();
    						}
    				}
    				else
    				if(bool_2&&(eh==hour&&minute<=em)){
    					
    					
    					bool_1=false;
    						te_e=arraylist.get(6+n*7).toString();
    						te_sweek=arraylist.get(2+n*7).toString();
    						te_eweek=arraylist.get(3+n*7).toString();
    				}
    				else
						if(bool_2&&hour<eh)
						{
							bool_1=false;
							te_e=arraylist.get(6+n*7).toString();
    						te_sweek=arraylist.get(2+n*7).toString();
    						te_eweek=arraylist.get(3+n*7).toString();
						}
    			}
    			else
    				if(bool_&&h_>0)
    				{
    					if(h_1==-1&&m_1==-1)
    					{
    						h_1=h_;
    						m_1=m_;
    							te_e=arraylist.get(6+n*7).toString();
    							te_sweek=arraylist.get(2+n*7).toString();
        						te_eweek=arraylist.get(3+n*7).toString();
    							
    					}
    					else
    					{
    						if(h_1>h_)
    						{
    							h_1=h_;
    							m_1=m_;
    								te_e=arraylist.get(6+n*7).toString();
    								te_sweek=arraylist.get(2+n*7).toString();
    	    						te_eweek=arraylist.get(3+n*7).toString();
    						}
    						else
    							if(h_1==h_&&m_1>m_)
    							{
    								h_1=h_;
    								m_1=m_;
    								te_e=arraylist.get(6+n*7).toString();
    								te_sweek=arraylist.get(2+n*7).toString();
    	    						te_eweek=arraylist.get(3+n*7).toString();
    							}
    						
    					//Toast.makeText(getApplicationContext(), "1: "+te_e+" ", Toast.LENGTH_LONG).show();
    						}			
    				}
    		 }
    		//Toast.makeText(getApplicationContext(), te_sweek+" "+te_eweek+" "+te_e+" ", Toast.LENGTH_LONG).show();
    		
    		if(te_e==null||te_sweek==null||te_eweek==null)
    		{
    			Toast.makeText(getApplicationContext(), "不可签到!", Toast.LENGTH_SHORT).show();
    			return;
    		}
    		
    		
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
    		
    		int h=Integer.parseInt(te_e.subSequence(0, te_e.charAt(1)==':'?1:2).toString());
			int m=Integer.parseInt(te_e.subSequence(te_e.charAt(1)==':'?2:3, te_e.charAt(1)==':'?(te_e.charAt(3)=='-'?3:4):(te_e.charAt(4)=='-'?4:5)).toString());
			
			int select_number=-1;
			
			if(h-hour>1)
			{
				Toast.makeText(getApplicationContext(), "不可签到", Toast.LENGTH_SHORT).show();
				return;
			}
			
			if(h-hour==1)
			{
				if(m-5<0)
				{
					if(minute<55+m)
					{
						Toast.makeText(getApplicationContext(), "不可签到", Toast.LENGTH_SHORT).show();
						return;
					}
					else
						select_number=1;
				}		
				
			}
			
			if(h==hour)
			{
				if(m-5>=0)
				{
					if(minute<m-5)
					{
						Toast.makeText(getApplicationContext(), "不可签到", Toast.LENGTH_SHORT).show();
						return;
					}  
					else
						if(minute>=m-5&&minute<=m)
							select_number=1;
						else
						{
							if(m+15<=60)
							{
								if(minute>m&&minute<=m+15)
									select_number=0;
							}
							else
								if(m+15>60)
								{
									if(minute>m)
										select_number=0;
								}
						}
				}
				else
					if(m-5<0)
					{
						if(minute>=0&&minute<=m)
							select_number=1;
						else
						{
							if(m+15<=60)
							{
								if(minute>m&&minute<=m+15)
									select_number=0;
							}
							else
								if(m+15>60)
								{
									if(minute>m)
										select_number=0;
								}
						}
					}
				
			}
			
			
	
			if(h-hour==-1)
			{
				if(m+15>60)
				{
					if(minute<=m-45)
						select_number=0;
				}
			}
			
			//Toast.makeText(getApplicationContext(), select_number+"", Toast.LENGTH_LONG).show();
			
			int delete_id=c_data_model.delete_id_image_get(te_sweek,te_eweek,te_e);
			//Toast.makeText(getApplicationContext(), ""+select_number, Toast.LENGTH_LONG).show();
			
			
			switch(select_number)
			{
			case 0:
				Toast.makeText(getApplicationContext(), "迟到 "+delete_id, Toast.LENGTH_LONG).show();
				if(!c_data_model.image_save(initValues,  "迟到",delete_id,year,monthOfYear,dayOfMonth,user_name,hour,h,m,minute))
					Toast.makeText(getApplicationContext(), "不可重复签到!", Toast.LENGTH_LONG).show();
				break;
				
			case 1:
				Toast.makeText(getApplicationContext(), "正常 "+delete_id, Toast.LENGTH_LONG).show();
				if(!c_data_model.image_save(initValues,  "正常",delete_id,year,monthOfYear,dayOfMonth,user_name,hour,h,m,minute))
					Toast.makeText(getApplicationContext(), "不可重复签到!", Toast.LENGTH_LONG).show();
				break;
				
			case -1:
				if(!c_data_model.image_save(initValues,  "旷课",delete_id,year,monthOfYear,dayOfMonth,user_name,hour,h,m,minute))
					Toast.makeText(getApplicationContext(), "你已签到或旷课!", Toast.LENGTH_SHORT).show();
			}
			
			/*
			if(hour==h)
			{	
				if(minute<=m)
				{
					if(!c_data_model.image_save(user_name, initValues,  "正常",delete_id))
					{
						Toast.makeText(getApplicationContext(), "不可重复签到!", Toast.LENGTH_LONG).show();
						return;
					}
				}
				
				if(minute>m)
				{
					if(m+15<=60)
					{
						if(!c_data_model.image_save(user_name, initValues, "迟到",delete_id))
    					{
    						Toast.makeText(getApplicationContext(), "不可重复签到!", Toast.LENGTH_LONG).show();
    						return;
    					}
					}
					
			//		if(minute<=60)
					
				}
			}
    		*/
    		
       //     c_data_model.image_save(user_name, initValues);
           /*
            Bitmap bitmapget=c_data_model.image_get(Settings.this,user_name); 
                
            if(bitmapget==null)
            	Toast.makeText(getApplicationContext(), "图片null", Toast.LENGTH_SHORT).show();
            else
            {
            	Toast.makeText(getApplicationContext(), "seeting image>..", Toast.LENGTH_SHORT).show();
            	imageIV.setImageBitmap(bitmapget);
            }
            */
       
    		 
    		}
	}	
}
