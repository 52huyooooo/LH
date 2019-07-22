package com.haoming.hm_xzt.app;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {
	private Button button;
	private EditText passwordEdit;
	private EditText accountEdit;
	private Button login;
	private CheckBox rememberPass;
	private SharedPreferences prefs;
	private SharedPreferences.Editor editor;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_layout);
		Exit.activityList.add(this);
		prefs = PreferenceManager.getDefaultSharedPreferences(this);
		accountEdit = (EditText) findViewById(R.id.account);
		passwordEdit = (EditText) findViewById(R.id.password);
		rememberPass = (CheckBox) findViewById(R.id.remember_pass);
		login = (Button) findViewById(R.id.login);
		button = (Button)this.findViewById(R.id.button1);
		boolean isRemember = prefs.getBoolean("remember_password", false);
		if (isRemember) {
			String account = prefs.getString("account", "");
			String password = prefs.getString("password", "");
			accountEdit.setText(account);
			passwordEdit.setText(password);
			rememberPass.setChecked(true);

		}

		new Thread() {
			public void run() {
				login.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						String account = accountEdit.getText().toString();
						String password = passwordEdit.getText().toString();
						if (account.equals("admin") && password.equals("123456")) {
							editor = prefs.edit();
							if (rememberPass.isChecked()) {
								editor.putBoolean("remember_password", true);
								editor.putString("account", account);
								editor.putString("password", password);
							} else {
								editor.clear();
							}
							editor.apply();
							finish();
						} else {
							Toast.makeText(LoginActivity.this, "账户或密码错错误", Toast.LENGTH_SHORT).show();
						}
					}
				});
			}
		}.start();
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			    finish();
			}
		});
	}
}
