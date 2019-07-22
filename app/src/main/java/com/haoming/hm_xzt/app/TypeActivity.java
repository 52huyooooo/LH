package com.haoming.hm_xzt.app;

import android.app.Activity;
import android.os.Bundle;

public class TypeActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.type_layout);
		Exit.activityList.add(this);

	}

}
