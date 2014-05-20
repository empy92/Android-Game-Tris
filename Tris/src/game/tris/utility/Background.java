package game.tris.utility;

import game.tris.R;
import game.tris.activity.GridActivity;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.widget.LinearLayout;

public class Background implements Runnable{
	
	private static final String MAX = "";
	private static final String MIN = "";
	private static final boolean UNLOCK = true;
	private static final boolean LOCK = false;
	public static final String UNKNOWN = MAX + "? ? ?" + MIN;
	
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
	
	private static boolean[] unlock = {
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
	private static int availableColor = 0;
	private LinearLayout layout;
	private Activity activity;
	
	public Background(int color, Activity activity, LinearLayout layout){
		index = color;
		availableColor = color;
		this.activity = activity;
		this.layout = layout;
	}
	
	public Background(Activity activity, LinearLayout layout){
		this.activity = activity;
		this.layout = layout;
	}
	
	public void changeLeft(){
		index = (++index)%(color.length);
		if(unlock[index] == UNLOCK)
			availableColor = index;
	}
	
	public void changeRight(){
		if(--index<0)
			index = color.length-1;
		else
			index = index%(color.length);
		if(unlock[index] == UNLOCK)
			availableColor = index;
	}
	
	public String getColortoString(){
		String string = UNKNOWN;
		if(unlock[index] == UNLOCK)
			string = MAX+color[index]+MIN;
		return string;
	}
	
	public int getColor(){
		return availableColor;
	}
	
	public void paintBackground(){
		activity.runOnUiThread(this);
	}

	@Override
	public void run() {
		if(activity!=null){
			if(activity.getClass() == GridActivity.class){
				Drawable[] layers = new Drawable[2];
				layers[0] = activity.getResources().getDrawable(background[availableColor]);
				layers[1] = activity.getResources().getDrawable(R.drawable.grid);
				LayerDrawable layerDrawable = new LayerDrawable(layers);
				layout.setBackgroundDrawable(layerDrawable);
			}
			else
				layout.setBackgroundResource(background[availableColor]);
		}else
			System.out.println("NULL");
	}
	
	public static boolean unLock(int color){
		boolean unlockable = false;
		if(unlock[color] == LOCK){
			unlock[color] = UNLOCK;
			unlockable = true;
		}
		return unlockable;
	}
	
	public String getUnlockSegret(){
		String segret = null;
		if(index==VINTAGE)
			segret = "SHARE WITH YOUR FRIENDS";
		if(index==ICE)
			segret = "WIN 5 TIME";
		if(index==GOLD)
			segret = "WIN 10 TIME WITH GOOD DIFFICULT";
		return segret;
	}
}

