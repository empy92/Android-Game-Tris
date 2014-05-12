package game.tris.activity;

import game.tris.utility.ArcadeTextView;
import game.tris.utility.Background;
import game.tris.utility.OnSwipeTouchListener;
import game.tris.R;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class SettingsActivity extends Activity {

	RadioGroup radioGroupSound, radioGroupDiff;
	final String SOUND = "soundKey"; 
	final String DIFFICULTY = "diffKey";
	static int soundSet;
	Background background;
	private long speedAnimation = 200;
	OnSwipeTouchListener onSwipeTouchListener;
	private final int COLORUNLOCK = Background.VINTAGE;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		
		radioGroupSound = (RadioGroup)findViewById(R.id.radioGroup2);
		radioGroupDiff = (RadioGroup)findViewById(R.id.radioGroup1);
		
		radioGroupSound.setOnCheckedChangeListener(radioSoundGroupOnCheckedChangeListener);
		radioGroupDiff.setOnCheckedChangeListener(radioDiffGroupOnCheckedChangeListener);

		background = new Background(this, (LinearLayout)findViewById(R.id.activity_settings));
		background.paintBackground();
		
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
		        		sendIntent.putExtra(Intent.EXTRA_TEXT, "Pixel Tris - Wild Stone Studio! Join the fun!\n\nCheck now on: https://play.google.com/store/apps/details?id=game.tris");
		        		sendIntent.setType("text/plain");
		        		startActivity(sendIntent);
		        		Background.unLock(COLORUNLOCK);
		        		//TODO add unlock message
		            }
	            });
		        
		        // swiper per il cambio background
		        ArcadeTextView swiper = (ArcadeTextView)findViewById(R.id.swipe);
		        swiper.setText(background.getColortoString());
		        
		        onSwipeTouchListener = new OnSwipeTouchListener(SettingsActivity.this, swiper, background);
		        
	    	    swiper.setOnTouchListener(onSwipeTouchListener);
		        swiper.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						if(((TextView)v).getText().toString().compareTo(Background.UNKNOWN)==0)
							Toast.makeText(SettingsActivity.this, background.getUnlockSegret(), 
									Toast.LENGTH_SHORT).show();
					}
				});
	    	    
	    	    // setto i radio button con il valore che ho inserito nelle preferences
		        LoadSoundPreferences();
		        LoadDiffPreferences();
	}
	
	
				OnCheckedChangeListener radioSoundGroupOnCheckedChangeListener = new OnCheckedChangeListener(){
		
		    	    @Override
		    	    public void onCheckedChanged(RadioGroup group, int checkedId) {
			
			    	     RadioButton checkedRadioButton = (RadioButton)radioGroupSound.findViewById(checkedId);
			    	     int checkedIndex = radioGroupSound.indexOfChild(checkedRadioButton);
			    	     /*
			    	     // quando scelgo ON nel radiogroup mi fa partire la musica se questa non sta gi� andando
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
	    	    
    	    public void SavePreferences(String key, int value){
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
	
	public void setAnimationRight(final TextView v){
			
			final Animation animationSlideInLeft;
			final Animation animationSlideOutRight;
				
		 	animationSlideInLeft = AnimationUtils.loadAnimation(this, 
	        		android.R.anim.slide_in_left);
	        animationSlideOutRight = AnimationUtils.loadAnimation(this, 
	        		android.R.anim.slide_out_right);
	        
	        animationSlideInLeft.setDuration(speedAnimation);
	        animationSlideOutRight.setDuration(speedAnimation);
	            
	        animationSlideInLeft.setAnimationListener(new AnimationListener() {
				
				@Override
				public void onAnimationStart(Animation animation) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onAnimationRepeat(Animation animation) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onAnimationEnd(Animation animation) {
					v.setOnTouchListener(onSwipeTouchListener);
				}
			});
	        animationSlideOutRight.setAnimationListener(new AnimationListener() {
				
				@Override
				public void onAnimationStart(Animation animation) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onAnimationRepeat(Animation animation) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void onAnimationEnd(Animation animation) {
					v.startAnimation(animationSlideInLeft);
					v.setText(background.getColortoString());
			    	background.paintBackground();
			    	v.setOnTouchListener(null);
				}
			});
	        v.startAnimation(animationSlideOutRight);
	        v.setVisibility(View.VISIBLE); 
	}
	
	public void setAnimationLeft(final TextView v){
		
		final Animation animationSlideInRight;
		final Animation animationSlideOutLeft;
        
    	animationSlideInRight = AnimationUtils.loadAnimation(this, 
        		R.anim.slide_in_right);
        animationSlideOutLeft = AnimationUtils.loadAnimation(this, 
        		R.anim.slide_out_left);
        
        animationSlideInRight.setDuration(speedAnimation);
        animationSlideOutLeft.setDuration(speedAnimation);
        
        
        animationSlideInRight.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				v.setOnTouchListener(onSwipeTouchListener);
			}
		});
        animationSlideOutLeft.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				 v.startAnimation(animationSlideInRight);
				 v.setText(background.getColortoString());
				 background.paintBackground();
				 v.setOnTouchListener(null);
			}
		});
        
        v.startAnimation(animationSlideOutLeft);
        v.setVisibility(View.VISIBLE); 
	}
}
