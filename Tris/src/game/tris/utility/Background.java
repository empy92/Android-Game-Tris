package game.tris.utility;

import game.tris.R;
import android.app.Activity;
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
	};
	
	private int index;
	private LinearLayout layout;
	private Activity activity;
	
	public Background(int color, Activity activity){
		index = color;
		this.activity = activity;
		layout = (LinearLayout)activity.findViewById()
	}
	
	public String changeLeft(){
		System.out.println(index);
		return color[(++index)%(color.length)];
	}
	
	public String changeRight(){
		return color[(--index)%(color.length)];
	}
	
	public int getColor(){
		return index;
	}
	
	public void paintBackground(){
		activity.runOnUiThread(this);
	}

	@Override
	public void run() {
		if(activity!=null)
			layout.setBackgroundResource(R.drawable.flame);
		else
			System.out.println("NULL");
	}
}

