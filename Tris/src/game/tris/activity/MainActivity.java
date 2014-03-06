package game.tris.activity;

import java.util.List;

import game.tris.utility.AudioPlay;
import com.example.tris.R;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener, OnTouchListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //faccio partire la musica di background all'avvio dell' activity
        AudioPlay.playAudioBackground(MainActivity.this, R.raw.get_lucky);
        
        ImageView startGame1vIA = (ImageView)findViewById(R.id.startGame1vIA);
        startGame1vIA.setOnClickListener(this);
        ImageView startGame1v1 = (ImageView)findViewById(R.id.startGame1v1);
        startGame1v1.setOnClickListener(this);
        ImageView settingsButton = (ImageView)findViewById(R.id.settings);
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
		if(v.getId() == R.id.startGame1vIA){
			Intent intent = new Intent(this,GridActivity.class);
			startActivity(intent);
		}else if(v.getId() == R.id.settings){
			Intent intent = new Intent(this,SettingsActivity.class);
			startActivity(intent);
		}
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
	
	
	@Override
	  protected void onPause() {
		
		//il primo if quando premo il pulsante BACK
	    if (this.isFinishing()){
	    	Toast.makeText(MainActivity.this, "BACK PRESSED and STOP MUSIC", Toast.LENGTH_SHORT).show();
	    	AudioPlay.stopAudioBackground();	    
	    }
	    
	    //!!!!! DA IMPLEMENTARE LO STOP DELLA MUSICA QUANDO PREMO TASTO HOME O BLOCCO LO SCHERMO
	    
    	
    	/*	#####FASE DI CONTROLLO#####  
    	if (this.isFinishing()){ //basically BACK was pressed from this activity
	    	AudioPlay.stopAudio();
	    	Toast.makeText(MainActivity.this, "YOU PRESSED BACK FROM YOUR 'HOME/MAIN' ACTIVITY", Toast.LENGTH_SHORT).show();
	    }
	    Context context = getApplicationContext();
	    ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
	    List<RunningTaskInfo> taskInfo = am.getRunningTasks(1);
	    if (!taskInfo.isEmpty()) {
	      ComponentName topActivity = taskInfo.get(0).topActivity; 
	      if (!topActivity.getPackageName().equals(context.getPackageName())) {
	    	AudioPlay.stopAudio();
	        Toast.makeText(MainActivity.this, "YOU LEFT YOUR APP", Toast.LENGTH_SHORT).show();
	      }
	      else {
	        Toast.makeText(MainActivity.this, "YOU SWITCHED ACTIVITIES WITHIN YOUR APP", Toast.LENGTH_SHORT).show();
	      }
	    }
	    super.onPause();
    	################################ */
    	
	    super.onPause();  
	  }
}
