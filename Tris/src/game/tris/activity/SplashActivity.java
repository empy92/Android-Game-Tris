package game.tris.activity;
 
import game.tris.utility.AudioPlay;
import com.example.tris.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
 
public class SplashActivity extends Activity {
 
    // Splash screen timer 3 second
    private static int SPLASH_TIME_OUT = 3000;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        
        //start sound in splash screen
        AudioPlay.playAudio(SplashActivity.this, R.raw.hammer);
        
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
    	AudioPlay.stopAudio();
    	super.onPause();  
    }
 
}