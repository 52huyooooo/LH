package com.haoming.hm_xzt.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ScrollView;

public class MypublishActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.mypublishgoodslayout);
		Exit.activityList.add(this);

		
	}

}
