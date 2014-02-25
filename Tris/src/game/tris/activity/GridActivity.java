package game.tris.activity;

import com.example.tris.R;

import game.tris.utility.Game;
import game.tris.utility.Grid;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

public class GridActivity extends Activity implements OnClickListener{

	private Game game;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grid);
		init();
		game = new Game();
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
		if(game.mark(p.x, p.y)){
			if(game.getPlayer() == 1)
				display(v, game.getPlayer());
			else
				;//((ImageButton)v).setImageResource(R.drawable.o);
			if(game.isfinish()){
				disable();
				
				//####### TEST DIALOG ######
				
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		 
				// set title
				alertDialogBuilder.setTitle("Good job player "+Integer.toString(game.getPlayer())+"!");
		 
				// set dialog message
				alertDialogBuilder
				.setMessage("Select a option below")
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
				
				//Toast toast = Toast.makeText(this, "Good job player "+Integer.toString(game.getPlayer())+" !!!!",Toast.LENGTH_LONG);
				//toast.show();
			}
			else if(game.getTurn()>=9){
				disable();
				
				//####### TEST DIALOG ######
				
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		 
				// set title
				alertDialogBuilder.setTitle("No one win!");
		 
				// set dialog message
				alertDialogBuilder
				.setMessage("Select a option below")
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
				
				//Toast toast = Toast.makeText(this, R.string.noonewins,Toast.LENGTH_LONG);
				//toast.show();
			}else{
				game.changePlayer();
				//for IA--------------------------------
				if(game.getTurn()<=8)
					IA(R.drawable.o);
				if(game.isfinish()){
					disable();
					
					//####### TEST DIALOG ######
					
					AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
			 
					// set title
					alertDialogBuilder.setTitle("Good job player "+Integer.toString(game.getPlayer())+"!");
			 
					// set dialog message
					alertDialogBuilder
					.setMessage("Select a option below")
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
					
					//Toast toast = Toast.makeText(this, "Good job player "+Integer.toString(game.getPlayer())+" !!!!",Toast.LENGTH_LONG);
					//toast.show();
				}
				else
					game.changePlayer();
			}
		}
		else{
			Toast toast = Toast.makeText(this, R.string.alreadysign, Toast.LENGTH_SHORT);
			toast.show();
		}
	}
	
	private void init(){
		ImageButton b00 = (ImageButton) findViewById(R.id.b00);
		b00.setOnClickListener(this);
		ImageButton b01 = (ImageButton) findViewById(R.id.b01);
		b01.setOnClickListener(this);
		ImageButton b02 = (ImageButton) findViewById(R.id.b02);
		b02.setOnClickListener(this);
		ImageButton b10 = (ImageButton) findViewById(R.id.b10);
		b10.setOnClickListener(this);
		ImageButton b11 = (ImageButton) findViewById(R.id.b11);
		b11.setOnClickListener(this);
		ImageButton b12 = (ImageButton) findViewById(R.id.b12);
		b12.setOnClickListener(this);
		ImageButton b20 = (ImageButton) findViewById(R.id.b20);
		b20.setOnClickListener(this);
		ImageButton b21 = (ImageButton) findViewById(R.id.b21);
		b21.setOnClickListener(this);
		ImageButton b22 = (ImageButton) findViewById(R.id.b22);
		b22.setOnClickListener(this);
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
		ImageButton b00 = (ImageButton) findViewById(R.id.b00);
		b00.setClickable(false);
		ImageButton b01 = (ImageButton) findViewById(R.id.b01);
		b01.setClickable(false);
		ImageButton b02 = (ImageButton) findViewById(R.id.b02);
		b02.setClickable(false);
		ImageButton b10 = (ImageButton) findViewById(R.id.b10);
		b10.setClickable(false);
		ImageButton b11 = (ImageButton) findViewById(R.id.b11);
		b11.setClickable(false);
		ImageButton b12 = (ImageButton) findViewById(R.id.b12);
		b12.setClickable(false);
		ImageButton b20 = (ImageButton) findViewById(R.id.b20);
		b20.setClickable(false);
		ImageButton b21 = (ImageButton) findViewById(R.id.b21);
		b21.setClickable(false);
		ImageButton b22 = (ImageButton) findViewById(R.id.b22);
		b22.setClickable(false);
	}
	
	private void IA(int resId){
		Point p = game.markIA();
		if(p.x == 0 && p.y ==0)
			((ImageButton) findViewById(R.id.b00)).setImageResource(resId);
		else if(p.x == 0 && p.y ==1)
			((ImageButton) findViewById(R.id.b01)).setImageResource(resId);
		else if(p.x == 0 && p.y ==2)
			((ImageButton) findViewById(R.id.b02)).setImageResource(resId);
		else if(p.x == 1 && p.y ==0)
			((ImageButton) findViewById(R.id.b10)).setImageResource(resId);
		else if(p.x == 1 && p.y ==1)
			((ImageButton) findViewById(R.id.b11)).setImageResource(resId);
		else if(p.x == 1 && p.y ==2)
			((ImageButton) findViewById(R.id.b12)).setImageResource(resId);
		else if(p.x == 2 && p.y ==0)
			((ImageButton) findViewById(R.id.b20)).setImageResource(resId);
		else if(p.x == 2 && p.y ==1)
			((ImageButton) findViewById(R.id.b21)).setImageResource(resId);
		else if(p.x == 2 && p.y ==2)
			((ImageButton) findViewById(R.id.b22)).setImageResource(resId);
	}
	
	
	private void display(View v, int player){
		if(player == Grid.X)
			((ImageButton) v).setImageResource(R.drawable.x);
		else
			((ImageButton) v).setImageResource(R.drawable.o);
	}
}
