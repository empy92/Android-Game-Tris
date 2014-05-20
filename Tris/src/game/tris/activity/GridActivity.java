package game.tris.activity;


import game.tris.R;
import game.tris.utility.AudioPlay;
import game.tris.utility.Background;
import game.tris.utility.Game;
import game.tris.utility.Grid;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class GridActivity extends Activity implements OnClickListener, OnTouchListener{

	MediaPlayer mp;
	private Game game;
	int[] view;
	int gameType;
	static int level, soundSet;
	private static int winMedium = 0;
	private static int winEasy = 0;
	private final static int UNLOCKICE = 5;
	private final static int UNLOCKFLAME = 10;
	private final static int UNLOCKGOLD = 15;
	private final static int ROTATIONSPEED = 500;
			
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grid);
		Background background = new Background(this, (LinearLayout)findViewById(R.id.activity_grid));
		background.paintBackground();
		
		view = new int[9];
		init();
		Intent intent = getIntent();
		gameType = intent.getIntExtra(getPackageName()+".gameType",1);
		game = new Game();
		setLevel();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.grid, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		Point p = getPoint(v);
		
		if(game.mark(p.x, p.y)){//if free display sign
			display(v, game.getPlayer());
			buttonSound();
			
			if(game.isfinish()){
				disable();
				
				//####### TEST DIALOG ######
				
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);	 
				
				// set title
				if(gameType == 1){
					if(level == Game.GOOD)
						winMedium++;
					else if(level == Game.EASY)
						winEasy++;
					if(winMedium+winEasy == UNLOCKICE){
						Background.unLock(Background.ICE);
						alertDialogBuilder.setTitle("Good job! You win!\nUnlock new ICE background!");
					}
					else if(winEasy+winMedium == UNLOCKFLAME){
						Background.unLock(Background.FLAME);
						alertDialogBuilder.setTitle("Good job! You win!\nUnlock new FLAME background!");
					}
					else if(winEasy+winMedium == UNLOCKGOLD){
						Background.unLock(Background.GOLD);
						alertDialogBuilder.setTitle("Good job! You win!\nUnlock new GOLD background!");
					}
					else
						alertDialogBuilder.setTitle("Good job! You win!");
				}
				else if (gameType == 2)	
					alertDialogBuilder.setTitle("Good job player "+Integer.toString(game.getPlayer())+"!");
					
				
				// set dialog message
				alertDialogBuilder
				//.setMessage("Select a option below")
				
				
				.setCancelable(false)
				.setPositiveButton("Home",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						GridActivity.this.finish();
					}
				})
				.setNegativeButton("Play Again!",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						Intent intent = getIntent();
						finish();
						startActivity(intent);
					}
				});
		 
				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();
				// show it
				alertDialog.show();
			
				//####### TEST DIALOG ######
			}
			else if(game.getTurn()>=9){
				disable();
				
				//####### TEST DIALOG ######
				
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
				// set title
				alertDialogBuilder.setTitle("No one win!");
		 
				// set dialog message
				alertDialogBuilder
				//.setMessage("Select a option below")
				.setCancelable(false)
				.setPositiveButton("Home",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						GridActivity.this.finish();
					}
				})
				.setNegativeButton("Play Again!",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						Intent intent = getIntent();
						finish();
						startActivity(intent);
					}
				});
		 
				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create(); 
				// show it
				alertDialog.show();
			
				//####### TEST DIALOG ######
			}else{
				game.changePlayer();
				//for IA--------------------------------
				if(gameType == 1){
					if(game.getTurn()<=8)
						playturnIA();
					if(game.isfinish()){
						disable();
						
						//####### TEST DIALOG ######
						
						AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);		 
						// set title
						alertDialogBuilder.setTitle("ARGH! You lose!");
				 
						// set dialog message
						alertDialogBuilder
						//.setMessage("Select a option below")
						.setCancelable(false)
						.setPositiveButton("Home",new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int id) {
								GridActivity.this.finish();
							}
						})
						.setNegativeButton("Play Again!",new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,int id) {
								Intent intent = getIntent();
								finish();
								startActivity(intent);
							}
						});
				 
						// create alert dialog
						AlertDialog alertDialog = alertDialogBuilder.create();
						// show it
						alertDialog.show();
					
						//####### TEST DIALOG ######				
					}
					else
						game.changePlayer();
				}
			}
		}
		/*else{
			Toast toast = Toast.makeText(this, R.string.alreadysign, Toast.LENGTH_SHORT);
			toast.show();
		}*/
	}
	
	private void init(){
		ImageView b00 = (ImageView) findViewById(R.id.b00);
		b00.setOnClickListener(this);
		b00.setOnTouchListener(this);
		ImageView b01 = (ImageView) findViewById(R.id.b01);
		b01.setOnClickListener(this);
		b01.setOnTouchListener(this);
		ImageView b02 = (ImageView) findViewById(R.id.b02);
		b02.setOnClickListener(this);
		b02.setOnTouchListener(this);
		ImageView b10 = (ImageView) findViewById(R.id.b10);
		b10.setOnClickListener(this);
		b10.setOnTouchListener(this);
		ImageView b11 = (ImageView) findViewById(R.id.b11);
		b11.setOnClickListener(this);
		b11.setOnTouchListener(this);
		ImageView b12 = (ImageView) findViewById(R.id.b12);
		b12.setOnClickListener(this);
		b12.setOnTouchListener(this);
		ImageView b20 = (ImageView) findViewById(R.id.b20);
		b20.setOnClickListener(this);
		b20.setOnTouchListener(this);
		ImageView b21 = (ImageView) findViewById(R.id.b21);
		b21.setOnClickListener(this);
		b21.setOnTouchListener(this);
		ImageView b22 = (ImageView) findViewById(R.id.b22);
		b22.setOnClickListener(this);
		b22.setOnTouchListener(this);
		view[0] = R.id.b00;
		view[1] = R.id.b01;
		view[2] = R.id.b02;
		view[3] = R.id.b10;
		view[4] = R.id.b11;
		view[5] = R.id.b12;
		view[6] = R.id.b20;
		view[7] = R.id.b21;
		view[8] = R.id.b22;
	}
	
	private Point getPoint(View v){
		Point p = new Point();
		if(v.getId() == R.id.b00){
			p.x = 0;
			p.y = 0;
		}
		else if(v.getId() == R.id.b01){
				p.x = 0;
				p.y = 1;
		}
		else if(v.getId() == R.id.b02){
			p.x = 0;
			p.y = 2;
		}
		else if(v.getId() == R.id.b10){
			p.x = 1;
			p.y = 0;
		}
		else if(v.getId() == R.id.b11){
			p.x = 1;
			p.y = 1;
		}
		else if(v.getId() == R.id.b12){
			p.x = 1;
			p.y = 2;
		}
		else if(v.getId() == R.id.b20){
			p.x = 2;
			p.y = 0;
		}
		else if(v.getId() == R.id.b21){
			p.x = 2;
			p.y = 1;
		}
		else if(v.getId() == R.id.b22){
			p.x = 2;
			p.y = 2;
		}
		return p;
	}
	private void disable(){
		ImageView b00 = (ImageView) findViewById(R.id.b00);
		b00.setClickable(false);
		ImageView b01 = (ImageView) findViewById(R.id.b01);
		b01.setClickable(false);
		ImageView b02 = (ImageView) findViewById(R.id.b02);
		b02.setClickable(false);
		ImageView b10 = (ImageView) findViewById(R.id.b10);
		b10.setClickable(false);
		ImageView b11 = (ImageView) findViewById(R.id.b11);
		b11.setClickable(false);
		ImageView b12 = (ImageView) findViewById(R.id.b12);
		b12.setClickable(false);
		ImageView b20 = (ImageView) findViewById(R.id.b20);
		b20.setClickable(false);
		ImageView b21 = (ImageView) findViewById(R.id.b21);
		b21.setClickable(false);
		ImageView b22 = (ImageView) findViewById(R.id.b22);
		b22.setClickable(false);
	}
	
	private void playturnIA(){
		Point p = game.markIA(level); 		//add difficulty
		if(p.x == 0 && p.y ==0)
			display(findViewById(R.id.b00), game.getPlayer());
		else if(p.x == 0 && p.y ==1)
			display(findViewById(R.id.b01), game.getPlayer());
		else if(p.x == 0 && p.y ==2)
			display(findViewById(R.id.b02), game.getPlayer());
		else if(p.x == 1 && p.y ==0)
			display(findViewById(R.id.b10), game.getPlayer());
		else if(p.x == 1 && p.y ==1)
			display(findViewById(R.id.b11), game.getPlayer());
		else if(p.x == 1 && p.y ==2)
			display(findViewById(R.id.b12), game.getPlayer());
		else if(p.x == 2 && p.y ==0)
			display(findViewById(R.id.b20), game.getPlayer());
		else if(p.x == 2 && p.y ==1)
			display(findViewById(R.id.b21), game.getPlayer());
		else if(p.x == 2 && p.y ==2)
			display(findViewById(R.id.b22), game.getPlayer());
	}
	
	
	private void display(View v, int player){
		if(player == Grid.X)
			((ImageView) v).setImageResource(R.drawable.x);
		else
			((ImageView) v).setImageResource(R.drawable.o);
		rotate(v);
	}
	/*
	@Override
	 protected void onPause() {
		
		if (this.isFinishing()){
			// si premuto il tasto BACK del dispositivo e non succede nulla    	
	    }else{
	    	//l'activity passata allo stato pause per un altro motivo rispetto alla premuto del tasto BACK
	    	
	    	SharedPreferences sharedPreferences = getSharedPreferences("MY_SHARED_PREF", MODE_PRIVATE);
			soundSet = sharedPreferences.getInt("soundKey", 0);
			if(soundSet == 0){	
				AudioPlay.stopAudioBackground();     
			}
	    }
		super.onPause();
	  }
	  */

	@Override
	public boolean onTouch(View arg0, MotionEvent event) {
		onlyOne(arg0.getId());
		return false;
	}
	
	private void onlyOne(int id){
		for(int i=0; i<9; i++){
			if(view[i] != id)
				findViewById(view[i]).setPressed(false);
		}
	}
	
	private void buttonSound(){
		
        // verifico che la musica non sia disattivata
        SharedPreferences sharedPreferences = getSharedPreferences("MY_SHARED_PREF", MODE_PRIVATE);
		soundSet = sharedPreferences.getInt("soundKey", 0);
		if(soundSet == 0){				
			if(AudioPlay.isPlayingAudio()){
	            AudioPlay.stopAudio();
	            AudioPlay.resetAudio();
	        }
	
	        //sound when button is pressed NO LOOP
	        AudioPlay.playAudioNoLoop(GridActivity.this, R.raw.buttonpress);
		}
	}
	
	private void setLevel(){
		SharedPreferences sharedPreferences = getSharedPreferences("MY_SHARED_PREF", MODE_PRIVATE);
		level = sharedPreferences.getInt("diffKey", Game.EASY);
	}
	
	private void rotate(View v){
		Animation animation = new RotateAnimation(0f, 360f, 
        		Animation.RELATIVE_TO_SELF, 0.5f, 
        		Animation.RELATIVE_TO_SELF, 0.5f);
		animation.setDuration(ROTATIONSPEED);
		v.setAnimation(animation);
	}
}
