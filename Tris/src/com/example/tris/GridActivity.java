package com.example.tris;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class GridActivity extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_grid);
		init();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.grid, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		((ImageButton)v).setImageResource(R.drawable.x);
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

}
