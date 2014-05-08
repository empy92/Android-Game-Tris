package game.tris.utility;

import game.tris.R;
import game.tris.activity.GridActivity;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.widget.LinearLayout;

public class Background implements Runnable{
	
	private static final String MAX = "      ";
	private static final String MIN = "      ";
	private static final boolean UNLOCK = true;
	private static final boolean LOCK = false;
	private static final String UNKNOWN = "? ? ?";
	
	public static final int BLUE = 0;
	public static final int GREEN = 1;
	public static final int RED = 2;
	public static final int PURPLE = 3;
	public static final int BROWN = 4;
	public static final int ICE = 5;
	public static final int FLAME = 6;
	public static final int VINTAGE = 7;
	public static final int GOLD = 8;
	
	
	private String[] color = {
			"BLUE",
			"GREEN",
			"RED",
			"PURPLE",
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
	
	private boolean[] unlock = {
			UNLOCK,
			UNLOCK,
			UNLOCK,
			UNLOCK,
			UNLOCK,
			LOCK,
			LOCK,
			LOCK,
			LOCK
	};
	
	private static int index = 0;
	private LinearLayout layout;
	private Activity activity;
	
	public Background(int color, Activity activity, LinearLayout layout){
		index = color;
		this.activity = activity;
		this.layout = layout;
	}
	
	public Background(Activity activity, LinearLayout layout){
		this.activity = activity;
		this.layout = layout;
	}
	
	public void changeLeft(){
		index = (++index)%(color.length);
	}
	
	public void changeRight(){
		if(--index<0)
			index = color.length-1;
		else
			index = index%(color.length);
	}
	
	public String getColortoString(){
		String string = MAX+UNKNOWN+MIN;
		if(unlock[index] == UNLOCK)
			string = MAX+color[index]+MIN;
		return string;
	}
	
	public int getColor(){
		return index;
	}
	
	public void paintBackground(){
		if(unlock[index] == UNLOCK)
			activity.runOnUiThread(this);
	}

	@Override
	public void run() {
		if(activity!=null){
			if(activity.getClass() == GridActivity.class){
				Drawable[] layers = new Drawable[2];
				layers[0] = activity.getResources().getDrawable(background[index]);
				layers[1] = activity.getResources().getDrawable(R.drawable.grid);
				LayerDrawable layerDrawable = new LayerDrawable(layers);
				layout.setBackgroundDrawable(layerDrawable);
			}
			else
				layout.setBackgroundResource(background[index]);
		}else
			System.out.println("NULL");
	}
	
	public void unLock(int color){
		unlock[color] = UNLOCK;
	}
}

