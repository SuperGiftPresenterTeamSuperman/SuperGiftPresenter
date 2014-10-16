package com.supergiftpresenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends ActionBarActivity implements OnClickListener {
	private Button loginButton;
	private Button registerButton;
	
	private EditText usernameInput;
	private EditText passwordInput;
	
	private Context context = this;
	
	private DBUsers dbUsers;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		dbUsers = new DBUsers(context);
		
		loginButton = (Button) findViewById(R.id.login_button);
		registerButton = (Button) findViewById(R.id.register_button);
		
		usernameInput = (EditText) findViewById(R.id.name_input);
		passwordInput = (EditText) findViewById(R.id.password_input);
		
		loginButton.setOnClickListener(this);
		registerButton.setOnClickListener(this);
	}
	
	private boolean isNetworkAvailable() {
	     ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	     NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
	     return activeNetworkInfo != null; 
	}

	public void onClick(View v) {
		
		if (!isNetworkAvailable()) {
			Toast.makeText(context, "No Internet Connection", Toast.LENGTH_SHORT).show();
			return;
		}
		
		int buttonID = v.getId();
		String username = usernameInput.getText().toString();
		String password = passwordInput.getText().toString();
		String storedPassword = dbUsers.getPassword(username);
		
		if (buttonID == R.id.login_button) {
						
			if (username == null || username.equals("")) {
				Toast warning = Toast.makeText(this, "Enter Username", Toast.LENGTH_LONG);
				warning.show();
				return;
			}
			
			if (password == null || password.equals("")) {
				Toast warning = Toast.makeText(this, "Enter Password", Toast.LENGTH_LONG);
				warning.show();
				return;
			}
			
			if(password.equals(storedPassword))
			{
				
				Intent intent = new Intent(LoginActivity.this, MainActivity.class);
				intent.putExtra("username", username);
				this.startActivity(intent);
			}
			else
			{
				Toast warning =Toast.makeText(this, "User Name or Password does not match", Toast.LENGTH_LONG);
				warning.show();
				return;
			}
			
			
		} else if (buttonID == R.id.register_button) {
			Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
			this.startActivity(intent);
		}
	}
}
