package com.supergiftpresenter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity implements OnClickListener{

	EditText editTextUserName, editTextPassword, editTextConfirmPassword;
	Button btnRegister;	
	DBUsers dbUsers;
	
	public void init(){
		
		dbUsers = new DBUsers(this);
		
		editTextUserName=(EditText)findViewById(R.id.usernameBox);
		editTextPassword=(EditText)findViewById(R.id.passwordBox);
		editTextConfirmPassword=(EditText)findViewById(R.id.confirmBox);
		
		btnRegister=(Button)findViewById(R.id.registerBtn);
		btnRegister.setOnClickListener(this);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_register);
		init();
	}

	@Override
	public void onClick(View v) {
		
		String userName=editTextUserName.getText().toString();
		String password=editTextPassword.getText().toString();
		String confirmPassword=editTextConfirmPassword.getText().toString();
		
		if(userName.equals("")||password.equals("")||confirmPassword.equals(""))
		{
				Toast.makeText(getApplicationContext(), "Empty field, please fill it", Toast.LENGTH_LONG).show();
				return;
		}
		
		if (userName.length() < 3) {
			Toast.makeText(getApplicationContext(), "Too short username", Toast.LENGTH_LONG).show();
			return;
		}
		
		if(dbUsers.userExists(userName)){
			Toast.makeText(getApplicationContext(), "Such user exists", Toast.LENGTH_LONG).show();
			return;
		}
		
		if (password.length() < 6) {
			Toast.makeText(getApplicationContext(), "Six symbols password needed", Toast.LENGTH_LONG).show();
			return;
		}
		
		if(!password.equals(confirmPassword))
		{
			Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
			return;
		}
		else
		{
		    // Save the Data in Database
		    dbUsers.addUser(userName, password);
		    Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
			intent.putExtra("username", userName);
			this.startActivity(intent);
		}
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();

		dbUsers.close();
	}
}
