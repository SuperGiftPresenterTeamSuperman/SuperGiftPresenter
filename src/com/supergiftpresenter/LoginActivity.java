package com.supergiftpresenter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends ActionBarActivity implements OnClickListener {
	private Button loginButton;
	private Button registerButton;
	
	private EditText usernameInput;
	private EditText passwordInput;
	
	private Context context = this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		loginButton = (Button) findViewById(R.id.login_button);
		registerButton = (Button) findViewById(R.id.register_button);
		
		usernameInput = (EditText) findViewById(R.id.name_input);
		passwordInput = (EditText) findViewById(R.id.password_input);
		
		loginButton.setOnClickListener(this);
		registerButton.setOnClickListener(this);
	}

	public void onClick(View v) {
		int buttonID = v.getId();
		String username = usernameInput.getText().toString();
		String password = passwordInput.getText().toString();
		if (buttonID == R.id.login_button) {
			// LOG IN
			// MAKE ALL VALIDATIONS FOR LOGIN
			// TODO CHECK PASSWORD AND USERNAME
			// TODO CHECK FOR INTERNET CONNECTION AND WARN IF NO CONNECTION
			
			if (username == null || username =="") {
				// TODO show notification to input password
				username = "Unknown";
			}
			
			if (password == null || password =="") {
				// TODO show notification to input password
			}
			
			
			Intent intent = new Intent(LoginActivity.this, MainActivity.class);
			intent.putExtra("username", username);
			this.startActivity(intent);
		} else if (buttonID == R.id.register_button) {
			// Make validations for register
			// TODO CHECK PASSWORD AND USERNAME
			// TODO CHECK FOR INTERNET CONNECTION AND WARN IF NO CONNECTION
		}
	}
}
