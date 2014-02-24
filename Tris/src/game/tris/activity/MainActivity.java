package game.tris.activity;

import com.example.tris.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener, OnTouchListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button startGame1vIA = (Button)findViewById(R.id.startGame1vIA);
        startGame1vIA.setOnClickListener(this);
        Button startGame1v1 = (Button)findViewById(R.id.startGame1v1);
        startGame1v1.setOnClickListener(this);
        Button settingsButton = (Button)findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(this);
        
        findViewById(R.id.settingsButton).setOnTouchListener(this);
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
		}else if(v.getId() == R.id.settingsButton){
			Intent intent = new Intent(this,SettingsActivity.class);
			startActivity(intent);
		}
	}


	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		if(arg0.getId() == R.id.settingsButton){
			findViewById(R.id.startGame1vIA).setPressed(false);
			findViewById(R.id.startGame1v1).setPressed(false);
		}
		else if(arg0.getId() == R.id.startGame1vIA){
			findViewById(R.id.settingsButton).setPressed(false);
			findViewById(R.id.startGame1v1).setPressed(false);
		}
		else if(arg0.getId() == R.id.startGame1v1){
			findViewById(R.id.startGame1vIA).setPressed(false);
			findViewById(R.id.settingsButton).setPressed(false);
		}
		return false;
	}
    
}
