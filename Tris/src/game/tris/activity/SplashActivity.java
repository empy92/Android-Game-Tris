package game.tris.activity;
 
import game.tris.utility.AudioPlay;
import com.example.tris.R;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
 
public class SplashActivity extends Activity {
 
    // Splash screen timer 3 second
    private static int SPLASH_TIME_OUT = 3000;
    static int soundSet;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        
        // verifico che la musica non sia disattivata
        SharedPreferences sharedPreferences = getSharedPreferences("MY_SHARED_PREF", MODE_PRIVATE);
		soundSet = sharedPreferences.getInt("soundKey", 0);
		if(soundSet == 0){
	        //start sound in splash screen
	        AudioPlay.playAudio(SplashActivity.this, R.raw.hammer);
		}
        
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
 
                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
    
    
    protected void onPause(){
    	//stop music in splash screen
    	if(soundSet == 0){
    		AudioPlay.stopAudio();
    	}
    	super.onPause();  
    }
 
}