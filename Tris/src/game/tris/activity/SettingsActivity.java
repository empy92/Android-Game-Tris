package game.tris.activity;


import game.tris.utility.AudioPlay;

import java.util.List;

import com.example.tris.R;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class SettingsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		
		// bottone che mi collega al sito di wild stone studio
		ImageView linksito = (ImageView)findViewById(R.id.imageView1);
	        linksito.setOnClickListener(new OnClickListener() {
	        	@Override
	        	public void onClick(View arg0){
	        		Intent browserIntent = 
	        		new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.wildstonestudio.altervista.org"));
	                startActivity(browserIntent);
	            }
            });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}
	
	@Override
	 protected void onPause() {
		
		if (this.isFinishing()){
			   
	    	Toast.makeText(SettingsActivity.this, "YOU PRESSED BACK FROM YOUR 'HOME/MAIN' ACTIVITY", Toast.LENGTH_SHORT).show();
	    }else{
		
			AudioPlay.stopAudio();
	        Toast.makeText(SettingsActivity.this, "YOU LEFT YOUR APP", Toast.LENGTH_SHORT).show();
	    }
	    
	    super.onPause();
	  }
	
	
}
