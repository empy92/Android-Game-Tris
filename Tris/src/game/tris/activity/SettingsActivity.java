package game.tris.activity;


import game.tris.utility.AudioPlay;

import java.util.List;
import com.example.tris.R;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SettingsActivity extends Activity {

	private RadioGroup radioGroupSound;
	private RadioGroup radioGroupDiff;
	SharedPreferences sharedpreferences;
	private static Integer indexDefaultSound = 0;
	private static Integer indexDefaultDiff = 0;
	public static final String MyPREFERENCES = "MyPrefs" ;
	public static final String Sound = "soundKey"; 
	public static final String Difficulty = "diffKey";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
			
		getRadio();

		Toast.makeText(SettingsActivity.this,"Audio: "+sharedpreferences.getString(Sound, ""), Toast.LENGTH_SHORT).show();
		Toast.makeText(SettingsActivity.this,"Difficulty: "+sharedpreferences.getString(Difficulty, ""), Toast.LENGTH_SHORT).show();	
		
		
		// imageview che mi collega al sito di wild stone studio
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
	
	private void getRadio(){
		
		radioGroupSound = (RadioGroup) findViewById(R.id.radioGroup2);
		radioGroupDiff = (RadioGroup) findViewById(R.id.radioGroup1);
			
		sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
		final Editor editor = sharedpreferences.edit();
		
		
		if(indexDefaultSound == 0){
			if(radioGroupSound.getCheckedRadioButtonId()!=-1){
	            int id1= radioGroupSound.getCheckedRadioButtonId();
	            View radioButton1 = radioGroupSound.findViewById(id1);
	            int radioId1 = radioGroupSound.indexOfChild(radioButton1);
	            RadioButton btn1 = (RadioButton) radioGroupSound.getChildAt(radioId1);
	          
	            if(btn1.getText().equals("On")){   
	                editor.putString(Sound, "0");
	                editor.commit();
	            }
			}
			indexDefaultSound++;
		}
		
		if(indexDefaultDiff == 0){
			if(radioGroupDiff.getCheckedRadioButtonId()!=-1){
	            int id2= radioGroupDiff.getCheckedRadioButtonId();
	            View radioButton2 = radioGroupDiff.findViewById(id2);
	            int radioId2 = radioGroupDiff.indexOfChild(radioButton2);
	            RadioButton btn2 = (RadioButton) radioGroupDiff.getChildAt(radioId2);
	          
	            if(btn2.getText().equals("Easy")){   
	                editor.putString(Difficulty, "0");
	                editor.commit();
	            }
			}
			indexDefaultDiff++;
		}
		
		
		 OnClickListener listener = new OnClickListener() {
	            @Override
	            public void onClick(View v) {
	                   RadioButton rb = (RadioButton) v;
	                   Toast.makeText(SettingsActivity.this, rb.getText(), Toast.LENGTH_SHORT).show();
	                    
	                             
	                   if(rb.getText().equals("On")){   
		                   editor.putString(Sound, "0");
	                   }else if(rb.getText().equals("Off")){           	
		                   editor.putString(Sound, "1");
	                   }else if(rb.getText().equals("Easy")){
	                	   editor.putString(Difficulty, "0");
	                   }else if(rb.getText().equals("Medium")){
	                	   editor.putString(Difficulty, "1");
	                   }else if(rb.getText().equals("Hard")){
	                	   editor.putString(Difficulty, "2");
	                   }
	                   editor.commit(); 
	            }
	      };

	      //this are radio buttons for sound
	      RadioButton rbOn = (RadioButton) findViewById(R.id.soundOn);
	      rbOn.setOnClickListener(listener);
	      RadioButton rbOff = (RadioButton) findViewById(R.id.soundOff);
	      rbOff.setOnClickListener(listener);
	      
	      //this are radio buttons for difficulty
	      RadioButton rbEasy = (RadioButton) findViewById(R.id.diffEasy);
	      rbEasy.setOnClickListener(listener);
	      RadioButton rbMedium = (RadioButton) findViewById(R.id.diffMedium);
	      rbMedium.setOnClickListener(listener);
	      RadioButton rbHard = (RadioButton) findViewById(R.id.diffHard);
	      rbHard.setOnClickListener(listener);
	}
	
	@Override
	 protected void onPause() {
		
		if (this.isFinishing()){			 
			// do nothing
	    }else{	
			AudioPlay.stopAudio();
	    }    
	    super.onPause();
	  }
	
	
}
