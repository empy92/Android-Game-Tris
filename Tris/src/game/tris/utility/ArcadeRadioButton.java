package game.tris.utility;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.RadioButton;
import android.widget.TextView;

public class ArcadeRadioButton extends RadioButton{
	   	
	private static Typeface font;

    public ArcadeRadioButton(Context context) {
        super(context);
        setFont(context);
    }
    public ArcadeRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFont(context);
    }
    public ArcadeRadioButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setFont(context);
    }
    
    private void setFont(Context context){
    	if(font == null)
        	font = Typeface.createFromAsset(context.getAssets(), "arcade.ttf");
        this.setTypeface(font);
    }

}
