package game.tris.utility;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class ArcadeTextView extends TextView{
	   	
	private static Typeface font;

    public ArcadeTextView(Context context) {
        super(context);
        setFont(context);
    }
    public ArcadeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFont(context);
    }
    public ArcadeTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setFont(context);
    }
    
    private void setFont(Context context){
    	if(font == null)
        	font = Typeface.createFromAsset(context.getAssets(), "arcade.ttf");
        this.setTypeface(font);
    }

}