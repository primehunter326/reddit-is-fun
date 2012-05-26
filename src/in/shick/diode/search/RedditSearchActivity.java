package in.shick.diode.search;



import in.shick.diode.R;
import in.shick.diode.common.Constants;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

public class RedditSearchActivity extends Activity implements OnClickListener, OnItemSelectedListener,OnKeyListener {

	private Button btn;
	private EditText searchText;
	private Spinner sortBy;
	private CheckBox limitSearch;
	private String mSort = Constants.DEFAULT_SEARCH_SORT; //default to show most relevant results first
	private String mSubreddit;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.search_view);
		
	    sortBy = (Spinner) findViewById(R.id.sortBy);
	    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
	            this, R.array.search_results_sorting, android.R.layout.simple_spinner_item);
	    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	    sortBy.setAdapter(adapter);
		sortBy.setOnItemSelectedListener(this);

		btn = (Button) findViewById(R.id.searchButton);
		btn.setOnClickListener(this);
		
		searchText = (EditText) findViewById(R.id.searchText);
		searchText.setOnKeyListener(this);
		
		//use the subreddit passed via the intent to determine whether to display the checkbox
		
		limitSearch = (CheckBox)findViewById(R.id.limitSearch);
		Intent i = getIntent();
		mSubreddit = i.getStringExtra("subreddit");
		if(mSubreddit.equals(Constants.FRONTPAGE_STRING)){
			limitSearch.setVisibility(View.GONE);
		}
		else{
			limitSearch.setText(Constants.LIMIT_SEARCH_STRING + mSubreddit);
			limitSearch.setVisibility(View.VISIBLE);
		}
		
		
	}
	
	private void activityDone()
	{
		Intent intent = new Intent();
		//intent.putExtra("search", true);
		String query = searchText.getText().toString();
		if(query == null){
			query = Constants.DEFAULT_REDDIT_SEARCH;
		}
		if(limitSearch.isChecked()){
			intent.putExtra("limit", true);
		}
		intent.putExtra("query", query);
		intent.putExtra("sort", mSort);
		setResult(RESULT_OK, intent);
		finish();
	}
	
	//event handlers
	
	//not sure this is needed
	public boolean onKey(View v, int keyCode, KeyEvent event){
		if(keyCode == KeyEvent.KEYCODE_ENTER)
		{
			if(event.getAction() == KeyEvent.ACTION_UP)
				activityDone();
			return true;
		}
		else
			return false;
	}
	
	public void onItemSelected(AdapterView<?> parent, View view, int pos, long id){
	    mSort = parent.getItemAtPosition(pos).toString();  
	}
	
    public void onNothingSelected(AdapterView<?> parent) {
	      // Do nothing.
    }
	
	public void onClick(View v){
		activityDone();
	}
	
}
