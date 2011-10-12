package com.andrewshu.android.reddit.search;

import java.net.URI;

import com.andrewshu.android.reddit.R;
import com.andrewshu.android.reddit.common.Constants;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class RedditSearchActivity extends Activity {

	private Button btn;
	private EditText searchText;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.search);
		btn = (Button) findViewById(R.id.searchButton);
		searchText = (EditText) findViewById(R.id.searchText);
		
		btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				activityDone();
				
			}
			
		});
		
	}
	private void activityDone()
	{
		Intent intent = new Intent();
		StringBuilder sb = new StringBuilder();
		sb.append(Constants.REDDIT_BASE_URL + "/r/");
		sb.append(searchText.getText().toString());
		intent.setData(Uri.parse(sb.toString()));
		setResult(RESULT_OK, intent);
		finish();
	}
	
}
