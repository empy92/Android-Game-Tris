package game.tris.utility;

import android.R;
import android.widget.LinearLayout;

public class Background implements Runnable{
	
	private String[] color = {
			"BLUE",
			"GREEN",
			"RED",
			"VIOLET",
			"BROWN",
			"ICE",
			"FLAME",
			"VINTAGE",
			"GOLD"
	};
	
	private int[] background = {
			R.drawable.blue,
			R.drawable.green,
			R.drawable.red,
			R.drawable.violet,
			R.drawable.brown,
			R.drawable.ice,
			R.drawable.flame,
			R.drawable.vintage,
			R.drawable.gold
	}
	
	private int index;
	private LinearLayout layout;
	private Thread thread;
	
	public Background(LinearLayout layout, int color){
		index = color;
		this.layout = layout;
		thread = new Thread();
	}
	
	public String changeLeft(){
		return color[(++index)%(color.length)];
	}
	
	public String changeRight(){
		return color[(--index)%(color.length)];
	}
	
	public int getColor(){
		return index;
	}
	
	public void paintBackground(){
		thread.start();
	}

	@Override
	public void run() {
		layout.setBackgroundResource(background[index]);
	}
}

