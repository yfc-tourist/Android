package com.example.project;

import com.example.project.model.C_data_model;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class User_login extends Activity implements OnClickListener{
	
	C_data_model c_data_model;
	EditText edit_user,edit_password;
	Button btn_cancel,btn_login;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.userlogin);
		c_data_model=new C_data_model(this, "Student", null, 1);
		edit_user=(EditText) findViewById(R.id.edt_user);
		edit_password=(EditText) findViewById(R.id.edt_password);
		
		btn_cancel=(Button) findViewById(R.id.btn_cancel);
		btn_login=(Button) findViewById(R.id.btn_login);
		btn_cancel.setOnClickListener(this);
		btn_login.setOnClickListener(this);
		
		//c_data_model.test();
		
		
	}
	 
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		
		case R.id.btn_cancel:
			Toast.makeText(getApplicationContext(), "cancel", Toast.LENGTH_SHORT).show();
			this.finish();
			break;
		case R.id.btn_login:
			String data[]={edit_user.getText().toString(),edit_password.getText().toString()};
			if(data[0].equals("")||data[1].equals(""))
			{
				Toast.makeText(getApplicationContext(), "ÁÐ±íÎª¿Õ!", Toast.LENGTH_SHORT).show();
				return;
			}
			if(c_data_model.userlogin(User_login.this,data))
			{
				Toast.makeText(getApplicationContext(), "Login", Toast.LENGTH_SHORT).show();
				Intent intent=new Intent(User_login.this,Control_main.class);
				intent.putExtra("user_login", edit_user.getText().toString());
				startActivity(intent);
				//finish();
			}
			break;
		}
	}

}
