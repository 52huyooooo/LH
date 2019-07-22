package com.haoming.hm_xzt.app;

import android.app.Activity;
import android.os.Bundle;

public class PublishActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.publish_layout);
		Exit.activityList.add(this);

		
	}

}
