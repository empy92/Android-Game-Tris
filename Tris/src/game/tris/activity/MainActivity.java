package game.tris.activity;

import game.tris.utility.ArcadeTextView;
import game.tris.utility.AudioPlay;
import game.tris.utility.Background;
import game.tris.R;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;


public class MainActivity extends Activity implements OnClickListener, OnTouchListener{
	
	static int soundSet;
	Background background;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        /*
        // verifico che la musica non sia disattivata
        SharedPreferences sharedPreferences = getSharedPreferences("MY_SHARED_PREF", MODE_PRIVATE);
		soundSet = sharedPreferences.getInt("soundKey", 0);
		if(soundSet == 0){
	        //faccio partire la musica di background all'avvio dell' activity
	        AudioPlay.playAudioBackground(MainActivity.this, R.raw.song);
		}
		*/
        background = new Background(this, (LinearLayout)findViewById(R.id.activity_mn));
        background.paintBackground();
        
        ArcadeTextView startGame1vIA = (ArcadeTextView)findViewById(R.id.startGame1vIA);
        startGame1vIA.setOnClickListener(this);
        ArcadeTextView startGame1v1 = (ArcadeTextView)findViewById(R.id.startGame1v1);
        startGame1v1.setOnClickListener(this);
        ArcadeTextView settingsButton = (ArcadeTextView)findViewById(R.id.settings);
        settingsButton.setOnClickListener(this);
	
        findViewById(R.id.settings).setOnTouchListener(this);
        findViewById(R.id.startGame1v1).setOnTouchListener(this);
        findViewById(R.id.startGame1vIA).setOnTouchListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.settings){
			Intent intent = new Intent(this,SettingsActivity.class);
			startActivity(intent);
		}else{
			Intent intent = new Intent(this,GridActivity.class);
			if(v.getId() == R.id.startGame1vIA)
				intent.putExtra(getPackageName()+".gameType", 1);
			else if(v.getId() == R.id.startGame1v1)
				intent.putExtra(getPackageName()+".gameType", 2);
			startActivity(intent);
		}

	}
	
	@Override
	protected void onRestart() {
		super.onRestart();
		background.paintBackground();
	}


	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		if(arg0.getId() == R.id.settings){
			findViewById(R.id.startGame1vIA).setPressed(false);
			findViewById(R.id.startGame1v1).setPressed(false);
		}
		else if(arg0.getId() == R.id.startGame1vIA){
			findViewById(R.id.settings).setPressed(false);
			findViewById(R.id.startGame1v1).setPressed(false);
		}
		else if(arg0.getId() == R.id.startGame1v1){
			findViewById(R.id.startGame1vIA).setPressed(false);
			findViewById(R.id.settings).setPressed(false);
		}
		return false;
	}
	
	/*
	@Override
	  protected void onPause() {
		
		//quando premo il pulsante BACK
	    if (this.isFinishing()){
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
