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
		return MAX+color[index]+MIN;
	}
	
	public int getColor(){
		return index;
	}
	
	public void paintBackground(){
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
}

