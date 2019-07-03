package com.example.project.control;

import com.example.project.Add_Delete;
import com.example.project.model.C_data_model;

import android.widget.Toast;

public class C_data_control implements In_data_control{

	Add_Delete add_delete;
	public C_data_model c_data_model;
	
	public void C_data_control_delete(Add_Delete m){
		c_data_model.data_delete_m(m);
		//Toast.makeText(m, "sf", Toast.LENGTH_LONG).show();
	}
	
	public C_data_control(Object m) {
		// TODO Auto-generated constructor stub

		if(m.getClass().getSimpleName().equals("Add_Delete"))
		{	
			add_delete=(Add_Delete)m;
			c_data_model=new C_data_model(add_delete, "Student", null, 1);
			
		}
	}
	
	@Override
	public boolean In_data_c(String[] data,String operation) {
		// TODO Auto-generated method stub
		c_data_model.idtable_get(add_delete);
		return c_data_model.data_set_m(data, operation,add_delete);
		//Toast.makeText(add_delete, "yse", Toast.LENGTH_SHORT).show();
	}
	
	
}
