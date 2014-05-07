package game.tris.activity;

import game.tris.utility.OnSwipeTouchListener;
import game.tris.utility.AudioPlay;
import game.tris.R;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class SettingsActivity extends Activity {

	RadioGroup radioGroupSound, radioGroupDiff;
	final String SOUND = "soundKey"; 
	final String DIFFICULTY = "diffKey";
	static int soundSet;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		
		radioGroupSound = (RadioGroup)findViewById(R.id.radioGroup2);
		radioGroupDiff = (RadioGroup)findViewById(R.id.radioGroup1);
		
		radioGroupSound.setOnCheckedChangeListener(radioSoundGroupOnCheckedChangeListener);
		radioGroupDiff.setOnCheckedChangeListener(radioDiffGroupOnCheckedChangeListener);
		
		// imageview che mi collega al sito di wild stone studio
		ImageView linksito = (ImageView)findViewById(R.id.wild);
	        linksito.setOnClickListener(new OnClickListener() {
	        	@Override
	        	public void onClick(View arg0){
	        		Intent browserIntent = 
	        		new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.wildstonestudio.altervista.org"));
	                startActivity(browserIntent);
	            }
            });
	        
	    	// imageview che mi permette di condividere del testo con le app presenti sul dispositivo
			ImageView share = (ImageView)findViewById(R.id.share);
		        share.setOnClickListener(new OnClickListener() {
		        	@Override
		        	public void onClick(View arg0){
		        		Intent sendIntent = new Intent();
		        		sendIntent.setAction(Intent.ACTION_SEND);
		        		sendIntent.putExtra(Intent.EXTRA_TEXT, "Pixel Tris - Wild Stone Studio! Join the fun!");
		        		sendIntent.setType("text/plain");
		        		startActivity(sendIntent);
		            }
	            });
		        
		        ImageView swiper = (ImageView)findViewById(R.id.swipe);
	    	    swiper.setOnTouchListener(new OnSwipeTouchListener(SettingsActivity.this));
		        
		        LoadSoundPreferences();
		        LoadDiffPreferences();
	}
	
	
				OnCheckedChangeListener radioSoundGroupOnCheckedChangeListener = new OnCheckedChangeListener(){
		
		    	    @Override
		    	    public void onCheckedChanged(RadioGroup group, int checkedId) {
			
			    	     RadioButton checkedRadioButton = (RadioButton)radioGroupSound.findViewById(checkedId);
			    	     int checkedIndex = radioGroupSound.indexOfChild(checkedRadioButton);
			    	     /*
			    	     // quando scelgo ON nel radiogroup mi fa partire la musica se questa non sta giˆ andando
			    	     if(!AudioPlay.isPlayingAudioBackground() && checkedIndex == 0){
			    	    	 AudioPlay.playAudioBackground(SettingsActivity.this, R.raw.song);
			    	     }else if(AudioPlay.isPlayingAudioBackground() && checkedIndex == 1){
			    	    	 AudioPlay.stopAudioBackground();
			    	     }
			    	     */
			    	     SavePreferences(SOUND, checkedIndex);
	    	    }};
	    	    
	    	    OnCheckedChangeListener radioDiffGroupOnCheckedChangeListener = new OnCheckedChangeListener(){
	
	        	    @Override
	        	    public void onCheckedChanged(RadioGroup group, int checkedId) {
	    	
	    	    	     RadioButton checkedRadioButton = (RadioButton)radioGroupDiff.findViewById(checkedId);
	    	    	     int checkedIndex = radioGroupDiff.indexOfChild(checkedRadioButton);
	    	    	     SavePreferences(DIFFICULTY, checkedIndex);
	        	    }};
	    	    
    	    private void SavePreferences(String key, int value){
    	    	SharedPreferences sharedPreferences = getSharedPreferences("MY_SHARED_PREF", MODE_PRIVATE);
    	    	SharedPreferences.Editor editor = sharedPreferences.edit();
    	    	editor.putInt(key, value);
    	    	editor.commit(); 
    	    }
    	    
    	    private void LoadSoundPreferences(){
    	    	  SharedPreferences sharedPreferences = getSharedPreferences("MY_SHARED_PREF", MODE_PRIVATE);
    	    	  int savedRadioIndex = sharedPreferences.getInt(SOUND, 0);
    	    	  RadioButton savedCheckedRadioButton = (RadioButton)radioGroupSound.getChildAt(savedRadioIndex);
    	    	  savedCheckedRadioButton.setChecked(true);
    	    }
    	    
    	    private void LoadDiffPreferences(){
  	    	  SharedPreferences sharedPreferences = getSharedPreferences("MY_SHARED_PREF", MODE_PRIVATE);
  	    	  int savedRadioIndex = sharedPreferences.getInt(DIFFICULTY, 0);
  	    	  RadioButton savedCheckedRadioButton = (RadioButton)radioGroupDiff.getChildAt(savedRadioIndex);
  	    	  savedCheckedRadioButton.setChecked(true);
  	    }
    	    
    	    
    	    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.settings, menu);
		return true;
	}
	/*
	@Override
	 protected void onPause() {
		
		if (this.isFinishing()){			 
			// do nothing
	    }else{	
	        // verifico che la musica non sia disattivata
	        SharedPreferences sharedPreferences = getSharedPreferences("MY_SHARED_PREF", MODE_PRIVATE);
			soundSet = sharedPreferences.getInt("soundKey", 0);
			if(soundSet == 0){
				AudioPlay.stopAudioBackground();
			}
	    }    
	    super.onPause();
	  }
	*/
	
}
