package com.haoming.hm_xzt.app;

import android.app.Activity;
import android.os.Bundle;

public class MyStoreActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.mystorelayout);
		Exit.activityList.add(this);

	}

}
